package RoomReservationSystem.security;

/**
 * A védelemmel kapsolatos konstansokat tartalmazó osztály
 *
 * @author Tomecz Patrik
 */
public class SecurityConstants {

    /**
     *
     */
    public static final String SECRET = "SecretKeyToGenJWTs";

    /**
     *
     */
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days

    /**
     *
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     *
     */
    public static final String HEADER_STRING = "Authorization";

    /**
     *
     */
    public static final String REGISTER_URL = "/api/user/createUser";

    /**
     *
     */
    public static final String GET_EVENTS_URL = "/api/calendar/getEvents";

    /**
     *
     */
    public static final String CHECK_USERNAME_URL = "/api/user/existsByUsername*";

    /**
     *
     */
    public static final String GET_NAME_URL = "/api/user/getName*";

    /**
     *
     */
    public static final String CLASS_RESERVATIONS_URL = "/api/classReservation/getAccepted";

    /**
     *
     */
    public static final String CLASS_RESERVATIONS_FIND_BY_ID_URL = "/api/classReservation/findById*";

    /**
     *
     */
    public static final String EVENT_RESERVATIONS_URL = "/api/eventReservation/getAccepted";

    /**
     *
     */
    public static final String EVENT_RESERVATIONS_FIND_BY_ID_URL = "/api/eventReservation/findById*";

    /**
     *
     */
    public static final String LOGIN_URL = "/api/user/login";
}
