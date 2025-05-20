//Grace Swenson Hollis

public class Stock
{

    public PriceData[] stock;
    public String name;

    public Stock(PriceData[] s, String n)
    {
        stock = s;
        name = n;
       
    }

    //returns the stock
    public PriceData[] getStock()
    {
        return stock;
    }

    //returns an instence of priceData based on the date
    public PriceData getPriceData(int date)
    {
        return stock[date];
    }

    //returns company name
    public String toString()
    {
        return name;
    }
   

}
