package cz.cvut.fit.tjv.semestral.rest_api.model;

import org.springframework.hateoas.RepresentationModel;
import java.util.Collection;

public class BookDto extends RepresentationModel<BookDto> {
    private String id;
    private String bookName;
    private Integer satisfactionScore;
    private String publishDate;
    private Integer issueNumber;
    private String description;
    private Collection<Long> creatorId;

    public BookDto() {
    }

    public BookDto( String id, String bookName, Integer satisfactionScore, String publishDate,
                    Integer issueNumber, String description, Collection<Long> creatorId) {
        this.id = id;
        this.bookName = bookName;
        if ( satisfactionScore == null )
            this.satisfactionScore = 0;
        else
            this.satisfactionScore = satisfactionScore;
        this.publishDate = publishDate;
        this.issueNumber = issueNumber;
        this.description = description;
        this.creatorId = creatorId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id){ this.id = id; }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getSatisfactionScore() {
        return satisfactionScore;
    }

    public void setSatisfactionScore(Integer satisfactionScore) {
        this.satisfactionScore = satisfactionScore;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(Integer issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Long> getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Collection<Long> creatorId) {
        this.creatorId = creatorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookDto bookDto = (BookDto) o;

        if (!bookName.equals(bookDto.bookName)) return false;
        if (!satisfactionScore.equals(bookDto.satisfactionScore)) return false;
        if (issueNumber != null ? !issueNumber.equals(bookDto.issueNumber) : bookDto.issueNumber != null) return false;
        if (description != null ? !description.equals(bookDto.description) : bookDto.description != null) return false;
        return publishDate != null ? !publishDate.equals(bookDto.publishDate) : bookDto.publishDate != null;
    }


    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + bookName.hashCode();
        result = 31 * result + satisfactionScore.hashCode();
        result = 31 * result + (publishDate != null ? publishDate.hashCode() : 0);
        result = 31 * result + (issueNumber != null ? issueNumber.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (creatorId != null ? creatorId.hashCode() : 0);
        return result;
    }
}
