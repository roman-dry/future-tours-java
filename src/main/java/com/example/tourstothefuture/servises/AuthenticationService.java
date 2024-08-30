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
import org.springframework.security.authentication.BadCredentialsException;
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

    /*public AuthenticationResponse register(RegisterRequest request) {
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
    }*/

    public AuthenticationResponse register(RegisterRequest request) {
        try {
            // Перевірка наявності користувача з такою ж електронною поштою
            if (userRepository.existsByEmail(request.getEmail())) {
                return new AuthenticationResponse("Email already in use", null, null);
            }

            User newUser;

            if (request.getAdminKey() != null && request.getAdminKey().equals(appConfig.getAdminKey())) {
                // Створюємо адміністратора
                newUser = UserFactory.createUser(UserRole.ADMIN, request.getName(), request.getEmail(), passwordEncoder.encode(request.getPassword()));
            } else {
                // Створюємо звичайного користувача
                newUser = UserFactory.createUser(UserRole.CUSTOMER, request.getName(), request.getEmail(), passwordEncoder.encode(request.getPassword()));
            }

            // Зберігаємо користувача в базі даних
            userRepository.save(newUser);

            // Генеруємо токен
            var jwtToken = jwtService.generateToken(newUser);

            // Зберігаємо токен для користувача
            saveUserToken(newUser, jwtToken);

            return new AuthenticationResponse("success", jwtToken, newUser);
        } catch (Exception e) {
            // Обробка помилок і повернення повідомлення про помилку
            return new AuthenticationResponse("Registration failed: " + e.getMessage(), null, null);
        }
    }

    /*public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail());
        System.out.println(user.toString());
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        String message ="success";

        return new AuthenticationResponse(message, jwtToken, user);
    }*/

    public AuthenticationResponse login(LoginRequest request) {
        try {
            // Перевірка наявності користувача з такою електронною поштою
            var user = userRepository.findByEmail(request.getEmail());
            if (user == null) {
                return new AuthenticationResponse("User not found", null, null);
            }

            // Аутентифікація користувача
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            // Генерація токена
            var jwtToken = jwtService.generateToken(user);

            // Відкликання старих токенів користувача
            revokeAllUserTokens(user);

            // Збереження нового токена
            saveUserToken(user, jwtToken);

            String message = "success";

            return new AuthenticationResponse(message, jwtToken, user);

        } catch (BadCredentialsException e) {
            // Обробка помилок аутентифікації
            return new AuthenticationResponse("Invalid email or password", null, null);
        } catch (Exception e) {
            // Обробка інших помилок
            return new AuthenticationResponse("Login failed: " + e.getMessage(), null, null);
        }
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
