package sudols.ecopercent.domain;


import lombok.*;

@Builder
@Setter
@Getter
public class User {
    private Long userId;
    private String nickname;
    private String email;
    private String profileImage;
    private String profileMessage;
    private Long titleTumblerId;
    private Long titleEcobagId;
}
