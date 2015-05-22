import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;


public class Scraper {

    public static final String BASE_URL = "http://www.imdb.com/search/title?num_votes=30000,&release_date=1990-01-01,&user_rating=7.0,10";
    private static final int ENTRIES_NUMBER = 1338 - 50; //TODO: Make this dynamic.

    HashMap<String,String> map = new HashMap<String, String>();

    public void getFromURL(String url) throws IOException {
        addToMapFromIMDB(url);
        for(int i = 51; i < ENTRIES_NUMBER; i+=50 )
            addToMapFromIMDB(url + "&start=" + i);
        //saveToFile(map);
        Parser.fillUpDB(map);
    }

    private String getMovieID(String url) {
        url = url.substring("/title/".length(),"/title/".length() + "tt0000000".length());
        return url;
    }

    public void saveToFile(){
        try {
            FileWriter fwriter = new FileWriter("IDdb.txt");
            BufferedWriter writer = new BufferedWriter(fwriter);
            for(String id : map.keySet()) {
                writer.write(id);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addToMapFromIMDB(String url){
        Document doc = null;
        try {
            doc = Jsoup.connect(url).data("query", "Java")
                    .userAgent("Mozilla")
                    .cookie("auth", "token")
                    .timeout(3000)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements titles = doc.select(".title a[href^=/title]");

        for(Element movie : titles){
            String movieurl = movie.attr("href");
            String title = movie.text();
            if(!map.containsKey(getMovieID(movieurl)))
                map.put(getMovieID(movieurl),title);
        }
    }

}
