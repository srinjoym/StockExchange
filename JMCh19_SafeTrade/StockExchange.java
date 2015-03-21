import java.lang.reflect.*;
import java.util.*;
//_____   ____ _______       _    _ _______ ____  
//|  __ \ / __ \__   __|/\   | |  | |__   __/ __ \ 
//| |__) | |  | | | |  /  \  | |__| |  | | | |  | |
//|  ___/| |  | | | | / /\ \ |  __  |  | | | |  | |
//| |    | |__| | | |/ ____ \| |  | |  | | | |__| |
//|_|     \____/  |_/_/    \_\_|  |_|  |_|  \____/ 
//                                              
//
/**
 * Represents a stock exchange. A <code>StockExchange</code> keeps a
 * <code>HashMap</code> of stocks, keyed by a stock symbol. It has methods to
 * list a new stock, request a quote for a given stock symbol, and to place a
 * specified trade order.
 */
public class StockExchange
{
    private Map<String, Stock> listedStocks;
    
    // TODO complete class

    
    //
    // The following are for test purposes only
    //
    protected Map<String, Stock> getListedStocks()
    {
        return listedStocks;
    }
    
    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     * 
     * @return a string representation of this StockExchange.
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
