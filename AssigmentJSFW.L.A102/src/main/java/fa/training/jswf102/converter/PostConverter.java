package fa.training.jswf102.converter;

import fa.training.jswf102.dto.LookupDTO;
import fa.training.jswf102.dto.PostDTO;
import fa.training.jswf102.dto.PostTagDTO;
import fa.training.jswf102.dto.UserDTO;
import fa.training.jswf102.entities.Lookup;
import fa.training.jswf102.entities.Post;
import fa.training.jswf102.entities.PostTag;
import javafx.geometry.Pos;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostConverter {
    private ModelMapper modelMapper = new ModelMapper();
    @Autowired PostTagConverter postTagConverter;
    @Autowired private UserConverter userConverter;
    public PostDTO convertToDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setTitle(post.getTitle());
        postDTO.setTag(post.getTag());
        postDTO.setContent(post.getContent());
        postDTO.setCreateTime(post.getCreateTime());
        postDTO.setModifiedTime(post.getModifiedTime());
        postDTO.setStatus(post.getStatus());
        postDTO.setImage(post.getImage());
        postDTO.setId(post.getId());
        if(post.getAuthor() != null){
            postDTO.setAuthor(userConverter.convertToDTO(post.getAuthor()));
        }
        if(post.getPostTags() != null){
            postDTO.setPostTag(postTagConverter.listPostTagDTO(post.getPostTags()));
        }
        return postDTO;
    }

    public Post convertToEntity(PostDTO post) {
        Post postConvert = modelMapper.map(post, Post.class);
        return postConvert;
    }
}
