package RoomReservationSystem.service.impl;

import RoomReservationSystem.dto.EventDTO;
import RoomReservationSystem.dto.reservation.ReservationInfoDTO;
import RoomReservationSystem.enums.ReservationType;
import RoomReservationSystem.service.reservation.ClassReservationService;
import RoomReservationSystem.service.reservation.EventReservationService;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Az eseményekhez tartozó szervíz osztály tesztesetei
 *
 * @author Tomecz Patrik
 */
//@RunWith(MockitoJUnitRunner.class)
public class EventServiceImplTest {

    @InjectMocks
    EventServiceImpl service;

    @Mock
    ClassReservationService classRService;

    @Mock
    EventReservationService eventRService;

    private final EventDTO TEST_EVENT_1 = new EventDTO(
            "2018.11.02 10:30",
            "2018.11.02 11:30",
            "Ez egy teszt óra foglalás",
            new ReservationInfoDTO(
                    1,
                    ReservationType.CLASS,
                    "Teszt János",
                    "ÉSZAKI",
                    "Turán Pál tanterem",
                    "A megjegyzés",
                    "Analízis 1 Gyakorlat",
                    "2018-2019/1"
            )
    );

    private final EventDTO TEST_EVENT_2 = new EventDTO(
            "2018.11.03 08:30",
            "2018.11.03 14:30",
            "Ez egy teszt esemény foglalás",
            new ReservationInfoDTO(
                    1,
                    ReservationType.EVENT,
                    "Teszt Gábor",
                    "DÉLI",
                    "Turán Pál tanterem",
                    "A megjegyzés",
                    "Analízis 2 EA",
                    "2018-2019/1"
            )
    );

//    @Test
//    public void testFindByUserName() {
//        Mockito.when(service.getEvents()).thenReturn(Arrays.asList(TEST_EVENT_1, TEST_EVENT_2));
//        List<EventDTO> found = service.findByUserName("Teszt János");
//        assertEquals(TEST_EVENT_1, found.get(0));
//    }
}
