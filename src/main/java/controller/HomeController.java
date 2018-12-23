package controller;

import databaseConnector.MoviesDBConnector;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.Movie;

import java.io.IOException;
import java.util.Optional;

public class HomeController {
    private ObservableList<ImageView> topRate, newMovie;
    private ObservableList<Movie> topRateMovies, newMovies;
    @FXML private ImageView search;
    @FXML private ImageView topRate1, topRate2, topRate3;
    @FXML private ImageView newMovie1, newMovie2, newMovie3;
    @FXML private TextField keyword;
    @FXML private Button sciFi, action, comedy, drama, adventure, war, add, topRateMore, newMore;

    public void initialize() {
        topRate = FXCollections.observableArrayList();
        newMovie = FXCollections.observableArrayList();
        topRateMovies = MoviesDBConnector.getTopRating();
        newMovies = MoviesDBConnector.getNewMovies();
        topRate.clear();
        topRate.addAll(topRate1, topRate2, topRate3);
        if (topRateMovies.size() > 0)
            setUpImage(topRate, topRateMovies);

        search.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (keyword.getText().equals("")) return;
                search((Stage) search.getScene().getWindow(), keyword.getText());
            }
        });

        keyword.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER){
                    Stage stage = (Stage) keyword.getScene().getWindow();
                    search(stage, keyword.getText());
                }
            }
        });

//        set up ImageView can clickable
        topRate1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                goToSelectedMoviePage((Stage) topRate1.getScene().getWindow(), topRateMovies.get(0));
            }
        });
        topRate2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                goToSelectedMoviePage((Stage) topRate1.getScene().getWindow(), topRateMovies.get(1));
            }
        });
        topRate3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                goToSelectedMoviePage((Stage) topRate1.getScene().getWindow(), topRateMovies.get(2));
            }
        });

        newMovie1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                goToSelectedMoviePage((Stage) newMovie1.getScene().getWindow(), newMovies.get(0));
            }
        });

        newMovie2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                goToSelectedMoviePage((Stage) newMovie2.getScene().getWindow(), newMovies.get(1));
            }
        });

        newMovie3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                goToSelectedMoviePage((Stage) newMovie3.getScene().getWindow(), newMovies.get(2));
            }
        });

        newMovie.clear();
        newMovie.addAll(newMovie1, newMovie2, newMovie3);
        if (newMovies.size() > 0)
            setUpImage(newMovie, newMovies);

//        set up Category menu
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

    public void addMovie(ActionEvent event) {
// Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Login");
        dialog.setHeaderText("This function for admin only");

// Set the button types.
        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

// Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);

// Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

// Do some validation (using the Java 8 lambda syntax).
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
        Platform.runLater(() -> username.requestFocus());

// Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
            if (usernamePassword.getKey().equals("admin") && usernamePassword.getValue().equals("admin")) {
                Stage stage = (Stage) add.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddNewMoviePage.fxml"));
                try {
                    stage.setScene(new Scene(loader.load()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.show();
            }
        });
    }

    public void topRateMore(ActionEvent event) {
        Stage stage = (Stage) topRateMore.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SearchPage.fxml"));
        try {
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        CategoryPageController c = loader.getController();
        c.setMovies(MoviesDBConnector.getTopRating());
        stage.show();
    }

    public void newMore(ActionEvent event) {
        Stage stage = (Stage) newMore.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SearchPage.fxml"));
        try {
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        CategoryPageController c = loader.getController();
        c.setMovies(MoviesDBConnector.getNewMovies());
        stage.show();
    }

    public void search(Stage stage, String keyword) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CategoryPage.fxml"));
        try {
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        SearchPageController s = loader.getController();
        s.setMovies(keyword);
        stage.show();
    }

    private void goToSelectedMoviePage(Stage stage, Movie selectedMovie) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MoviePage.fxml"));
            stage.setScene(new Scene(loader.load()));
            MovieController movieController = loader.getController();
            movieController.setMovie(selectedMovie);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private void setUpImage(ObservableList<ImageView> tmp, ObservableList<Movie> tmpDB) {
        int i = 0;
        for (ImageView imageView : tmp) {
            imageView.setImage(new Image("/image/"+tmpDB.get(i++).getPicture()));
        }
    }


}
