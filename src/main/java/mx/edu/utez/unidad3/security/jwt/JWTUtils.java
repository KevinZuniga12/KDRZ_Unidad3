package mx.edu.utez.unidad3.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import mx.edu.utez.unidad3.modules.user.BeanUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

//CUARTO PASO: Generar las utilerias para jwt
@Service
public class JWTUtils {
    @Value("${secret.key}")
    private String SECRET_KEY;

    //Esta función ayuda a extraer todas las propiedades del token
    //Es decir que el cuerpo del token
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    //Esta funcion nos ayuda a poder extraer las propiedades del token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims CLAIMS = extractAllClaims(token);
        return claimsResolver.apply(CLAIMS);
    }

    //Esta funcion extraer el nombre de usuario del token
    public String exctractUsername(String token) {
        return  extractClaim(token, Claims::getSubject);
    }

    //Esta funcion extrae la fecha de expiracion
    public Date extractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //Validar funcion ayuda a validar si el token esta expirado
    public Boolean isTokenExpired(String token) {
        return extractExpirationDate(token).before(new Date());
    }

    //Esta funcion consume la funcion anterior
    //Tambien verifica que concidadn el usuario con el token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = exctractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    //Esta funcion nos ayuda a crear nuestro token
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder() //Construccion de un token
                .setClaims(claims).setSubject(subject) //Mandamos la informacion del usuario
                .setIssuedAt(new Date(System.currentTimeMillis())) //Cuando se creo el token
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) //Duracion del token
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // Aqui se firma
                .compact(); //Compactamos el token
    }


    //Esta funion consume el metodo de crear, solo para retornar
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }


}
