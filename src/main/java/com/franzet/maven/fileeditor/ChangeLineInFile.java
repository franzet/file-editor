package com.franzet.maven.fileeditor;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.codehaus.plexus.util.StringUtils;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class ChangeLineInFile {

    void byVariable(File file, String[] props) throws MojoExecutionException, MojoFailureException {
        try {
            String filePath = file.getAbsolutePath();
            List<String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
            List<String> copyLines = new ArrayList<>(lines);
            for (String holders : props) {
                String[] params = holders.split(":");
                String name = params[0];
                String newValue = params[1];
                for(int i=0; i<copyLines.size(); i++) {
                    String line = lines.get(i);
                    int nameIndex = line.indexOf(name);
                    if (nameIndex > 0) {
                        int end = nameIndex + name.length();
                        String prefix = line.substring(0, end);
                        String suffix = line.substring(end + 1);
                        int equalsIndex = suffix.indexOf("=");
                        if (equalsIndex > -1) {
                            String newLine = prefix + " = " + newValue;
                            if(line.endsWith(";")){
                                newLine = newLine+";";
                            }
                            lines.set(i, newLine);
                            break;
                        }
                    }
                }
            }

            // Re-write
            writeListToFile(filePath, lines);

        } catch (NumberFormatException e) {
            throw new MojoFailureException("First param is not a number " + e.getMessage());
        } catch (IOException io) {
            throw new MojoFailureException("File not found " + io.getMessage());
        } catch (Exception ex) {
            throw new MojoExecutionException("Error by execute plugin " + ex.getMessage(), ex);
        }
    }

    void byLine(File file, String[] props) throws MojoExecutionException, MojoFailureException {
        try {
            String filePath = file.getAbsolutePath();
            List<String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
            for (String holders : props) {
                String[] params = holders.split(":");
                int nr = Integer.valueOf(params[0]);
                String newLine = params[1];
                lines.set(nr, newLine);
            }

            // Re-write
            writeListToFile(filePath, lines);

        } catch (NumberFormatException e) {
            throw new MojoFailureException("First param is not a number " + e.getMessage());
        } catch (IOException io) {
            throw new MojoFailureException("File not found " + io.getMessage());
        } catch (Exception ex) {
            throw new MojoExecutionException("Error by execute plugin " + ex.getMessage(), ex);
        }
    }


    private void writeListToFile(String fileName, List<String> lines) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        for (String str : lines) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();
    }

}