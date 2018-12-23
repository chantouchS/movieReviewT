package controller;

import databaseConnector.MoviesDBConnector;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Movie;

import java.io.IOException;

public class MovieController {
    private Movie movie;

    @FXML private ImageView picture, home, search;
    @FXML private Label title, description, review, rating;
    @FXML private ChoiceBox<String> score;
    @FXML private TextField keyword;
    @FXML private Button sciFi, action, comedy, drama, adventure, war, rate;

    public void initialize() {
        score.getItems().addAll("1", "2", "3", "4", "5");
        rate.setDisable(false);

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

    public void search(Stage stage, String name) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CategoryPage.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/SearchPage.fxml"));
            stage.setScene(new Scene(loader.load()));
            CategoryPageController c = loader.getController();
            c.setMovies(MoviesDBConnector.getMoviesByGenre(genre));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
        setUpPage();
    }

    public void rateMovie(ActionEvent event) {
        if (score.getValue() != null) {
            movie.rate(Integer.parseInt(score.getValue()));
            MoviesDBConnector.update(movie);
            setUpPage();
            rate.setDisable(true);
        }
    }

    private void setUpPage() {
        picture.setImage(new Image("/image/" + movie.getPicture()));
        title.setText(movie.getName());
        description.setText(movie.getSynopsis());
        review.setText(movie.getReview());
        rating.setText(String.valueOf(movie.getRating()));
    }
}
