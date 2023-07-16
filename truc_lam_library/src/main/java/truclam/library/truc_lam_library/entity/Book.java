package truclam.library.truc_lam_library.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String author;

    @Column
    private String publisher;

    @Column
    private String publishedYear;

    @Column(nullable = true)
    private String remarks;

    @ManyToOne
    @JoinColumn(name="category_id", nullable = true, referencedColumnName = "id")
    @ToString.Exclude
    private Category category;

    @OneToMany( cascade = {CascadeType.ALL})
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    @ToString.Exclude
    private List<TableContent> tableContents ;
}
