package fa.training.jswf102.repository;

import fa.training.jswf102.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository  extends JpaRepository<Tag,Integer> {
    Tag getByName(String Name);
}
