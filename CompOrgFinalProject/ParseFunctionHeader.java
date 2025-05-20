import java.util.Scanner;  // Import the Scanner class
    import java.util.ArrayList;

public class ParseFunctionHeader{

public ParseFunctionHeader(){}


//assuming all guidlines are followed
public void parseHeader(String str){

char[] theHeader = str.toCharArray();
int size = theHeader.length;
int starterIndex=-1;
int stopSpot; 

//the parameters start with (, so step one is to find that spot

for(int i = 0; i<size;i++){
    String holder = ""+theHeader[i];
    if(holder.equals("(")){
        starterIndex = i;
        break;
    }
}
//and we know if following the criteria
//since each has 1 space inbeweetn
//the distence to the end from the last char we care about is 4
if(size-4<0)
    stopSpot = -1;
else stopSpot = size-4;

String printer="";
String intTester=" int ";

for(int i = starterIndex+6;i<stopSpot;i+=3){

    String holder = ""+theHeader[i];
    String intBuilder =""+theHeader[i-1]+theHeader[i]+theHeader[i+1]+theHeader[i+2]+theHeader[i+3];
    if(intTester.equals(intBuilder))
        {i+=1;}
    else{
    while(true){
    String spot =""+theHeader[i];
        if(spot.equals(" "))
        {
            printer=printer+" ";
                break;
            }
        
        printer=printer+theHeader[i];
        i++;
    }
    }
}

System.out.println(printer);
}


public ArrayList<String> parseHeaderList(String str){
    ArrayList<String> list = new ArrayList<String>();


char[] theHeader = str.toCharArray();
int size = theHeader.length;
int starterIndex=-1;
int stopSpot; 

for(int i = 0; i<size;i++){
    String holder = ""+theHeader[i];
    if(holder.equals("(")){
        starterIndex = i;
        break;
    }
}

if(size-4<0)
    stopSpot = -1;
else stopSpot = size-4;

String adder="";
String intTester=" int ";

for(int i = starterIndex+6;i<stopSpot;i+=3){

    String holder = ""+theHeader[i];
    String intBuilder =""+theHeader[i-1]+theHeader[i]+theHeader[i+1]+theHeader[i+2]+theHeader[i+3];
    if(intTester.equals(intBuilder))
        {i+=1;}
    else{
    while(true){
    String spot =""+theHeader[i];
        if(spot.equals(" "))
        {
            list.add(adder.trim());
            adder ="";
                break;
            }
        
        adder=adder+theHeader[i];
        i++;
    }
    }

}
    return list;

}

 public static void main(String[] args) {
    //Scanner keyboard = new Scanner(System.in); 
    //ParseFunctionHeader runner = new ParseFunctionHeader();

    //System.out.println("Enter function header with no space after {");
   // String header = keyboard.nextLine(); 
   // runner.parseHeader(header);
 }
}
