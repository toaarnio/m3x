package loader.opengl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.Reader;
import m3x.m3g.Object3D;
import m3x.translation.m3g.XmlToBinaryTranslator;
import util.DemoFrame;

/**
 * @author jgasseli
 */
public class XMLLoaderDemo extends AbstractLoaderDemo
{
    private static final class Saver extends Thread
    {
        private final OutputStream stream;
        private final Object3D[] binRoots;

        Saver(OutputStream stream, m3x.m3g.Object3D[] binRoots)
        {
            this.stream = stream;
            this.binRoots = binRoots;
        }

        @Override
        public void run()
        {
            try
            {
                m3x.m3g.Saver.save(stream, binRoots, "1.0", null);
                stream.flush();
                stream.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public void loadXML(Reader reader)
    {
        try
        {
            //deserialise XML stream
            final m3x.xml.Deserializer xmlDeserializer = new m3x.xml.Deserializer();
            final m3x.xml.M3G xmlRoot = xmlDeserializer.deserialize(reader);
            //convert to binary
            final m3x.m3g.Object3D[] binRoots = XmlToBinaryTranslator.convertRoot(
                    xmlRoot);
            final PipedOutputStream outstream = new PipedOutputStream();
            final PipedInputStream instream = new PipedInputStream(outstream);
            //save to pipe
            Saver saver = new Saver(outstream, binRoots);
            saver.start();
            //allow the conversion to get a head start
            try
            {
                Thread.sleep(5);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            //load from the pipe
            canvas.load(instream);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public XMLLoaderDemo()
    {
        super();
        loadXML(new InputStreamReader(
                getClass().getResourceAsStream("model1.m3x")));
    }

    public static void main(String[] args)
    {
        DemoFrame frame = new XMLLoaderDemo();
        frame.present(false);
    }
}
