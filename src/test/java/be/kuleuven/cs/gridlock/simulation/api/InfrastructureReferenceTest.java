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
public abstract class InfrastructureReferenceTest<T> {

    @Test( expected = IllegalArgumentException.class )
    public void testNullConstructor() {
        createInfrastructureReference( null );
    }

    public abstract InfrastructureReference createInfrastructureReference( T reference );

    public abstract T nextReference();

    @Test
    public void testEqualsAndHashCode() {
        T firstReference = this.nextReference();
        T secondReference = this.nextReference();

        InfrastructureReference a = createInfrastructureReference( firstReference );
        InfrastructureReference b = createInfrastructureReference( secondReference );
        InfrastructureReference ac = createInfrastructureReference( firstReference );
        InfrastructureReference bc = createInfrastructureReference( secondReference );

        assertTrue( a.equals( ac ) );
        assertTrue( b.equals( bc ) );
        assertEquals( a.hashCode(), ac.hashCode() );
        assertEquals( b.hashCode(), bc.hashCode() );
        assertFalse( a.equals( b ) );
        assertFalse( a.equals( bc ) );
        assertFalse( a.hashCode() == b.hashCode() );
    }

    @Test
    public void testGetId() {
        T reference = nextReference();
        InfrastructureReference a = createInfrastructureReference( reference );
        assertSame( reference, a.getId() );
    }

}