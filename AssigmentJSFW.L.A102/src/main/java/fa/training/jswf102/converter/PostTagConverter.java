package fa.training.jswf102.converter;

import fa.training.jswf102.dto.PostDTO;
import fa.training.jswf102.dto.PostTagDTO;
import fa.training.jswf102.entities.Post;
import fa.training.jswf102.entities.PostTag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostTagConverter {
    @Autowired
    private PostConverter postConverter;
    @Autowired
    private TagConverter tagConverter;
    private ModelMapper modelMapper = new ModelMapper();

    public PostTagDTO convertToDTO(PostTag postTag) {
        PostTagDTO postTagDTO = new PostTagDTO();
        if (postTag.getPostTagId().getTag() != null) {
            postTagDTO.setTag(tagConverter.convertToDTO(postTag.getPostTagId().getTag()));
        }
        return postTagDTO;
    }

    public PostTag convertToEntity(PostTagDTO postTagDTO) {
        return modelMapper.map(postTagDTO, PostTag.class);
    }
    public List<PostTagDTO> listPostTagDTO(List<PostTag> list){
        List<PostTagDTO> list1 = new ArrayList<>();
        list.forEach(postTag -> {
            System.out.println(postTag.getPostTagId());
            list1.add(convertToDTO(postTag));
        });
        return list1;
    }
}
