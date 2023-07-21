package entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String name;
    private String profileImage;

    public User(String email, String password, String name, String profileImage){
        this.email = email;
        this.password = password;
        this.name = name;
        this.profileImage = profileImage;
    }

    public User(String email, String password, String name){
        this.email = email;
        this.password = password;
        this.name = name;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserIngredient> userIngredients;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", profileImage='" + profileImage + '\'' +
                '}';
    }
}
