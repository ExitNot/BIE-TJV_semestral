package cz.cvut.fit.tjv.semestral.data.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "booksCreatedBy")  // it is a week side
    private Collection<User> creatorId; // FK

    @Column(name = "book_name")
    private String bookName;
    private String publishDate;
    private String description;
    private Integer issueNumber;  // nomer vipuska, dobav volume

    private Integer satisfactionScore;  // 1-5 rate, where 5 - is the best grade.

    public Long getId() {
        return id;
    }

    public Collection<User> getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Collection<User> creatorId) {
        this.creatorId = creatorId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(Integer chaptersAmount) {
        this.issueNumber = chaptersAmount;
    }

    public Integer getSatisfactionScore() {
        return satisfactionScore;
    }

    public void setSatisfactionScore(Integer satisfactionScore) {
        this.satisfactionScore = satisfactionScore;
    }

    public void upSatisfactionScore(){ this.satisfactionScore++; }
    public void downSatisfactionScore(){ this.satisfactionScore--; }
}
