package truclam.library.truc_lam_library.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.engine.internal.Cascade;

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

    @Column
    private String thumbnailUrl;

    @Column
    private String pdfUrl;

    @Column(nullable = true)
    private String remarks;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="category_id", referencedColumnName = "id")
    @ToString.Exclude
    private Category category;

    @OneToMany( cascade = {CascadeType.ALL})
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    @ToString.Exclude
    private List<TableContent> tableContents ;

}
