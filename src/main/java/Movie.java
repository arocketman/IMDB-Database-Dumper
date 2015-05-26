/**
 * Created by Andrew on 22/05/2015.
 */
public class Movie {
    private String id;
    private String title;
    private String genre;
    private String actors;
    private String rating;
    private String runtime;
    private String year;
    private String plot;
    private String poster;

    public static class Builder{

        /* Required params */
        private final String id;
        private final String title;

        /* Optional params */
        private String genre;
        private String actors;
        private String rating;
        private String runtime;
        private String year;
        private String plot;
        private String poster;

        public Builder(String id, String title){
            this.id = id;
            this.title = title;
        }

        public Builder genre(String genre){
            this.genre = genre;
            return this;
        }

        public Builder actors(String actors){
            this.actors = actors;
            return this;
        }

        public Builder rating(String rating){
            this.rating = rating;
            return this;
        }

        public Builder runtime(String runtime){
            this.runtime = runtime;
            return this;
        }

        public Builder year(String year){
            this.year = year;
            return this;
        }

        public Builder plot(String plot){
            this.plot = plot;
            return this;
        }

        public Builder poster(String poster){
            this.poster = poster;
            return this;
        }

        public Movie build(){
            return new Movie(this);
        }

    }

    private Movie(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.actors = builder.actors;
        this.rating = builder.rating;
        this.runtime = builder.runtime;
        this.year = builder.year;
        this.plot = builder.plot;
        this.genre = builder.genre;
        this.poster = builder.poster;
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
