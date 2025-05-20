import java.io.IOException;
import java.util.ArrayList;

public class Reduction {

     String input;
     int rows;

    SepChainHT<String,Integer> dictionary;



    ArrayList<String> keychain;

    

    public Reduction(String s) 
    {

        ReadCSV ec;
        try {
            ec = new ReadCSV("https://cs.brynmawr.edu/cs151/Data/Hw6/words.txt",1);
            while(ec.hasNext())
            {
            String[] ss = ec.getLine();
            String key = ss[0];
            dictionary.put(key, 1);
            }
        }
         catch (IOException e1) {

        }
         
        
        input = s;
        rows = input.length()-1;
    }

    
    
    //if string is null, returns false

    protected boolean isSolved(String s)
    {
        return s.length()==2&&isInEnglish(s)==true;
    }

    public boolean solve(boolean first)
    {
        int val;
        //first row base
        if(first==true)
        {
         val = row(input,0);
            if(val==0)
                return false;
         if(isSolved(keychain.get(0))==true) 
            return true;      
        else
            solve(false);
        }
        else if(keychain.size()==0)
                return false;
            else if(isSolved(keychain.get(0))==true)
                    return true;
            else
            {
                val = row(keychain.get(0),0);
                 if( val == 0)
                 {
                     keychain.remove(0);
                     solve(false);
                 }

            }
            return false;
            

        
    }

    //if a row has any that can be deduced, the row value will increase, if a row value returns 0, its automaticly false,
    public int row(String word,int col)
    {

        if(col<word.length())
        {
        if(isInEnglish(removeNchar(col,word))==true)
        {
            keychain.add(removeNchar(col,word));

            return 1 + row(word,col + 1);
        }
        return row(word,col + 1);
        }
        else if(col==word.length())
        {
        if(isInEnglish(removeNchar(col,word))==true)
        {
            keychain.add(removeNchar(col,word));
            return 1;
        }
        return 0;
        }
        return 0;
    }




   public String toString()
   {
       return input + ":" + solve(true);
   }
    

    boolean isInEnglish(String aString)
    {
        return dictionary.containsKey(aString);
    }

    	/*
		* Remove the nth letter from a string
		* @param n the index of the char to remove
		* @param s the string to remove from
		* @return a string, one letter shorter.  if n is 
		* out of bounds, just return the string without its last letter
		*/
    String removeNchar(int n, String s) {
        if (s==null) { return s; }
        if (n>0 && n<s.length()-1) {
            return s.substring(0,n)+s.substring(n+1);
        }
        if (n==0)
            return s.substring(1);
        return s.substring(0, s.length()-1);
    }

    public static void main(String[] args) throws IOException {
        Reduction red = new Reduction("core");
        System.out.println(red);



    }
}


