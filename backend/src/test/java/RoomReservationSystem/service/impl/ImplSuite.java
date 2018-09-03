/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomReservationSystem.service.impl;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Tomecz Patrik
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({RoomReservationSystem.service.impl.StatusServiceImplTest.class, RoomReservationSystem.service.impl.AuthorityServiceImplTest.class, RoomReservationSystem.service.impl.SubjectServiceImplTest.class, RoomReservationSystem.service.impl.SemesterServiceImplTest.class, RoomReservationSystem.service.impl.UserServiceImplTest.class, RoomReservationSystem.service.impl.reservation.ReservationSuite.class, RoomReservationSystem.service.impl.BuildingServiceImplTest.class, RoomReservationSystem.service.impl.ClassroomServiceImplTest.class})
public class ImplSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
