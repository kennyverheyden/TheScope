package thescope.models;

public class Movie {

    private String title;
    private String genre;
    private double rating;
    private int length;


    /**contructor**/

    public Movie() {

    }
    public Movie(String title, String genre, double rating, int length) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.length = length;
    }

    /**get&set**/

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
