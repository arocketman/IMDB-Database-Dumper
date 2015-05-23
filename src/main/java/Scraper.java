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
    Parser parser = new Parser();

    /**
     * Given a url it begins the scraping project. This will iterate throughout the whole page results.
     * @param url
     * @throws IOException
     */
    public void getFromURL(String url) throws IOException {
        addToMapFromIMDB(url);
        for(int i = 51; i < ENTRIES_NUMBER; i+=50 )
            addToMapFromIMDB(url + "&start=" + i);
        //saveToFile(map);
        parser.fillUpDB(map);
    }

    /**
     * given the "href" parameter it extrapolates the id in the form of : ttXXXXXXX
     * @param url the href parameter.
     * @return the ID of the given movie.
     */
    private String getMovieID(String url) {
        url = url.substring("/title/".length(),"/title/".length() + "tt0000000".length());
        return url;
    }

    /**
     * Saves to file representation the given HashMap in the following fashion : "ttXXXXXXX - Movie title"
     * @param fileName file where to save the map.
     */
    public void saveToFile(String fileName){
        try {
            if(!fileName.endsWith(".txt"))
                fileName = fileName.concat(".txt");
            FileWriter fwriter = new FileWriter(fileName);
            BufferedWriter writer = new BufferedWriter(fwriter);
            for(String id : map.keySet()) {
                writer.write(id);
                writer.write(" - ");
                writer.write(map.get(id));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Scrapes the imdb website and retrieves the titles given a search result page.
     * @param url The url of the search page.
     */
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
