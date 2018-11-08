package RoomReservationSystem.security;

import static RoomReservationSystem.security.SecurityConstants.HEADER_STRING;
import static RoomReservationSystem.security.SecurityConstants.SECRET;
import static RoomReservationSystem.security.SecurityConstants.TOKEN_PREFIX;
import RoomReservationSystem.service.impl.UserServiceImpl;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.ServletContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Tomecz Patrik
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private UserServiceImpl userService;

    /**
     *
     * @param authManager
     */
    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    /**
     *
     * @param req
     * @param res
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            String username = Jwts.parser()
                    .setSigningKey(SECRET.getBytes())
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            /*
                Ha a userService még nincs inicalizálva:
             */
            if (userService == null) {
                ServletContext servletContext = request.getServletContext();
                WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
                userService = webApplicationContext.getBean(UserServiceImpl.class);
            }

            if (username != null) {
                userService.setAuthenticatedUser(username);
                return new UsernamePasswordAuthenticationToken(username, null, userService.loadUserByUsername(username).getAuthorities());
            }
            return null;
        }
        return null;
    }
}
