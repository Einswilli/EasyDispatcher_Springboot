package com.ftf.easyfuturedispatch.security.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    response.addHeader("Access-Control-Allow-Origin","*");
    response.addHeader("Access-Control-Allow-Headers","*");
    response.addHeader("Access-Control-Expose-Headers","*");
    response.setHeader("Access-Control-Allow-Credentials","true");
    response.addHeader("Access-Control-Allow-Methods","GET,POST,DELETE,PATCH");

    if(request.getMethod().equals("OPTIONS")){
      response.setStatus(HttpServletResponse.SC_OK);
    }
    else {
      String jwt = request.getHeader(SecurityParams.JWT_HEADER_NAME);
      if (jwt == null || !jwt.startsWith(SecurityParams.HEADER_PREFIX)) {
        filterChain.doFilter(request, response);
        return;
      }
      JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecurityParams.SECRET)).build();
      DecodedJWT decodedJWT = verifier.verify(jwt.substring(SecurityParams.HEADER_PREFIX.length()));
      String appUserName = decodedJWT.getSubject();
      List<String> roles = decodedJWT.getClaims().get("roles").asList(String.class);
      Collection<GrantedAuthority> authorities = new ArrayList<>();
      roles.forEach(rn -> {
        authorities.add(new SimpleGrantedAuthority(rn));
      });
      UsernamePasswordAuthenticationToken user
          = new UsernamePasswordAuthenticationToken(appUserName, null, authorities);
      SecurityContextHolder.getContext().setAuthentication(user);
      filterChain.doFilter(request, response);


    }

  }
}
