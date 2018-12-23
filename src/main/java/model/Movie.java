package model;

public class Movie {
    private String picture;
    private String genre;
    private String name;
    private String review;
    private String synopsis;
    private double rating;
    private int score;
    private int rateCount;
    private int id;

    public Movie(int id, String picture, String genre, String name, String review, String synopsis, double rating, int score, int rateCount) {
        this.id = id;
        this.picture = picture;
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
        rateCount++;
        this.score += score;
        this.rating = score / rateCount;
    }
}
