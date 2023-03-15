package ru.alishev.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;
@Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Book> index() {
        List<Book> books = jdbcTemplate.query("SELECT book_id, title, author, year_of_publication FROM Book",
                new BeanPropertyRowMapper<>(Book.class));
        return books;
    }

    public Book show(int id) {
        Book book = jdbcTemplate.query("SELECT book_id, title, author, year_of_publication FROM Book WHERE " +
                        "book_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
        return book;
    }
    public List<Book> booksById(int id) {
        return jdbcTemplate.query("SELECT book_id, title, author, year_of_publication FROM Book WHERE person_id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }
    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(title, author, year_of_publication) VALUES(?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYearOfPublication());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, year_of_publication=? WHERE book_id=?", updatedBook.getTitle(),
                updatedBook.getAuthor(), updatedBook.getYearOfPublication(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
    }

    public void assign(int bookId, Person personWithId) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE book_id=?", personWithId.getPersonId(), bookId);
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE Book SET person_id=null WHERE book_id=?", id);
    }
}
