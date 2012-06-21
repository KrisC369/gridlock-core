/*
 *  Part of the Skout Application by Rubato
 */
package be.kuleuven.cs.gridlock.simulation.api;

import java.util.Date;
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
public class VirtualTimeTest {

    /**
     * Test of toString method, of class VirtualTime.
     */
    @Test
    public void testToString() {
        {
            VirtualTime time = VirtualTime.createVirtualTime( 0.0 );
            assertEquals( "000:00:00", time.toString() );
        }

        {
            VirtualTime time = VirtualTime.createVirtualTime( 1.0 );
            assertEquals( "000:00:01", time.toString() );
        }

        {
            VirtualTime time = VirtualTime.createVirtualTime( 60.0 );
            assertEquals( "000:01:00", time.toString() );
        }

        {
            VirtualTime time = VirtualTime.createVirtualTime( 3601.0 );
            assertEquals( "001:00:01", time.toString() );
        }

        {
            VirtualTime time = VirtualTime.createVirtualTime( 23 * 3600.0 + 59 * 60.0 + 59.0 );
            assertEquals( "023:59:59", time.toString() );
        }

        {
            VirtualTime time = VirtualTime.createVirtualTime( 101 * 3600.0 + 59 * 60.0 + 59.0 );
            assertEquals( "101:59:59", time.toString() );
        }
    }

    /**
     * Test of compareTo method, of class VirtualTime.
     */
    @Test
    public void testCompareTo() {
        VirtualTime a = VirtualTime.createVirtualTime( 1000 );
        VirtualTime b = VirtualTime.createVirtualTime( 1000 );
        VirtualTime c = VirtualTime.createVirtualTime( 1001 );

        assertTrue( a.compareTo( b ) == 0 );
        assertTrue( a.compareTo( c ) < 0 );
        assertTrue( c.compareTo( a ) > 0 );
    }

    /**
     * Test of add method, of class VirtualTime.
     */
    @Test
    public void testAdd() {
        {
            VirtualTime base = VirtualTime.createVirtualTime( 0 );
            VirtualTime result = base.add( 60 );

            assertEquals( 60, result.getSeconds(), 0 );
            assertEquals( 0, base.getSeconds(), 0 );
        }

        {
            VirtualTime base = VirtualTime.createVirtualTime( 0 );
            VirtualTime result = base.add( 0 );
            assertEquals( base, result );
            assertEquals( 0, result.getSeconds(), 0 );
        }

        {
            VirtualTime base = VirtualTime.createVirtualTime( 0 );
            try {

                VirtualTime result = base.add(  -10 );
                fail( "No exception thrown" );
            } catch( Error e ) {
                assertEquals( 0, base.getSeconds(), 0 );
            }
        }
    }

    /**
     * Test of toDate method, of class VirtualTime.
     */
    @Test
    public void testToDate() {
        fail( "Not implemented" );
    }

    /**
     * Test of createVirtualTime method, of class VirtualTime.
     */
    @Test
    public void testCreateVirtualTimeFromLong() {
        fail( "Not implemented" );
    }

    /**
     * Test of createVirtualTime method, of class VirtualTime.
     */
    @Test
    public void testCreateVirtualTimeFromDouble() {
        fail( "Not implemented" );
    }
}
