//Date:Febuary 9th 2022
//Grace Swenson Hollis

public class PopulatedPlace extends LocatedPlace
{
    private String population2;

public PopulatedPlace( String zip, String st, String ci, String lat, String lon, String pop)
{
    super(zip, st, ci, lat, lon);
    population2 = pop;
}

public String toString()
    {
        return (super.toString() + "  Pop: " + population2);
    }

}
