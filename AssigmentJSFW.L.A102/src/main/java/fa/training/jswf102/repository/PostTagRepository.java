package fa.training.jswf102.repository;

import fa.training.jswf102.entities.PostTag;
import fa.training.jswf102.entities.PostTagId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTagRepository extends JpaRepository<PostTag, PostTagId> {

}
