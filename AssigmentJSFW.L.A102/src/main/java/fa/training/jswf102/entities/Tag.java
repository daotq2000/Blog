package fa.training.jswf102.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = "tag")
@Entity
@Setter
@Getter
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "frequency")
    private int frequency;
    @OneToMany(mappedBy = "postTagId.tag",fetch = FetchType.LAZY)
    private List<PostTag> postTags;
     public Tag(String name, int frequency) {
        this.name = name;
        this.frequency = frequency;
    }

    public Tag() {
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", frequency=" + frequency +
                '}';
    }
}
