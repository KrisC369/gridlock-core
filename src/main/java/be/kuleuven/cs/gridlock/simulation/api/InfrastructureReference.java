package be.kuleuven.cs.gridlock.simulation.api;

/**
 * Reference to a single piece of infrastructure
 *
 * Infrastructure references are identified based on
 * an <code>id</code>.
 *
 * @author Rutger Claes <rutger.claes@cs.kuleuven.be>
 */
public abstract class InfrastructureReference {

    private final Object id;

    /**
     * Create a new reference
     *
     * @param id the id that will uniquely define the reference
     */
    public InfrastructureReference( Object id ) {
        if( id == null ) {
            throw new IllegalArgumentException( "Cannot accept null as reference id" );
        }
        this.id = id;
    }

    /**
     * @return the reference id
     */
    public Object getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals( Object o ) {
        return ( o instanceof InfrastructureReference ) ? id.equals( ( (InfrastructureReference) o ).getId() ) : false;
    }

}