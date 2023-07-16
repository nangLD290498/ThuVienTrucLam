package truclam.library.truc_lam_library.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 2000)
    private String content;

    @Column
    private String pageHeader;

    @Column
    private Integer pageNo;

    @ManyToOne
    @JoinColumn(name="book_id", nullable = false, referencedColumnName = "id")
    private Book book;

}
