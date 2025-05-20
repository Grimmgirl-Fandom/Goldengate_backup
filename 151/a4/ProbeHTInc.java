import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * An incomplete Hashtable using probing. The get and put functions both uae a 
 * private finction named find, that is incomplete. It must be completed for 
 * the probe hashtable to work.
 * @param <K> the type of key
 * @param <V> the type of value 
 * @author gtowell 
 * Created:  Sep 27, 2020 
 * Modified Oct 2, 2020
 * Modified: Sep 27, 2021
 */
public class ProbeHTInc<K, V> implements Map151Interface<K, V> {

    /**
     * Small inner class to group together key,value pairs
     */
    protected class Pair<L, W> {
        /** The key, cannot be changed */
        final L key;
        /**
         * The value. It can be changed as a second put with the key will change the
         * value
         */
        W value;

        /**
         * Initialize the node
         */
        public Pair(L ll, W ww) {
            key = ll;
            value = ww;
        }

        /** Print the node, and all subsequent nodes in the linked list */
        public String toString() {
            return "<" + key + ", " + value + ">";
        }
    }

    /** A Constant .. One of the cases in which static are acceptable
     * This one specifies the maximum number of tombstones allowed before 
     * rehashing for tombstone accumulation
     */
    /** When the hashtable needs to grow, by what factor should it grow */
    private static final double GROWTH_RATE = 2.0;
    /** How full the table should be before initiating rehash/growth */
    private static final double MAX_OCCUPANCY = 0.60;
    /** The default size of the backing array */
    private static int DEFAULT_CAPACITY = 1009;
   /** The array in which the hashtable stores data */
    private Pair<K, V>[] backingArray;
    /** The number of active items in the hashtable */
    private int itemCount;
    
 
    /** Default initialization */
    public ProbeHTInc() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Initialize a hashtable of the given size
     * 
     * @param size the size of the hashtable to create
     */
    @SuppressWarnings("unchecked")
    public ProbeHTInc(int size) {
        // Cannot make an array in which you mention a parameterized type.
        // So just make the generic array. This is a narrowing cast so it does not
        // even need to be explicitly case.
        backingArray = new Pair[size];
        itemCount = 0;
     }

     private int h(K k) {
         return objectHasher(k).mod(BigInteger.valueOf(backingArray.length)).intValue();
     }
    
    /**
     * The hash function. Just uses the java object hashvalue. 
     * @param key the Key to be hashed
     * @return the hash value
     */
    private BigInteger objectHasher(Object ob) {
        return stringHasher(ob.toString());
    }

    /**
     * Implemets Horner's on strings.
     * Since every object can be translated into a string This can be run
     * on an arbitrary object with no loss of generality.
     * @param ss the string to generate a hash value for
     * @return the hash value
     */
    private BigInteger stringHasher(String ss) {
        BigInteger mul = BigInteger.valueOf(23);
        BigInteger ll = BigInteger.valueOf(0);
        for (int i=0; i<ss.length(); i++) {
            ll = ll.multiply(mul);
            ll = ll.add(BigInteger.valueOf(ss.charAt(i)));
        }
        return ll;
    }

    /**
     * The number of active items in the hashtable
     * @return The number of active items in the hashtable
     */
    public int size() {
        return itemCount;
    }

    /**
     * WRITE ME
     * @param key
     * @return
     */
    private Pair<K, V> find(K key) {
        //sets and creates the int hasLoc equal to, the hashed key
        int hashLoc = h(key);
        //sets/creates variable q to be 0 for the while loop
        int q = 0;
        //while loop (keeps looping until it returns)
        while (true) {
            //gets the pair at the index of q, to check
            Pair<K, V> pair = backingArray[(hashLoc + q) % backingArray.length];
           //if that spot is vaccent, it stops looking and returns null
            if (pair == null)
                return null;
                //if the pairs key is equal to the entered key, return that pair
            if (pair.key.equals(key))
                return pair;
            //if neither, increment q to move the spot and repeat/loop
            q++;
        }
    }
    /**
     * Add a key-value pair to the hashtable. If the key is already in the
     * hashtable, then the old value is replaced. Otherwise this adds a new
     * key-value pair
     * Be sure to update itemCount as needed.
     * 
     * @param key   the key
     * @param value the value
     */
    public void put(K key, V value) {
        //checks to see if the number of items is larger than the backing array
        if (itemCount > backingArray.length * MAX_OCCUPANCY) {
            //if the backingArray isnt  big enough, it will rehash and grows the backing Array
            rehash((int)(backingArray.length*GROWTH_RATE));
        }
        //creates a new pair equal to  the fair at the key entered
        Pair<K, V> foundPair = find(key);
        //if the key does not exist
        if (foundPair == null) {
            //creates and assigns the int hashLoc to be the key from h 
            int hashLoc = h(key);
            //creates and sets the int q to be 0, for the while loop
            int q = 0;
            //a while loop that is looking for an empty indext to put it into, if its not empty, increment q
            while (backingArray[(hashLoc + q) % backingArray.length] != null) {
                q++;
            }
            //once it found an empty indext, add the pair to that index
            backingArray[(hashLoc + q) % backingArray.length] = new Pair<>(key, value);
            //increment count
            itemCount++;
        } 
        //if the key does exist(from the ealier check) assign that pairs "value" to be the found value
        else {
            foundPair.value = value;
        }
    }

