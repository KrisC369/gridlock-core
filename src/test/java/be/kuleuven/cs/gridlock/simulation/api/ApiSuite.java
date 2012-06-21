package be.kuleuven.cs.gridlock.simulation.api;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Rutger Claes <rutger.claes@cs.kuleuven.be>
 */
@RunWith( Suite.class )
@Suite.SuiteClasses( { be.kuleuven.cs.gridlock.simulation.api.NodeReferenceTest.class, be.kuleuven.cs.gridlock.simulation.api.InfrastructureReferenceTest.class, be.kuleuven.cs.gridlock.simulation.api.VirtualTimeTest.class, be.kuleuven.cs.gridlock.simulation.api.VehicleReferenceTest.class, be.kuleuven.cs.gridlock.simulation.api.LinkReferenceTest.class } )
public class ApiSuite {

}