package fa.training.jswf102.controller;

import fa.training.jswf102.dto.PostDTO;
import fa.training.jswf102.dto.UserDTO;
import fa.training.jswf102.entities.Post;
import fa.training.jswf102.entities.User;
import fa.training.jswf102.exception.NotFoundException;
import fa.training.jswf102.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "*")
public class PostController {
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private PostService postService;
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public ResponseEntity<Map<String,Object>> searchPost(@RequestParam(value = "pageNum",defaultValue = "1")  int  pageNum,
                                                         @RequestParam(name = "pageSize",defaultValue = "10") int pageSize,
                                                         @RequestParam(name = "search",defaultValue = "") String search,
                                                         @RequestParam(name = "status",defaultValue = "1") byte status){
        Map<String,Object> mapPost = postService.search(pageNum, pageSize, search,status);
        return new ResponseEntity<>(mapPost, HttpStatus.OK);
    }
    @PostMapping("/admin/post")
    public ResponseEntity<PostDTO> insertPost(@RequestBody PostDTO postDTO) throws NotFoundException {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        UserDTO user = new UserDTO();
        user.setEmail(authentication.getPrincipal().toString());
        postDTO.setAuthor(user);
        return new ResponseEntity<>(postService.save(postDTO),HttpStatus.CREATED);
    }
    @GetMapping("post/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable("id") int id) throws NotFoundException {
        return new ResponseEntity<>(postService.findById(id),HttpStatus.OK);
    }
    @PutMapping("/admin/post/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable("id") int id,@RequestBody PostDTO postDTO) throws NotFoundException {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        UserDTO user = new UserDTO();
        logger.info(postDTO.getStatus()+"");
        user.setEmail(authentication.getPrincipal().toString());
        postDTO.setAuthor(user);
        return new ResponseEntity<>(postService.update(postDTO,id),HttpStatus.OK);
    }
    @DeleteMapping("/admin/post/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") int id) throws NotFoundException {
        boolean isDelete = postService.delete(id);
        if(isDelete){
            return new ResponseEntity<>("Delete Success",HttpStatus.OK);
        }
        return new ResponseEntity<>("Delete Failed",HttpStatus.NOT_ACCEPTABLE);
    }
}
