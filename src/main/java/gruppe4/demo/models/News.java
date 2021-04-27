package gruppe4.demo.models;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private int rank;


    private String link;


    private String title;


    private String siteName;


    private int points;

    private String postedBy;

    private String postTime;


    private int amountOfComments;


    public News(){}
    public News(int id, int rank, String link, String title, String siteName, int points, String postedBy, String postTime, int amountOfComments) {
        this.id = id;
        this.rank = rank;
        this.link = link;
        this.title = title;
        this.siteName = siteName;
        this.points = points;
        this.postedBy = postedBy;
        this.postTime = postTime;
        this.amountOfComments = amountOfComments;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getRank() {
        return rank;
    }
    public void setRank(int rank) {
        this.rank = rank;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getSiteName() {
        return siteName;
    }
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    public String getPostedBy() {
        return postedBy;
    }
    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }
    public String getPostTime() {
        return postTime;
    }
    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }
    public int getAmountOfComments() {
        return amountOfComments;
    }
    public void setAmountOfComments(int amountOfComments) {
        this.amountOfComments = amountOfComments;
    }
}
