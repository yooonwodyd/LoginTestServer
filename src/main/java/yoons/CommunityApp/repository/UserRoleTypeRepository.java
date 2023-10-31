package yoons.CommunityApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yoons.CommunityApp.domain.UserRoleType;

public interface UserRoleTypeRepository extends JpaRepository<UserRoleType, Long> {
}
