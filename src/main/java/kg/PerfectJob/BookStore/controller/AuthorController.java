package kg.PerfectJob.BookStore.controller;

import kg.PerfectJob.BookStore.dto.AuthorDTO;
import kg.PerfectJob.BookStore.entity.Author;
import kg.PerfectJob.BookStore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.getAll();
    }

    @GetMapping("/deleted/{deleted}")
    public List<Author> getAllAuthorsByDeleted(@PathVariable boolean deleted) {
        return authorService.getAllByDeleted(deleted);
    }

    @PostMapping
    public Author createNewAuthor(@RequestBody AuthorDTO authorDTO) {
        return authorService.create(authorDTO);
    }

    @GetMapping("/{id}")
    public Author getAuthorByID(@PathVariable Long id) {
        return authorService.getAuthorByID(id);
    }
}