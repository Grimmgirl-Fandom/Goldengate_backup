import java.util.ArrayList;
import java.util.Hashtable;
public class ErrorHandling{

    public ErrorHandling(){
    }

    //true means there is a double declaration
    //FIX
    public boolean doubleTrouble(String line,ArrayList<String> stuff,int ln){

        int end = line.indexOf("=");
        String checker = line.substring(0,end);
        String holder = "";

        for(int i = ln-1;i>-1;i--){
            holder = stuff.get(i);
            if(holder.indexOf(checker)>-1)
                return true;
        }

        return false;
    }
      


    //true means there is an undeclared
    public boolean undeclared(String line,Hashtable<String,Integer> table){
        String tok = firstTokin(line);
        //not int and not in table
        if(isIntStart(line)==false){
            if(notInTable(tok,table)==true)
            return true;
            else{
                ArrayList<String> others = sideVar(line);
                for(int i = 0;i<others.size();i++)
                {
                if(notInTable(others.get(i),table)==true)
                    return true;
                }


            }
            }

        return false;
    }

    //only works if longer than 3 length which it should be
    public boolean isIntStart(String line){
        String front = line.substring(0,3);
       if(front.equals("int"))
            return true;
        return false;
    }

    public String firstTokin(String line){
        String str = "";
        String subby = line;
        int spaceSpot = subby.indexOf(" ");
        str = subby.substring(0,spaceSpot);

        return str;


    }
    public boolean notInTable(String tokin,Hashtable<String,Integer> table){
            if(table.containsKey(tokin)==true||table.containsKey(tokin+" ")==true)
                return false;

        return true;
    }

        public boolean inTable(String tokin,Hashtable<String,Integer> table){
            if(table.containsKey(tokin)==true||table.containsKey(tokin+" ")==true)
                return true;

        return false;
    }

    public ArrayList<String> sideVar(String line){
    ArrayList<String> vars = new ArrayList<String>();

    String adder = "";

    String subby = line;
    int spaceSpot = subby.indexOf(" ");

    adder = subby.substring(0,spaceSpot);
    vars.add(adder);
    if(subby.length()>spaceSpot+3){
    
    while(subby.length()>3){
    subby=subby.substring(spaceSpot+3);
    spaceSpot = subby.indexOf(" ");

    adder = subby.substring(0,spaceSpot);
    vars.add(adder);
    }
    }
    return vars;

}

   


public boolean allClear(ArrayList<String> content,Hashtable<String,Integer> table){
    boolean bol = true;
    String line ="";
    ArrayList<String> beenDeclared = new ArrayList<String>();

    //not including header
    for(int lineNumber = 1;lineNumber<content.size()-2;lineNumber++){
        boolean hasError = false;
        line = content.get(lineNumber);

        if(undeclared(line,table)==true){
            hasError=true;
        }
        else if(isIntStart(line)==true)
        {
            if(doubleTrouble(line,content,lineNumber)==true)
                hasError=true;
        }

        if(hasError==true){
            int spot = lineNumber+1;
            bol = false;
        System.out.println("illegal line "+spot+": "+line);
        }
    }
    return bol;
}



}
