package yoons.CommunityApp.controller;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import yoons.CommunityApp.config.security.JwtTokenProvider;
import yoons.CommunityApp.domain.UserAccount;
import yoons.CommunityApp.dto.BoardPrincipal;
import yoons.CommunityApp.repository.UserRepository;
import yoons.CommunityApp.repository.UserRoleTypeRepository;

import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/logintest")
public class LoginTestController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final UserRoleTypeRepository userRoleTypeRepository;

    // 로그인

    @Transactional
    @PostMapping("formLogin")
    public String login(@RequestBody Map<String, String> user) {
        log.info("user id = {}", user.get("id"));
        UserAccount member = userRepository.findById(user.get("id"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));

        member.setRoles(Set.of(userRoleTypeRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("없는 권한을 요청함"))));
        BoardPrincipal boardPrincipal = BoardPrincipal.from(member);
        return jwtTokenProvider.createToken(boardPrincipal.username(), boardPrincipal.roles());
    }
    @Secured("ROLE_USER")
    @GetMapping("hasUser")
    public String test1() {
        System.out.println("유저 확인");
        return "유저 권한이 확인되었습니다.";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("hasAdmin")
    public String test2() {
        System.out.println("관리자 확인");
        return "관리자 권한이 확인되었습니다.";
    }

    @GetMapping("require")
    public String test3() {
        System.out.println("require");
        return "인증이 필요한 url 입니다.";
    }

}
