package fa.training.jswf102.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
@Getter
@Setter
@ToString
@Table(name = "post_tag")
@Entity
@EqualsAndHashCode
public class PostTag {
    @EmbeddedId
    private PostTagId postTagId;

    public PostTag(PostTagId postTagId) {
        this.postTagId = postTagId;
    }

    public PostTag() {
    }

}
