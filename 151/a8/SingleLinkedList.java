/**
 * Implementation of the LinkedListInterface
 * For all public methods, see documentation in LinkedListInterface
 * 
 * @author gtowell
 * Created: April 2021
 * Modified: April 2022
 * @param <J>
 */
public class SingleLinkedList<J> implements LinkedListInterface<J> {
        
    protected class Node<H> 
    {
	    public H data;
	    public Node<H> next;
	    public Node(H data) 
	    {
            this.data = data;
            this.next = null;
	    }	
    }   

    protected Node<J> head = null;
    
    public int size() {
        int siz = 0;
        Node<J> n = head;
        while (n != null) {
            siz++;
            n = n.next;
        }
        return siz;
    }

    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public J first() {
        return head.data;
    }

    private Node<J> lastNode() {
        Node<J> n = head;
        if (n == null)
            return null;
        while (n.next != null) {
            n = n.next;
        }
        return n;
    }

    @Override
    public J last() {
        Node<J> n = lastNode();
        if (n == null)
            return null;
        return n.data;
    }
    
    @Override
    public void addLast(J c) {
        Node<J> n = lastNode();
        Node<J> newnode = new Node<>(c);
        if (n == null) {
            head = newnode;
            return;
        }
        n.next = newnode;
    }

    @Override
    public void addFirst(J c) {
        Node<J> newnode = new Node<>(c);
        if (head == null) {
            head = newnode;
            return;
        }
        newnode.next = head;
        head = newnode;
    }

    @Override
    public J removeFirst() {
        if (head == null)
            return null;
        Node<J> tmp = head;
        head = head.next;
        return tmp.data;
    }


    public J removeLast() {
        if (head == null)
            return null;
        if (head.next == null) {
            // only one item in list
            J tmp = head.data;
            head = null;
            return tmp;
        }
        Node<J> curr = head;
        while (curr.next.next != null) {
            curr = curr.next;
        }
        J tmp = curr.next.data;
        curr.next = null;
        return tmp;
    }

    @Override
    public boolean remove(J r) {
        if (head == null)
            return false;
        Node<J> prev = null;
        Node<J> here = head;
        while (here.next != null) {
            if (here == r)
                break;
            prev = here;
            here = here.next;
        }
        if (here.data == r) {
            if (prev == null)
                head = head.next;
            else
                prev.next = here.next;
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        Node<J> node = head;
        while (node != null) {
            sb.append(node.data.toString());
            sb.append("\n");
            node = node.next;
        }
        return sb.toString();
    }
    
   /*  boolean contains(String r)
    {
        if(isEmpty()==true)
            return false;

            Node<J> n = head;
            while (n != null) 
            {
                if(n.data.equals(r))
                    return true;
                else
                    n = n.next;
            }

        return false;
    }
*/
   public boolean contains(J r)
    {
        if(isEmpty()==true)
            return false;

            Node<J> n = head;
            while (n != null) 
            {
                if(n.data.toString().equals(r.toString()))
                    return true;
                else
                    n = n.next;
            }

        return false;
    }

    boolean addSorted(Comparable<J> r)
    {
        Node<J> n = head;
        if(isEmpty()==true)
        {
            addFirst((J)r);
            return true;
        }

        if(head.next==null)
        {
            if(r.compareTo(head.data)>0)
                addFirst((J)r);
            else
                addLast((J)r);
            return true;
        }    

        if(r.compareTo(head.data)>0)
        {
            addFirst((J)r);
            return true;
        }
            while (n.next != null) 
            {
                if(n.next.data==null)
                {
                    addLast((J)r);
                    return true;
                }
                if(r.compareTo(n.next.data)>0)
                {
                    Node<J> itR = new Node<>((J)r);
                    Node<J> holder = n.next;
                    n.next = itR;
                    (n.next).next = holder;
                    return true;
                }
                else
                    n = n.next;
            }
        return true;
    }
    public int findPos(J r) {
        int spot = 0;
        if(contains(r)==true)
      {
        Node<J> n = head;
            while (n != null) 
            {
                if(n.data.toString().equals(r.toString()))
                    return spot;
                else
                {
                    n = n.next;
                    spot++;

                } 
        }
    }
        return -1;
    }
    public J find(J r) {

        if(contains(r)==true)
      {
        Node<J> n = head;
            while (n != null) 
            {
                if(n.data.toString().equals(r.toString()))
                    return n.data;
                else
                    n = n.next;
        }
    }

        return null;
    }
}
