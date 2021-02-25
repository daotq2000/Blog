package fa.training.jswf102.service.impl;

import fa.training.jswf102.converter.CommentConverter;
import fa.training.jswf102.dto.CommentDTO;
import fa.training.jswf102.entities.Comment;
import fa.training.jswf102.entities.Post;
import fa.training.jswf102.exception.NotFoundException;
import fa.training.jswf102.repository.CommentRepository;
import fa.training.jswf102.repository.PostRepository;
import fa.training.jswf102.service.CommentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommentServiceImpl  implements CommentService {
    Logger logger = Logger.getLogger(this.getClass());
    @Autowired private
    PostRepository postRepository;
    @Autowired private CommentRepository commentRepository;
    @Autowired private CommentConverter commentConverter;

    public CommentServiceImpl() {
    }

    @Override
    public List<CommentDTO> findAll() {
        List<Comment> comments = commentRepository.findAll();

        return null;
    }

    @Override
    public CommentDTO findById(Integer id) throws NotFoundException {
        Optional<Comment> comment = commentRepository.findById(id);
        if(!comment.isPresent()){
            throw new NotFoundException("Comment not Found");
        }
        return commentConverter.convertToDTO(comment.get());
    }

    @Override
    public CommentDTO save(CommentDTO commentDTO) throws NotFoundException {
        logger.info(commentDTO);
        commentDTO.setCreateTime(new Date());
        if(commentDTO.getAuthor().trim().equalsIgnoreCase("")){
            throw new IllegalArgumentException("Author is require");
        }
        if(commentDTO.getEmail().trim().equalsIgnoreCase("")){
            throw new IllegalArgumentException("Email is require");
        }
        if(!commentDTO.getEmail().trim().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
            throw new IllegalArgumentException("Email is invalid");
        }
        if(commentDTO.getComment().trim().equalsIgnoreCase("")){
            throw new IllegalArgumentException("Comment is require");
        }
        if(commentDTO.getUrl().trim().equalsIgnoreCase("")){
            throw new IllegalArgumentException("Profile is require");
        }

        Comment comment = commentRepository.save(commentConverter.convertToEntity(commentDTO));
        return commentConverter.convertToDTO(comment);
    }

    @Override
    public CommentDTO update(CommentDTO commentDTO, Integer id) throws NotFoundException {
        if(findById(id) == null){
            throw new NotFoundException("Comment Not Found");
        }
        if(commentDTO.getAuthor().trim().equalsIgnoreCase("")){
            throw new IllegalArgumentException("Author is require");
        }
        if(commentDTO.getEmail().trim().equalsIgnoreCase("")){
            throw new IllegalArgumentException("Email is require");
        }
        if(!(commentDTO.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"))){
            throw new IllegalArgumentException("Email is invalid");
        }
        if(commentDTO.getComment().trim().equalsIgnoreCase("")){
            throw new IllegalArgumentException("Comment is require");
        }
        if(commentDTO.getUrl().trim().equalsIgnoreCase("")){
            throw new IllegalArgumentException("Profile is require");
        }
        Comment commentUpdate = commentRepository.getOne(id);
        commentUpdate.setPost(commentUpdate.getPost());
        commentUpdate.setCreateTime(commentUpdate.getCreateTime());
        commentUpdate.setUrl(commentDTO.getUrl());
        commentUpdate.setAuthor(commentDTO.getAuthor());
        commentUpdate.setComment(commentDTO.getComment());
        commentUpdate.setStatus(commentDTO.getStatus());
        commentUpdate.setEmail(commentDTO.getEmail());
        commentRepository.save(commentUpdate);

        return commentConverter.convertToDTO(commentUpdate);
    }

    @Override
    public boolean delete(Integer id) throws NotFoundException {

        if(findById(id) == null){
            throw new NotFoundException("Comment Not Found");
        }
        try {
            commentRepository.deleteById(id);
            return true;
        }catch (Exception e){
            logger.error(e);
        }
        return false;
    }

    @Override
    public List<CommentDTO> getListCommentByPostId(int postId) {
        List<Comment> comments = commentRepository.getByPostId(postId);
        List<CommentDTO> commentDTOS = new ArrayList<>();
        comments.forEach(comment -> commentDTOS.add(commentConverter.convertToListDTO(comment)));
        return commentDTOS;
    }

    @Override
    public List<CommentDTO> getTop10CommentLast() {
        List<Comment> comments = commentRepository.findTop10Comment();
        List<CommentDTO> commentDTOS = new ArrayList<>();
        comments.forEach(comment -> commentDTOS.add(commentConverter.convertToDTO(comment)));
        return commentDTOS;
    }

    @Override
    public Map<String, Object> searchComment(int page, int size, String search) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Comment> commentPage = commentRepository.searchComment(pageable,search);
        List<Comment> list = commentPage.toList();
        List<CommentDTO> commentDTOS = new ArrayList<>();
        list.forEach(comment -> {
            commentDTOS.add(commentConverter.convertToDTO(comment));
        });
        Map<String,Object> map = new HashMap<>();
        map.put("comments",commentDTOS);
        map.put("currentPage",page);
        map.put("totalPages",commentPage.getTotalPages());
        map.put("totalElements",commentPage.getTotalElements());
        return map;
    }
}
