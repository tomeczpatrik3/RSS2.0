package RoomReservationSystem.security;

import com.sun.media.jfxmedia.logging.Logger;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 *
 * @author tomeczp
 */
@Profile("DEV")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebDevSecurity extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll();
    }
}
