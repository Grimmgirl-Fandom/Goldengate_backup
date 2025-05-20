//Date:Febuary 9th 2022
//Grace Swenson Hollis

public class Place
{
    //instence varibles
    private String zipCode, state, city;

    //constructor
    public Place (String zip, String st, String ci)
    {
        zipCode = zip;
        state = st;
        city = ci;

    }

    //toString method to print 
    @Override
    public String toString()
    {
        return ("\t" + zipCode + " " + city + ", " + state);
    }
    
    
    @Override
    public boolean equals(Object inputZipcode){
          Place inputZip = (Place) inputZipcode; 
          if (this.zipCode.equals(inputZip.zipCode)){
                return true;
          }
          return false;
    }

}
