# file-editor
Plugin to edit some file. two methods:
1. typ=line (example: 1:new line)
the line content in file will be replace by new value

            <plugin>
                <artifactId>file-editor-maven-plugin</artifactId>
                <version>1.0.0-SNAPSHOT</version>
                <groupId>com.franzet</groupId>
                <executions>
                <execution>
                <id>one</id>
                <phase>generate-resources</phase>
                <goals>
                    <goal>file-editor</goal>
                </goals>
                <configuration>
                    <typ>line</typ>
                    <outputDirectory>${project.basedir}</outputDirectory>
                    <filename>test.properties</filename>
                    <props>
                        <param>0:line nr.1 will be raplace with this</param>
						<param>4:line nr.5 will be raplace with this</param>
                    </props>
                </configuration>
                </execution>
                </executions>
            </plugin>
			
			
1. typ=variable (example: MY_VARIABLE:myvariable)
the first world with the variablename be replace by new value

            <plugin>
                <artifactId>file-editor-maven-plugin</artifactId>
                <version>1.0.0-SNAPSHOT</version>
                <groupId>com.franzet</groupId>
                <executions>
                <execution>
                <id>one</id>
                <phase>generate-resources</phase>
                <goals>
                    <goal>file-editor</goal>
                </goals>
                <configuration>
                    <typ>line</typ>
                    <outputDirectory>${project.basedir}</outputDirectory>
                    <filename>test.properties</filename>
                    <props>
                        <param>MY_VARIABLE1:new vairable</param>
						<param>MY_VARIABLE2:new vairable</param>
                    </props>
                </configuration>
                </execution>
                </executions>
            </plugin>