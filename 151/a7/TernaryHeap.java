public class TernaryHeap<K extends Comparable<K>, V> extends BinaryHeap<K,V>
{

    public TernaryHeap(int capacity)
    {
        super(capacity);

    }
    public TernaryHeap()
    {
        super();

    }

    @Override
    protected void removeTop()
    {
		size--;//decrease the size 
		backArray[0] = backArray[size];//take whatever is last and put it at the front
		backArray[size]=null;//set the spot that you just took the thing from at the end, make it null
		int parentLoc=0; //this is the root
		while (true) //keep going until theres a break
		{
	    	int bestChildLoc;//this is what we are looking for   
	    	int childLoc1 = parentLoc*3+1;// first branch from parent
	    	int childLoc2 = parentLoc*3+2;//second branch from parent 
            int childLoc3 = parentLoc*3+3;
            
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
                if(childLoc3<size)    
                {
                    cmp = backArray[bestChildLoc].compareTo(backArray[childLoc3]);
                    if (cmp>0)
                        bestChildLoc=childLoc3;
                }

			}
			
	    	if (0 > backArray[bestChildLoc].compareTo(backArray[parentLoc]))//omg drama, compare the best child to the parent, if the child is better
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
	public static void main(String[] args) {
      
		TernaryHeap<Integer, String> test = new TernaryHeap<>();

		

		//test one with the alphabet
		for(int i = 1; i<27; i++)
		{
			test.offer(i, (char)(96+i) +"");
		}

		//print out abcs in order while putting into new reverse heap
		TernaryHeap<Integer, String> reverseABC = new TernaryHeap<>(27);
		for(int i = 1; i<27; i++)
		{
			reverseABC.offer(27-i, test.peek());
			System.out.print(test.poll()+" ");
		}
		System.out.println();

		while(reverseABC.isEmpty()==false)
		{
			System.out.print(reverseABC.poll()+ " ");
		}
		System.out.println();


		//numbers test
		TernaryHeap<String, Integer> numbers = new TernaryHeap<>(11);
		numbers.offer("zero", 0);
		numbers.offer("one", 1);
		numbers.offer("two", 2);
		numbers.offer("three",3);
		numbers.offer("four", 4);
		numbers.offer("five", 5);
		numbers.offer("six", 6);
		numbers.offer("seven", 7);
		numbers.offer("eight", 8);
		numbers.offer("nine", 9);

		System.out.println("Numbers Ternary Heap");

		while(numbers.isEmpty()==false)
		{
			System.out.print(numbers.poll()+ " ");
		}
		System.out.println();
		BinaryHeap<String, Integer> biNum = new BinaryHeap<>(CAPACITY);
		biNum.offer("zero", 0);
		biNum.offer("one", 1);
		biNum.offer("two", 2);
		biNum.offer("three",3);
		biNum.offer("four", 4);
		biNum.offer("five", 5);
		biNum.offer("six", 6);
		biNum.offer("seven", 7);
		biNum.offer("eight", 8);
		biNum.offer("nine", 9);

		System.out.println("Numbes Ternary Heap");
		while(biNum.isEmpty()==false)
		{
			System.out.print(biNum.poll()+ " ");
		}





	



	
	}

}
