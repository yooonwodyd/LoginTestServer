package yoons.CommunityApp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import yoons.CommunityApp.domain.UserAccount;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<UserAccount, String> {
}