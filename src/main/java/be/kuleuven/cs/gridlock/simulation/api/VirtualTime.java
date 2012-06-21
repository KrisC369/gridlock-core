package be.kuleuven.cs.gridlock.simulation.api;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Rutger Claes <rutger.claes@cs.kuleuven.be>
 */
public class VirtualTime implements Comparable<VirtualTime>, Serializable {

    private static final long serialVersionUID = 1L;

    private final double seconds;

    /**
     * Create a virtual time object representing <code>seconds</code>
     *
     * @param seconds the number of seconds that passed since the beginning of the virtual time
     */
    private VirtualTime( double seconds ) {
        this.seconds = seconds;
    }

    @Override
    public String toString() {
        long hours = (long)Math.floor( this.seconds / 3600.0 );
        short minutes = (short)Math.floor( ( (long)Math.floor( seconds ) % 3600 ) / 60.0 );
        short seconds = (short)Math.floor( this.seconds - 3600 * hours - 60 * minutes );
        return String.format( "%1$03d:%2$02d:%3$02d", hours, minutes, seconds );
    }

    @Override
    public int compareTo( VirtualTime t ) {
        assert t != null : "Virtual Time should not be compared to null";
        return ((Double)this.seconds).compareTo( t.getSeconds() );
    }

    public VirtualTime add( double delta ) {
        assert delta >= 0 : "Virtual Time add should only be called with positive numbers";
        return new VirtualTime( this.seconds + delta );
    }

    public VirtualTime sub( double delta ) {
        assert delta >= 0 : "Virtual Time sub should only be called with negative numbers";
        return new VirtualTime( this.seconds - delta );
    }

    public VirtualTime sub( VirtualTime time ) {
        return sub( time.getSeconds() );
    }

    /**
     * @return the number of seconds that have passed since the beginning of virtual time
     */
    public double getSeconds() {
        return this.seconds;
    }

    /**
     * @return a date object that corresponds with this virtual time seconds
     */
    public Date toDate() {
        return new Date( (long)Math.floor(this.seconds * 1000) );
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (int) ( Double.doubleToLongBits( this.seconds ) ^ ( Double.doubleToLongBits( this.seconds ) >>> 32 ) );
        return hash;
    }

    @Override
    public boolean equals( Object obj ) {
        return (obj instanceof VirtualTime) && ((VirtualTime)obj).seconds == this.seconds;
    }


    /**
     * Create a new virtual time object
     *
     * @param millis time in milliseconds
     *
     * @return a virtual time with seconds set to millis / 1000
     */
    public static VirtualTime createVirtualTime( long millis ) {
        return createVirtualTime( millis / 1000d );
    }

    /**
     * Create a new virtual time object
     *
     * @param seconds time in seconds
     *
     * @return a virtual time with seconds
     */
    public static VirtualTime createVirtualTime( double seconds ) {
        assert seconds >= 0 : "Negative time values should not occur";
        return new VirtualTime( seconds );
    }
}