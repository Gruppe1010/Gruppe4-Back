package gruppe4.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class HTMLScraper {

    public HTMLScraper() {
        scrape();
    }

    void scrape(){
        try {

            URL oracle = new URL("https://news.ycombinator.com/");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(oracle.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
            in.close();
        }
        catch(Exception e){
            System.out.println("error in scraping: " + e.getMessage());
        }
    }
}