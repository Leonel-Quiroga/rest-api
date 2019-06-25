package ar.edu.unnoba.util;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenUtil {

	public static String getJWTString(String email, String password, Date expires, Key key) {

		if (email == null) {
			throw new NullPointerException("Email null");
		}
		if(password ==null) {
			throw new NullPointerException("Contraseña null");
		}
		if (expires == null) {
			throw new NullPointerException("Expiracion null");
		}
		if (key == null) {
			throw new NullPointerException("Key null");
		}

		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		String jwtString = Jwts.builder()
				.setIssuer("Jersey-Security-Basic")
				.setSubject(email)
				.setExpiration(expires)
				.setIssuedAt(new Date())
				.signWith(signatureAlgorithm, key)
				.compact();
		return jwtString;
	}

	public static boolean isValid(String token, Key key) {
		try {
			Jwts.parser().setSigningKey(key).parseClaimsJws(token.trim());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static String getName(String jwsToken, Key key) {
		if (isValid(jwsToken, key)) {
			Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(jwsToken);
			return claimsJws.getBody().getSubject();
		}
		return null;
	}

	public static String getPassword(String jwsToken, Key key) {
		if (isValid(jwsToken, key)) {
			Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(jwsToken);
			return claimsJws.getBody().getSubject();
		}
		return null;
	}
}
