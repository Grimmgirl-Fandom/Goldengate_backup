public class Baby implements Comparable<Baby>
{
    String name;//baby name
    int babyNumber;//number of babies with that name in the given years
    int appears;//number of times the name shows up on lists;

    public Baby(String n)
    {
        name = n.toLowerCase();
        babyNumber = 0;
        appears = 0;
    }

    public String getName()
    {
        return name;
    }

    public int getBabyNumber()
    {
        return babyNumber;
    }

    public int getAppears()
    {
        return appears;
    }

    @Override
    public String toString()
    {
        return name;
    }


    public void addBabyNumber(int num)
    {
        babyNumber += num;
    }

    public void addAppears(int num)
    {
        appears += num;
    }

    @Override
    public int compareTo(Baby o) {
       int comp = getName().compareTo(o.getName());
        return comp;
    }


    public static void main(String[] args) {
        
        Baby ba = new Baby(("Grace"));
        Baby baba = new Baby("Ben");
        System.out.println(baba.getName());
        System.out.println(ba.compareTo(baba));
    }
}
