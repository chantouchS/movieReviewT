package controller;

import databaseConnector.MoviesDBConnector;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Movie;

import java.io.IOException;

public class SearchPageController {
    private ObservableList<Movie> movies;
    @FXML private TableView<Movie> movieTableView;
    @FXML private TableColumn picture, name, type, rating;
    @FXML private Button sciFi, action, comedy, drama, adventure, war;
    @FXML private ImageView search, home;
    @FXML private TextField keyword;

    public void initialize() {
        picture.setCellValueFactory(new PropertyValueFactory<Movie, ImageView>("picture"));
        name.setCellValueFactory(new PropertyValueFactory<Movie, String>("name"));
        type.setCellValueFactory(new PropertyValueFactory<Movie, String>("type"));
        rating.setCellValueFactory(new PropertyValueFactory<Movie, Double>("rating"));

        movieTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Movie selected = movieTableView.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    goToSelectedMoviePage((Stage) movieTableView.getScene().getWindow(), selected);
                }
            }
        });

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
        MovieController m = loader.getController();

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

    public void setMovies(String keyword) {
        movies = MoviesDBConnector.getMovieByKeyword(keyword);
        movieTableView.setItems(movies);
    }
}
