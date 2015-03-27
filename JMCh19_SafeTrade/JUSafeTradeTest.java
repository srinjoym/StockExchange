import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.regex.*;

import org.junit.*;

import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;
//_____   ____ _______       _    _ _______ ____  
//|  __ \ / __ \__   __|/\   | |  | |__   __/ __ \ 
//| |__) | |  | | | |  /  \  | |__| |  | | | |  | |
//|  ___/| |  | | | | / /\ \ |  __  |  | | | |  | |
//| |    | |__| | | |/ ____ \| |  | |  | | | |__| |
//|_|     \____/  |_/_/    \_\_|  |_|  |_|  \____/ 
//                                              
//
/**
 * SafeTrade tests:
 *   TradeOrder
 *   PriceComparator
 *   Trader
 *   Brokerage
 *   StockExchange
 *   Stock
 *
 * @author TODO Name of principal author
 * @author TODO Name of group member
 * @author TODO Name of group member
 * @version TODO date
 * @author Assignment: JM Chapter 19 - SafeTrade
 * 
 * @author Sources: TODO sources
 *
 */
public class JUSafeTradeTest
{
    // --Test TradeOrder
    /**
     * TradeOrder tests:
     *   TradeOrderConstructor - constructs TradeOrder and then compare toString
     *   TradeOrderGetTrader - compares value returned to constructed value
     *   TradeOrderGetSymbol - compares value returned to constructed value
     *   TradeOrderIsBuy - compares value returned to constructed value
     *   TradeOrderIsSell - compares value returned to constructed value
     *   TradeOrderIsMarket - compares value returned to constructed value
     *   TradeOrderIsLimit - compares value returned to constructed value
     *   TradeOrderGetShares - compares value returned to constructed value
     *   TradeOrderGetPrice - compares value returned to constructed value
     *   TradeOrderSubtractShares - subtracts known value & compares result
     *     returned by getShares to expected value
     */
    private String symbol = "GGGL";
    private boolean buyOrder = true;
    private boolean marketOrder = true;
    private int numShares = 123;
    private int numToSubtract = 24;
    private double price = 123.45;

    @Test
    public void tradeOrderConstructor()
    {
        TradeOrder to = new TradeOrder( null, symbol, buyOrder, marketOrder,
            numShares, price );
        String toStr = to.toString();

        assertTrue( "<< Invalid TradeOrder Constructor >>",
                    toStr.contains( "TradeOrder[Trader trader:null" )
                        && toStr.contains( "java.lang.String symbol:" + symbol )
                        && toStr.contains( "boolean buyOrder:" + buyOrder )
                        && toStr.contains( "boolean marketOrder:" + marketOrder )
                        && toStr.contains( "int numShares:" + numShares )
                        && toStr.contains( "double price:" + price ) );
    }
    
    @Test
    public void TradeOrderToString()
    {
        TradeOrder to = new TradeOrder( null, symbol, buyOrder, marketOrder,
            numShares, price );
        assertNotNull( to.toString() );
    }

    @Test
    public void tradeOrderGetTrader()
    {
        TradeOrder to = new TradeOrder( null, symbol, buyOrder, marketOrder,
            numShares, price );
        assertNull( "<< TradeOrder: " + to.getTrader() + " should be null >>",
                    to.getTrader() );
    }

    @Test
    public void tradeOrderGetSymbol()
    {
        TradeOrder to = new TradeOrder( null, symbol, buyOrder, marketOrder,
            numShares, price );
        assertEquals( "<< TradeOrder: " + to.getTrader() + " should be "
            + symbol + " >>", symbol, to.getSymbol() );
    }

    @Test
    public void tradeOrderIsBuy()
    {
        TradeOrder to = new TradeOrder( null, symbol, buyOrder, marketOrder,
            numShares, price );

        assertTrue( "<< TradeOrder: " + to.isBuy() + " should be " + buyOrder
            + " >>", to.isBuy() );
    }

    @Test
    public void tradeOrderIsSell()
    {
        TradeOrder to = new TradeOrder( null, symbol, buyOrder, marketOrder,
            numShares, price );
        assertFalse( "<< TradeOrder: " + to.isSell() + " should be "
            + !buyOrder + " >>", to.isSell() );
    }

