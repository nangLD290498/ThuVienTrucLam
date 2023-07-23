package truclam.library.truc_lam_library.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String remarks;

    @OneToMany( cascade = {CascadeType.ALL})
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ToString.Exclude
    private List<Book> books;
}
