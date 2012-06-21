package be.kuleuven.cs.gridlock.simulation.api;

/**
 * Class to uniquely identify a vehicle
 *
 * @author Rutger Claes <rutger.claes@cs.kuleuven.be>
 */
public class VehicleReference {

    public static VehicleReference parseVehicle( String string ) {
        try {
            return parseVehicle( Long.parseLong( string ) );
        } catch( NumberFormatException e ) {
            throw new IllegalArgumentException( "Cannot convert " + string + " to long" );
        }
    }

    public static VehicleReference parseVehicle( Long value ) {
        return new VehicleReference( value );
    }

    private final long id;

    /**
     * Create a new Vehicle Reference
     *
     * @param vehicleId reference id
     */
    public VehicleReference( Long vehicleId ) {
        if( vehicleId == null ) {
            throw new IllegalArgumentException( "Vehicle id cannot be null" );
        }

        this.id = vehicleId;
    }

    @Override
    public String toString() {
        return String.format( "%d", this.id );
    }

    public long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (int) ( this.id ^ ( this.id >>> 32 ) );
        return hash;
    }

    @Override
    public boolean equals( Object obj ) {
        return (obj instanceof VehicleReference) && ((VehicleReference)obj).getId() == this.id;
    }

}