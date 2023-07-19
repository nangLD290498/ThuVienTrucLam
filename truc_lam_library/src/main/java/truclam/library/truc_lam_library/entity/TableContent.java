package truclam.library.truc_lam_library.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
public class TableContent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String headerContent;

    @Column
    private Integer fromPage;

    @Column
    private Integer toPage;

    @ManyToOne
    @JoinColumn(name="parent")
    private TableContent parent;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="book_id", referencedColumnName = "id")
    @ToString.Exclude
    private Book book;

}
