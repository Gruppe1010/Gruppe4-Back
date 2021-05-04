package gruppe4.demo.service;

import gruppe4.demo.models.News;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class HTMLScraper {

    public HTMLScraper() {} // test

    public Set<News> scrape(String url){
        try {

            Set<News> news = new HashSet<>();

            URL oracle = new URL(url);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(oracle.openStream()));

            String inputLine;

            while((inputLine = in.readLine()) != null) {

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
                    newNews.setId(Integer.parseInt(id));

                    // + ranking
                    String ranking = findData(line2, "class=\"rank\">", ".");
                    newNews.setRanking(Integer.parseInt(ranking));

                    // + siteName
                    newNews.setSiteName(findData(line2, "class=\"sitestr\">", "<"));

                    // + link
                    String link = findData(line2, "<a href=\"", "\"");

                    // hvis linket IKKE container en http er det fordi det linker til deres EGEN side,
                    // og så skal vi lige tilføje deres egen side til link-roden
                    // PLUS vi skal ændre siteName-attributten
                    if(!link.contains("http")){
                        link = "https://news.ycombinator.com/" + link;

                        newNews.setSiteName("news.ycombinator.com");
                    }

                    newNews.setLink(link);

                    // + title
                    String title = findData(line2, "class=\"storylink\"", "<");

                    if(title.contains("rel=\"nofollow\">")) {
                        title = title.replaceAll("rel=\"nofollow\">", "");
                    }

                    title = title.substring(1);

                    newNews.setTitle(title);



                    // + points
                    String points = findData(line3, "id=\"score_" + newNews.getId() + "\">", "p");

                    points = points.substring(0, points.length()-1); // sletter det sidste mellemrum som er efter tallet

                    newNews.setPoints(Integer.parseInt(points));

                    // + postedBy
                    newNews.setPostedBy(findData(line3, "class=\"hnuser\">", "<"));

                    // + postTime
                    newNews.setPostTime(findData(line3, "class=\"age\"><a href=\"item?id=" + newNews.getId() + "\">", "<"));

                    // + amountOfComments
                    String amountOfComments = findData(line3, "| <a href=\"item?id=" + newNews.getId() + "\">", "</a>");

                    // HVIS der er lavet kommentarer:
                    if(amountOfComments.contains("&nbsp;comments")){

                        amountOfComments = amountOfComments.replaceAll("&nbsp;comments", "");
                    }
                    else if(amountOfComments.contains("&nbsp;comment")){

                        amountOfComments = amountOfComments.replaceAll("&nbsp;comment", "");
                    }
                    // hvis IKKE der er lavet kommentarer, står der kun 'discuss', og det ændrer vi til 0
                    else{
                        amountOfComments = "0";
                    }

                    newNews.setAmountOfComments(Integer.parseInt(amountOfComments));

                    // tilføj newNews-obj til Set som returneres
                    news.add(newNews);
                }

            }
            in.close();

            if(news.size() == 0){
                return null;
            }
            return news;
        }
        catch(Exception e){
            System.out.println("Error in scraping: " + e);
        }

        return null;
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