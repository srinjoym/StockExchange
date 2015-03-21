import java.lang.reflect.*;
import java.util.*;


/**
 * edward rulesfda Represents a brokeragXZe.asdf
 */
public class Brokerage implements Login
{
    private Map<String, Trader> traders;

    private Set<Trader> loggedTraders;

    private StockExchange exchange;


    // TODO complete class

    public Brokerage( StockExchange ex )
    {
        exchange = ex;
        TreeMap<String, Trader> tree = new TreeMap<String, Trader>();
        tree.putAll( traders );
        loggedTraders = new TreeSet<Trader>();
    }


    @Override
    public int addUser( String name, String password )
    {
        if ( name.length() < 11 && name.length() > 3 )
        {

            if ( password.length() > 1 && password.length() < 11 )
            {
                if ( !traders.containsKey( name ) )
                {
                    return 0;
                }
                return 3;
            }
            return 2;
        }
        return 1;
    }


    @Override
    public int login( String name, String password )
    {
        // TODO Auto-generated method stub
        return 0;
    }


    //
    // The following are for test purposes only
    //
    protected Map<String, Trader> getTraders()
    {
        return traders;
    }


    protected Set<Trader> getLoggedTraders()
    {
        return loggedTraders;
    }


    protected StockExchange getExchange()
    {
        return exchange;
    }


    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     * 
     * @return a string representation of this Brokerage.
     */
    public String toString()
    {
        String str = this.getClass().getName() + "[";
        String separator = "";

        Field[] fields = this.getClass().getDeclaredFields();

        for ( Field field : fields )
        {
            try
            {
                str += separator + field.getType().getName() + " "
                    + field.getName() + ":" + field.get( this );
            }
            catch ( IllegalAccessException ex )
            {
                System.out.println( ex );
            }

            separator = ", ";
        }

        return str + "]";
    }

}
