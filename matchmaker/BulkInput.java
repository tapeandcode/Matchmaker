/*

 * BulkInput.java
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

package matchmaker;

import database.Database;
import input.TextOperations;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles the bulk input of data from file
 * @author Travis Olbrich <travis at tapeandcode.com>
 */
class BulkInput {

    Connection conn;
    Statement stmt;
    private final String TITLE = "bulkInput";

    /**
     * Sets us up to input data in bulk
     * @param conn the database connection
     */
    boolean init(Connection c) throws SQLException {
        //Set us up to use the database
        conn = c;
        stmt = conn.createStatement();

        //Create the person table if it does not exist
        Database.createPersonsTable(stmt, conn);

        //Confirm the file is in the correct location
        System.out.println("> Please make sure that the .csv file is in \n"+
                "> the same directory as this program before continuing.\n"+
                "> Press <enter> to continue.");
        TextOperations.getCommand(TITLE);
        
        //Prepare file filter
        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".csv");
            }
        };
        
        //Grab all of the files in this directory that in in .csv
        File folder = new File("./");
        File[] files = folder.listFiles(filter);

        try{
            //Print the key and name of each file
            int x = 0;
            for(File file : files){
                System.out.println(x + "\t" + file.getName());
                x++;
            }
        }catch (Exception ex){
            System.out.println("ERROR> No .csv files could be found.");
            return false;
        }
        
        boolean isFileValid = false;
        
        //Find the user's file they wish to import
        File userInputFile = null;

        while (!isFileValid){
            //Prompt the user for the file ID
            System.out.println("> Input the numerical ID of the file you wish to use.");
            int key = TextOperations.getCleanInt(TITLE);

            //Attempt to load the file
            try{
                userInputFile = files[key];
                isFileValid = true;
            }
            catch (ArrayIndexOutOfBoundsException ex){
                System.out.println("ERROR> You must input a valid file key");
            }
        }

        try {
            //Read the input from the file
            Scanner fileReader = new Scanner(userInputFile);
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR> The file could not be opened.");
            return false;
        }

        
        return true;
    }
}
