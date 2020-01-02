package com.franzet.maven.fileeditor;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

/**
 * @author Anatole Nkwadjo
 */

public class ChangeLineInFileTest {

	private ChangeLineInFile changer = new ChangeLineInFile();

	@Test
	public void testByLine() throws Exception {
        Path resourceDirectory = Paths.get("src","test","resources", "test.properties");
        File file =  resourceDirectory.toFile();
        String[] params = {"3:A complete log of this;"};
		changer.byLine(file,  params);
	}

    @Test
    public void testByVariable() throws Exception {
        Path resourceDirectory = Paths.get("src","test","resources", "test.properties");
        File file =  resourceDirectory.toFile();
        String[] params = {"MISSING_VALUE: update what will be committed"};
        changer.byVariable(file,  params);
    }
}