package yoons.CommunityApp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class UserRoleType {
    @Id @Setter private Long id;
    @Setter private String roleName;

    private UserRoleType(Long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    protected UserRoleType() {

    }

    public static UserRoleType of(Long id, String roleName){
        return new UserRoleType(id, roleName);
    }
}
