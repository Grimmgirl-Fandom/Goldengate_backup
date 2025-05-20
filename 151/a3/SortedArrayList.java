//Febuary 18th 2022
//Grace Swenson Hollis

public class SortedArrayList<Y> extends List151Impl<Y> {

public SortedArrayList(int i)
{
   super(100);
}

//grows the array
@SuppressWarnings("unchecked")
private void grow()
{
int size = arra.length;
size*=2;
Y[] grown = (Y[]) new Object[size];
for(int i = 0; i<arra.length; i++)
{
    grown[i] = arra[i]; 
}
arra=grown;

}

//adds an object
@Override
public boolean add(Y t) 
{
    
    if (count >= arra.length)
    {
        grow();
        return add(t);
    }
        else
        {
            if (count == 0)
                arra[0] = t;
    
    //sort
    int index = 0;
    int comp;
    for( int i = 0; i<arra.length; i++)
    {
       if(arra[i]==null) 
       {
           comp = 1;
       }
       else{
                comp = t.toString().compareTo(arra[i].toString());
       }
        if (comp>=0)
            index ++;
        else
            break;
    }
//move and shift
    Y tempHold = t;
    Y tempPlace;
    for( int i = index; i<arra.length; i++)
    {
        if(i == index)
            {
                tempHold = arra[i];
                arra[i] = t;
            }
        else
        {
            tempPlace = tempHold;
            tempHold = arra[i];
            arra[i] = tempPlace;
        }    

    }
    count++;
    return true;
        }
}



@Override
public boolean add(int index, Y t) throws IndexOutOfBoundsException
{
   throw new IndexOutOfBoundsException("This could break the sorting order");
}

//finds the index of a given input
@Override
public int indexOf(Y t) 
{

    int comp =t.toString().compareTo(arra[arra.length/2].toString());

    if(comp>0)
    {
        for(int i = arra.length/2; i<arra.length; i++)
        {
            if (arra[i].equals(t))
            return i;
        }
    }
    else if (comp<0)
    {
        for(int i = arra.length/2; i>=0; i--)
        {
            if (arra[i].equals(t))
            return i;
        }
    }
    else if(comp==0)
        return arra.length/2;

   
    return -1;
}

}
