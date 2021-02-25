package fa.training.jswf102.dto;

import fa.training.jswf102.entities.PostTag;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class PostTagDTO {
    private PostDTO post;
    private TagDTO tag;
}
