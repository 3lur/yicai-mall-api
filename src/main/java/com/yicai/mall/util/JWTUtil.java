package com.yicai.mall.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Key;
import java.util.Date;


public class JWTUtil {
    private static final Logger logger = LoggerFactory.getLogger(JWTUtil.class);
    private static final long TOKEN_EXPIRED_TIME = 30 * 24 * 60 * 50;

    private static Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode("======================YiCaiMall=========================="));
    }

    /*
     * createJWT
     *  @param userID
     *  @param userName
     * */
    public static String createJWT(Long userID, String userName) {
        return Jwts.builder()
                .setSubject("AUTH_USER")
                .setIssuer("YiCaiMall")
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + TOKEN_EXPIRED_TIME))
                .claim("userID", userID)
                .claim("userName", userName)
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    /*
     * getValueFromJWT
     *   @param token
     *   @param key
     * */
    public String getValueFromJWT(String token, String key) {
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token);
        return (String) claimsJws.getBody().get(key);
    }

    public boolean verifyJWT(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
