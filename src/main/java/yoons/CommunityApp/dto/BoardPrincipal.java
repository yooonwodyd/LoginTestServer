package yoons.CommunityApp.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import yoons.CommunityApp.domain.UserAccount;
import yoons.CommunityApp.domain.UserRoleType;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public record BoardPrincipal(
        String username,
        String password,
        String nickname,
        Set<UserRoleType> roles
) implements UserDetails {

    public static BoardPrincipal from(UserAccount userAccount){
        return new BoardPrincipal(
                userAccount.getUserId(),
                userAccount.getUserPassword(),
                userAccount.getNickname(),
                userAccount.getRoles()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("getAuthorities() 호출");

        return roles.stream()
                .map(UserRoleType::getRoleName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
