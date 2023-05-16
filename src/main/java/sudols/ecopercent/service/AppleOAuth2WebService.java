package sudols.ecopercent.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sudols.ecopercent.domain.User;
import sudols.ecopercent.dto.auth.apple.AppleAuthorizationResponse;
import sudols.ecopercent.repository.UserRepository;
import sudols.ecopercent.security.JwtTokenProvider;
import sudols.ecopercent.security.auth.AppleOAuth2Provider;
import sudols.ecopercent.security.TokenResponseProvider;

import java.net.URI;
import java.security.PublicKey;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppleOAuth2WebService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenResponseProvider tokenResponseProvider;
    private final AppleOAuth2Provider appleOAuth2Provider;

    public ResponseEntity<?> login(HttpServletResponse response, AppleAuthorizationResponse appleAuthorizationResponse) {
        // TODO: 하드코딩
        final String domain = "https://www.ecopercent.com";
        final String cookieDomain = "ecopercent.com";
        String identityToken = appleAuthorizationResponse.getId_token();
        PublicKey publicKey = appleOAuth2Provider.getPublicKey(identityToken);
        String email = appleOAuth2Provider.getEmailFromTokenWithPublicKey(identityToken, publicKey);
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            tokenResponseProvider.generateAccessTokenAndAddCookieForWeb(response, email, cookieDomain);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(URI.create(domain + "/signup"));
            return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
        }
        tokenResponseProvider.generateTokensAndAddCookieForWeb(response, optionalUser.get(), cookieDomain);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(domain + "/welcome"));
        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
    }
}