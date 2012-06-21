package be.kuleuven.cs.gridlock.utilities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.math3.random.RandomData;

/**
 * Permutation wheel reorders a list based on probabilities
 *
 * @author Rutger Claes <rutger.claes@cs.kuleuven.be>
 */
public class PermutationWheel<T> {

    private final List<T> entries;

    private final List<Float> probabilities;

    private float scale;

    private final RandomData random;

    /**
     * Initialize a permutation wheel
     *
     * @param random random data to use
     */
    public PermutationWheel( RandomData random ) {
        this.random = random;
        this.entries = new LinkedList<T>();
        this.probabilities = new LinkedList<Float>();
    }

    /**
     * Initialize a permutation wheel
     *
     * @param random random data to use
     * @param probabilities list of relative probabilities
     * @param entries list of entries
     */
    public PermutationWheel( RandomData random, List<Float> probabilities, List<T> entries ) {
        this( random );
        for( int i = 0; i < probabilities.size(); i++ ) {
            this.addEntry( probabilities.get( i ), entries.get( i ) );
        }
    }

    /**
     * Add an entry to this wheel
     *
     * @param probability probability of choosing the entry
     * @param entry the entry
     */
    public final void addEntry( Float probability, T entry ) {
        if( probability <= 0 || Float.isInfinite( probability ) || Float.isNaN( probability ) )
            return;

        synchronized( this ) {
            scale += probability;
            entries.add( entry );
            probabilities.add( probability );
        }
    }

    /**
     * Returns one of the entries added and not yet returned
     *
     * The chance of one particular entry being returned is that entries
     * relative probability divided by the sum of the relative probabilities of
     * all remaining entries
     *
     * When the entry is returned, it is also removed from the wheel
     *
     * @return the next entry
     */
    public T nextEntry() {
        return this.selectEntry( this.random.nextUniform( 0, 1 ) );
    }

    private T selectEntry( double pick ) {
        float scaledPick = (float)pick*scale;
        int i;

        for( i = 0; i < probabilities.size() - 1; i++ ) {
            if( scaledPick < probabilities.get(i) ) {
                return this.removeEntry( i );
            }
            scaledPick -= probabilities.get( i );
        }

        return this.removeEntry( i );
    }

    private T removeEntry( int i ) {
        synchronized( this ) {
            this.scale -= this.probabilities.remove( i );
            return this.entries.remove( i );
        }
    }

    /**
     * @return true if the wheel is empty
     */
    public boolean isEmpty() {
        synchronized( this ) {
            return this.entries.isEmpty();
        }
    }

    /**
     * @return the remaining number of entries in the wheel
     */
    public int size() {
        synchronized( this ) {
            return this.entries.size();
        }
    }

    /**
     * Permute a list of entries based on a list of relative probabilities
     *
     * This method will create a PermutationWheel and add all entries and their probabilities.
     * It will then use the wheel to construct a new list.  The position of each entry in the
     * new list is determined by the relative probability.  The higher the probability, the
     * more likely the entry will end up at the start of the list.
     * 
     * @param <T> the type of entries
     * @param random the random number generator to use
     * @param probabilities the list of relative probabilities
     * @param entries the list of entries
     *
     * @return a permutation of the entries based on their relative probabilities
     */
    public static <T> List<T> permute( RandomData random, List<Float> probabilities, List<T> entries ) {
        PermutationWheel<T> wheel = new PermutationWheel<T>( random, probabilities, entries );
        List<T> result = new ArrayList<T>( entries.size() );
        for( int i = 0; i < entries.size(); i++ ) {
            result.add( wheel.nextEntry() );
        }

        assert wheel.isEmpty() : "Wheel should be empty at the end";
        return result;
    }
}