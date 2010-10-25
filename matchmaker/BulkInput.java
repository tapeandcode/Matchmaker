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
import java.io.FilenameFilter;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

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
    void init(Connection c) throws SQLException {
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
            //List these files
            for(File file : files){
                System.out.println(file.getName());
            }
        }catch (Exception ex){
            System.out.println("ERROR> No .csv files could be found.");
        }
    }

}
