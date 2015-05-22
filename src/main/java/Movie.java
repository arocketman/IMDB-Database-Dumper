/**
 * Created by Andrew on 22/05/2015.
 */
public class Movie {
    private String id;
    private String genre;
    private String title;
    private String actors;
    private String rating;
    private String runtime;
    private String year;
    private String plot;
    private String poster;

    public Movie() {
    }

    public Movie(String id,String title, String actors, String rating, String runtime, String year, String plot,String genre,String poster) {
        this.id = id;
        this.title = title;
        this.actors = actors;
        this.rating = rating;
        this.runtime = runtime;
        this.year = year;
        this.plot = plot;
        this.genre = genre;
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
