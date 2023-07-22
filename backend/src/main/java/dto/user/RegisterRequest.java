package dto.user;

import lombok.Data;

import java.util.Optional;

@Data
public class RegisterRequest {
    public String name;
    public String email;
    public String password;
    public String profileImage;

    public Optional<String> getProfileImage(){
        return Optional.ofNullable(this.profileImage);
    }
}
