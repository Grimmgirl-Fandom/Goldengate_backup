/**
 * @author gtowell
 * Created April 6, 2020
 * Modified: April 12, 2021
 * Modified: April 4, 2022
 */
@SuppressWarnings("unchecked")
public class BinaryHeap<K extends Comparable<K>, V> extends AbstractPriorityQueue<K, V>
{

    //instence variables
	protected static final int CAPACITY = 1032;
	protected Pair<K,V>[] backArray;
    protected int size;

    //default constructor
	public BinaryHeap() {
        this(CAPACITY);
	}
	
    //constructor with capacity to set Pairs capacity
	public BinaryHeap(int capacity) {
        backArray = new Pair[capacity];
	}

    //returns the size
    @Override
    public int size()
    {
		return size;
    }

    //checks to see if there is anything in the backing array
    @Override
    public boolean isEmpty()
    {
		return size==0;
    }


    @Override
    public boolean offer(K key, V value)
	{
        //checks to see if there is room to add, if theres not return false
		if (size >= (backArray.length - 1))
			return false;
        //there is room so set loc to be the next free spot and put a new pair with the passed in values there    
		int loc = size++;
		backArray[loc] = new Pair<K, V>(key, value);
        //mid point holder
		int upp = (loc - 1) / 2;
        //as until the location is 0 or the beginning
		while (loc != 0) {
            //basicly this is restorying order to the heap
			if (0 > backArray[loc].compareTo(backArray[upp])) {
				Pair<K, V> tmp = backArray[upp];
				backArray[upp] = backArray[loc];
				backArray[loc] = tmp;
				loc = upp;
				upp = (loc - 1) / 2;
			} else {
				break;
			}
		}
		return true;
	}
	
	protected void removeTop()
    {
		size--;//decrease the size 
		backArray[0] = backArray[size];//take whatever is last and put it at the front
		backArray[size]=null;//set the spot that you just took the thing from at the end, make it null
		int parentLoc=0; //this is the root
		while (true) //keep going until theres a break
		{
	    	int bestChildLoc;//this is what we are looking for   
	    	int childLoc1 = parentLoc*2+1;// first branch from parent
	    	int childLoc2 = parentLoc*2+2;//second branch from parent 
	    	if (childLoc1>=size)//if there are no children 
				break;//exit while loop           
	    	if (childLoc2>=size)//if there is onle one child
	    	{
				bestChildLoc=childLoc1; //make that child the best
	    	}
	    	else//if there are two children
	    	{
				int cmp = backArray[childLoc1].compareTo(backArray[childLoc2]);//compaire the children
				if (cmp<=0)//if child one is better
		    		bestChildLoc=childLoc1;//make it the best child
				else
		    		bestChildLoc=childLoc2;//otherwise make child two the best
			}
			
	    	if (0 > backArray[bestChildLoc].compareTo(backArray[parentLoc]))//omg drama, compare the best child to the parent, if the child is beeter
	    	{
                //switch parent and best child by
				Pair<K,V> tmp = backArray[bestChildLoc];//tempary holder for the child
				backArray[bestChildLoc] = backArray[parentLoc];//the parent becomes the child
				backArray[parentLoc] = tmp;//the best child becomes the parent
				parentLoc=bestChildLoc;//set location
	    	}
	    	else {
				break;//exit while loop
	    	}
		}
    }

	@Override
    //if there is anything at the front, remove it then return it but also restore heap order
	public V poll() {
		if (isEmpty())
			return null;
		Pair<K,V> tmp = backArray[0];
		removeTop();
		return tmp.theV;
	}

	@Override
    //shows us the first thing if it exsists
	public V peek() {
		if (isEmpty())
			return null;
		return backArray[0].theV;
	}


	public static void main(String[] args) {
		BinaryHeap<Integer, String> pq = new BinaryHeap<>(CAPACITY);
		pq.offer(1, "Jane");
		pq.offer(10, "WET");
		pq.offer(5, "WAS");
		System.out.println(pq.poll());
		System.out.println(pq.poll());
		System.out.println(pq.poll());
		System.out.println();

		pq = new BinaryHeap<>(CAPACITY);
		pq.offer(2, "Jane");
		pq.offer(1, "WET");
		pq.offer(5, "WAS");
		System.out.println(pq.poll());
		System.out.println(pq.poll());
		System.out.println(pq.poll());
		
	}

}
