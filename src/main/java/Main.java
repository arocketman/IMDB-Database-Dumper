import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Scraper scraper = new Scraper();
        scraper.getFromURL(Scraper.BASE_URL);
    }
}
