package kg.PerfectJob.BookStore.repository;

import kg.PerfectJob.BookStore.entity.Author;
import kg.PerfectJob.BookStore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findAuthorByUser(User user);
    List<Author> findAllByNameContainingIgnoringCase(String name);
    List<Author> findAllByTypeIgnoringCase(String type);
}
