package sudols.ecopercent.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Email
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "profile_image")
    private byte[] profileImage;

    @Column(name = "profile_message")
    private String profileMessage;

    @Column(name = "oauth_provider")
    private String oAuthProvider;
}