    @SuppressWarnings("unchecked")
    /**
     * Rehash the current table. This should be done rarely as it is expensive
     * @param newSize the size of the table after rehashing
     */
    private void rehash(int newSize) {
        System.out.println("Reshashing to " + newSize);
        Pair<K, V>[] oldArray = backingArray;
        itemCount = 0;
        backingArray = new Pair[newSize];
        for (int i = 0; i < oldArray.length; i++) {
            if (oldArray[i] != null) {
                put(oldArray[i].key, oldArray[i].value);
            }
        }
    }

    /**
     * Get the value associated with the key
     * @param key the key whose value is sought
     * @return the associated value, or null
     */
    public V get(K key) {
        //gets and finds the pair from the given key
        Pair<K, V> pair = find(key);
        //if that pair is empty, return null
        if (pair==null)
            return null;
            //otherwise it returns the value from the given key for the pair
        return pair.value;
    }

    

    @Override
    /**
     * Does the hashtable contain the key
     * @param key the key
     * @return true iff the key is in the hashtable
     */
    public boolean containsKey(K key) {
        return null != get(key);
    }

    @Override
    /**
     * The complete set of keys active in the hashtable.
     * @return a set containing all of the keys in the hashtable
     */
    public Set<K> keySet() {
        TreeSet<K> set = new TreeSet<>();
        for (Pair<K,V> pr : backingArray) {
            if (pr!=null) {
                set.add(pr.key);
            }
        } 
        return set;
    }


    /**
     * A very basic set of tests for the class
     */
    public static void main(String[] args) {
        /* *
        ProbeHTInc<String, String> phi = new ProbeHTInc<>(10);
        phi.put("A", "B");
        phi.put("C", "D");
        phi.put("C1", "D");
        phi.put("C2", "D");
        phi.put("C3", "D");
        phi.put("C4", "D");
        phi.put("C5", "D");
        phi.put("C6", "D");
        phi.put("C7", "D");
        phi.put("C8", "D");
        phi.put("C9", "D");
        phi.put("C10", "D10");
        System.out.println(phi.get("A"));
        System.out.println(phi.get("B"));
        System.out.println(phi.get("C10"));
        */


        final int NANOS_SEC = 1000000000; // nanosec per sec
        {
            
                long start = System.nanoTime();
        
        ArrayList<String> keychain = new ArrayList<>();
        ProbeHTInc<String,Integer> probe = new ProbeHTInc<>();
        ReadCSV ec;
        try {
            // get a new instance of the class -- through a static maker.
            //ec = new ReadCSV("code.csv");
            ec = new ReadCSV(args[0], 4);
            
        }
        catch (IOException ioe) {
            System.err.println("Ending. Cannot read. " + ioe.toString());
            return;
        }
        try {
            int uniqueWords = 0;
            int mostTimes = 0;
            String mostCommen = "";

            // read the file line by line
            while (ec.hasNext())
            {
                 int value = 1;
                String[] ss = ec.getLine();
                String key = ss[0];
               
                //if that key isnt in the map, add it
                if(probe.containsKey(key)==false)
                {
                    uniqueWords++;
                    probe.put(key,value);
                    keychain.add(key);
                }
                //if it is, chnage the value to be that firsrt value plus one
                else
                {
                    value = probe.get(key);
                    value++;
                    probe.put(key,value);

                }
                    
              
            }

            for(int i = 0; i< uniqueWords; i++)
            {
                String key = keychain.get(i);
                int val = probe.get(key);
                
                if (val>mostTimes)
                {
                    mostCommen = key;
                    mostTimes = val;
                }
            }

            //print table
            //System.out.println(map.toString());
            System.out.println("Unique Words: " + uniqueWords);
            System.out.println("Most commen word: " + mostCommen);
            System.out.println(mostCommen + " occurred " + mostTimes + " times");
        } catch (IOException ioe) {
            System.err.println("Problem while reading file " + ioe);
        }
        long finish = System.nanoTime();
        System.out.println("Time taken: " + (double) (finish - start) / NANOS_SEC);   
        }
    }

}
