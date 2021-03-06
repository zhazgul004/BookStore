package kg.PerfectJob.BookStore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "authors")
public class Author extends Base {
    
    @Column(name = "name")
    private String name;

    @Column(name = "average_rating")
    private double averageRating;

    @Column(name = "type")
    private String type;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "biography")
    private String biography;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne
    private Media image;
}
