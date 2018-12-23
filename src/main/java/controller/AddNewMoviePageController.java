package controller;

import databaseConnector.MoviesDBConnector;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Movie;

import java.io.File;
import java.io.IOException;


public class AddNewMoviePageController {
    private File file;
    @FXML private Button sciFi, action, comedy, drama, adventure, war;
    @FXML private TextField title;
    @FXML private TextArea description, review;
    @FXML private ChoiceBox<String> score, genres;
    @FXML private ImageView search, home, upload;
    @FXML private TextField keyword;
    @FXML private Button loadPictureBtn;

    public void initialize() {
        score.getItems().addAll("1", "2", "3", "4", "5");
        genres.getItems().addAll("action", "adventure", "comedy", "drama", "sci-fi", "war");

        home.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) home.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomePage.fxml"));
                try {
                    stage.setScene(new Scene(loader.load()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.show();
            }
        });

        search.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (keyword.getText().equals("")) return;
                search((Stage) search.getScene().getWindow(), keyword.getText());
            }
        });

        sciFi.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedGenre((Stage) sciFi.getScene().getWindow(), "sci-fi");
            }
        });

        action.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedGenre((Stage) sciFi.getScene().getWindow(), "action");
            }
        });

        comedy.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedGenre((Stage) sciFi.getScene().getWindow(), "comedy");
            }
        });

        drama.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedGenre((Stage) sciFi.getScene().getWindow(), "drama");
            }
        });

        adventure.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedGenre((Stage) sciFi.getScene().getWindow(), "adventure");
            }
        });

        war.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedGenre((Stage) sciFi.getScene().getWindow(), "war");
            }
        });
    }

    public void saveNewMovie(ActionEvent event) {
        String tmpTitle = title.getText();
        String tmpDes = description.getText();
        String tmpReview = review.getText();
        String genre = genres.getValue();
        String r = score.getValue();
        if (!tmpTitle.equals("") && !tmpDes.equals("") && !tmpReview.equals("") && !file.equals("") && !genre.equals("") && !r.equals("")) {
            MoviesDBConnector.add(new Movie(file.getName(), genre, tmpTitle, tmpReview, tmpDes, Double.valueOf(r), Integer.valueOf(r), 1));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Movie Review");
            alert.setHeaderText(null);
            alert.setContentText("Add complete");
            alert.showAndWait();
            title.setText("");
            description.setText("");
            review.setText("");
        }
    }


    public void search(Stage stage, String name) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SearchPage.fxml"));
        try {
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        SearchPageController s = loader.getController();
        s.setMovies(MoviesDBConnector.getMovieByKeyword(name));
        stage.show();
    }

    private void selectedGenre(Stage stage, String genre) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CategoryPage.fxml"));
            stage.setScene(new Scene(loader.load()));
            CategoryPageController c = loader.getController();
            c.setMovies(MoviesDBConnector.getMoviesByGenre(genre));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void loadPicture(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose your picture");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Choose \".png\" and \".jpg\" file", "*.png", "*.jpg"));
        file = fileChooser.showOpenDialog(upload.getScene().getWindow());

        upload.setImage(new Image("/image/" + file.getName()));
    }
}
