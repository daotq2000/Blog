package fa.training.jswf102.service;

import fa.training.jswf102.dto.CommentDTO;
import fa.training.jswf102.entities.Comment;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface CommentService extends BaseService<CommentDTO,Integer> {
    List<CommentDTO> getListCommentByPostId(int postId);
    List<CommentDTO> getTop10CommentLast();
    Map<String,Object> searchComment(int page,int size,String search);
}
