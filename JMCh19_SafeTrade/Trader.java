
import java.lang.reflect.*;
import java.util.*;
 
 
/**
 * Represents a stock trader.
 */
public class Trader implements Comparable<Trader>
{
    private Brokerage brokerage;
 
    private String screenName, password;
 
    private TraderWindow myWindow;
 
    private Queue<String> mailbox;
 
 
    public Trader (Brokerage broker, String name, String pswd)
    {
        brokerage = broker;
        screenName = name;
        password = pswd;
        mailbox = new LinkedList<String>();
        brokerage.login( screenName, pswd );
    }
 
    protected Queue<String> mailbox()
    {
        return mailbox;
    }
 
 
    public String getName()
    {
        return screenName;
    }
 
    public String getPassword()
    {
        return password;
    }
 
    @Override
    public int compareTo( Trader arg0 )
    {
        if ( arg0 == null )
        {
            return -1;
        }
        int i = this.getName().compareToIgnoreCase( arg0.getName() );
        return i;
    }
 
    public boolean equals (Trader trade)
    {
        return this.compareTo( trade )==0;
    }
     
    public void openWindow()
    {
        myWindow = new TraderWindow(this);
        while (!mailbox.isEmpty())
        {
            myWindow.showMessage( mailbox.remove() );
        }
    }
     
    public boolean hasMessages()
    {
        return mailbox.isEmpty();
         
    }
 
    public void getQuote (String symbol)
    {
        brokerage.getQuote(symbol,this);
    }
 
    public void quit()
    {
         brokerage.logout(this);
         myWindow = null;
 
    }
     
    public void receiveMessage(String msg)
    {
        mailbox.add( msg );
        if (myWindow!=null)
        {
            myWindow.showMessage( mailbox.remove() );
        }
    }
 
    public void placeOrder (TradeOrder tradeO)
    {
        brokerage.placeOrder( tradeO );
    }
 
    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     * 
     * @return a string representation of this Trader.
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
                if ( field.getType().getName().equals( "Brokerage" ) )
                    str += separator + field.getType().getName() + " "
                        + field.getName();
                else
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