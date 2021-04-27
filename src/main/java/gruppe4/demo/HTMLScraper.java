package gruppe4.demo;

import gruppe4.demo.models.News;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class HTMLScraper {

    public HTMLScraper() {
        scrape();
    }

    public void scrape(){
        try {

            URL oracle = new URL("https://news.ycombinator.com/");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(oracle.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {

                // vores News-obj info kommer når de bruger klassen athing i html

                if(inputLine.contains("athing")){

                    // der er 3 linjer html som beskriver hvert news-obj
                    // disse 3 linjer gemmer vi i htmlNewsContent-arrayet
                    String[] htmlNewsContent = new String[2];

                    for(int i = 0; i < 2; i++){
                        htmlNewsContent[i] = in.readLine();
                    }

                    // de 3 linjer html hvor alt vores News-obj data står
                    String line1 = inputLine;
                    String line2 = htmlNewsContent[0];
                    String line3 = htmlNewsContent[1];

                    News newNews = new News();

                  /*
                    id : int
                    ranking: int
                    link : String
                    title: String
                    siteName : String
                    points : int
                    postedBy : String
                    postTime : String
                    amountOfComments : int
                  * */

                    // + id
                    // vi sorterer alt andet end tal fra første linje
                    // første linje: <tr class='athing' id='26949862'>
                    newNews.setId(Integer.parseInt(line1.replaceAll("[^0-9]", "")));

                    // + ranking
                    newNews.setRanking(Integer.parseInt(findData(line2, "class=\"rank\">", ".")));

                    // + link
                    newNews.setLink(findData(line2, "<a href=\"", "\""));

                    // + title
                    newNews.setTitle(findData(line2, "class=\"storylink\">", "<"));

                    // + siteName
                    newNews.setSiteName(findData(line2, "class=\"sitestr\">", "<"));

                    // + points
                    String points = findData(line3, "id=\"score_" + newNews.getId() + "\">", "p");

                    points = points.substring(0, points.length()-1); // sletter det sidste mellemrum

                    newNews.setPoints(Integer.parseInt(points));

                    // + postedBy
                    newNews.setPostedBy(findData(line3, "class=\"hnuser\">", "<"));

                    // + postTime
                    newNews.setPostTime(findData(line3, "<a href=\"item?id=" + newNews.getId() + "\">", "<"));

                    // + amountOfComment

                    /*

                    String indicator = "<a href=\"item?id=" + newNews.getId() + "\">";


                    int startIndexOfIndicator = line3.lastIndexOf(indicator);

                    System.out.println("startIndex: " + startIndexOfIndicator);
                    System.out.println("fra start af indicator: " + line3.substring(startIndexOfIndicator));


                    // vi laver ny String uden alt det som står før og inkl. indicator-string
                    String amountOfComments = line3.substring(startIndexOfIndicator + indicator.length());

                    // vi cutter resten af line2 væk så vi kun har et tal tilbage
                    newNews.setAmountOfComments(Integer.parseInt(amountOfComments.substring(0, amountOfComments.indexOf(amountOfComments))));

                     */







                    String testString = findData(line3, "| <a href=\"item?id=" + newNews.getId() + "\">", "&");

                    System.out.println("TESTSTRING: " + testString);

                    newNews.setAmountOfComments(Integer.parseInt(testString));




                    // System.out.println("NEWNEWS: " + newNews);


                    for (int i = 0; i < htmlNewsContent.length; i++) {

                        System.out.println(htmlNewsContent[i]);
                    }
                }

                //System.out.println(inputLine);

            }
            in.close();
        }
        catch(Exception e){
            System.out.println("Error in scraping: " + e.getMessage());
        }
    }

    public String findData(String line, String indicator, String endChar){
        // indicator = denne String står foran den data vi vil finde

        // vi finder det index-tal på line2-stringen hvor indeicator-stringen begynder
        int startIndexOfIndicator = line.indexOf(indicator);

        // vi laver ny String uden alt det som står før og inkl. indicator-string
        String data = line.substring(startIndexOfIndicator + indicator.length());

        data = data.substring(0, data.indexOf(endChar));

        // vi cutter resten af line2 væk så vi kun har et tal tilbage
        return data;
    }







}