    @Test
    public void tradeOrderIsMarket()
    {
        TradeOrder to = new TradeOrder( null, symbol, buyOrder, marketOrder,
            numShares, price );
        assertTrue( "<< TradeOrder: " + to.isMarket() + " should be "
            + marketOrder + " >>", to.isMarket() );
    }

    @Test
    public void tradeOrderIsLimit()
    {
        TradeOrder to = new TradeOrder( null, symbol, buyOrder, marketOrder,
            numShares, price );

        assertFalse( "<< TradeOrder: " + to.isLimit() + " should be "
            + !marketOrder + ">>", to.isLimit() );
    }

    @Test
    public void tradeOrderGetShares()
    {
        TradeOrder to = new TradeOrder( null, symbol, buyOrder, marketOrder,
            numShares, price );
        assertTrue( "<< TradeOrder: " + to.getShares() + " should be "
            + numShares + ">>", numShares == to.getShares()
            || ( numShares - numToSubtract ) == to.getShares() );
    }

    @Test
    public void tradeOrderGetPrice()
    {
        TradeOrder to = new TradeOrder( null, symbol, buyOrder, marketOrder,
            numShares, price );
        assertEquals( "<< TradeOrder: " + to.getPrice() + " should be " + price
            + ">>", price, to.getPrice(), 0.0 );
    }

    @Test
    public void tradeOrderSubtractShares()
    {
        TradeOrder to = new TradeOrder( null, symbol, buyOrder, marketOrder,
            numShares, price );
        to.subtractShares( numToSubtract );
        assertEquals( "<< TradeOrder: subtractShares(" + numToSubtract
            + ") should be " + ( numShares - numToSubtract ) + ">>", numShares
            - numToSubtract, to.getShares() );
    }
    
    // --Test TraderWindow Stub
    @Test
    public void traderWindowConstructor()
    {
        TraderWindow tw = new TraderWindow( null );
        assertNotNull( tw );
    }

    @Test
    public void traderWindowShowMessage()
    {
        TraderWindow tw = new TraderWindow( null );
        assertNotNull( tw );
        tw.showMessage( null );
    }

    //  --Test PriceComparator
    /*
     * Price Comparator Tests:
     *      priceComparatorCompare()
     */
    public void priceComparatorAscending()
    {
        PriceComparator pc = new PriceComparator();
        TradeOrder t1 = new TradeOrder( new Trader( new Brokerage( new StockExchange() ),
            "Test",
            "Test" ),
            symbol,
            buyOrder,
            false,
            numShares,
            price );
        TradeOrder t2 = new TradeOrder( new Trader( new Brokerage( new StockExchange() ),
            "Test",
            "Test" ),
            symbol,
            buyOrder,
            false,
            numShares,
            price + 5.0 );
        assertNotNull( pc );
        assertNotNull( t1 );
        assertNotNull( t2 );
        assertEquals( "<< PriceComparator: compare(" + t1 + ", " + t2
            + ") should be " + (int)Math.round( (100 * t1.getPrice()) - (100 * t2.getPrice()) )
            + ">>",
            (int)Math.round( (100 * t1.getPrice()) - (100 * t2.getPrice()) ),
            pc.compare( t1, t2 ) );
    }
 
 
    @Test
    public void priceComparatorDescending()
    {
        PriceComparator pc = new PriceComparator( false );
        TradeOrder t1 = new TradeOrder( new Trader( new Brokerage( new StockExchange() ),
            "Test",
            "Test" ),
            symbol,
            buyOrder,
            false,
            numShares,
            price + 5.0 );
        TradeOrder t2 = new TradeOrder( new Trader( new Brokerage( new StockExchange() ),
            "Test",
            "Test" ),
            symbol,
            buyOrder,
            false,
            numShares,
            price );
        assertNotNull( pc );
        assertNotNull( t1 );
        assertNotNull( t2 );
        assertEquals( "<< PriceComparator: compare(" + t1 + ", " + t2
            + ") should be " + (int)Math.round(( 100 * t2.getPrice()) - (100 * t1.getPrice()) )
            + ">>",
            (int)Math.round( (100 * t2.getPrice()) - (100 * t1.getPrice()) ),
            pc.compare( t1, t2 ) );
    }
    
