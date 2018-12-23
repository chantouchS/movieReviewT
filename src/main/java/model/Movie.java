package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Movie {
    private ImageView pic;
    private String picture;
    private String genre;
    private String name;
    private String review;
    private String synopsis;
    private double rating;
    private int score;
    private int rateCount;
    private int id;

    public Movie() {
    }

    public Movie(int id, String picture, String genre, String name, String review, String synopsis, double rating, int score, int rateCount) {
        this.id = id;
        this.picture = picture;
        this.pic = new ImageView(new Image("/image/"+picture));
        this.genre = genre;
        this.name = name;
        this.review = review;
        this.synopsis = synopsis;
        this.rating = rating;
        this.score = score;
        this.rateCount = rateCount;
    }

    public Movie(String picture, String genre, String name, String review, String synopsis, double rating, int score, int rateCount) {
        this.picture = picture;
        this.pic = new ImageView(new Image("/image/"+picture));
        this.genre = genre;
        this.name = name;
        this.review = review;
        this.synopsis = synopsis;
        this.rating = rating;
        this.score = score;
        this.rateCount = rateCount;
    }

    public int getId() {
        return id;
    }

    public ImageView getPic() {
        return pic;
    }

    public String getPicture() {
        return picture;
    }

    public String getGenre() {
        return genre;
    }

    public String getName() {
        return name;
    }


    public String getReview() {
        return review;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public double getRating() {
        return rating;
    }

    public int getScore() {
        return score;
    }

    public int getRateCount() {
        return rateCount;
    }

    public void rate(int score) {
        if(score<0){
            throw new IllegalArgumentException("Score can't be negative.");
        }
        else {
            rateCount++;
            this.score += score;
            this.rating = this.score / rateCount;
        }

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRateCount(int rateCount) {
        this.rateCount = rateCount;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
