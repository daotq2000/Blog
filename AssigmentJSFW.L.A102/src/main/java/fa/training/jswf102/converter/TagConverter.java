package fa.training.jswf102.converter;

import fa.training.jswf102.dto.PostDTO;
import fa.training.jswf102.dto.TagDTO;
import fa.training.jswf102.entities.Post;
import fa.training.jswf102.entities.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TagConverter {
    private ModelMapper modelMapper = new ModelMapper();

    public TagDTO convertToDTO(Tag tag) {
        TagDTO tagDTO = modelMapper.map(tag, TagDTO.class);
        return tagDTO;
    }

    public Tag convertToEntity(TagDTO tag) {
        Tag tagConverter = modelMapper.map(tag, Tag.class);
        return tagConverter;
    }
}
