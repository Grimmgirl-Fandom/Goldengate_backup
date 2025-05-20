import java.util.Scanner;  // Import the Scanner class
    import java.util.ArrayList;

public class ParseLine{

public ParseLine(){}


//assuming all guidlines are followed
public void findVariables(String str){

    char[] myLine = str.toCharArray();
    if(myLine.length>2){
        String builder = ""+myLine[0]+myLine[1]+myLine[2];
        if(builder.equals("int")){
            //now we've establlished there IS a declaration
            String printer="";

            for(int i=4;i<myLine.length-2;i++){
                String mySubString="";
                String comp = ""+myLine[i];
                if(comp.equals(","))
                    {
                   mySubString =", ";
                    i++;
                    }
                else if(comp.equals("+"))
                    i+=3;
                else if(comp.equals("="))
                    i+=3;
                else
                    mySubString=""+myLine[i];
            printer+= mySubString;
            }

            System.out.println(printer);
        }
    }
}


public String GetParsedLine(String str){

    String var ="";
    char[] myLine = str.toCharArray();
    if(myLine.length>2){
        String builder = ""+myLine[0]+myLine[1]+myLine[2];
        if(builder.equals("int")){
            //now we've establlished there IS a declaration
            String printer="";

            for(int i=4;i<myLine.length-2;i++){
                String mySubString="";
                String comp = ""+myLine[i];
                if(comp.equals(","))
                    {
                   mySubString =", ";
                    i++;
                    }
                else if(comp.equals("+"))
                    i+=3;
                else if(comp.equals("="))
                    i+=3;
                else
                    mySubString=""+myLine[i];
            printer+= mySubString;
            }
            var = printer;
        }
    }
    return var;
}



 public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in); 
    ParseLine runner = new ParseLine();

    System.out.println("Enter Line");
    String line = keyboard.nextLine(); 
    runner.findVariables(line);
 }
}
