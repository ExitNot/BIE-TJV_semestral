package cz.cvut.fit.tjv.data.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToMany(mappedBy = "writtenBooks")  // it is a week side (maybe later)
    private Collection<Author> authorId; // FK

    @ManyToMany(mappedBy = "artedBooks")  // it is a week side (maby later)
    private Collection<Painter> painterId; // FK

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

    public Collection<Author> getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Collection<Author> authorId) {
        this.authorId = authorId;
    }

    public Collection<Painter> getPainterId() {
        return painterId;
    }

    public void setPainterId(Collection<Painter> painterId) {
        this.painterId = painterId;
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
