package fa.training.jswf102.dto;

import fa.training.jswf102.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PostDTO {
    private Integer id;
    private String title;
    private String image;
    private String content;
    private String tag;
    private Date createTime;
    private Date modifiedTime;
    private UserDTO author;
    private byte status;
    private int commentCount;
    private List<PostTagDTO> postTag;
}
