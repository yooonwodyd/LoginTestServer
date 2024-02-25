# LoginTestServer
앱 및 웹 화경에서 로그인 관련 API를 테스트 하기 위해 만들었습니다.
## 쿠키를 이용한 로그인
서버사이드를 가정으로 구현했습니다.
브라우저의 쿠키 보안 정책으로, 클라이언트 사이드 랜더링의 경우 HTTPS 배포가 필수적으로 보입니다.
## JWT를 이용한 로그인
UserDetail을 세부구현 했습니다.
별도의 detail클래스에 작성하는 방법과, Security Config에서 함수형 인터페이스를 구현하는 방법. 총 두 가지로 구현했습니다.
프로젝트가 복잡해진다면 별도의 detail 클래스를 사용하는 것이 좋을 것이라 생각합니다.
## Oauth2를 이용한 로그인
userAttributes에서 필요한 정보들을 가져오기 위해 다음의 레코드로 변환했습니다.
```Java
public record OAuth2UserResponse(
        Long id,
        String nickname,
        String email
) {
    public static OAuth2UserResponse of(String socialName, Map<String, Object> attributes) {
        if (socialName.equals("kakao")) {
            return OAuth2UserResponse.fromKakao(attributes);
        }
        if(socialName.equals("google") {
        ...
        }
    }

    private static OAuth2UserResponse fromKakao(Map<String, Object> attributes) {
      ...
    }
}
```
여러 로그인 마다 포메팅 형식이 다르기에 위와 같은 방법을 선택했지만, 좋은 방법인지에 대한 고민이 필요해보입니다...
