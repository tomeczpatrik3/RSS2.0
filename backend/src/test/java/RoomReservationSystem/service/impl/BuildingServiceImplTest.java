/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomReservationSystem.service.impl;

import RoomReservationSystem.dto.BuildingDTO;
import RoomReservationSystem.model.Building;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tomecz Patrik
 */
public class BuildingServiceImplTest {
    
    public BuildingServiceImplTest() {
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
     * Test of delete method, of class BuildingServiceImpl.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Building building = null;
        BuildingServiceImpl instance = new BuildingServiceImpl();
        instance.delete(building);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteByName method, of class BuildingServiceImpl.
     */
    @Test
    public void testDeleteByName() {
        System.out.println("deleteByName");
        String name = "";
        BuildingServiceImpl instance = new BuildingServiceImpl();
        instance.deleteByName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class BuildingServiceImpl.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        Building building = null;
        BuildingServiceImpl instance = new BuildingServiceImpl();
        Building expResult = null;
        Building result = instance.save(building);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByName method, of class BuildingServiceImpl.
     */
    @Test
    public void testFindByName() {
        System.out.println("findByName");
        String name = "";
        BuildingServiceImpl instance = new BuildingServiceImpl();
        Building expResult = null;
        Building result = instance.findByName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class BuildingServiceImpl.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        int id = 0;
        BuildingServiceImpl instance = new BuildingServiceImpl();
        Building expResult = null;
        Building result = instance.findById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class BuildingServiceImpl.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        BuildingServiceImpl instance = new BuildingServiceImpl();
        List<Building> expResult = null;
        List<Building> result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNames method, of class BuildingServiceImpl.
     */
    @Test
    public void testGetNames() {
        System.out.println("getNames");
        BuildingServiceImpl instance = new BuildingServiceImpl();
        List<String> expResult = null;
        List<String> result = instance.getNames();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByDTO method, of class BuildingServiceImpl.
     */
    @Test
    public void testFindByDTO() {
        System.out.println("findByDTO");
        BuildingDTO buildingDTO = null;
        BuildingServiceImpl instance = new BuildingServiceImpl();
        Building expResult = null;
        Building result = instance.findByDTO(buildingDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
