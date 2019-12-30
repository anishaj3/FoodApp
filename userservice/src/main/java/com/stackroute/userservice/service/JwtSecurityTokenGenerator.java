package com.stackroute.userservice.service;

import com.stackroute.userservice.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtSecurityTokenGenerator implements SecurityTokenGenerator {


	@Override
	public Map<String, String> generateToken(User user) {
		String jwtToken = "";
		jwtToken = Jwts.builder().setSubject(user.getUserId())
					   .setIssuedAt(new Date())
					   .setSubject(user.getUserId())
					   .signWith(SignatureAlgorithm.HS256, "secretKey")
					   .compact();
		Map<String, String> map = new HashMap<>();
		map.put("token", jwtToken);
		map.put("message", "User successfully logged in");
		return map;
	}

}
