package kg.PerfectJob.BookStore.controller;

import kg.PerfectJob.BookStore.dto.CommentDTO;
import kg.PerfectJob.BookStore.dto.BookDTO;
import kg.PerfectJob.BookStore.dto.ResponseMessage;
import kg.PerfectJob.BookStore.entity.Book;
import kg.PerfectJob.BookStore.entity.BookComment;
import kg.PerfectJob.BookStore.exception.ResourceNotFoundException;
import kg.PerfectJob.BookStore.service.BookCommentService;
import kg.PerfectJob.BookStore.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;
    private final BookCommentService bookCommentService;

    public BookController(BookService bookService, BookCommentService bookCommentService) {
        this.bookService = bookService;
        this.bookCommentService = bookCommentService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    @GetMapping("/deleted/{deleted}")
    public List<Book> getAllBooksByDeleted(@PathVariable boolean deleted) {
        return bookService.getAllByDeleted(deleted);
    }

    @PostMapping
    public Book createNewBook(@RequestBody BookDTO bookDTO) {
        return bookService.create(bookDTO);
    }

    @GetMapping("/{id}")
    public Book getBookByID(@PathVariable Long id) {
        return bookService.getBookByID(id);
    }

    @PostMapping("/{bookID}/comment")
    public Book createComment(@PathVariable Long bookID, @RequestBody CommentDTO commentDTO, Principal principal) {
        Book book = bookService.getBookByID(bookID);
        List<BookComment> comments = book.getComments();
        comments.add(bookCommentService.create(commentDTO, principal.getName()));
        book.setComments(comments);
        return bookService.save(book);
    }

    @PutMapping("/{bookID}/comment/{commentID}")
    public Book updateComment(@PathVariable Long bookID, @RequestBody CommentDTO commentDTO, @PathVariable Long commentID, Principal principal) {
        Book book = bookService.getBookByID(bookID);
        List<BookComment> comments = book.getComments();
        BookComment comment = bookCommentService.getByID(commentID);
        if (!comments.contains(comment))
            throw new ResourceNotFoundException("Comment with ID " + commentID + " has not found in list of this book.");
        comments.remove(comment);
        comments.add(bookCommentService.update(comment, commentDTO, principal.getName()));
        book.setComments(comments);
        return book;
    }

    @DeleteMapping("/{bookID}/comment/{commentID}")
    public Book deleteComment(@PathVariable Long bookID, @PathVariable Long commentID) {
        Book book = bookService.getBookByID(bookID);
        List<BookComment> comments = book.getComments();
        BookComment comment = bookCommentService.getByID(commentID);
        if (!comments.contains(comment))
            throw new ResourceNotFoundException("Comment with ID " + commentID + " has not found in list of this book.");
        comments.remove(comment);
        bookCommentService.delete(comment);
        return book;
    }

    @PutMapping("/{id}")
    public Book updateBookInfo(@PathVariable("id") Long bookID, @RequestBody BookDTO bookDTO) {
        return bookService.update(bookID, bookDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage deleteBookByID(@PathVariable("id") Long bookID) {
        return new ResponseMessage(bookService.delete(bookID));
    }

    @PutMapping("/unarchive/{id}")
    public Book unarchiveBookByID(@PathVariable("id") Long bookID) {
        return bookService.unarchive(bookID);
    }
}
