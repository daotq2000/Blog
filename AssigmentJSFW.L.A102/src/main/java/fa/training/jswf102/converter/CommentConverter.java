package fa.training.jswf102.converter;

import fa.training.jswf102.dto.CommentDTO;
import fa.training.jswf102.dto.PostDTO;
import fa.training.jswf102.dto.UserDTO;
import fa.training.jswf102.entities.Comment;
import fa.training.jswf102.entities.Post;
import fa.training.jswf102.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {
    private ModelMapper modelMapper = new ModelMapper();
    @Autowired private PostConverter postConverter;
    public CommentDTO convertToDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCreateTime(comment.getCreateTime());
        commentDTO.setComment(comment.getComment());
        commentDTO.setEmail(comment.getEmail());
        commentDTO.setAuthor(comment.getAuthor());
        commentDTO.setUrl(comment.getUrl());
        commentDTO.setStatus(comment.getStatus());
        commentDTO.setId(comment.getId());
        if(comment.getPost() != null){
            PostDTO postDTO = new PostDTO();
            postDTO.setTitle(comment.getPost().getTitle());
            postDTO.setId(comment.getPost().getId());
            commentDTO.setPost(postDTO);
        }

        return commentDTO;
    }

    public Comment convertToEntity(CommentDTO commentDTO) {
        Comment comment = modelMapper.map(commentDTO, Comment.class);
        return comment;
    }
    public CommentDTO convertToListDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCreateTime(comment.getCreateTime());
        commentDTO.setComment(comment.getComment());
        commentDTO.setEmail(comment.getEmail());
        commentDTO.setAuthor(comment.getAuthor());
        commentDTO.setUrl(comment.getUrl());
        commentDTO.setStatus(comment.getStatus());
        commentDTO.setId(comment.getId());
        return commentDTO;
    }
}
