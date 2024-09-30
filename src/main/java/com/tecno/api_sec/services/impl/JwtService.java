package com.tecno.api_sec.services.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;

import java.time.Duration;


@Service
public class JwtService{

    // Esta variable representa el tiempo de expiración del token en minutos. 
    // Por ejemplo, si EXPIRATION_IN_MINUTES es 30, significa que el token expirará 30 minutos después de ser emitido
    // Además está configurado en el archivo de propiedades application.properties
    @Value("${security.jwt.expiration-in-minutes}")
    private Long EXPIRATION_IN_MINUTES;

    // Clave secreta utilizada para firmar el token, configurada en el archivo de propiedades application.properties
    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    /**
     * Genera un token JWT para el usuario proporcionado.
     * 
     * @param user          Detalles del usuario para el cual se genera el token.
     * @param extraClaims   Claims adicionales que se desean incluir en el token.
     * @return              Token JWT generado.
     */
    public String generateToken(UserDetails user, Map<String, Object> extraClaims) {

        // Fecha actual en la que se emite el token
        Date issuedAt = new Date(System.currentTimeMillis());

        // Fecha de expiración del token, calculada a partir de la fecha de emisión
        // EXPIRATION_IN_MINUTES * 60 * 1000    : Convierte el tiempo de expiración de minutos a milisegundos.
        // issuedAt.getTime()                   : Obtiene la fecha de emisión en milisegundos.
        // expirationTime                       : Suma el tiempo de expiración en milisegundos a la fecha de emisión en milisegundos para obtener la fecha de expiración total.

        Long expirationTime = (EXPIRATION_IN_MINUTES * 60 * 1000) + issuedAt.getTime();
        Date expiration = new Date( expirationTime );

        // Construye el token JWT
        String jwt = Jwts.builder()
        .header()
        .type("JWT")                        // Establece el tipo de token como JWT
        .and()
        .subject(user.getUsername())            // Establece el sujeto del token (nombre de usuario)
        .issuedAt(issuedAt)                     // Establece la fecha de emisión
        .expiration(expiration)                 // Establece la fecha de expiración
        .claims(extraClaims)                    // Añade claims adicionales al token
        .signWith(generateKey(),Jwts.SIG.HS256) // Firma el token con la clave secreta y el algoritmo HS256
        .compact();                             // Compacta el token en una cadena
                

        return jwt;
    }

    /**
     * Genera una clave secreta a partir de la clave secreta configurada.
     * 
     * @return Clave secreta utilizada para firmar el token.
     */
    private SecretKey generateKey() {
        // Decodifica la clave secreta de base64 a un array de bytes
        byte[] passwordDecoded = Decoders.BASE64.decode(SECRET_KEY);
        
        // Imprime la clave secreta decodificada (solo para propósitos de depuración)
        System.out.println( new String(passwordDecoded) );

        // Genera una clave HMAC-SHA a partir de los bytes decodificados
        return Keys.hmacShaKeyFor(passwordDecoded);
    }

    /**
     * Extrae el nombre de usuario (subject) del token JWT.
     * 
     * @param jwt Token JWT del cual se extrae el nombre de usuario.
     * @return Nombre de usuario extraído del token.
     */
    public String extractUsername(String jwt) {
        return extractAllClaims(jwt).getSubject();
    }

    /**
     * Extrae todos los claims del token JWT.
     * 
     * @param jwt Token JWT del cual se extraen los claims.
     * @return Todos los claims extraídos del token.
     */
    private Claims extractAllClaims(String jwt) {
        // Parsea y verifica el token con la clave secreta generada
        return Jwts.parser().verifyWith(generateKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    /**
     * Valida un token JWT.
     * 
     * Este método verifica la firma del token utilizando la clave secreta y también verifica
     * que el token no haya expirado. Si la firma es válida y el token no ha expirado,
     * devuelve `true`; de lo contrario, devuelve `false`.
     * 
     * @param jwt El token JWT a validar.
     * @return `true` si el token es válido, `false` en caso contrario.
     */
    public boolean validateToken(String jwt) {
        try {
            // Extrae todos los claims del token utilizando la clave secreta generada
            Claims claims = Jwts.parser().verifyWith(generateKey())
                    .build()
                    .parseSignedClaims(jwt)
                    .getPayload();

            // Obtiene la fecha de expiración del token
            Date expiration = claims.getExpiration();

            // Obtiene la fecha actual
            Date now = new Date();

            // Calcula la duración restante hasta la expiración
            Duration duration = Duration.between(now.toInstant(), expiration.toInstant());

            // Formatea la duración en formato HH:mm:ss
            String timeRemainingFormatted = String.format("%02d:%02d:%02d", 
                    duration.toHours(), 
                    duration.toMinutesPart(), 
                    duration.toSecondsPart());

            // Muestra en la consola el tiempo actual, el tiempo de expiración del token y el tiempo restante en formato HH:mm:ss
            System.out.println("Tiempo actual: " + now);
            System.out.println("Tiempo de expiración del token: " + expiration);
            System.out.println("Tiempo restante para que expire el token: " + timeRemainingFormatted);

            // Verifica si la fecha de expiración es anterior a la fecha actual
            // Si la fecha de expiración es anterior a la fecha actual, el token ha expirado
            return !expiration.before(now);
        } catch (SignatureException e) {
            // Captura la excepción si la firma del token no es válida
            System.err.println("Error al validar el token: Firma inválida");
            return false;
        } catch (MalformedJwtException e) {
            // Captura la excepción si el token está mal formado
            System.err.println("Error al validar el token: Token mal formado");
            return false;
        } catch (UnsupportedJwtException e) {
            // Captura la excepción si el token no es soportado
            System.err.println("Error al validar el token: Token no soportado");
            return false;
        } catch (IllegalArgumentException e) {
            // Captura la excepción si el token es nulo o vacío
            System.err.println("Error al validar el token: Token nulo o vacío");
            return false;
        } catch (ExpiredJwtException e) {
            // Captura la excepción si el token ha expirado
            System.err.println("Error al validar el token: Token ha expirado");
            return false;
        }
    }

    

}
