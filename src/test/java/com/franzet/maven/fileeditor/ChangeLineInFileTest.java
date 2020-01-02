package com.franzet.maven.fileeditor;

import java.io.File;
import java.net.URL;

import org.apache.maven.plugin.testing.MojoRule;
import org.apache.maven.plugin.testing.resources.TestResources;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Anatole Nkwadjo
 */
public class ChangeLineInFileTest {

	private ChangeLineInFile changer = new ChangeLineInFile();


	@Test
	public void testChangeALineInATextFile() throws Exception {
		File pom = getFileFromResources("pom.xml");
		Assert.assertNotNull(pom);
		Assert.assertTrue(pom.exists());

		changer.changeALineInATextFile(pom,  "Merci", 0);
	}

	// get file from classpath, resources folder
	private File getFileFromResources(String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();
		URL resource = classLoader.getResource(fileName);
		if (resource == null) {
			throw new IllegalArgumentException("file is not found!");
		} else {
			return new File(resource.getFile());
		}

	}
}