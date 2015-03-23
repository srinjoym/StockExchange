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
        traders = new TreeMap<String, Trader>();
        loggedTraders = new TreeSet<Trader>();
    }


    @Override
    public int addUser( String name, String password )
    {
        // checks name
        if ( name.length() < 11 && name.length() > 3 )
        {
            // checks password
            if ( password.length() > 1 && password.length() < 11 )
            {
                // checks if name already exists in map of traders
                // makes new trader if everything is good and puts it into the
                // map
                if ( !traders.containsKey( name ) )
                {
                    Trader trader = new Trader( this, name, password );
                    traders.put( name, trader );
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
        // if given username is in map of usernames
        if ( traders.containsKey( name ) )
        {
            // compares the pass of trader in map with the password supplied
            if ( password.equals( traders.get( name ).getPassword() ) )
            {
                // sees if trader is logged in already
                if ( !loggedTraders.contains( traders.get( name ) ) )
                {
                    Trader trader = traders.get( name );
                    // if no messages
                    if ( !trader.hasMessages() )
                    {
                        trader.recieveMessage( "Welcome to SafeTrade!" );
                    }
                    return 0;
                }
                return 3;
            }
            return 2;
        }
        return 1;
    }


    public void logout( Trader trader )
    {
        if ( loggedTraders.contains( trader ) )
        {
            loggedTraders.remove( trader );
        }
    }


    public void getQuote( String symbol, Trader trader )
    {

        trader.recieveMessage( exchange.getQuote( symbol ) );
    }


    public void placeOrder( TradeOrder order )
    {

        exchange.placeOrder( order );
        
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
