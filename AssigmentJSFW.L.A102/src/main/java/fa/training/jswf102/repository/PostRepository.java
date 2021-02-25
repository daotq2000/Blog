package fa.training.jswf102.repository;

import fa.training.jswf102.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
    @Query(value = "select p from Post p where p.status =:status and (p.title like %:search% or p.tag like %:search%)   ")
    Page<Post> searchPost(Pageable pageable,String search,byte status);
    @Query(value = "select p from Post p where p.status > 0 and p.id =:Id")
    Post getPostById(int Id);
}
