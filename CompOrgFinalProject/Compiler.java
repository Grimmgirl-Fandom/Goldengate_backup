import java.util.ArrayList;
import java.io.FileOutputStream;
import java.util.Hashtable;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors


//https://www.w3schools.com/java/java_files_create.asp

public class Compiler{
    public CreateSymbolTable theTable;
    public Hashtable<String,Integer> symbolTable;
    public String fileName;
    public GGReader reader;
    public ParseLine pl;
    public ArrayList<String> contents;
    public FileWriter output;
    
    public Compiler(String f){

    theTable = new CreateSymbolTable();
    fileName = f;
    pl = new ParseLine();
    reader= new GGReader();
    contents = reader.readFile(fileName);
    symbolTable = theTable.PST(fileName);
    try{
    output = new FileWriter("sampleOutput.txt");
    

    
    for(int i=1;i<contents.size();i++){

        String currentLine = contents.get(i);

        if(simpleDeclaration(currentLine)==true){

        }
        else if(assignment(currentLine)==true){
          evaluateAssignment(currentLine);
        }
        else if(initialization(currentLine)==true){
            evaluateInitialization(currentLine);

        }
        else if(returnStatement(currentLine)==true){
            evaluateReturn(currentLine);

        }

    }

    output.close();
}
    catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

    }


private boolean hasEqual(String line){
    return (line.indexOf("=")>-1);
}

private boolean startInt(String line){
    int siz = line.length();
    if(siz>3){
    boolean checker = line.substring(0,3).equals("int");
        return checker;
    }
    return false;
}

    //statement type
public boolean simpleDeclaration(String line){
    return (hasEqual(line)==false&&startInt(line)==true);
}

public boolean assignment(String line){
    return (hasEqual(line)==true&&startInt(line)==false);
}

/*public void evaluateAssignment(String line){

//works if the form x=y+z

    System.out.println(";;"+line);



    int equ = line.indexOf("=");
    int rejNum = 0;


    String front  = line.substring(0,equ);



    String subby = line.substring(equ+2);
    int spaceSpot = subby.indexOf(" ");

    String firstTokin = subby.substring(0,spaceSpot);
    int firstOffset = symbolTable.get(firstTokin);

    //read first
    System.out.println(ldr(firstTokin,rejNum));

    rejNum++;    

        //clear next spot
    System.out.println(andClear(rejNum));

    subby=subby.substring(spaceSpot+1);
    spaceSpot = subby.indexOf(" ");


    //add second to first
        //ONLY IF YOU KNOW YOU ARE ADDING 2 THINGS
        String secondTokin = subby.substring(2,subby.length()-2);

    //more than two
    //String secondTokin = subby.substring(2,spaceSpot);

    System.out.println(add(secondTokin,rejNum,rejNum));

    //put sum in
    System.out.println(add("R"+rejNum,rejNum-1,rejNum-1));

    //write
    int os = (int)symbolTable.get(front);
    System.out.println(str(os,rejNum-1));


   System.out.println();

}
*/

//second half to end
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


public void evaluateReturn(String line){

try{
    System.out.println(";;"+line);
    output.write(";;"+line+"\r");


    int rejNum = 0;

    //after return and space
    String subby = line.substring(7);

    ArrayList<String> list = sideVar(subby);

    int spaceSpot = subby.indexOf(" ");

    String firstTokin = subby.substring(0,spaceSpot);
    int firstOffset = symbolTable.get(firstTokin+" ");
        System.out.println(ldr(firstTokin+" ",rejNum));
        output.write(ldr(firstTokin+" ",rejNum)+"\r");



    for(int i =1;i<list.size();i++){
    String nextTokin =list.get(i);
    rejNum++;    
    System.out.println(ldr(nextTokin+" ",rejNum));
    output.write(ldr(nextTokin+" ",rejNum)+"\r");
      //put sum in
    System.out.println(add("R"+rejNum,rejNum-1,rejNum-1));
    output.write(add("R"+rejNum,rejNum-1,rejNum-1)+"\r");

    }
  
    //write
    System.out.println(str(3,0));
    output.write(str(3,0)+"\r");

}
catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }


   System.out.println();


}

public String ldr(String tokin,int rejNumber){
    int val;

try {
    val = symbolTable.get(tokin);}
catch(Exception e) {
    val = symbolTable.get(tokin+" ");
}

    return "LDR R"+rejNumber+", FP, "+val;
}


