import java.util.ArrayList;
import java.io.FileOutputStream;
import java.util.Hashtable;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors


//https://www.w3schools.com/java/java_files_create.asp

public class Compiler2{
    public CreateSymbolTable theTable;
    public Hashtable<String,Integer> symbolTable;
    public String fileName;
    public GGReader reader;
    public ParseLine pl;
    public ArrayList<String> contents;
    public ErrorHandling checker;
    public FileWriter output;
    
    public Compiler2(String f){

    checker = new ErrorHandling();
    theTable = new CreateSymbolTable();
    fileName = f;
    pl = new ParseLine();
    reader= new GGReader();
    contents = reader.readFile(fileName);
    symbolTable = theTable.PST(fileName);
    try{
   // output = new FileWriter("multiOutput.txt");

    

    
    for(int i=1;i<contents.size();i++){

        String currentLine = contents.get(i);
        
        
        if(multi(currentLine)==true){
            ArrayList<String> mini = splitter(currentLine);
            contents.remove(i);

             for(int j=0;j<mini.size();j++){
                   String now = mini.get(j);
                   contents.add(i+j,now);
                    }
        }}
   
        symbolTable = theTable.rePopulate(contents);
        if(checker.allClear(contents,symbolTable)==false){}
        else
        {
             output = new FileWriter("testDump.txt");
    
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
    boolean checks = line.substring(0,3).equals("int");
        return checks;
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


public boolean multi(String line){
    int in = line.indexOf(",");
     return (in>-1);
}

public ArrayList<String> splitter(String line){
    ArrayList<String> split = new ArrayList<String>();

    int splitSpot = line.indexOf(",");
    if(line.length()>3){
    String one ="";
    String two="";
        if(line.substring(0,3).equals("int"))//im going to actully asume this is try im very tired and is true for all examples given
        {
            one+="int ";
            two+="int ";

            one+=line.substring(4,splitSpot-1)+" ;";
            two+=line.substring(splitSpot+2);
            split.add(one);
            split.add(two);
        }   
    }
    return split;

}



public static void main(String[] args) {
if(args.length==0)
    System.err.println("File not entered");
else{
String file = args[0];

Compiler2 compile = new Compiler2(file);
 



}

}
    
}
