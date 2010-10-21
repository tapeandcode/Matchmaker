/*

 * MenuOutput.java
 * 
 * Copyright (c) 2010 Travis Olbrich.
 * 
 * This file is part of Matchmaker.
 * 
 * Matchmaker is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Matchmaker is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Matchmaker.  If not, see <http ://www.gnu.org/licenses/>.
 */

package output;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Basic output for the Matchmaker menu
 * @author Travis Olbrich <travis at tapeandcode.com>
 */
public class MenuOutput {


    /**
     * Display the helpfile
     * @throws FileNotFoundException if File is not found
     * @throws IOException 
     */
    public static void displayHelp() throws FileNotFoundException, IOException {
        //Load in the file
        BufferedReader in = new BufferedReader(new FileReader("src/HELP"));
        String readLine = in.readLine();

        //Output file to screen
        while(readLine != null){
            System.out.println(readLine);
            readLine = in.readLine();
        }

        in.close();
    }

}