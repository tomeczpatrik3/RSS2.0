/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomReservationSystem.api;

import RoomReservationSystem.dto.BuildingDTO;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

/**
 *
 * @author Tomecz Patrik
 */
public class BuildingApiControllerTest {
    
    public BuildingApiControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAll method, of class BuildingApiController.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        BuildingApiController instance = new BuildingApiController();
        List<BuildingDTO> expResult = null;
        List<BuildingDTO> result = instance.getAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNames method, of class BuildingApiController.
     */
    @Test
    public void testGetNames() {
        System.out.println("getNames");
        BuildingApiController instance = new BuildingApiController();
        List<String> expResult = null;
        List<String> result = instance.getNames();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByName method, of class BuildingApiController.
     */
    @Test
    public void testFindByName() {
        System.out.println("findByName");
        String name = "";
        BuildingApiController instance = new BuildingApiController();
        ResponseEntity expResult = null;
        ResponseEntity result = instance.findByName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class BuildingApiController.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        int id = 0;
        BuildingApiController instance = new BuildingApiController();
        ResponseEntity expResult = null;
        ResponseEntity result = instance.findById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createBuilding method, of class BuildingApiController.
     */
    @Test
    public void testCreateBuilding() {
        System.out.println("createBuilding");
        BuildingDTO buildingDTO = null;
        BindingResult bindingResult = null;
        BuildingApiController instance = new BuildingApiController();
        ResponseEntity expResult = null;
        ResponseEntity result = instance.createBuilding(buildingDTO, bindingResult);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteByName method, of class BuildingApiController.
     */
    @Test
    public void testDeleteByName() {
        System.out.println("deleteByName");
        String name = "";
        BuildingApiController instance = new BuildingApiController();
        ResponseEntity expResult = null;
        ResponseEntity result = instance.deleteByName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
