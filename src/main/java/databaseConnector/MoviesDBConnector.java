package databaseConnector;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Movie;

import java.sql.*;

public class MoviesDBConnector {
    private static String myDriver = "org.sqlite.JDBC";
    private static String urlDB = "jdbc:sqlite:Database.db";

    public static ObservableList<Movie> getMovies() {
        ObservableList<Movie> movies = FXCollections.observableArrayList();
        try {
            Class.forName(myDriver);
            Connection connection = DriverManager.getConnection(urlDB);
            if (connection != null) {
                String query = "select * from Movies";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String picture = resultSet.getString("Picture");
                    String genre = resultSet.getString("Genre");
                    String name = resultSet.getString("Name");
                    String review = resultSet.getString("Review");
                    String synopsis = resultSet.getString("Synopsis");
                    double rating = resultSet.getInt("Rating");
                    int score = resultSet.getInt("Score");
                    int rateCount = resultSet.getInt("RateCount");
                    int id = resultSet.getInt("ID");
                    movies.add(new Movie(id, picture, genre, name, review, synopsis, rating, score, rateCount));
                }
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public static ObservableList<Movie> getMoviesByGenre(String gen) {
        ObservableList<Movie> movies = FXCollections.observableArrayList();
        try {
            Class.forName(myDriver);
            Connection connection = DriverManager.getConnection(urlDB);
            if (connection != null) {
                String query = "select * from Movies where Genre = '" + gen + "'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String picture = resultSet.getString("Picture");
                    String genre = resultSet.getString("Genre");
                    String name = resultSet.getString("Name");
                    String review = resultSet.getString("Review");
                    String synopsis = resultSet.getString("Synopsis");
                    double rating = resultSet.getInt("Rating");
                    int score = resultSet.getInt("Score");
                    int rateCount = resultSet.getInt("RateCount");
                    int id = resultSet.getInt("ID");
                    movies.add(new Movie(id, picture, genre, name, review, synopsis, rating, score, rateCount));
                }
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public static ObservableList<Movie> getTopRating() {
        ObservableList<Movie> movies = FXCollections.observableArrayList();
        try {
            Class.forName(myDriver);
            Connection connection = DriverManager.getConnection(urlDB);
            if (connection != null) {
                String query = "select * from Movies order by Rating DESC";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String picture = resultSet.getString("Picture");
                    String genre = resultSet.getString("Genre");
                    String name = resultSet.getString("Name");
                    String review = resultSet.getString("Review");
                    String synopsis = resultSet.getString("Synopsis");
                    double rating = resultSet.getInt("Rating");
                    int score = resultSet.getInt("Score");
                    int rateCount = resultSet.getInt("RateCount");
                    int id = resultSet.getInt("ID");
                    movies.add(new Movie(id, picture, genre, name, review, synopsis, rating, score, rateCount));
                }
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public static ObservableList<Movie> getNewMovies() {
        ObservableList<Movie> movies = FXCollections.observableArrayList();
        try {
            Class.forName(myDriver);
            Connection connection = DriverManager.getConnection(urlDB);
            if (connection != null) {
                String query = "select * from Movies order by ID DESC";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String picture = resultSet.getString("Picture");
                    String genre = resultSet.getString("Genre");
                    String name = resultSet.getString("Name");
                    String review = resultSet.getString("Review");
                    String synopsis = resultSet.getString("Synopsis");
                    double rating = resultSet.getInt("Rating");
                    int score = resultSet.getInt("Score");
                    int rateCount = resultSet.getInt("RateCount");
                    int id = resultSet.getInt("ID");
                    movies.add(new Movie(id, picture, genre, name, review, synopsis, rating, score, rateCount));
                }
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public static ObservableList<Movie> getMovieByKeyword(String keyword) {
        ObservableList<Movie> movies = FXCollections.observableArrayList();
        try {
            Class.forName(myDriver);
            Connection connection = DriverManager.getConnection(urlDB);
            if (connection != null) {
                String query = "Select * from Movies";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    if (resultSet.getString("Name").contains(keyword)) {
                        String picture = resultSet.getString("Picture");
                        String genre = resultSet.getString("Genre");
                        String name = resultSet.getString("Name");
                        String review = resultSet.getString("Review");
                        String synopsis = resultSet.getString("Synopsis");
                        double rating = resultSet.getInt("Rating");
                        int score = resultSet.getInt("Score");
                        int rateCount = resultSet.getInt("RateCount");
                        int id = resultSet.getInt("ID");
                        movies.add(new Movie(id, picture, genre, name, review, synopsis, rating, score, rateCount));
                    }
                }
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public static void update(Movie movie) {
        try {
            Class.forName(myDriver);
            Connection connection = DriverManager.getConnection(urlDB);
            if (connection != null) {
                String query = "Update Movies set Picture = '" + movie.getPicture() + "' , Name = '" + movie.getName() + "' , Genre = '" + movie.getGenre() + "' , Review = '" +
                        movie.getReview() + "' , Synopsis = '" + movie.getSynopsis() + "' , Rating = '" + movie.getRating() + "' , Score = '" + movie.getScore() + "' , RateCount = '" +
                        movie.getRateCount() + "' Where ID = '" + movie.getId() + "'";
                PreparedStatement p = connection.prepareStatement(query);
                p.executeUpdate();
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void add(Movie movie) {
        try {
            Class.forName(myDriver);
            Connection connection = DriverManager.getConnection(urlDB);
            if (connection != null) {
                String query = "insert into Movies (Picture, Genre, Name, Review, Synopsis, Rating, Score, RateCount) values ('" + movie.getPicture() + "' , '" + movie.getGenre() + "' , '" + movie.getName() +
                        "' , '" + movie.getReview() + "' , '" + movie.getSynopsis() + "' , '" + movie.getRating() + "' , '" + movie.getScore() + "' , '" + movie.getRateCount() + "')";
                PreparedStatement p = connection.prepareStatement(query);
                p.executeUpdate();
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
