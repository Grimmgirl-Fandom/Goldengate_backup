//Grace Swenson Hollis

public class Trade
{
    public int date,amount;
    public Stock stock;


    public Trade(Stock s, int a,int d)
    {

        date = d;
        stock = s;
        amount = a;
    }

    //returns positive if the stock is being bought and negitive if its being sold
    public boolean buying()
    {
        return amount>0;

    }

//returns the date
    public int getDate()
    {return date;}
//returns the amount
    public int getAmount()
    {return amount;}

    //returns the stock
    public Stock getStock()
    {return stock;}
    
//ideally a method that returns that days price(close) multiplied by the amount

    //toString lol
    public String toString()
    {
        return date + " " + "SELL OR Sell " + Math.abs(amount) + " net ";
    }
}
