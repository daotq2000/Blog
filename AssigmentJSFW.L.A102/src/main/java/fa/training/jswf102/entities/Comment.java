package fa.training.jswf102.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Table(name = "comment")
@Entity
@Setter
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "status")
    private byte status;
    @Temporal(TemporalType.DATE)
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "author")
    private String author;
    @Column(name = "email")
    private String email;
    @Column(name = "url")
    private String url;
    @Column(name = "comment",columnDefinition = "nvarchar(MAX)")
    private String comment;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "post_id")
    private Post post;
}
