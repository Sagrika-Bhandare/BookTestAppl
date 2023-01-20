package com.example.BookTestAppl.entities;

public class Book {
    private long isbnNo;
    private String bookName;
    private int authorId;
    private String authorName;
    private int yearOfPublication;

    public Book(long isbnNo, String bookName, int authorId, String authorName, int yearOfPublication) {
        this.isbnNo = isbnNo;
        this.bookName = bookName;
        this.authorId = authorId;
        this.authorName = authorName;
        this.yearOfPublication = yearOfPublication;
    }

    public Book() {
    }



    public long getIsbnNo() {
        return isbnNo;
    }

    public void setIsbnNo(long isbnNo) {
        this.isbnNo = isbnNo;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbnNo=" + isbnNo +
                ", bookName='" + bookName + '\'' +
                ", authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                '}';
    }
}
