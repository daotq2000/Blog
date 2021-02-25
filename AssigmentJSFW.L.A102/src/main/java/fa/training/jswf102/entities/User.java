package fa.training.jswf102.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "users")
@Table
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "username", columnDefinition = "varchar(100)")
    private String username;
    @Column(name = "password", columnDefinition = "varchar(100)")
    private String password;
    @Column(name = "salt", columnDefinition = "varchar(100)")
    private String salt;
    @Column(name = "email", columnDefinition = "varchar(100)")
    private String email;
    @Column(name = "profile", columnDefinition = "nvarchar(2000)")
    private String profile;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "author")
    private List<Post> posts;
    @Column(name = "role")
    private Integer role;

}
