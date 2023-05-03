package sudols.ecopercent.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import sudols.ecopercent.dto.oauth2.apple.AppleAuthorizationResponse;
import sudols.ecopercent.service.oauth2.AppleOAuth2IosService;
import sudols.ecopercent.service.oauth2.AppleOAuth2WebService;
import sudols.ecopercent.service.oauth2.KakaoOAuth2Service;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login/oauth2")
public class OAuth2Controller {

    private final KakaoOAuth2Service kakaoOAuth2Service;
    private final AppleOAuth2IosService appleOAuth2IosService;
    private final AppleOAuth2WebService appleOAuth2WebService;

    @PostMapping("/kakao")
    @ResponseBody
    public ResponseEntity<?> kakaoOAuth2Login(HttpServletRequest request,
                                              HttpServletResponse response) {
        System.out.println("kakaoOAuth2Login");
        return kakaoOAuth2Service.login(request, response);
    }

    @PostMapping("/apple/ios")
    @ResponseBody
    public ResponseEntity<?> appleOAuth2IosLogin(HttpServletRequest request) {
        return appleOAuth2IosService.login(request);
    }

    @PostMapping("/apple/web")
    public ResponseEntity<?> appleOAuth2LoginWeb(HttpServletResponse response,
                                                 @RequestBody MultiValueMap<String, String> appleAuthorizationResponse) {
        System.out.println("appleOAuth2LoginWeb");
        System.out.println(appleAuthorizationResponse.toString());
//        return appleOAuth2WebService.login(response, appleAuthorizationResponse);
        return null;
    }
}
