/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.cs.gridlock.utilities;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.commons.math3.random.RandomData;

/**
 * Helper class allowing the selection of one object out of a collection of
 * objects based on a per-object probability.
 *
 * @author rutger
 */
public class SelectionWheel<T> {

    private final RandomData random;

    private final SortedMap<Float,T> entries;

    /**
     * Create a new selection wheel using <code>random</code> as the source
     * of randomness.
     *
     * @param random
     *          The RandomData provider
     *
     * @throws IllegalArgumentException
     *          If random is null
     */
    public SelectionWheel( RandomData random ) {
        if( random == null ) {
            throw new IllegalArgumentException( "RandomData source cannot be null" );
        }

        this.random = random;
        this.entries = new TreeMap<Float, T>();
    }

    /**
     * Create a new selection wheel using <code>random</code> as the source of
     * randomness, <code>chances</code> as the list of probabilities and <code>
     * entries</code> as the list of items from which to select one.
     *
     * The list <code>chances</code> should contain the probability of each entry
     * in <code>entries</code> to be selected.  Both lists must contain the same
     * number of elements.  The float at position <code>i</code> in <code>chances</code>
     * is the probability of the element at position <code>i</code> in <code>entries</code>.
     *
     * The probabilities are relative.
     *
     * @param random
     *          The RandomData provider
     * @param chances
     *          The probabilities of selection
     * @param entries
     *          The items from which to select
     *
     * @throws IllegalArgumentException
     *          If random is null
     * @throws IllegalArgumentException
     *          If lists differ in length
     */
    public SelectionWheel( RandomData random, List<Float> chances, List<T> entries ) {
        this( random );

        if( chances.size() != entries.size() )
            throw new IllegalArgumentException( "Both lists must have equal number of elements" );

        for( int i = 0; i < chances.size(); i++ ) {
            this.addEntry( chances.get( i ), entries.get( i ) );
        }
    }

    public boolean isEmpty() {
        synchronized( this.entries ) {
            return this.entries.isEmpty();
        }
    }

    public int size() {
        synchronized( this.entries ) {
            return this.entries.size();
        }
    }

    public final void addEntry( Float chance, T entry ) {
        if( chance <= 0 ) {
            return;
        }

        synchronized( this.entries ) {
            Float cumulChance = ( this.entries.isEmpty() ? 0 : this.entries.lastKey() ) + chance;
            this.entries.put( cumulChance, entry );
        }
    }

    public T nextEntry() {
        return this.getEntry( random.nextUniform( 0, 1 ) );
    }

    private T getEntry( double pick ) {
        synchronized( this.entries ) {
            if( this.isEmpty() ) {
                throw new IndexOutOfBoundsException( "Trying to select an entry from an empty selection wheel" );
            }

            Float scaledPick = (float)pick * this.entries.lastKey();
            SortedMap<Float,T> selection = this.entries.tailMap( scaledPick );
            assert !selection.isEmpty() : "Selected portion of the map should not be empty";
            return selection.get(  selection.firstKey() );
        }
    }
}