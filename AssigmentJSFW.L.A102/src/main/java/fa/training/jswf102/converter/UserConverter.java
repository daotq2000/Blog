package fa.training.jswf102.converter;

import fa.training.jswf102.dto.TagDTO;
import fa.training.jswf102.dto.UserDTO;
import fa.training.jswf102.entities.Tag;
import fa.training.jswf102.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    private ModelMapper modelMapper = new ModelMapper();

    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

    public User convertToDTO(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        return user;
    }
}
