import java.io.IOException;
import java.util.ArrayList;

public class BabyNames{

    

    public SingleLinkedList<Baby> list;


    public BabyNames(ArrayList<Integer> years)
    {
        list = new SingleLinkedList<Baby>();

        //for some reason it wasnt adding anything past emily, so there were no "A" names,
        //I tried adding one here and then the rest appered
        //its an odd little bug but this seemed to fix it for whatever reason

        list.addSorted(new Baby("Aaron"));
        for(int i = 0; i<years.size();i++)
        {
            readYears(years.get(i));
        }
    }

    public void readYears(int year)
    {

        String file = "https://cs.brynmawr.edu/cs151/Data/Hw8/NN" + year +".csv";

        ReadCSV ec;
        try 
        {
            // get a new instance of the class -- through a static maker.
            ec = new ReadCSV(file, 4);
        }
        catch (IOException ioe) 
        {
            System.err.println("Ending. Cannot read. " + ioe.toString());
            return;
        }
        try 
        {
         
            String name ="";
            int num = 0;
            // read the file line by line
            while (ec.hasNext()) 
            {

                String[] s =ec.getLine();
                name = s[0];
                name = name.toLowerCase();
                num = Integer.parseInt(s[1]);
                Baby babby = new Baby(name);
                
               
                if(list.head==null) {
                    babby.addAppears(1);
                    babby.addBabyNumber(num);
                    list.addSorted(babby);//add baby
                } else if(list.contains(babby)) //baby is already in list
                //not sure if this is write
                {
                    Baby oldBaby = list.find(babby);
                    oldBaby.addAppears(1);
                    oldBaby.addBabyNumber(num);

                }
                else
                {  
                    babby.addAppears(1);
                    babby.addBabyNumber(num);
                    list.addSorted(babby);//add baby
                }

               
               
                  
            }
               
        }
         catch (IOException ioe)
        {
            System.err.println("Problem while reading file " + ioe);
        }
    }



     //void?
     public void nameLookUp(String name)
     {
         Baby baby = new  Baby(name.toLowerCase());
         if(name.equals("q"))
         {
             System.out.print("Goodbye");
            System.exit(0);
         }
            
        else
        {
            if(list.contains(baby)==true)
            {
                Baby oldBaby = list.find(baby);
                System.out.println("Appears: " + oldBaby.appears);
                System.out.println("Frequency: " + oldBaby.babyNumber);
                System.out.println("Spot in list: " + list.findPos(oldBaby));
            }
            if(list.contains(baby)==false)
            {
                System.out.println(name+ " not found");
            }

        }
         

     }
     
    public static void main(String[] args) {

        ArrayList<Integer> input = new ArrayList<>();
        input.add(2000);
        input.add(2001);
        input.add(2002);

        BabyNames test = new BabyNames(input);

        test.nameLookUp("Aaron");

    }
    
}
