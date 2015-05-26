import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;


public class Parser {

    private DatabaseWorker dbWorker = new DatabaseWorker("databaseName");

    /**
     * Fills up the database with the values inside the map, which will be parsed and inserted into the db.
     * @param map map of values <Id,Title>.
     */
    public void fillUpDB(HashMap<String,String> map){
        for(String movieID : map.keySet()){
            insertEntry(movieID);
        }
    }

    /**
     * Inserts the movie with the given ID into the database.
     * @param ID ID in the form of ttXXXXXXX
     */
    private void insertEntry(String ID){
        Movie movie = getMovieFromJson("http://www.omdbapi.com/?i="+ID+"&plot=short&r=json",ID);
        if(movie != null) {
            dbWorker.insertIntoDB(movie);
            System.out.println("Inserted : " + movie.getTitle());
        }
    }

    /**
     * Transforms a omdb json result to a Movie.
     * @param json
     * @param ID
     * @return the built movie
     */
    private Movie getMovieFromJson(String json,String ID){
        JSONObject jsonObject = null;
        try {
            jsonObject = readJsonFromUrl(json);
            Movie movie = new Movie.Builder(ID,jsonObject.getString("Title"))
                    .actors(jsonObject.getString("Actors"))
                    .rating(jsonObject.getString("imdbRating"))
                    .runtime(jsonObject.getString("Runtime"))
                    .year(jsonObject.getString("Year"))
                    .plot(jsonObject.getString("Plot"))
                    .genre(jsonObject.getString("Genre"))
                    .poster(jsonObject.getString("Poster"))
                    .build();
            return movie;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Creates a JSONObject from a URL containing json text.
     * @param url
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

}
