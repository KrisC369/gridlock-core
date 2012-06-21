package be.kuleuven.cs.gridlock.simulation.api;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * @author Rutger Claes <rutger.claes@cs.kuleuven.be>
 */
public class LinkReferenceTest extends InfrastructureReferenceTest<String> {

    private int referenceCount = 0;


    @Before
    public void resetReferenceCount() {
        referenceCount = 0;
    }

    /**
     * Test of parseLinkReference method, of class LinkReference.
     */
    @Test
    public void testParseLinkReference() {
        String refRepr = "testReference";
        LinkReference ref = LinkReference.parseLinkReference( refRepr );

        assertNotNull( ref );
        assertEquals( refRepr, ref.getId() );
    }

    @Override
    public LinkReference createInfrastructureReference( String reference ) {
        return new LinkReference( reference );
    }

    @Override
    public String nextReference() {
        referenceCount += 1;
        return String.format( "%d", referenceCount );
    }
}