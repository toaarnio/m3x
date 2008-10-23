package m3x.m3g;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import m3x.m3g.primitives.Matrix;
import m3x.m3g.primitives.ObjectIndex;

/**
 * See http://java2me.org/m3g/file-format.html#Node<br>
  Boolean       enableRendering;
  Boolean       enablePicking;
  Byte          alphaFactor;
  UInt32        scope;
  Boolean       hasAlignment;
  IF hasAlignment==TRUE, THEN
    Byte          zTarget;
    Byte          yTarget;

    ObjectIndex   zReference;
    ObjectIndex   yReference;
  END

 * @author jsaarinen
 */
public abstract class Node extends Transformable implements M3GSerializable
{
    public static final int NONE = 144;
    public static final int ORIGIN = 145;
    public static final int X_AXIS = 146;
    public static final int Y_AXIS = 147;
    public static final int Z_AXIS = 148;
    private boolean enableRendering;
    private boolean enablePicking;
    private byte alphaFactor;
    private int scope;
    private boolean hasAlignment;
    private byte zTarget;
    private byte yTarget;
    private ObjectIndex zReference;
    private ObjectIndex yReference;

    public Node(ObjectIndex[] animationTracks, UserParameter[] userParameters,
        Matrix transform, boolean enableRendering, boolean enablePicking,
        byte alphaFactor, int scope) throws FileFormatException
    {
        super(animationTracks, userParameters, transform);
        this.enableRendering = enableRendering;
        this.enablePicking = enablePicking;
        this.alphaFactor = alphaFactor;
        this.scope = scope;
        this.hasAlignment = false;
        this.zTarget = 0;
        this.yTarget = 0;
        this.zReference = null;
        this.yReference = null;
    }

    public Node(ObjectIndex[] animationTracks, UserParameter[] userParameters,
        Matrix transform, boolean enableRendering, boolean enablePicking,
        byte alphaFactor, int scope, byte zTarget, byte yTarget,
        ObjectIndex zReference, ObjectIndex yReference) throws FileFormatException
    {
        this(animationTracks, userParameters, transform,
            enableRendering, enablePicking, alphaFactor, scope);
        this.hasAlignment = true;
        validateZTarget(zTarget);
        this.zTarget = zTarget;
        validateYTarget(yTarget);
        this.yTarget = yTarget;
        this.zReference = zReference;
        this.yReference = yReference;
    }

    private static void validateYTarget(byte yTarget) throws FileFormatException
    {
        if (yTarget < NONE || yTarget > Z_AXIS)
        {
            throw new FileFormatException("Invalid yTarget: " + yTarget);
        }
    }

    private static void validateZTarget(byte zTarget) throws FileFormatException
    {
        if (zTarget < NONE || zTarget > Z_AXIS)
        {
            throw new FileFormatException("Invalid zTarget: " + zTarget);
        }
    }

    public Node()
    {
        super();
    }

    public void deserialize(DataInputStream dataInputStream, String m3gVersion)
        throws IOException, FileFormatException
    {
        super.deserialize(dataInputStream, m3gVersion);
        this.enableRendering = dataInputStream.readBoolean();
        this.enablePicking = dataInputStream.readBoolean();
        this.alphaFactor = dataInputStream.readByte();
        this.scope = M3GSupport.readInt(dataInputStream);
        this.hasAlignment = dataInputStream.readBoolean();
        if (this.hasAlignment)
        {
            this.zTarget = dataInputStream.readByte();
            validateZTarget(this.zTarget);
            this.yTarget = dataInputStream.readByte();
            validateYTarget(this.yTarget);
            this.zReference = new ObjectIndex();
            this.zReference.deserialize(dataInputStream, m3gVersion);
            this.yReference = new ObjectIndex();
            this.yReference.deserialize(dataInputStream, m3gVersion);
        }
    }

    public void serialize(DataOutputStream dataOutputStream, String m3gVersion)
        throws IOException
    {
        super.serialize(dataOutputStream, m3gVersion);
        dataOutputStream.writeBoolean(this.enableRendering);
        dataOutputStream.writeBoolean(this.enablePicking);
        dataOutputStream.write(this.alphaFactor);
        M3GSupport.writeInt(dataOutputStream, this.scope);
        dataOutputStream.writeBoolean(this.hasAlignment);
        if (this.hasAlignment)
        {
            dataOutputStream.write(this.zTarget);
            dataOutputStream.write(this.yTarget);
            this.zReference.serialize(dataOutputStream, m3gVersion);
            this.yReference.serialize(dataOutputStream, m3gVersion);
        }
    }

    public boolean isEnableRendering()
    {
        return this.enableRendering;
    }

    public boolean isEnablePicking()
    {
        return this.enablePicking;
    }

    public byte getAlphaFactor()
    {
        return this.alphaFactor;
    }

    public int getScope()
    {
        return this.scope;
    }
}