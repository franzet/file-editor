package com.franzet.maven.fileeditor;

import java.io.*;
import java.util.Properties;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.util.StringUtils;

/**
 * Create a Java File Editor
 *
 * @author Anatole Nkwadjo <ankwadjo@franzet.net>
 */
@Mojo(name = "file-editor", defaultPhase = LifecyclePhase.GENERATE_RESOURCES, threadSafe = true)
public class FileEditor extends AbstractMojo {

	/**
	 * Output directory (defaults to ${project.build.outputDirectory})
	 */
	@Parameter(property = "outputDirectory", defaultValue = "${project.build.outputDirectory}", required = true)
	private File outputDirectory;

	/**
	 * Filename where the properties are saved
	 */
	@Parameter(property = "filename", required = true)
	private String filename;

	/**
	 * Properties to save
	 */
	@Parameter(property = "properties", required = true)
	private Properties properties;

	@Parameter(property = "props", required = true)
	private String[] props;

	@Parameter(property = "typ", defaultValue = "line", required = true)
	private String typ;

	/**
	 * Create intermediate directories if necessary (defaults to true)
	 */
	@Parameter(property = "createDirectory", defaultValue = "true")
    private boolean createDirectory;

    public void execute() throws MojoExecutionException, MojoFailureException {
        File finalFile = new File(outputDirectory, filename).getAbsoluteFile();
        String finalFilename = finalFile.getAbsolutePath();
        File finalDirectory = finalFile.getParentFile();
        String finalDirectoryName = finalDirectory.getAbsolutePath();

        if (!finalDirectory.exists()) {
            getLog().info("Creating directory " + finalDirectoryName);
            finalDirectory.mkdirs();
        }
        getLog().info("Saving properties to file " + finalFilename);
        ChangeLineInFile changeFile = new ChangeLineInFile();

        if (StringUtils.equals(typ, "line")) {
            changeFile.byLine(finalFile, props);
        }
        if(StringUtils.equals(typ, "variable")){
            changeFile.byVariable(finalFile, props);
        }
    }

}
