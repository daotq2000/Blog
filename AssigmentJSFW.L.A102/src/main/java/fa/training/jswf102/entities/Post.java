 package fa.training.jswf102.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "post")
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;
    @Column(name = "image",columnDefinition = "nvarchar(MAX)")
    private String image;
    @Column(name = "content",columnDefinition = "text")
    private String content;
    @Column(name = "tag")
    private String tag;
    @Temporal(TemporalType.DATE)
    @Column(name = "create_time")
    private Date createTime;
    @Temporal(TemporalType.DATE)
    @Column(name = "modified_time")
    private Date modifiedTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User author;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "post")
    private List<Comment> comments;
    @Column(name = "status",columnDefinition = "tinyint default 1")
    private byte status;
    @OneToMany(mappedBy = "postTagId.post",cascade = CascadeType.ALL)
    private List<PostTag> postTags;
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<Comment> commentsList;
}
