import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * Implements the interface. A concrete implementaion of a map with all of the
 * ugly search
 * 
 * @author gtowell Created: Sep 21, 2020 Modified: Jul 2021
 */
public class Map151<K, V> implements Map151Interface<K, V> {

    /** The underlying data structure to actually do the storage */
    private ArrayList<Pair<K, V>> underlying = new ArrayList<>();

    /**
     * Holds key-value pairs together The only time it is OK (or even good) to have
     * public instance vars is in private classes. (Since they are not really public
     * anyway)
     */
    private class Pair<L, W> {
        // the key. Once set it canot be changed
        public final L key;
        // the value
        public W value;

        // Create a key value pair.
        Pair(L ky, W val) {
            key = ky;
            value = val;
        }
    }

    /**
     * Set a key value pair. Will overwrite value if key already present.
     * 
     * @param key the key
     * @param val the new value
     */
    public void put(K key, V val) {
        Pair<K, V> pair = iContainsKey(key);
        if (pair == null) {
            Pair<K, V> np = new Pair<>(key, val);
            underlying.add(np);
        } else {
            pair.value = val;
        }
    }

    /**
     * Get the value associated with a key
     * 
     * @param key the key
     * @return the associated value
     */
    public V get(K key) {
        Pair<K, V> pair = iContainsKey(key);
        if (pair != null)
            return pair.value;
        return null;
    }

    /**
     * does the Map contain the key?
     * 
     * @param key the key in question
     * @return true iff the key is in the map
     */
    public boolean containsKey(K key) {
        return null != iContainsKey(key);
    }

    private Pair<K, V> iContainsKey(K ky) {
        for (Pair<K, V> pair : underlying) {
            if (pair.key.equals(ky)) {
                return pair;
            }
        }
        return null;
    }

    /**
     * The number of items in the map
     * 
     * @return The number of items in the map
     */
    public int size() {
        return underlying.size();
    }

    /**
     * All of the keys in the map This method allows users of the map to see all of
     * the keys. That this returns a Set is to make this consistent with the Java
     * Map interface.
     * 
     * @return All of the keys in the map
     */
    public Set<K> keySet() {
        TreeSet<K> set = new TreeSet<>();
        for (Pair<K, V> pair : underlying) {
            set.add(pair.key);
        }
        return set;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (Pair<K, V> pair : underlying) {
            sb.append("[" + pair.key + ":" + pair.value + "]\n");
        }
        return sb.toString();
    }

public static void main(String[] args) {
    final int NANOS_SEC = 1000000000; // nanosec per sec
    {
        
            long start = System.nanoTime();

    ArrayList<String> keychain = new ArrayList<>();
    Map151<String,Integer> map = new Map151<>();
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
            if(map.containsKey(key)==false)
            {
                uniqueWords++;
                map.put(key,value);
                keychain.add(key);
            }
            //if it is, chnage the value to be that firsrt value plus one
            else
            {
                value = map.get(key);
                value++;
                map.put(key,value);

            }
                
          
        }

        for(int i = 0; i< uniqueWords; i++)
        {
            String key = keychain.get(i);
            int val = map.get(key);
            
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
