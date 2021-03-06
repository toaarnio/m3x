/**
 * Copyright (c) 2010, Jacques Gasselin de Richebourg
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

package m3x.translation.xml;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.LogLevel;
import org.apache.tools.ant.types.Mapper;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.resources.FileResource;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.util.FileUtils;

/**
 * An ANT task for converting image files to Image2D wrapped M3X files.
 * <p>The task supports mappers and applies up-to-date checks on the mapped
 * Source and Target files. Source-Target pairs will be skipped if they
 * are up-to-date according to FileUtils.isUpToDate.</p>
 *
 * <p>
 * Suggested use in a build.xml file:
 *
 * <pre>
 * {@code
 * <taskdef name="image_to_m3x"
 *          classname="m3x.translation.xml.ImageToXmlTask">
 *   <classpath>
 *   ... path that includes m3x.jar ...
 *   </classpath>
 * </taskdef>
 * ...
 * <image_to_m3x>
 *   <classpath>
 *   ... path that includes m3x.jar & jaxb-impl.jar...
 *   </classpath>
 *   <fileset dir="directory containing image assets">
 *     <include name="pattern that includes the image files you want to convert"/>
 *   </fileset>
 *   <mapper type="glob" from="*.png" to="*.m3x"/>
 * </image_to_m3x>
 * }
 * </pre>
 *
 * Please Note: The classpath to the JAXB implementation jar is needed in the task.
 * This is due to JAXB being lazy instatiated at first use, so the taskdef does
 * not load all the classes needed to use JAXB.
 * </p>
 *
 * @author jgasseli
 * @see FileUtils#isUpToDate(java.io.File, java.io.File)
 */
public class ImageToXmlTask extends Task
{
    private List<FileSet> filesetList;
    private Path classpath;
    private Mapper mapper;

    public ImageToXmlTask()
    {
        filesetList = new ArrayList<FileSet>();
    }

    @Override
    public void init()
    {
        super.init();
    }

    public void addFileSet(FileSet fileset)
    {
        filesetList.add(fileset);
    }

    /**
     * Allows the classpath to be set with a refid as the attribute 'classpath'.
     * @param p the resolved Path reference
     */
    public void setClasspath(Path p)
    {
        this.classpath = p;
    }

    /**
     * Allows the classpath to be set with a nested path element with the name
     * 'classpath'.
     * @return the created classpath
     * @throws BuildException if multiple classpaths are set.
     */
    public Path createClasspath() throws BuildException
    {
        if (this.classpath != null)
        {
            throw new BuildException("a classpath has already been set");
        }

        this.classpath = new Path(getProject());
        return this.classpath;
    }

    /**
     * Allows a mapper to be set with a nested mapper element of any type.
     * The most likely candidate for use is &lt;mapper type='glob' ... &gt;,
     * but any other mapper is also allowed.
     *
     * @return the created mapper
     * @throws BuildException if multiple mappers are set.
     */
    public Mapper createMapper() throws BuildException
    {
        if (mapper == null)
        {
            mapper = new Mapper(getProject());
        }
        else
        {
            throw new BuildException("only one mapper allowed");
        }
        return mapper;
    }

    @Override
    public void execute()
    {
        AntClassLoader loader = null;
        Method convertMethod = null;
        final String classname = "m3x.translation.xml.ImageToXmlTranslator";

        {
            Class<?> targetClass = null;
            try
            {
                if (classpath == null)
                {
                    targetClass = Class.forName(classname);
                }
                else
                {
                    loader = getProject().createClassLoader(classpath);
                    loader.setParent(getProject().getCoreLoader());
                    loader.setParentFirst(false);
                    loader.addJavaLibraries();
                    loader.setIsolated(true);
                    loader.setThreadContextLoader();
                    loader.forceLoadClass(classname);
                    targetClass = Class.forName(classname, true, loader);
                }
            }
            catch (ClassNotFoundException e)
            {
                throw new BuildException("Could not find " + classname + "."
                                         + " Make sure you have it in your"
                                         + " classpath");
            }
            try
            {
                convertMethod = targetClass.getMethod("convert",
                        new Class<?>[] {File.class, File.class, boolean.class});
            }
            catch (NoSuchMethodException e)
            {
                throw new BuildException("No method convert(File, File, boolean) in "
                                         + classname);
            }
            if (convertMethod == null)
            {
                throw new BuildException("Could not find convert(File, File, boolean) method in "
                                         + classname);
            }
            if ((convertMethod.getModifiers() & Modifier.STATIC) == 0)
            {
                throw new BuildException("convert(File, File, boolean) method in " + classname
                    + " is not declared static");
            }
        }

        final FileNameMapper fnmapper = mapper.getImplementation();
        //System.out.println("mapper: " + fnmapper.toString());
        //System.out.println("filesetList: " + filesetList.toString());
        final FileUtils fileUtils = FileUtils.getFileUtils();
        for (FileSet fs : filesetList)
        {
            //System.out.println("fs: " + fs.toString());
            final Iterator<?> it = fs.iterator();
            if (!it.hasNext())
            {
                System.out.println("empty fileset!");
            }
            while (it.hasNext())
            {
                final FileResource sourceFileResource = (FileResource)it.next();
                //System.out.println("sourceFileResource: " + sourceFileResource.toString());
                final File sourceFile = sourceFileResource.getFile();
                try
                {
                    String preMappingPath = sourceFile.getCanonicalPath();
                    String postMappingPath = fnmapper.mapFileName(preMappingPath)[0];
                    //apply the conversion for the given file names.
                    final File targetFile = new File(postMappingPath);
                    if (!fileUtils.isUpToDate(sourceFile, targetFile))
                    {
                        log("Converting " + sourceFile + " to " + targetFile,
                                LogLevel.VERBOSE.getLevel());
                        convertMethod.invoke(null,
                                new Object[] {sourceFile, targetFile});
                    }
                    else
                    {
                        log("Skipping " + sourceFile + " to " + targetFile,
                                LogLevel.VERBOSE.getLevel());
                    }
                }
                catch (InvocationTargetException ex)
                {
                    String message = ex.getMessage();
                    final Throwable cause = ex.getCause();
                    if (cause != null)
                    {
                        if (cause.getMessage() != null)
                        {
                            message = cause.getMessage();
                        }
                        else
                        {
                            cause.printStackTrace();
                        }
                    }
                    throw new BuildException("convert(File, File) failed on\n"
                            + sourceFile + ": "+ message, ex);
                }
                catch (IllegalAccessException ex)
                {
                    throw new BuildException("invoking convert(File, File) failed"
                        + " due to an access violation", ex);
                }
                catch (IOException ex)
                {
                    throw new BuildException("file " + sourceFile
                        + " does not exist");
                }
            }
        }
    }
}
