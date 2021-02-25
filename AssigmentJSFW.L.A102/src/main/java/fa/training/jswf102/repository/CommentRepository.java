package fa.training.jswf102.repository;

import fa.training.jswf102.entities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    @Query(value = "select count(c.id) from Comment c where c.status =1 and c.post.id =:PostId")
    int countByPostId(int PostId);
    @Query(value = "select  c from Comment c where c.status =1 and c.post.id =:PostId")
    List<Comment> getByPostId(int PostId);
    @Query(value = "Select TOP 10 c.* from comment c where c.status = 1 order by id ",nativeQuery = true)
    List<Comment> findTop10Comment();
    @Query(value = "Select c from Comment c where c.comment like %:comment%")
    Page<Comment> searchComment(Pageable pageable,String comment);
}
