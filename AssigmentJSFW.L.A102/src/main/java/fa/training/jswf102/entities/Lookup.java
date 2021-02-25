package fa.training.jswf102.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "lookup")
@Entity
@Getter
@Setter
public class Lookup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private String code;
    @Column(name = "type")
    private String type;
}
