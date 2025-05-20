public class Calculations{
    
    double[] dataSet;

    public Calculations(double[] data)
    {
        dataSet = data;
        
    }
    

    public double mean()
    {
        double sum = 0;
        for(int i = 0; i<dataSet.length; i++)
            sum+=dataSet[i];

            return sum/dataSet.length;

    }

    public double sd()
    {
        double theMean = mean();
        double sum =0;

        for(int i = 0; i<dataSet.length; i++)
            sum+=(Math.pow((theMean-dataSet[i]),2));
        

            return Math.pow((sum/dataSet.length),.5);

    }



    public void print()
    {
        System.out.println("Mean: " + mean());
        System.out.println("Standard Deviation : " + sd());

    }

public static void main(String[] args) {

    double[] sep = {2.406902958,2.431826625,2.379330417,2.404802916,2.413313292,2.37421725,2.401033166,2.471947125,2.392980375, 2.400021334};
    Calculations a = new Calculations(sep);
    a.print();

    double[] map = {73.000599166,65.735122458,71.95030625,67.949550166, 70.925147708,65.018010042,70.503734042,71.402667167,67.218278666,67.038781084};
    Calculations b = new Calculations(map);
    b.print();

    double[] probe = {2.311979208,2.770850042,2.287568625,2.362697125,2.345586708,2.346889541,2.277322375,2.286561667,2.297300666,2.253272125};
    Calculations c = new Calculations(probe);
    c.print();

}




}
