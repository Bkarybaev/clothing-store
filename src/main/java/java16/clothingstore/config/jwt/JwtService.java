package java16.clothingstore.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import java16.clothingstore.models.User;
import java16.clothingstore.repo.UserRepo;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;


@Component
@RequiredArgsConstructor
public class JwtService {
    private final UserRepo userRepo;

    @Value("${spring.security.jwt.secret_key}")
    private String SECRET_KEY;


    public String generateToken(User user) {

        //head
        JWTCreator.Builder builder = JWT.create();

        //peoloat
        builder.withClaim("email", user.getEmail());
        builder.withClaim("role",user.getRole().toString());
        builder.withClaim("id", user.getId());

        Instant now = Instant.now();
        builder.withIssuedAt(now);
        builder.withExpiresAt(now.plus(10, ChronoUnit.HOURS));

        //signatura
        return builder.sign(Algorithm.HMAC256(SECRET_KEY));
    }
    public User verifyToken(String token) {
        String email = JWT
                .require(Algorithm.HMAC256(SECRET_KEY))
                .build().verify(token)
                .getClaim("email")
                .asString();
       return userRepo.findByEmail(email).orElseThrow(
                () -> new RuntimeException("User with email " + email + " not found"));
    }
}
