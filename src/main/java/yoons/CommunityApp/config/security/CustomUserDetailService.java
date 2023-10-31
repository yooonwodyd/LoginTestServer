package yoons.CommunityApp.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import yoons.CommunityApp.domain.UserAccount;
import yoons.CommunityApp.dto.BoardPrincipal;
import yoons.CommunityApp.repository.UserRepository;

import java.util.Arrays;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다 - username: " + username));
        System.out.println("loadUserByUsername() 호출");
        System.out.println(Arrays.toString(userAccount.getRoles().stream().map(role -> role.getRoleName()).toArray()));

        return userRepository.findById(username)
                .map(BoardPrincipal::from)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다 - username: " + username));
    }

    private final UserRepository userRepository;
}
