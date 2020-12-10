package cz.cvut.fit.tjv.semestral.data.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany(mappedBy = "booksCreatedBy")  // it is a week side
    private Collection<User> creatorId; // FK

    @Column(name = "book_name")
    private String bookName;
    @Column(name = "written_by")
    private String writtenBy;  // Author pseudonym
    @Column(name = "art_by")
    private String artBy;  // Art pseudonym
    private String genre;
    private String publishDate;
    private String description;
    private Integer chaptersAmount;
    private Integer booksAmount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getWrittenBy() {
        return writtenBy;
    }

    public void setWrittenBy(String writtenBy) {
        this.writtenBy = writtenBy;
    }

    public String getArtBy() {
        return artBy;
    }

    public void setArtBy(String artBy) {
        this.artBy = artBy;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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

    public Integer getChaptersAmount() {
        return chaptersAmount;
    }

    public void setChaptersAmount(Integer chaptersAmount) {
        this.chaptersAmount = chaptersAmount;
    }

    public Integer getBooksAmount() {
        return booksAmount;
    }

    public void setBooksAmount(Integer booksAmount) {
        this.booksAmount = booksAmount;
    }
}
