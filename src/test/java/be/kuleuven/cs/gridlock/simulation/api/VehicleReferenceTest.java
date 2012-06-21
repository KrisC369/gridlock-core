/*
 *  Part of the Skout Application by Rubato
 */
package be.kuleuven.cs.gridlock.simulation.api;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rutger
 */
public class VehicleReferenceTest {

    public VehicleReferenceTest() {
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
     * Test of parseVehicle method, of class VehicleReference.
     */
    @Test
    public void testParseVehicleFromString() {
        {
            Long id = 1l;
            VehicleReference reference = VehicleReference.parseVehicle( id.toString() );

            assertNotNull( reference );
            assertEquals( (Object)id, reference.getId() );
        }

        try {
            String id = null;
            VehicleReference reference = VehicleReference.parseVehicle( id );
            fail( "No exception thrown" );
        } catch( IllegalArgumentException e ) {
            // GOOD
        }

        try {
            String id = "a";
            VehicleReference reference = VehicleReference.parseVehicle( id );
            fail( "No exception thrown" );
        } catch( IllegalArgumentException e ) {
            // GOOD
        }
    }

    /**
     * Test of parseVehicle method, of class VehicleReference.
     */
    @Test
    public void testParseVehicleFromLong() {
        {
            Long id = 1l;
            VehicleReference reference = VehicleReference.parseVehicle( id );
            assertNotNull( reference );
            assertEquals( (Object)id, reference.getId() );
        }

        try {
            Long id = null;
            VehicleReference reference = VehicleReference.parseVehicle( id );
            fail( "No exception thrown" );
        } catch( IllegalArgumentException e ) {
            // GOOD
        }
    }

    @Test
    public void testConstructor() {
        try {
            Long id = null;
            VehicleReference reference = new VehicleReference( id );
            fail( "No exception thrown" );
        } catch( IllegalArgumentException e ) {
            // GOOD
        }
    }

    /**
     * Test of getId method, of class VehicleReference.
     */
    @Test
    public void testGetId() {
        Long vehicleId = 1l;
        VehicleReference reference = new VehicleReference( vehicleId );
        assertEquals( (Object)vehicleId, reference.getId() );
    }

    @Test
    public void testEqualsAndHashCode() {
        VehicleReference a = new VehicleReference( 1l );
        VehicleReference b = new VehicleReference( 2l );
        VehicleReference ab = new VehicleReference( 1l );
        VehicleReference bb = new VehicleReference( 2l );

        assertTrue( a.equals( ab ) );
        assertTrue( a.equals( a ) );
        assertTrue( b.equals( bb ));
        assertTrue( b.equals( b ) );

        assertFalse( a.equals( b ) );
        assertFalse( b.equals( a ) );

        assertEquals( a.hashCode(), ab.hashCode() );
        assertEquals( b.hashCode(), bb.hashCode() );
        assertFalse( a.hashCode() == b.hashCode() );
    }
}
