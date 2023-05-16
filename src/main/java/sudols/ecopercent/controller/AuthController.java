package sudols.ecopercent.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sudols.ecopercent.dto.auth.apple.AppleAuthorizationResponse;
import sudols.ecopercent.security.JwtTokenProvider;
import sudols.ecopercent.service.*;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final KakaoOAuth2Service kakaoOAuth2Service;
    private final AppleOAuth2IosService appleOAuth2IosService;
    private final AppleOAuth2WebService appleOAuth2WebService;
    private final TokenService tokenService;

    @PostMapping("/login/oauth2/kakao")
    @ResponseBody
    public ResponseEntity<?> kakaoOAuth2Login(HttpServletRequest request,
                                              HttpServletResponse response) {
        return kakaoOAuth2Service.login(request, response);
    }

    @PostMapping("/login/oauth2/apple/ios")
    @ResponseBody
    public ResponseEntity<?> appleOAuth2IosLogin(HttpServletRequest request) {
        return appleOAuth2IosService.login(request);
    }

    @PostMapping("/login/oauth2/apple/web")
    public ResponseEntity<?> appleOAuth2LoginWeb(HttpServletResponse response,
                                                 @ModelAttribute AppleAuthorizationResponse appleAuthorizationResponse) {
        return appleOAuth2WebService.login(response, appleAuthorizationResponse);
    }

    @PostMapping("/token/access")
    public void reissueAccessToken(HttpServletRequest request, HttpServletResponse response,@CookieValue("refresh") String refresh) {
        System.out.println("cookies: " + request.getCookies());
        String referer = request.getHeader("Referer");
        Cookie cookie = tokenService.reissueUserAccessToken(referer, refresh);
        response.addCookie(cookie);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
    }
}
