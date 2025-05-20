import java.io.IOException;
public class Runner
{

    public static void main(String[] args) throws IOException 
    {
        
        PriceData[] listA = new PriceData[3000];
        PriceData[] listB = new PriceData[3000];
        PriceData[] listC = new PriceData[3000];
        String[] lineInfo; 
        try
        {
           
            String fileURL = "https://cs.brynmawr.edu/cs151/Data/Hw5/A.csv"; 
            ReadCSV file = new ReadCSV(fileURL); 
           int i = 0;
            while (file.hasNext())
            {
                lineInfo = file.getLine();
                    PriceData price = new PriceData(lineInfo[0],lineInfo[1],lineInfo[2],lineInfo[3],lineInfo[4],lineInfo[5]);
                    listA[i] = price;
            }

            
        }

        catch(IOException e)
        {
            System.out.println("Error");
        }
/** 
        try 
        {
            String fileURL = "https://cs.brynmawr.edu/cs151/Data/Hw5/B.csv"; 
            ReadCSV file = new ReadCSV(fileURL); 
           

            int i = 0;
            while (file.hasNext())
            {
                lineInfo = file.getLine();
                    PriceData price = new PriceData(lineInfo[0],lineInfo[1],lineInfo[2],lineInfo[3],lineInfo[4],lineInfo[5]);
                    listB[i] = price;
            }
        }

        catch(IOException exception)
        {}

        
        try
        {
            String fileURL = "https://cs.brynmawr.edu/cs151/Data/Hw5/C.csv"; 
            ReadCSV file = new ReadCSV(fileURL); 
            int i = 0;
            while (file.hasNext())
            {
                lineInfo = file.getLine();
                    PriceData price = new PriceData(lineInfo[0],lineInfo[1],lineInfo[2],lineInfo[3],lineInfo[4],lineInfo[5]);
                    listC[i] = price;
            }
        }

        catch(IOException exception)
        {}
        */

       
Stock a = new Stock(listA,"A");
Stock b = new Stock(listB,"B");
Stock c = new Stock(listC,"C");
ArrayStack<Trade> trades = new ArrayStack<>(3000);


try
{
    String fileURL = "https://cs.brynmawr.edu/cs151/Data/Hw5/tradesA.csv"; 
    ReadCSV file = new ReadCSV(fileURL); 
    
    while (file.hasNext())
    {
        lineInfo = file.getLine();
           Trade trade = new Trade(a,Integer.parseInt(lineInfo[1]),Integer.parseInt(lineInfo[2]));

           if(trade.buying()==true)
           {
               trades.push(trade);
           }
           
           System.out.println(trade);
            
    }
}


catch(IOException exception)
{}





    }
}
