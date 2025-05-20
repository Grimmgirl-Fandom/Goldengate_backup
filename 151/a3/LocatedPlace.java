//Date:Febuary 9th 2022
//Grace Swenson Hollis

public class LocatedPlace extends Place
{
    private String latitude,longitude;

public LocatedPlace( String zip, String st, String ci, String lat, String lon)
{
    super(zip, st,ci);
    latitude = lat;
    longitude = lon;
  

}

@Override
public String toString()
    {
        return (super.toString() + "\t" + "Lat: " + latitude + "  Lon: " + longitude);
    }
}
