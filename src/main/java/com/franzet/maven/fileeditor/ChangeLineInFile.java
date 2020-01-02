package com.franzet.maven.fileeditor;

import org.apache.maven.plugin.MojoFailureException;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;

class ChangeLineInFile {

	void changeALineInATextFile(File file, String newLine, int lineNumber) throws MojoFailureException {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file) ;
			OutputStreamWriter writer = new OutputStreamWriter(out, Charset.forName("UTF-8"));
			String content = readFile(file);
			String editedContent = editLineInContent(content, newLine, lineNumber);
			writer.write(editedContent);
		} catch (IOException e) {
			throw new MojoFailureException("Unable to save properties to file " + file.getAbsolutePath() + ": " + e.getMessage(), e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
					// Ignore
				}
			}
		}
	}

	private static int numberOfLinesInFile(String content) {
		int numberOfLines = 0;
		int index = 0;
		int lastIndex = 0;
		lastIndex = content.length() - 1;
		while (true) {
			if (content.charAt(index) == '\n') {
				numberOfLines++;
			}
			if (index == lastIndex) {
				numberOfLines = numberOfLines + 1;
				break;
			}
			index++;
		}
		return numberOfLines;
	}

	private static String[] turnFileIntoArrayOfStrings(String content, int lines) {
		String[] array = new String[lines];
		int index = 0;
		int tempInt = 0;
		int startIndext = 0;
		int lastIndex = content.length() - 1;
		while (true) {
			if (content.charAt(index) == '\n') {
				tempInt++;
				String temp2 = "";
				for (int i = 0; i < index - startIndext; i++) {
					temp2 += content.charAt(startIndext + i);
				}
				startIndext = index;
				array[tempInt - 1] = temp2;

			}
			if (index == lastIndex) {
				tempInt++;
				String temp2 = "";
				for (int i = 0; i < index - startIndext + 1; i++) {
					temp2 += content.charAt(startIndext + i);
				}
				array[tempInt - 1] = temp2;
				break;
			}
			index++;
		}

		return array;
	}

	private static String editLineInContent(String content, String newLine, int line) {
		int lineNumber = 0;
		lineNumber = numberOfLinesInFile(content);
		String[] lines = turnFileIntoArrayOfStrings(content, lineNumber);
		if (line != 1) {
			lines[line - 1] = "\n" + newLine;
		} else {
			lines[line - 1] = newLine;
		}
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < lineNumber; i++) {
			buffer.append(lines[i]);
		}
		return buffer.toString();
	}

	private static String readFile(File file) throws IOException {
		//File is found
		System.out.println("File Found : " + file.exists());

		//Read File Content
		String content = new String(Files.readAllBytes(file.toPath()));
		return content;
	}


}