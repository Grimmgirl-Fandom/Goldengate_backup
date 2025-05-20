
public class PriceData
{

public String date, open, high, low, close, volume;

    public PriceData(String d, String o, String h, String l, String c, String v)
    {

        date = d;
        open = o;
        high = h;
        low = l;
        close = c;
        volume = v;

    }

    //returns date
    public String getDate()
    {
        return date;
    }

    //returns open
    public String getOpen()
    {
        return open;
    }

    //returns high
    public String getHigh()
    {
        return high;
    }

    //returns low
    public String getLow()
    {
        return low;
    }

    //returns close
    public String getClose()
    {
        return close;
    }

    //returns volume
    public String getVolume()
    {
        return volume;
    }

    @Override
    public String toString() {
        return date + " " + open + " " + high + " " + low + " " + close + " " + volume;
    }


}
