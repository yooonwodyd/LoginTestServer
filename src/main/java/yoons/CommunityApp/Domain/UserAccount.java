package yoons.CommunityApp.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@Entity
public class UserAccount extends AuditingFields{
    @Id
    @Column(length = 50)
    private String userId;
    @Setter @Column(nullable = false) private String userPassword;
    @Setter @Column(length = 100) private String nickname;

    protected UserAccount() {
    }

    private UserAccount(String userId, String userPassword, String nickname) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.nickname = nickname;
    }

    public UserAccount of(String userId, String userPassword, String nickname){
        return new UserAccount(userId, userPassword, nickname);
    }
}
