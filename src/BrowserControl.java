import java.io.IOException;

public class BrowserControl
{
    // The default system browser under windows.
    private static final String WIN_PATH = "rundll32";
    // The flag to display a url.
    private static final String WIN_FLAG = "url.dll,FileProtocolHandler";
    // The default browser under unix.
    private static final String UNIX_PATH = "netscape";
    // The flag to display a url.
    private static final String UNIX_FLAG = "-remote openURL";
    
    /**************************************************************************/
    
    /*
     * Display a file in the default web browser
     * 
     * @param url: the file's url (the url must start with "http://"
     */
    public static void openURL( String url )
    {
        boolean windows = isWindows();
        String cmd = null;
        try
        {
            if (windows)
            {
                // cmd = 'rundll32 url.dll.FileProtocolHandler http://...'
                cmd = WIN_PATH + " " + WIN_FLAG + " " + url;
                Process p = Runtime.getRuntime().exec( cmd );
            }
            else
            {
                
                // cmd = 'netscape -remote openURL(http://www.google.ca)'
                cmd = UNIX_PATH + " " + UNIX_FLAG + "(" + url + ")";
                
                Process p = Runtime.getRuntime().exec( cmd );
                
                // Netscape has to be running for the "-remote" command
            	// to work under Unix. So, we try sending the command and
                // check for an exit value.
                try
                {
                    // Wait for an exit code -- if it's 0, command worked.
                    int exitCode = p.waitFor();
                    if (exitCode != 0)
                    {
                        // Command failed, start up the browser.
                        // cmd = 'netscape http://www.google.ca'
                        cmd = UNIX_PATH + " "  + url;
                        p = Runtime.getRuntime().exec( cmd );
                    }
                }
                catch(InterruptedException x) // If we couldn't open the browser
                {
                	// Print error message to screen
                    System.out.println( "Error bringing up browser, cmd = '"
                    					+ cmd + "'" );
                    System.out.println( "Caught: " + x );
                }
            }
        }
        catch( IOException x ) // If we couldn't open the browser
        {
            // Print error message to screen
            System.out.println( "Could not open browser, command = " + cmd );
            System.out.println( "Caught: " + x );
        }
    }
    
    /***********************************************************************/
    
    /*
     * Determine whether the computer is Windows or some othe platform
     * 
     * @return true if the application is running under a Windows OS
     */
    public static boolean isWindows()
    {
    	// os = "Windows 8.1" OR "Mac OSX 10" or "Ubuntu 11.04"
        String os = System.getProperty( "os.name" );
        if( os != null && os.startsWith( "Windows" ) )
        	return true;
        return false;
    }
    /**************************************************************************/
    
    /*
     * Display multiple files in the default web browser
     * 
     * @param urls[]: url files that are to be opened (the url must start with "http://"
     */
    public static void openMany( String[] urls )
    {
    	for(String url: urls)
    		openURL(url);
    }
    
    /***********************************************************************/
    
    /*
     * Examples
     */
    public static void main( String[] args )
    {
        
    }

}