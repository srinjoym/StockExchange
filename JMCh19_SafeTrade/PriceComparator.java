/**
 * A price comparator for trade orders.
 */
public class PriceComparator implements java.util.Comparator<TradeOrder>
{
    boolean ascending;
    // TODO complete class
    public PriceComparator()
    {
        ascending = true;
    }
    public PriceComparator(boolean asc)
    {
        ascending = asc;
    }
    @Override
    public int compare( TradeOrder order1, TradeOrder order2 )
    {
        if(order1.isMarket()&&order2.isMarket())
        {
            return 0;
        }
        else if(order1.isMarket()&&order2.isLimit())
        {
            return -1;
        }
        else if(order1.isLimit()&&order2.isMarket())
        {
            return 1;
        }
        else
        {
            if(ascending)
            {
                return order1.getPrice()-order2.getPrice();
            }
            else
                return order2.getPrice()-order1.getPrice();
        }
    }

}
