package fa.training.jswf102.dto;

import fa.training.jswf102.entities.Post;
import fa.training.jswf102.entities.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
public class CommentDTO {
    private Integer id;
    private byte status;
    private Date createTime;
    private String author;
    private String email;
    private String url;
    private String comment;
    private PostDTO post;
}
