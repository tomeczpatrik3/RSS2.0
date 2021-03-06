package RoomReservationSystem.security;

import RoomReservationSystem.dto.AccountCredentialsDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static RoomReservationSystem.security.SecurityConstants.EXPIRATION_TIME;
import static RoomReservationSystem.security.SecurityConstants.HEADER_STRING;
import static RoomReservationSystem.security.SecurityConstants.SECRET;
import static RoomReservationSystem.security.SecurityConstants.TOKEN_PREFIX;

import RoomReservationSystem.service.impl.UserServiceImpl;
import io.jsonwebtoken.Claims;
import java.util.stream.Collectors;
import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * JWTAuthenticationFilter
 *
 * @author Tomecz Patrik
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private UserServiceImpl userService;

    /**
     * A JWTAuthenticationFilter
     * @param authenticationManager Az AuthenticationManager objektum
     */
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * Az autentikáció megpróbálásáért felelős függvény
     * @param req A kérés
     * @param res A válasz
     * @return A megfelelő Authentication objektum
     * @throws AuthenticationException A lehetséges kivétel
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
            HttpServletResponse res) throws AuthenticationException {
        try {
            AccountCredentialsDTO creds = new ObjectMapper()
                    .readValue(req.getInputStream(), AccountCredentialsDTO.class);

            /*
                Ha a userService még nincs inicalizálva:
             */
            if (userService == null) {
                ServletContext servletContext = req.getServletContext();
                WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
                userService = webApplicationContext.getBean(UserServiceImpl.class);
            }

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            userService.loadUserByUsername(creds.getUsername()).getAuthorities()
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * A sikeres autentikáció utáni tevékenységekért felelős függvény
     * @param req A kérés
     * @param res A válasz
     * @param chain A szűrő lánc
     * @param auth Az Authentication objektum
     * @throws IOException A lehetséges IO kivétel
     * @throws ServletException A lehetséges "Servlet" kivétel
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain,
            Authentication auth) throws IOException, ServletException {

        User user = (User) auth.getPrincipal();
        Claims claims = Jwts.claims().setSubject(user.getUsername());

        //Az angular szamara:
        claims.put("authorities", user.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList()));

        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }
}
