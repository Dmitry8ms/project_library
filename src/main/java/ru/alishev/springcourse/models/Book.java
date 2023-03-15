package ru.alishev.springcourse.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int bookId;

    @NotEmpty(message = "Title should not be empty")
    @Size(min = 1, max = 200, message = "Name should be between 2 and 100 characters")
    private String title;
    @NotEmpty(message = "Author name should not be empty")
    @Size(min = 1, max = 100, message = "Name should be between 2 and 100 characters")
    private String author;
    @Min(value = 0, message = "Age should be greater than 0")
    private int yearOfPublication;

    public Book() {}
    public Book(int bookId, String title, String author, int yearOfPublication) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }
}
