package RoomReservationSystem.api;

import RoomReservationSystem.model.Building;
import RoomReservationSystem.security.WebDevSecurity;
import RoomReservationSystem.service.BuildingService;
import RoomReservationSystem.validation.BuildingValidator;
import java.util.Arrays;
import java.util.Collections;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author Tomecz Patrik
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = BuildingApiController.class)
@ContextConfiguration(classes = {BuildingValidator.class, WebDevSecurity.class})
public class BuildingApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BuildingService service;

    private final Building TEST_BUILDING = new Building("TEST_BUILDING", Collections.EMPTY_LIST);

    /**
     * Test of getAll method, of class BuildingApiController.
     */
    @Test
    //@WithMockUser(roles = { "ADMIN", "USER" })
    public void testGetAll() throws Exception {
        Mockito.when(service.findAll()).thenReturn(Arrays.asList(TEST_BUILDING));
        
        mvc.perform(get("/api/building/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                //.andExpect(jsonPath("$", hasSize(1)))
                //.andExpect(jsonPath("$[0].name").value(TEST_BUILDING.getName()));
    }

//    /**
//     * Test of getNames method, of class BuildingApiController.
//     */
//    @Test
//    public void testGetNames() {
//        System.out.println("getNames");
//        BuildingApiController instance = new BuildingApiController();
//        ResponseEntity expResult = null;
//        ResponseEntity result = instance.getNames();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of findByName method, of class BuildingApiController.
//     */
//    @Test
//    public void testFindByName() {
//        System.out.println("findByName");
//        String name = "";
//        BuildingApiController instance = new BuildingApiController();
//        ResponseEntity expResult = null;
//        ResponseEntity result = instance.findByName(name);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of findById method, of class BuildingApiController.
//     */
//    @Test
//    public void testFindById() {
//        System.out.println("findById");
//        int id = 0;
//        BuildingApiController instance = new BuildingApiController();
//        ResponseEntity expResult = null;
//        ResponseEntity result = instance.findById(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of createBuilding method, of class BuildingApiController.
//     */
//    @Test
//    public void testCreateBuilding() {
//        System.out.println("createBuilding");
//        BuildingDTO buildingDTO = null;
//        BindingResult bindingResult = null;
//        BuildingApiController instance = new BuildingApiController();
//        ResponseEntity expResult = null;
//        ResponseEntity result = instance.createBuilding(buildingDTO, bindingResult);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of deleteByName method, of class BuildingApiController.
//     */
//    @Test
//    public void testDeleteByName() {
//        System.out.println("deleteByName");
//        String name = "";
//        BuildingApiController instance = new BuildingApiController();
//        ResponseEntity expResult = null;
//        ResponseEntity result = instance.deleteByName(name);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

}
