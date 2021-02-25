package fa.training.jswf102.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PostTagId implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private Post post;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    public PostTagId() {
    }

    public PostTagId(Post post, Tag tag) {
        this.post = post;
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostTagId)) return false;
        PostTagId postTagId = (PostTagId) o;
        return Objects.equals(post, postTagId.post) &&
                Objects.equals(tag, postTagId.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(post, tag);
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
