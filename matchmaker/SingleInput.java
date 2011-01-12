/*

 * SingleInput.java
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

import input.CLIinput;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import output.ScreenOutput;
/**
 * Inputs a single person
 * @author Travis Olbrich <travis at tapeandcode.com>
 */
public class SingleInput {
    Connection conn;
    final String TITLE = "singleInput";
    private Statement stmt;

    /**
     * Runs a single input
     * @param c the connection to the database
     */
    public void runSingleInput(Connection c){
        conn = c;
        String values = "";
        String inputPrompts[] = {"fName", "lName", "gender", "answers", "divID"};


        ScreenOutput.showOutput("Enter each requested value, pressing <enter> after\n"+
                "> each one. For 'gender', use 'm' or 'f'. For divID, use\n"+
                "> the numerical ID of the division. Use spaces between each answer");

        //Get each value and add it in
        for(int i = 0; i < inputPrompts.length; i++){
            if(!inputPrompts[i].equals("divID")){
                //Everything not divID is string
                values += "'" + CLIinput.getRawCommand(TITLE+"/"+
                        inputPrompts[i]).trim() + "',";
            }else{
                //divID is int
                values += CLIinput.getCleanInt(TITLE+"/"+inputPrompts[i]);
            }
        }

        //Create the full query
        String query = "INSERT INTO APP.persons (fname, lname, gender, answers, divID) VALUES("+values+")";
        
        try {
            stmt = conn.createStatement();
            stmt.execute(query);
            ScreenOutput.showOutput("Input is successful");
        } catch (SQLException ex) {
            ScreenOutput.showOutput(1, "Data Input Failure");
        }
    }
}
