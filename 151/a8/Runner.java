import java.util.ArrayList;
import java.util.Scanner;

public class Runner{


    public static void main(String[] args) {
        ArrayList<Integer> inputs = new ArrayList<>();

        if(args.length>0)
        {
            for(int i = 0; i<args.length; i++)
            {
                inputs.add(Integer.parseInt(args[i]));
            }
        }
        
        BabyNames test = new BabyNames(inputs);

        while(true)
        {
            Scanner sc = new Scanner(System.in);
            System.out.println();
            System.out.println("Enter a name to search or enter 'q' to quit");
            System.out.println();
            String name = sc.nextLine(); 
            test.nameLookUp(name);

        }

       


    }
}
