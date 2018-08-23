package RoomReservationSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author tomeczp
 */
@SpringBootApplication
@EnableJpaRepositories("RoomReservationSystem.repository")
public class RoomReservationSystemApplication {

    /**
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(RoomReservationSystemApplication.class, args);
    }
}