    // --Test Trader 
    @Test
    public void traderEquals()
    {
        Trader tr = new Trader( new Brokerage( new StockExchange() ),
            "Test",
            "Test" );
        assertTrue( tr.equals( tr ) );
    }
    @Test
    public void traderGetName()
    {
        Trader tr = new Trader( new Brokerage( new StockExchange() ),
            "Test",
            "Test" );
        assertEquals( tr.getName(), "Test" );
    }
 
 
    @Test
    public void traderGetPassword()
    {
        Trader tr = new Trader( new Brokerage( new StockExchange() ),
            "Test",
            "Test" );
        assertEquals( tr.getPassword(), "Test" );
    }
    public void traderGetQuote()
    {
        StockExchange exchange = new StockExchange();
        exchange.listStock( symbol, "Test", price );
        Stock gggl = exchange.getListedStocks().get( symbol );
        Brokerage broke = new Brokerage( exchange );
        Trader testr = new Trader( broke, "Test", "Test" );
        testr.getQuote( symbol );
 
        assertTrue( testr.hasMessages() );
        testr.mailbox();
    }
 
    @Test
    public void traderMessage()
    {
        Trader tr = new Trader( new Brokerage( new StockExchange() ),
            "Test",
            "Test" );
        tr.receiveMessage( "Test" );
        assertTrue( tr.hasMessages() );
        tr.openWindow();
        assertFalse( tr.hasMessages() );
        tr.quit();
    }
 
 
    @Test
    public void traderPlaceOrder()
    {
        StockExchange exchange = new StockExchange();
        exchange.listStock( symbol, "Test", price );
        Stock gggl = exchange.getListedStocks().get( symbol );
        Brokerage broke = new Brokerage( exchange );
        Trader testr = new Trader( broke, "Test", "Test" );
        TradeOrder test = new TradeOrder( testr,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        testr.placeOrder( test );
 
        assertFalse( gggl.getBuyOrders().isEmpty() );
        assertTrue( testr.hasMessages() );
    }  
    
    public void traderToString()
    {
        Trader t = new Trader( new Brokerage( new StockExchange() ),
            "Test",
            "Test" );
        assertNotNull( t.toString() );
    }
    // TODO your tests here
    
    
    // --Test Brokerage
    public void brokerageAddUser()
    {
        Brokerage b = new Brokerage( new StockExchange() );
        assertEquals( b.addUser( "T", "Test" ), -1 );
        assertEquals( b.addUser( "Test", "T" ), -2 );
        assertEquals( b.addUser( "Test", "Test" ), 0 );
        assertEquals( b.addUser( "Test", "Test" ), -3 );
        assertTrue( b.getTraders().containsKey( "Test" ) );
    }
    
    @Test
    public void brokerageGetQuote()
    {
        StockExchange exchange = new StockExchange();
        exchange.listStock( symbol, "Test", price );
        Stock s = exchange.getListedStocks().get( symbol );
        Brokerage broke = new Brokerage( exchange );
        Trader t = new Trader( broke, "Test", "Test" );
        broke.getQuote( symbol, t );
 
        assertTrue( t.hasMessages() );
    }
 
    @Test
    public void brokerageLogin()
    {
        Brokerage b = new Brokerage( new StockExchange() );
        assertEquals( b.addUser( "Test", "Test" ), 0 );
        assertEquals( b.login( "Test", "Test" ), 0 );
        assertEquals( b.login( "T", "Test" ), -1 );
        assertEquals( b.login( "Test", "T" ), -2 );  
        assertEquals( b.login( "Test", "Test" ), -3 );
        assertTrue( b.getLoggedTraders().contains( b.getTraders()
            .get( "Test" ) ) );
    }
 
 
    @Test
    public void brokerageGetExchange()
    {
        StockExchange s = new StockExchange();
        Brokerage b = new Brokerage( s );
        assertEquals( b.getExchange(), s );
    }
 
 
    @Test
    public void brokerageLogout()
    {
        Brokerage b = new Brokerage( new StockExchange() );
        assertEquals( b.login( "Test", "Test" ), 0 );
        assertEquals( b.addUser( "Test", "Test" ), 0 );
        b.logout( b.getTraders().get( "Test" ) );
        assertFalse( b.getLoggedTraders().contains( b.getTraders()
            .get( "Test" ) ) );
    }
 
 
    
 
 
    @Test
    public void brokeragePlaceOrder()
    {
        StockExchange exchange = new StockExchange();
        exchange.listStock( symbol, "Test", price );
        Stock gggl = exchange.getListedStocks().get( symbol );
        Brokerage broke = new Brokerage( exchange );
        Trader testr = new Trader( broke, "Test", "Test" );
        TradeOrder test = new TradeOrder( testr,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        broke.placeOrder( test );
 
        assertFalse( gggl.getBuyOrders().isEmpty() );
        assertTrue( testr.hasMessages() );
    }
    // TODO your tests here
    
    
    // --Test StockExchange
    public void StockExchangeGetQuote()
    {
        StockExchange exchange = new StockExchange();
        exchange.listStock( symbol, "Test", price );
        assertEquals( "Test" + " (" + symbol + ")\nPrice: " + price + "  hi: "
            + price + "  lo: " + price + "  vol: 0\nAsk: none Bid: none",
            exchange.getQuote( symbol ) );
    }
 
 
    @Test
    public void StockExchangeListStock()
    {
        StockExchange exchange = new StockExchange();
        exchange.listStock( symbol, "Test", price );
        assertEquals( false, exchange.getListedStocks().isEmpty() );
    }
 
 
    @Test
    public void StockExchangePlaceOrder()
    {
        StockExchange exchange = new StockExchange();
        exchange.listStock( symbol, "Test", price );
        Stock gggl = exchange.getListedStocks().get( symbol );
        Brokerage broke = new Brokerage( exchange );
        Trader testr = new Trader( broke, "Test", "Test" );
        TradeOrder test = new TradeOrder( testr,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        exchange.placeOrder( test );
 
        assertFalse( gggl.getBuyOrders().isEmpty() );
        assertTrue( testr.hasMessages() );
    }
    // TODO your tests here
    
    
    // --Test Stock
    public void getQuote()
    {
        Stock stock = new Stock( symbol, "Test", price );
        assertEquals( stock.getQuote(), "Test (" + symbol + ")\nPrice: "
            + price + "  hi: " + price + "  lo: " + price
            + "  vol: 0\nAsk: none Bid: none" );
    }
 
 
    @Test
    public void getBuyStockQuote()
    {
        Stock stock = new Stock( symbol, "Test", price );
        TradeOrder to = new TradeOrder( new Trader( new Brokerage( new StockExchange() ),
            "Test",
            "Test" ),
            symbol,
            false,
            buyOrder,
            numShares,
            price );
        stock.placeOrder( to );
 
        assertEquals( "Test (" + symbol + ")\nPrice: " + price + "  hi: "
            + price + "  lo: " + price
            + "  vol: 0\nAsk: market size: 123 Bid: none", stock.getQuote() );
    }
 
 
    @Test
    public void getSellStockQuote()
    {
        Stock stock = new Stock( symbol, "Test", price );
        TradeOrder to = new TradeOrder( new Trader( new Brokerage( new StockExchange() ),
            "Test",
            "Test" ),
            symbol,
            marketOrder,
            buyOrder,
            numShares,
            price );
        stock.placeOrder( to );
 
        assertEquals( "Test (" + symbol + ")\nPrice: " + price + "  hi: "
            + price + "  lo: " + price
            + "  vol: 0\nAsk: market size: 123 Bid: none", stock.getQuote() );
    }
 
 
    @Test
    public void getSellLimitStockQuote()
    {
        Stock stock = new Stock( symbol, "Test", price );
        TradeOrder to = new TradeOrder( new Trader( new Brokerage( new StockExchange() ),
            "Test",
            "Test" ),
            symbol,
            false,
            false,
            numShares,
            price );
        stock.placeOrder( to );
 
        assertEquals( "Test (" + symbol + ")\nPrice: " + price + "  hi: "
            + price + "  lo: " + price
            + "  vol: 0\nAsk: 123.45 size: 123 Bid: none", stock.getQuote() );
    }
 
 
    @Test
    public void getSellMarketStockQuote()
    {
        Stock stock = new Stock( symbol, "Test", price );
        buyOrder = false;
        assertEquals( stock.getQuote(), "Test (" + symbol + ")\nPrice: "
            + price + "  hi: " + price + "  lo: " + price
            + "  vol: 0\nAsk: none Bid: none" );
    }
 
 
    @Test
    public void placeStockBuyOrder()
    {
        StockExchange exchange = new StockExchange();
        Stock gggl = new Stock( symbol, "Test", price );
        exchange.listStock( symbol, "Test", price );
        Brokerage broke = new Brokerage( exchange );
        Trader testr = new Trader( broke, "Test", "Test" );
        TradeOrder test = new TradeOrder( testr,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
 
        gggl.placeOrder( test );
        assertFalse( gggl.getBuyOrders().isEmpty() );
        assertTrue( testr.hasMessages() );
    }
 
 
    @Test
    public void placeStockSellOrder()
    {
        StockExchange exchange = new StockExchange();
        Stock gggl = new Stock( symbol, "Test", price );
        exchange.listStock( symbol, "Test", price );
        Brokerage broke = new Brokerage( exchange );
        Trader testr = new Trader( broke, "Test", "Test" );
        TradeOrder test = new TradeOrder( testr,
            symbol,
            false,
            marketOrder,
            numShares,
            price );
 
        gggl.placeOrder( test );
        assertFalse( gggl.getSellOrders().isEmpty() );
        assertTrue( testr.hasMessages() );
    }
 
 
    @Test
    public void toStringTest()
    {
        StockExchange exchange = new StockExchange();
        Stock gggl = new Stock( symbol, "Test", price );
        Trader tr = new Trader( new Brokerage( new StockExchange() ),
            "Test",
            "Test" );
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        Brokerage br = new Brokerage( new StockExchange() );
        assertNotNull( exchange.toString() );
        assertNotNull( gggl.toString() );
        assertNotNull( tr.toString() );
        assertNotNull( to.toString() );
        assertNotNull( br.toString() );
    }
 
 
    @Test
    public void executeOrders()
    {
        StockExchange exchange = new StockExchange();
        Stock gggl = new Stock( symbol, "Test", price );
        exchange.listStock( symbol, "Test", price );
        Brokerage broke = new Brokerage( exchange );
        Trader testr = new Trader( broke, "Test", "Test" );
        TradeOrder test = new TradeOrder( testr,
            symbol,
            false,
            marketOrder,
            numShares,
            price );
        TradeOrder test2 = new TradeOrder( testr,
            symbol,
            true,
            marketOrder,
            numShares,
            price );
 
        gggl.placeOrder( test );
        gggl.placeOrder( test2 );
        assertFalse( gggl.getSellOrders().isEmpty() );
        assertTrue( testr.hasMessages() );
        gggl.executeOrders();
    }
 
 
    @Test
    public void stockGetMethods()
    {
        Stock gggl = new Stock( symbol, "Test", price );
        assertEquals( gggl.getStockSymbol(), symbol );
        assertEquals( gggl.getCompanyName(), "Test" );
        assertEquals( (int)gggl.getLoPrice(), (int)price );
        assertEquals( (int)gggl.getHiPrice(), (int)price );
        assertEquals( (int)gggl.getLastPrice(), (int)price );
        assertEquals( (int)gggl.getVolume(), 0 );
    }
    // TODO your tests here

    
    // Remove block comment below to run JUnit test in console
/*
    public static junit.framework.Test suite()
    {
        return new JUnit4TestAdapter( JUSafeTradeTest.class );
    }
    
    public static void main( String args[] )
    {
        org.junit.runner.JUnitCore.main( "JUSafeTradeTest" );
    }
*/
}
