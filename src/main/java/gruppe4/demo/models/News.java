package gruppe4.demo.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class News {

    @Id
    private int id;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private int ranking;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String siteName;

    @Column(nullable = false)
    private int points;

    @Column(nullable = false)
    private String postedBy;

    @Column(nullable = false)
    private String postTime;

    @Column(nullable = false)
    private int amountOfComments;




    public News(){}

   public News(int id, int ranking, String link, String title, String siteName, int points, String postedBy, String postTime, int amountOfComments) {
       this.id = id;
       this.ranking = ranking;
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
    public int getRanking() {
        return ranking;
    }
    public void setRanking(int ranking) {
        this.ranking = ranking;
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

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", link='" + link + '\'' +
                ", ranking=" + ranking +
                ", title='" + title + '\'' +
                ", siteName='" + siteName + '\'' +
                ", points=" + points +
                ", postedBy='" + postedBy + '\'' +
                ", postTime='" + postTime + '\'' +
                ", amountOfComments=" + amountOfComments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;
        return id == news.id &&
                ranking == news.ranking &&
                points == news.points &&
                amountOfComments == news.amountOfComments &&
                Objects.equals(link, news.link) &&
                Objects.equals(title, news.title) &&
                Objects.equals(siteName, news.siteName) &&
                Objects.equals(postedBy, news.postedBy) &&
                Objects.equals(postTime, news.postTime);
    }

    /* ! Find ud af om denne SKAL ind for at equals virker
    @Override
    public int hashCode() {
        return Objects.hash(id, link, ranking, title, siteName, points, postedBy, postTime, amountOfComments);
    }

     */
}
