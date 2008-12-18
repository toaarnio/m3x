package m3x.m3g;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * See See http://java2me.org/m3g/file-format.html#ExternalReference 
 * for more information.<br>
 * @author jsaarinen
 */
public class ExternalReference implements M3GTypedObject
{
    private String uri;

    public ExternalReference(String uri)
    {
        this.uri = uri;
    }

    public ExternalReference()
    {
        super();
    }

    public void deserialize(M3GDeserialiser deserialiser)
        throws IOException, FileFormatException
    {
        this.uri = deserialiser.readUTF8();
    }

    public void serialize(DataOutputStream dataOutputStream, String m3gVersion)
        throws IOException
    {
        if (this.uri != null)
        {
            // .. write the string data in raw UTF-8..
            dataOutputStream.write(this.uri.getBytes("UTF-8"));
            // .. and terminate it with a null byte
            dataOutputStream.write('\0');
        }
    }

    public int getObjectType()
    {
        return ObjectTypes.EXTERNAL_REFERENCE;
    }

    public String getUri()
    {
        return this.uri;
    }
}
