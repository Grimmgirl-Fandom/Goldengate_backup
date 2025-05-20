import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * A fairly basic implementation of a separate chanining hashtable
 * @param <K> the tpe of key
 * @param <V> the type of value
 * Implements full separate chaining, but not rehashing.
 * Similarly, the size of the underlying table, once it is created, cannot
 * be changed.
 * @author gtowell
 * Created: April 25, 2020
 * Modified: Sep 23, 2020 to use ArrayList
 * Modeifed: Mar 6, 2021 to use Map206
 * Modified: Sep 27, 2021 to use Map151Interface
 */
public class SepChainHT<K,V> implements Map151Interface<K,V> {

    


    /** The array holding the hashtable data.  Yes, this is an array
     * of Map151 objects!!
     */
    private Map151<K,V>[] backingArray;

    /** The default size of the backing array */
    private static int DEFAULT_CAPACITY = 1009;

    /** The number of items in the hashtable */
    private int count;

    /** Default initialization */
    public SepChainHT() {
        this(DEFAULT_CAPACITY);
    }
    /**
     * Initialize a hashtable of the given size
     * @param size the size of the hashtable to create
     */
    @SuppressWarnings("unchecked")
    public SepChainHT(int size) {
        // Cannot make an array object in which you mention a parameterized type.
        // So just make the generic array.  This is a narrowing cast so it does not 
        // even need to be explicit.
        count = 0;
        backingArray = new Map151[size];
    }

    public BigInteger objectHasher(Object ob) {
        return stringHasher(ob.toString());
    }
    /**
     * Implemets Horner's on strings.
     * Since every object can be translated into a string This can be run
     * on an arbitrary object with no loss of generality.
     * @param ss the string to generate a hash value for
     * @return the hash value
     */
    public BigInteger stringHasher(String ss) {
        BigInteger mul = BigInteger.valueOf(23);
        BigInteger ll = BigInteger.valueOf(0);
        for (int i=0; i<ss.length(); i++) {
            ll = ll.multiply(mul);
            ll = ll.add(BigInteger.valueOf(ss.charAt(i)));
        }
        return ll;
    }

    private int h(K k) {
        return objectHasher(k).mod(BigInteger.valueOf(backingArray.length)).intValue();
    }

    /**
     * Add a key-value pair to the hashtable.  If the key is already in the
     * hashtable, then the old value is replaced.  Otherwise this adds a 
     * new key-value pair
     * @param key the key
     * @param value the value
     */
    @Override
    public void put(K key, V value) {
        int loc = h(key);
        if (backingArray[loc] == null) {
            backingArray[loc] = new Map151<>();
        }
        if (!backingArray[loc].containsKey(key)) {
            count++;
        }
        backingArray[loc].put(key, value);
    }

    /**
     * Get the value stored in the hashtable given the key.
     * @param key the key 
     * @return the value associated with the key
     */
    @Override
    public V get(K key) {
        int loc = h(key);
        if (backingArray[loc]==null) {
            return null;
        }
        return backingArray[loc].get(key);
    }

    /**
     * The number of distinct keys in the hshtable.
     * @return The number of distinct keys in the hashtable
     */
    @Override
    public int size() {
        return count;
    }


    @Override
    public boolean containsKey(K key) {
        int loc = h(key);
        if (backingArray[loc] == null) {
            return false;
        }
        return backingArray[loc].containsKey(key);
    }

    @Override
    public Set<K> keySet() {
        TreeSet<K> set = new TreeSet<>();
        for (int i = 0; i < backingArray.length; i++) {
            if (backingArray[i] != null)
                set.addAll(backingArray[i].keySet());
        }
        return set;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < backingArray.length; i++) {
            if (backingArray[i] != null) {
                sb.append(i);
                sb.append(" ");
                sb.append(backingArray[i].toString());
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        /** 
        SepChainHT<Integer, String> scht = new SepChainHT<>(11);
        for (int i = 0; i < 25; i++) {
            scht.put(i, String.format("%c", ('a' + i)));
        }
        System.out.println(scht);
        */
        final int NANOS_SEC = 1000000000; // nanosec per sec
        {
            
                long start = System.nanoTime();
                
        ArrayList<String> keychain = new ArrayList<>();
        SepChainHT<String,Integer> sep = new SepChainHT<>();
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
                if(sep.containsKey(key)==false)
                {
                    uniqueWords++;
                    sep.put(key,value);
                    keychain.add(key);
                }
                //if it is, chnage the value to be that firsrt value plus one
                else
                {
                    value = sep.get(key);
                    value++;
                    sep.put(key,value);

                }
                    
              
            }

            for(int oh = 0; oh< uniqueWords; oh++)
            {
                String key = keychain.get(oh);
                int val = sep.get(key);
                
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
