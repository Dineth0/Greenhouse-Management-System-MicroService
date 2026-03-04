package lk.ijse.apigateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    private final String SECRET = "mysecret1234###";

    public Claims getAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValidate(String token) {
        try{
            getAllClaims(token);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
