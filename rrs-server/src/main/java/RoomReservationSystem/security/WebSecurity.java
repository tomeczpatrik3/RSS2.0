package RoomReservationSystem.security;

import static RoomReservationSystem.security.SecurityConstants.CALENDAR_URLS;
import static RoomReservationSystem.security.SecurityConstants.CHECK_USERNAME_URL;
import static RoomReservationSystem.security.SecurityConstants.CLASS_RESERVATIONS_FIND_BY_ID_URL;
import static RoomReservationSystem.security.SecurityConstants.CLASS_RESERVATIONS_URL;
import static RoomReservationSystem.security.SecurityConstants.EVENT_RESERVATIONS_FIND_BY_ID_URL;
import static RoomReservationSystem.security.SecurityConstants.EVENT_RESERVATIONS_URL;
import static RoomReservationSystem.security.SecurityConstants.FRONTEND_ASSETS_URL;
import static RoomReservationSystem.security.SecurityConstants.FRONTEND_HOME_URL;
import static RoomReservationSystem.security.SecurityConstants.GET_BUILDING_NAMES_URL;
import static RoomReservationSystem.security.SecurityConstants.GET_CLASSROOM_NAMES_URL;
import static RoomReservationSystem.security.SecurityConstants.GET_EVENT_NAMES_URL;
import static RoomReservationSystem.security.SecurityConstants.LOGIN_URL;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.context.annotation.Bean;

import static RoomReservationSystem.security.SecurityConstants.REGISTER_URL;
import java.util.Arrays;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import static RoomReservationSystem.security.SecurityConstants.GET_SEMESTER_NAMES_URL;
import static RoomReservationSystem.security.SecurityConstants.GET_SUBJECT_NAMES_URL;
import static RoomReservationSystem.security.SecurityConstants.GET_SUBJECT_NAME_URL;
import static RoomReservationSystem.security.SecurityConstants.GET_USER_NAMES_URL;
import static RoomReservationSystem.security.SecurityConstants.GET_USER_NAME_URL;

/**
 *
 * @author tomeczp
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     *
     * @param userDetailsService
     * @param bCryptPasswordEncoder
     */
    public WebSecurity(
            UserDetailsService userDetailsService,
            BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final JWTAuthenticationFilter authenticationFilter = new JWTAuthenticationFilter(authenticationManager());
        authenticationFilter.setFilterProcessesUrl(LOGIN_URL);

        http.cors().and().csrf().disable()
                //Engedélyezett végpontok:
                .authorizeRequests()
                /**
                 * Frontend végpontok:
                 */
                .antMatchers(FRONTEND_HOME_URL).permitAll()
                .antMatchers(FRONTEND_ASSETS_URL).permitAll()
                
                .antMatchers(HttpMethod.POST, REGISTER_URL).permitAll()
                .antMatchers(HttpMethod.GET, CHECK_USERNAME_URL).permitAll()
                
                .antMatchers(HttpMethod.GET, GET_USER_NAME_URL).permitAll()
                .antMatchers(HttpMethod.GET, GET_SUBJECT_NAME_URL).permitAll()
                
                .antMatchers(HttpMethod.GET, GET_BUILDING_NAMES_URL).permitAll()
                .antMatchers(HttpMethod.GET, GET_SUBJECT_NAMES_URL).permitAll()
                .antMatchers(HttpMethod.GET, GET_USER_NAMES_URL).permitAll()
                .antMatchers(HttpMethod.GET, GET_SEMESTER_NAMES_URL).permitAll()
                .antMatchers(HttpMethod.GET, GET_CLASSROOM_NAMES_URL).permitAll()
                .antMatchers(HttpMethod.GET, GET_EVENT_NAMES_URL).permitAll()
                
                .antMatchers(HttpMethod.GET, CALENDAR_URLS).permitAll()
                
                .antMatchers(HttpMethod.GET, CLASS_RESERVATIONS_URL).permitAll()
                .antMatchers(HttpMethod.GET, CLASS_RESERVATIONS_FIND_BY_ID_URL).permitAll()
                .antMatchers(HttpMethod.GET, EVENT_RESERVATIONS_URL).permitAll()
                .antMatchers(HttpMethod.GET, EVENT_RESERVATIONS_FIND_BY_ID_URL).permitAll()
                
                //Minden más autentikációt igényel:
                .anyRequest().authenticated()
                .and()
                .addFilter(authenticationFilter)
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                
                //Sessionok kikapcsolása:
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    /**
     *
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    /*
        CORS config:
     */
    /**
     *
     * @return
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "content-type", "x-auth-token"));
        configuration.setExposedHeaders(Arrays.asList("Authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
