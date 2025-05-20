import java.util.ArrayList; // import the ArrayList class

public class WomenProblem{

    public UnitedStates us;
    private String start;
    private String end;
    private ArrayList<String> path;


    public WomenProblem(){
      end = "District of Columbia";
      start = "Washington";
      us = new UnitedStates();
      path = new ArrayList<String>();

    }

    public void solve(){
        
          chooseLeft();
          System.out.println(path.toString());

      }
      
      //chooses the first option
      private boolean chooseRight()
        {     
        ArrayList<String> current = new ArrayList<String>();
        current.add(start);
        String a= nexter(start);
        current.add(a);
        String b = nextAndNot(a,current);
        current.add(b);

        while(true){
          b = nextAndNot(b,current);
          if(b=="Dead End"){
            System.out.println(b);
            return false;}
          else if (b.equals("Maryland")){
            current.add("Maryland");
            current.add("District of Columbia");
            System.out.println("Path found!!");
            path = current;
            return true;}
            
        current.add(b);}
        }

      
      
      private boolean chooseLeft()
        {     
        ArrayList<String> current = new ArrayList<String>();
        current.add(start);
        String a= nexter2(start);
        current.add(a);
        String b = nextAndNot2(a,current);
        current.add(b);

        while(true){
          b = nextAndNot2(b,current);
          
           if (b.equals("Maryland")){
            current.add("Maryland");
            current.add("District of Columbia");
            System.out.println("Path found!!");
            path = current;
            return true;}

            if(b=="Dead End"){
            System.out.println(b);
            return false;}
            
            current.add(b);}
        }

    private String nextAndNot2(String next,ArrayList<String> byebye){
         ArrayList<String> hold = us.validNeighbor(next);

        //remove all byebye things from hold
        for(int i = 0; i< byebye.size();i++)
        {
          hold.remove(byebye.get(i));
        }

        if(hold.isEmpty()==true)
          return "Dead End";

        return hold.get(hold.size()-1);
      }


      private String nexter2(String next){
        return (us.validNeighbor(next)).get(us.validNeighbor(next).size()-1);
      }

      private String nexter(String next){
        return (us.validNeighbor(next)).get(0);
      }

      private String nextAndNot(String next,ArrayList<String> byebye){
         ArrayList<String> hold = us.validNeighbor(next);

        //remove all byebye things from hold
        for(int i = 0; i< byebye.size();i++)
        {
          hold.remove(byebye.get(i));
        }

        if(hold.isEmpty()==true)
          return "Dead End";

        return hold.get(0);
      }



     public static void main(String[] args) 
   {


    WomenProblem run = new WomenProblem();
    run.solve();






   }
}
