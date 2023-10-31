package yoons.CommunityApp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@ToString(callSuper = true)
@Entity
public class UserAccount extends AuditingFields{
    @Id
    @Column(length = 50)
    private String userId;
    @Setter @Column(nullable = false) private String userPassword;
    @Setter @Column(length = 100) private String nickname;

    @ManyToMany(fetch = FetchType.EAGER)
    @Setter private Set<UserRoleType> roles;

    protected UserAccount() {
    }

    private UserAccount(String userId, String userPassword, String nickname) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.nickname = nickname;
    }
    private UserAccount(String userId, String userPassword, String nickname, Set<UserRoleType> roles) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.nickname = nickname;
        this.roles = roles;
    }

    public static UserAccount of(String userId, String userPassword, String nickname, Set<UserRoleType> roles){
        return new UserAccount(userId, userPassword, nickname, roles);
    }
}
