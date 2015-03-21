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
    }
    // TODO complete class

    //
    // The following are for test purposes only
    //
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

    
    
    public boolean hasMessages()
    {
        return mailbox.isEmpty();
        
    }

    public void getQuote (String symbol)
    {
        
    }

    public void quit()
    {
        // TODO Auto-generated method stub

    }
    
    public void recieveMessage(String msg)
    {
        
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