public void evaluateInitialization(String line){
    try{


    System.out.println(";;"+line);
    output.write(";;"+line+"\r");

    int equ = line.indexOf("=");

    int rejNum = 0;

    //after return and space
    String subby = line.substring(equ+2);
    String frontSub = line.substring(4,equ);

    ArrayList<String> list = sideVar(subby);

    int spaceSpot = subby.indexOf(" ");

    String firstTokin = list.get(0);
    int frontOffset = symbolTable.get(frontSub);

        if(list.size()==1){
            System.out.println(andClear(rejNum));
            output.write(andClear(rejNum)+"\r");
            System.out.println(add(firstTokin,rejNum,rejNum));
            output.write(add(firstTokin,rejNum,rejNum)+"\r");
            System.out.println(str(frontOffset,rejNum));
            output.write(str(frontOffset,rejNum)+"\r");

        }
        else{
          
        System.out.println(ldr(firstTokin,rejNum));
        output.write(ldr(firstTokin,rejNum)+"\r");


    for(int i =1;i<list.size();i++){
    String nextTokin =list.get(i);
    rejNum++;    
    System.out.println(ldr(nextTokin,rejNum));
    output.write(ldr(nextTokin,rejNum)+"\r");
   //put sum in
    System.out.println(add("R"+rejNum,rejNum-1,rejNum-1));
    output.write(add("R"+rejNum,rejNum-1,rejNum-1)+"\r");

    }
    
    //write
    System.out.println(str(frontOffset,0));
    output.write(str(frontOffset,0)+"\r");
        }
    }
    catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }


   System.out.println();


}

public void evaluateAssignment(String line){
    try{


    System.out.println(";;"+line);
    output.write(";;"+line+"\r");

    int equ = line.indexOf("=");

    int rejNum = 0;

    //after return and space
    String subby = line.substring(equ+2);
    int spaceSpot = subby.indexOf(" ");
    String frontSub = line.substring(0,spaceSpot);

    ArrayList<String> list = sideVar(subby);


    String firstTokin = list.get(0);
    int frontOffset;

    try {
    frontOffset = symbolTable.get(frontSub);
    }
    catch(Exception e) {
    frontOffset = symbolTable.get(frontSub+" ");
    }

    

        if(list.size()==1){
            System.out.println(andClear(rejNum));
            output.write((andClear(rejNum))+"\r");
            System.out.println(add(firstTokin,rejNum,rejNum));
            output.write(add(firstTokin,rejNum,rejNum)+"\r");
            System.out.println(str(frontOffset,rejNum));
            output.write(str(frontOffset,rejNum)+"\r");

        }
        else{
          
        System.out.println(ldr(firstTokin,rejNum));
        output.write(ldr(firstTokin,rejNum)+"\r");

    for(int i =1;i<list.size();i++){
    String nextTokin =list.get(i);
    rejNum++;    
    System.out.println(andClear(rejNum));
    output.write(andClear(rejNum)+"\r");
    System.out.println(add(nextTokin,rejNum,rejNum));
    output.write(add(nextTokin,rejNum,rejNum)+"\r");

   //put sum in
   output.write(add("R"+rejNum,rejNum-1,rejNum-1)+"\r");
    System.out.println(add("R"+rejNum,rejNum-1,rejNum-1));

    }
    
    //write
    System.out.println(str(frontOffset,0));
    output.write(str(frontOffset,0)+"\r");
        }
    }
    catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }


   System.out.println();
}


public String and(String tokin,int rejNumber){
    return "";
}

public String andClear(int rejNumber){
    return "AND R"+rejNumber+", R"+rejNumber+", 0";
}

public String add(String tokin,int rejNumber,int dest){

    return "ADD R"+dest+", R"+rejNumber+", "+tokin;
}


public String str(int offSet,int rejNumber){
    return "STR R"+rejNumber+", FP, "+offSet;
}


public boolean initialization(String line){
    return (hasEqual(line)==true&&startInt(line)==true);
}

public boolean returnStatement(String line){
      int spot = line.indexOf("return");
    return spot>-1;
}



public static void main(String[] args) {
if(args.length==0)
    System.err.println("File not entered");
else{
String file = args[0];

Compiler compile = new Compiler(file);
 



}

}
    
}
