import java.lang.reflect.*;
//_____   ____ _______       _    _ _______ ____  
//|  __ \ / __ \__   __|/\   | |  | |__   __/ __ \ 
//| |__) | |  | | | |  /  \  | |__| |  | | | |  | |
//|  ___/| |  | | | | / /\ \ |  __  |  | | | |  | |
//| |    | |__| | | |/ ____ \| |  | |  | | | |__| |
//|_|     \____/  |_/_/    \_\_|  |_|  |_|  \____/ 
//                                              
//
/**
 * Represents a buy or sell order for trading a given number of shares of a
 * specified stock.
 */
public class TradeOrder
{
    private Trader trader;
    private String symbol;
    private boolean buyOrder;
    private boolean marketOrder;
    private int numShares;
    private double price;

    // TODO complete class
    public TradeOrder(
        Trader trader,
        String symbol,
        boolean buyOrder,
        boolean marketOrder,
        int numShares,
        double price )
    {
        trader = this.trader;
        symbol = this.symbol;
        buyOrder = this.buyOrder;
        marketOrder = this.marketOrder;
        numShares = this.numShares;
        price = this.price;
    }
    
    public double getPrice()
    {
        return numShares/price;
    }
    public int getShares()
    {
        return numShares;
    }
    public String getSymbol()
    {
        return symbol;
    }
    public Trader getTrader()
    {
        return trader;
    }
    boolean isBuy()
    {
        return buyOrder;
    }
    boolean isLimit()
    {
        return !marketOrder;
    }
    boolean isMarket()
    {
        return marketOrder;
    }
    boolean isSell()
    {
        return !buyOrder;
    }
    public void subtractShares(int shares)
    {
        if ( shares > numShares)
        {
            throw new IllegalArgumentException("Too many shares!");
        }
        numShares -= shares;
    }
    //
    // The following are for test purposes only
    //
    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     * 
     * @return a string representation of this TradeOrder.
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
