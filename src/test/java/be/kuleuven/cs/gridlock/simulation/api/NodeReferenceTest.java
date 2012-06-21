package be.kuleuven.cs.gridlock.simulation.api;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Rutger Claes <rutger.claes@cs.kuleuven.be>
 */
public class NodeReferenceTest extends InfrastructureReferenceTest<Long> {

    private long referenceCount;

    @Before
    public void resetReferenceCount() {
        this.referenceCount = 0;
    }

    /**
     * Test of parseNode method, of class NodeReference.
     */
    @Test
    public void testParseNodeFromString() {
        {
            String value = "1";
            NodeReference reference = NodeReference.parseNode( value );

            assertEquals( 1l, reference.getId(), 0 );
        }

        {
            String value = "12345";
            NodeReference reference = NodeReference.parseNode( value );

            assertEquals( 12345l, reference.getId(), 0 );
        }
    }

    /**
     * Test of parseNode method, of class NodeReference.
     */
    @Test
    public void testParseNodeFromLong() {
        assertEquals( 1l, NodeReference.parseNode( 1l ).getId(), 0 );
        assertEquals( 12345l, NodeReference.parseNode( 12345l ).getId(), 0 );
    }

    @Override
    public InfrastructureReference createInfrastructureReference( Long reference ) {
        return new NodeReference( reference );
    }

    @Override
    public Long nextReference() {
        this.referenceCount += 1;
        return referenceCount;
    }

}