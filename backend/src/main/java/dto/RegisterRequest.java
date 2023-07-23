package dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Optional;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class RegisterRequest {
    public String name;
    public String email;
    public String password;
    public String profileImage;

    public Optional<String> getProfileImage(){
        return Optional.ofNullable(this.profileImage);
    }
}
