import java.util.*;
import java.lang.reflect.*;
import java.text.DecimalFormat;


/**
 * Represents a stock in the SafeTrade project
 */
// _____ ____ _______ _ _ _______ ____
// | __ \ / __ \__ __|/\ | | | |__ __/ __ \
// | |__) | | | | | | / \ | |__| | | | | | | |
// | ___/| | | | | | / /\ \ | __ | | | | | | |
// | | | |__| | | |/ ____ \| | | | | | | |__| |
// |_| \____/ |_/_/ \_\_| |_| |_| \____/
//
//
public class Stock
{
    public static DecimalFormat money = new DecimalFormat( "0.00" );

    private String stockSymbol;

    private String companyName;

    private double loPrice, hiPrice, lastPrice;

    private int volume;

    private PriorityQueue<TradeOrder> buyOrders, sellOrders;


    public Stock( java.lang.String symbol, java.lang.String name, double price )
    {
        stockSymbol = symbol;
        companyName = name;
        loPrice = price;
        hiPrice = price;
        lastPrice = price;
        volume = 0;
        sellOrders = new PriorityQueue<TradeOrder>( new PriceComparator( true ) );
        buyOrders = new PriorityQueue<TradeOrder>( new PriceComparator( false ) );
    }


    //
    // The following are for test purposes only
    //

    protected String getStockSymbol()
    {
        return stockSymbol;
    }


    protected String getCompanyName()
    {
        return companyName;
    }


    protected double getLoPrice()
    {
        return loPrice;
    }


    protected double getHiPrice()
    {
        return hiPrice;
    }


    protected double getLastPrice()
    {
        return lastPrice;
    }


    protected int getVolume()
    {
        return volume;
    }


    protected PriorityQueue<TradeOrder> getBuyOrders()
    {
        return buyOrders;
    }


    protected PriorityQueue<TradeOrder> getSellOrders()
    {
        return sellOrders;
    }


    protected void executeOrders()
    {
        TradeOrder sellOrder = sellOrders.peek();
        TradeOrder buyOrder  = buyOrders.peek();
        if(sellOrder.isLimit()&&buyOrder.isLimit()&& sellOrder.)
        {
            
        }
    }

//    Giggle.com (GGGL)
//    Price: 10.00  hi: 10.00  lo: 10.00  vol: 0
//    Ask: 12.75 size: 300  Bid: 12.00 size: 500
    public String getQuote()
    {
        return companyName+" ("+stockSymbol+")"+"\n"+"Price: "+lastPrice+" hi: "+ hiPrice+" lo: "+loPrice+" vol: "+volume+"\n"+"Ask: ";
        
    }


    public void placeOrder( TradeOrder order )
    {
        String msg = "New order: ";
        if(order.isBuy())
        {
            buyOrders.add( order );
            msg.concat( "Buy " );
        }
        else
        {
            sellOrders.add(order);
            msg.concat( "Sell " );
        }
        msg.concat(order.getSymbol()+" ("+order.getTrader().getName()+")"+"\n"+
        order.getShares()+ " shares at $"+order.getPrice());
        
    }


    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     * 
     * @return a string representation of this Stock.
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
                str += separator + field.getType().getName() + " " + field.getName() + ":" + field.get( this );
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
