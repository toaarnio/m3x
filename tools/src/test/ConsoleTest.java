package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.xml.bind.JAXBException;


/**Console test reader for the M3X tool-suite.
 * 
 * @author Jacques Gasselin de Richebourg
 */
public class ConsoleTest extends Object
{
    private ConsoleTest()
    {
        super();
        
    }
    
    private static final void print(final String s)
    {
        System.out.println(s);
    }

    private static final void printUsage()
    {
        print("M3X<->M3G format conversion tool");
        print("usage : M3X [-f m3g|m3g] [-o outfile] [infile]");
        print("- if outfile is omitted the application will write to stdout.");
        print("- if infile is omitted the application read from stdin.");
    }

    /**Parses command line options and arguments.
     * 
     * @param args - the command line arguments
     * @throws java.lang.IllegalArgumentException - if any option is unparsable
     */
    private static final ConsoleTest parseArgs(final String[] args)
            throws IllegalArgumentException
    {
        String infile = null;
        String outfile = null;
        String format = null;
        
        //parse the commandline arguments
        for (int i = 0; i < args.length; ++i)
        {
            final String option = args[i];
            if (option.equals("-f"))
            {
                //the next option is the format type
                try
                {
                    format = args[++i];
                }
                catch (ArrayIndexOutOfBoundsException e)
                {
                    //end of arg list
                    throw new IllegalArgumentException("-f requires an argument");
                }
                if (format.startsWith("-"))
                {
                    //probably another option flag
                    throw new IllegalArgumentException("-f requires an argument");
                }
                if (!(format.equals("m3x") || format.equals("m3g")))
                {
                    //unknown format
                    throw new IllegalArgumentException("unknown format; only m3x, m3g supported");
                }
            }
            else if (option.equals("-o"))
            {
                //the next option is the output file
                try
                { 
                    outfile = args[++i];
                }
                catch (ArrayIndexOutOfBoundsException e)
                {
                    //end of arg list
                    throw new IllegalArgumentException("-o requires an argument");
                }
                if (outfile.startsWith("-"))
                {
                    //probably another option flag
                    throw new IllegalArgumentException("-o requires an argument");
                }
            }
            else
            {
                if (!option.startsWith("-"))
                {
                    infile = option;
                }
                else
                {
                    //probably another option flag
                    throw new IllegalArgumentException("unknown option; " + option);
                }
            }
        }
        
        //process the options
        javax.xml.bind.JAXBContext context = null;
        try
        {
            context = javax.xml.bind.JAXBContext.newInstance("m3x.jaxb");
        }
        catch (javax.xml.bind.JAXBException e)
        {
            print("unable to bind schema: " + e.getMessage());
            return null;
        }
        
        javax.xml.bind.Unmarshaller unmarshaller = null;
        try
        {
            unmarshaller = context.createUnmarshaller();
        }
        catch (javax.xml.bind.JAXBException e)
        {
            print("unable to create unmarshaller: " + e.getMessage());
            return null;
        }
        
        m3x.jaxb.M3G root = null;
        try
        {
            root = (m3x.jaxb.M3G)unmarshaller.unmarshal(new FileInputStream(infile));
        }
        catch (FileNotFoundException e)
        {
            print("infile is not a valid file: " + e.getMessage());
            return null;
        }
        catch (javax.xml.bind.JAXBException e)
        {
            print("unable to parse infile: " + e.getMessage());
            return null;
        }
        
        return new ConsoleTest();
    }
    
    /**Entry function for M3X
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        if (args.length == 0)
        {
            printUsage();
            return;
        }
        
        ConsoleTest testClass = null;
        try
        {
            testClass = parseArgs(args);
        }
        catch (IllegalArgumentException e)
        {
            print("parse error: " + e.getMessage());
        }
    }

}
