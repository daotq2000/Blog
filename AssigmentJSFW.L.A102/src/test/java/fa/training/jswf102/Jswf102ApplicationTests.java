package fa.training.jswf102;

import fa.training.jswf102.entities.Tag;
import fa.training.jswf102.repository.CommentRepository;
import fa.training.jswf102.repository.TagRepository;
import fa.training.jswf102.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Jswf102ApplicationTests {
    @Autowired
    TagRepository tagRepository;
    @Test
    void contextLoads() {
        Tag tags = tagRepository.getByName("helo");
        System.out.println(tags.toString());
    }
    @Autowired
    UserRepository userRepository;
    @Test
    void count (){
        System.out.println(userRepository.getByEmail("daotqph08312"));
    }

}
