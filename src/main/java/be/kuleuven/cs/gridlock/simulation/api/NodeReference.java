package be.kuleuven.cs.gridlock.simulation.api;

/**
 * Reference to a node
 *
 * @author Rutger Claes <rutger.claes@cs.kuleuven.be>
 */
public class NodeReference extends InfrastructureReference {

    /**
     * Create a node reference based on <code>string</code>
     *
     * @param string string to base reference on
     *
     * @return reference to a node
     */
    public static NodeReference parseNode( String string ) {
        return parseNode( Long.parseLong( string ) );
    }

    public static NodeReference parseNode( long value ) {
        return new NodeReference( value );
    }

    /**
     * Create a new NodeReference
     *
     * @param id reference id
     */
    public NodeReference( Long id ) {
        super( id );
    }

    @Override
    public Long getId() {
        return (Long)super.getId();
    }
}