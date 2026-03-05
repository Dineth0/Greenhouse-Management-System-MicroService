package lk.ijse.apigateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    private final String SECRET = "2D4A614E645267556B58703273357638792F423F4428472B4B6250655368566DF423F4428472B4B6250655368566D";

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
