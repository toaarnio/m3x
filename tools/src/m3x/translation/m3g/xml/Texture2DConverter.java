/**
 * Copyright (c) 2008-2010, Jacques Gasselin de Richebourg
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * - Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * - Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package m3x.translation.m3g.xml;

import m3x.translation.m3g.XmlToBinaryTranslator;


/**
 * Translator for Texture2D object.
 * 
 * @author jsaarinen
 * @author jgasseli
 */
public class Texture2DConverter extends TransformableConverter
{
    @Override
    public m3x.m3g.Object3D toBinary(XmlToBinaryTranslator translator, m3x.xml.Object3D from)
    {
        m3x.m3g.Texture2D to = new m3x.m3g.Texture2D();
        setFromXML(translator, to, (m3x.xml.Texture2D)from);
        return to;
    }

    protected final void setFromXML(XmlToBinaryTranslator translator,
        m3x.m3g.Texture2D to, m3x.xml.Texture2D from)
    {
        super.setFromXML(translator, to, from);

        to.setImage(getImage(translator, from));
        to.setBlendColor(from.getBlendColor());
        to.setBlending(from.getBlending().value());
        to.setWrapping(from.getWrappingS().value(), from.getWrappingT().value());
        to.setFiltering(from.getLevelFilter().value(), from.getImageFilter().value());
    }

    private static m3x.m3g.Image2D getImage(
        XmlToBinaryTranslator translator, m3x.xml.Texture2D from)
    {
        m3x.xml.Image2D im = getObjectOrInstance(
            from.getImage2D(), from.getImage2DInstance());
        return (m3x.m3g.Image2D) translator.getReference(im);
    }

}
