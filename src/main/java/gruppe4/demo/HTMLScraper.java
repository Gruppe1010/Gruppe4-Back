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

                    // ! dette var the fix
                    // vi tjekker om det er en add - når det IKKE er en add indeholder line3 en class="hnuser"
                    // derfor skipper vi dette while-loop vi hvis den ikke container hnuser
                    if(!line3.contains("hnuser")){
                        continue;
                    }


                    News newNews = new News();

                    // + id
                    // vi sorterer alt andet end tal fra første linje
                    // første linje: <tr class='athing' id='26949862'>
                    String id = line1.replaceAll("[^0-9]", "");

                    System.out.println("ID: " + id);

                    newNews.setId(Integer.parseInt(id));

                    // + ranking
                    String ranking = findData(line2, "class=\"rank\">", ".");

                    System.out.println("RANKING: " + ranking);

                    newNews.setRanking(Integer.parseInt(ranking));

                    // + link
                    newNews.setLink(findData(line2, "<a href=\"", "\""));

                    // + title
                    newNews.setTitle(findData(line2, "class=\"storylink\">", "<"));

                    System.out.println("TITLE: " + newNews.getTitle());

                    // + siteName
                    newNews.setSiteName(findData(line2, "class=\"sitestr\">", "<"));

                    // + points
                    String points = findData(line3, "id=\"score_" + newNews.getId() + "\">", "p");

                    points = points.substring(0, points.length()-1); // sletter det sidste mellemrum som er efter tallet

                    System.out.println("POINTS: " + points);
                    newNews.setPoints(Integer.parseInt(points));

                    // + postedBy
                    newNews.setPostedBy(findData(line3, "class=\"hnuser\">", "<"));

                    // + postTime
                    newNews.setPostTime(findData(line3, "class=\"age\"><a href=\"item?id=" + newNews.getId() + "\">", "<"));

                    // + amountOfComments
                    String amountOfComments = findData(line3, "| <a href=\"item?id=" + newNews.getId() + "\">", "&");

                    System.out.println("AMOUNTOFCOMMENTS: " + amountOfComments);

                    newNews.setAmountOfComments(Integer.parseInt(amountOfComments));




                    System.out.println("NEWNEWS: " + newNews);





                }

                //System.out.println(inputLine);

            }
            in.close();
        }
        catch(Exception e){
            System.out.println("Error in scraping: " + e);
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