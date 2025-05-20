import java.util.ArrayList;
import java.util.Arrays;


public class ErrorChecker{

public ErrorChecker(){}

public boolean semiColonEnd(char[] line){

    int end = line.length-1;
    String comp = ""+line[end];
    return comp.equals(";");
}

public boolean badType(char[] line){
boolean bol = false;

return bol;


}

public boolean hasSpace(String line){
boolean bol = false;

if(line.length()>3){
String three ="";
three = line.substring(0,3);
if(three.equals("int")){
     int spot =line.indexOf(' ');
    String newLine = line.substring(spot+1);
    int nextSpot = newLine.indexOf(' ');
    String comp = newLine.charAt(nextSpot+1)+"";
    if(comp.equals("="))
    {

    }
    else bol = true;
}

else{
 int spot =line.indexOf(' ');
 String comp = line.charAt(spot+1)+"";
    if(comp.equals("="))
    {

    }
    else bol = true;
}

}
return bol;

}

public boolean firstEqual(char[] line){

    String comp = ""+line[0];
    return comp.equals("=");
}

public boolean needsThing(String line){
    boolean bol =false;
   // int num = 61;
    //char eq = (char)num;

   // BinarySearch ob = new BinarySearch();
   int spot =line.indexOf("=");
    if(spot>-1)
    {

    String comp =""+line.charAt(spot+2);
        if(comp.equals("+")||comp.equals("-")||comp.equals("=")||comp.equals("/")||comp.equals("*")||comp.equals("%"))
            bol = true;
    }

return bol;
}

public boolean needsOp(char[] line){
    boolean bol = false;

    String comp = ""+line[line.length-3];

    if(comp.equals("+")||comp.equals("-")||comp.equals("=")||comp.equals("/")||comp.equals("*")||comp.equals("%"))
        bol = true;


    return bol;
}

public boolean allTests(ArrayList<String> stuff){
    boolean bol = true;
    String line ="";
    String errType="";

    //not including header
    for(int lineNumber = 1;lineNumber<stuff.size()-1;lineNumber++){
        boolean hasError = false;
        line = stuff.get(lineNumber);
        char[] ray = line.toCharArray();

        if(semiColonEnd(ray)==false){
            hasError=true;
            errType="semiColonEnd";
        }
        else if(firstEqual(ray)==true)
        {
            hasError=true;
            errType="FirstEqual";
        }
        else if(needsOp(ray)==true){
            hasError=true;
            errType="needsOp";
        }
        else if(needsThing(line)==true){
            hasError=true;
            errType="NeedsThing";
        }
        else if(badType(ray)==true){
            hasError=true;
            errType="badType";
        }
        else if(hasSpace(line)==true){
            hasError=true;
            errType="hasSpace";
        }
        


        if(hasError==true){
            int spot = lineNumber+1;
            bol = false;
        System.out.println("illegal line "+spot+": "+line);
        //System.out.println(errType);
        }
    }



    return bol;
}




}
