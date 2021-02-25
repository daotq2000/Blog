package fa.training.jswf102.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private String salt;
    private String email;
    private String profile;
    private Integer role;
}
