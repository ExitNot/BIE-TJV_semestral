package cz.cvut.fit.tjv.semestral.data.entities;

import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collection;

@Entity
public class Book {
    @Id
    private final String id;

    @ManyToMany(mappedBy = "booksCreatedBy")  // it is a week side
    private Collection<User> creatorId; // FK

    @Column(name = "book_name")
    private String bookName;
    private String publishDate;
    private String description;
    private String img;
    private Integer issueNumber;  // nomer vipuska, dobav volume

    private Integer satisfactionScore = 0;  // can be rised up and down, by default = 0

    public Book(){
        this.bookName = "";
        this.publishDate = "";
        this.description = "";
        this.issueNumber = 0;
        this.creatorId = null;
        this.id = issueNumber.toString();
    }

    public Book(@NonNull String bookName, String publishDate,@NonNull Integer issueNumber,
                String description, Collection<User> creatorId) {
        this.bookName = bookName;
        this.publishDate = publishDate;
        this.issueNumber = issueNumber;
        this.description = description;
        this.satisfactionScore = 0;
        this.creatorId = creatorId;
        this.id = bookName.replaceAll("\\s","") + issueNumber.toString();
    }

    public String getId() {
        return id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Book))
            return false;
        Book other = (Book) obj;
        boolean bookNameEquals = (this.bookName == null && other.bookName == null)
                || (this.bookName != null && this.bookName.equals(other.bookName));
        boolean issueNumberEquals = (this.issueNumber == null && other.issueNumber == null)
                || (this.issueNumber != null && this.issueNumber.equals(other.issueNumber));
        boolean publishDateEquals = (this.publishDate == null && other.publishDate == null)
                || (this.publishDate != null && this.publishDate.equals(other.publishDate));
        boolean descriptionEquals = (this.description == null && other.description == null)
                || (this.description != null && this.description.equals(other.description));
        return bookNameEquals && issueNumberEquals && publishDateEquals && descriptionEquals;
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
