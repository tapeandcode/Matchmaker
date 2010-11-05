/*

 * ListOutput.java
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

import input.TextReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import output.ScreenOutput;

/**
 *
 * @author Travis Olbrich <travis at tapeandcode.com>
 */
class ListOutput {
    final String TITLE = "list";

    boolean runListOutput(Connection conn) {
        ScreenOutput.showOutput("Enter division ID or -1 for all.");

        int choice = TextReader.getCleanInt(TITLE);

        //Set up the query
        String query = null;
        if(choice == -1){
            query = "SELECT * FROM APP.persons ORDER BY 'lname'";
        }else{
            query = "SELECT * FROM APP.persons WHERE divID = " + choice + " ORDER BY 'lname'";
        }

        //Print results
        ResultSet rs = null;
        try {
            rs = conn.createStatement().executeQuery(query);
        } catch (SQLException ex) {
            ScreenOutput.showOutput(2, "Database Query Failure");
            return false;
        }

        //Open the file for writing
        FileWriter fw = null;
        try {
            fw = new FileWriter(new File("List Output " + System.console() + ".txt"));

            //Print each line
            fw.write("'lastName','firstName','gender','DivisionID','ID'\n");
            while(rs.next()){
                String fname = "'"+rs.getString("fname") + "',";
                String lname = "'"+rs.getString("lname") + "',";
                String gender = "'"+rs.getString("gender") + "',";
                int divid    = rs.getInt("divID");
                int id       = rs.getInt("ID");

                fw.write(lname+fname+gender+divid+","+id+"\n");
            }
            fw.close();
            ScreenOutput.showOutput("File writing success");
        } catch (Exception ex) {
            ScreenOutput.showOutput(3, "File writing failure");
        }

        return true;
    }

}
