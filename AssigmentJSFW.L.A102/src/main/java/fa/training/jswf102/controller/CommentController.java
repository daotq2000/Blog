package fa.training.jswf102.controller;

import fa.training.jswf102.dto.CommentDTO;
import fa.training.jswf102.dto.PostDTO;
import fa.training.jswf102.exception.NotFoundException;
import fa.training.jswf102.service.CommentService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class CommentController {
    @Autowired private CommentService commentService;
    @GetMapping("/comment")
    public ResponseEntity<Map<String,Object>> searchComment(@RequestParam(value = "page",defaultValue = "0") int page,
                                             @RequestParam(value = "size",defaultValue = "5") int size,
                                             @RequestParam(value = "param",defaultValue = "") String search) {
        return new ResponseEntity<>(commentService.searchComment(page, size, search), HttpStatus.OK);
    }
    @GetMapping("/comment/{id}")
    public ResponseEntity<List<CommentDTO>> getListCommentByPostId(@PathVariable("id") int id) {
        return new ResponseEntity<>(commentService.getListCommentByPostId(id), HttpStatus.OK);
    }
    @GetMapping("/comment/by/{id}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable("id") int id) throws NotFoundException {
        return new ResponseEntity<>(commentService.findById(id), HttpStatus.OK);
    }
    @PostMapping("/comment")
    public ResponseEntity<CommentDTO> insertComment(@RequestBody CommentDTO commentDTO) throws NotFoundException {
        return new ResponseEntity<>(commentService.save(commentDTO), HttpStatus.CREATED);
    }
    @PutMapping("/admin/comment/{id}")
    public ResponseEntity<CommentDTO> updateComment(@RequestBody CommentDTO commentDTO,
                                                    @PathVariable("id") int id) throws NotFoundException {
        return new ResponseEntity<>(commentService.update(commentDTO,id), HttpStatus.OK);
    }
    @DeleteMapping("/admin/comment/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("id") int id) throws NotFoundException {
        return  new ResponseEntity<>(commentService.delete(id)?"Delete Success":"Delete Failed",HttpStatus.OK);
    }

}
