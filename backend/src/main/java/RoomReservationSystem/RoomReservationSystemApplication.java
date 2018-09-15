package RoomReservationSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Az alkalmazást indító osztály
 *
 * @author Tomecz Patrik
 */
@SpringBootApplication
@EnableJpaRepositories("RoomReservationSystem.repository")
public class RoomReservationSystemApplication {

    /**
     * A bCryptPasswordEncoder() függvény
     *
     * @return A BCryptPasswordEncoder objektum
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * A main függvény
     *
     * @param args Az argumentumok
     */
    public static void main(String[] args) {
        SpringApplication.run(RoomReservationSystemApplication.class, args);
    }
}
