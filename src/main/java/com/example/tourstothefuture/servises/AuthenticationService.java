package com.example.tourstothefuture.servises;

import com.example.tourstothefuture.config.ApplicationConfig;
import com.example.tourstothefuture.models.User;
import com.example.tourstothefuture.models.UserFactory;
import com.example.tourstothefuture.models.UserRole;
import com.example.tourstothefuture.repositories.UserRepository;
import com.example.tourstothefuture.requests.LoginRequest;
import com.example.tourstothefuture.requests.RegisterRequest;
import com.example.tourstothefuture.responses.AuthenticationResponse;
import com.example.tourstothefuture.token.Token;
import com.example.tourstothefuture.token.TokenRepository;
import com.example.tourstothefuture.token.TokenType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    private final ApplicationConfig appConfig;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                                 JwtService jwtService, AuthenticationManager authenticationManager,
                                 TokenRepository tokenRepository, ApplicationConfig appConfig) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.tokenRepository = tokenRepository;
        this.appConfig = appConfig;
    }

    public AuthenticationResponse register(RegisterRequest request) {
        if (request.getAdminKey() != null && request.getAdminKey().equals(appConfig.getAdminKey())) {
            // Створюємо адміністратора
            var adminUser = UserFactory.createUser(UserRole.ADMIN, request.getName(), request.getEmail(), passwordEncoder.encode(request.getPassword()));
            userRepository.save(adminUser);
            var jwtToken = jwtService.generateToken(adminUser);
            saveUserToken(adminUser, jwtToken);
            String message ="success";

            return new AuthenticationResponse(message, jwtToken, adminUser);
        } else {
            // Створюємо звичайного користувача
            var user = UserFactory.createUser(UserRole.CUSTOMER, request.getName(), request.getEmail(), passwordEncoder.encode(request.getPassword()));
            userRepository.save(user);
            var jwtToken = jwtService.generateToken(user);
            saveUserToken(user, jwtToken);
            String message ="success";

            return new AuthenticationResponse(message, jwtToken, user);
        }
    }

    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail());
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        String message ="success";

        return new AuthenticationResponse(message, jwtToken, user);
    }



    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();

        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

}
