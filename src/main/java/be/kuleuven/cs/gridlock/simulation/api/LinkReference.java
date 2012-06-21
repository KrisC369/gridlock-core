package be.kuleuven.cs.gridlock.simulation.api;

/**
 * Reference to a link
 *
 * @author Rutger Claes <rutger.claes@cs.kuleuven.be>
 */
public class LinkReference extends InfrastructureReference {

    /**
     * Create a LinkReference based on <code>string</code>
     *
     * @param string string to base reference on
     *
     * @return a link reference for that string
     */
    public static LinkReference parseLinkReference( String string ) {
        return new LinkReference( string );
    }

    /**
     * Create a new LinkReference
     *
     * @param id the unique reference id
     */
    public LinkReference( String id ) {
        super( id );
    }

    @Override
    public String getId() {
        return (String)super.getId();
    }
}