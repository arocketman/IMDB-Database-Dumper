import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;


public class Parser {

    public static void fillUpDB(HashMap<String,String> map){
        for(String movieID : map.keySet()){
            insertEntry(movieID);
        }
    }

    private static void insertEntry(String ID){
        JSONObject jsonObject = null;
        try {
            jsonObject = readJsonFromUrl("http://www.omdbapi.com/?i="+ID+"&plot=short&r=json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Movie movie = new Movie(ID,jsonObject.getString("Title"),jsonObject.getString("Actors"),jsonObject.getString("imdbRating"),jsonObject.getString("Runtime"),jsonObject.getString("Year"),jsonObject.getString("Plot"),jsonObject.getString("Genre"),jsonObject.getString("Poster"));
        DatabaseWorker.insertIntoDB(movie);
        System.out.println("Inserted : " + movie.getTitle());
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
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

}
