/*

 * Division.java
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

import database.*;
import input.CLIinput;
import java.sql.*;
import java.util.ArrayList;
import output.ScreenOutput;
/**
 * Handles modification of divisions
 * @author Travis Olbrich <travis at tapeandcode.com>
 */
public class Division {

    Connection conn;
    Statement stmt;
    private final String TITLE = "divisions";

    /**
     * First called method, checks if divisions already exist
     * @param c the connection to the database
     * @throws SQLException
     */
    public void runDivisionModifier(Connection c) throws SQLException {
        //Set us up to use the database
        conn = c;
        stmt = conn.createStatement();

        //Check for the existence of at least one division
        if (Database.hasDivision(stmt)) {
            ScreenOutput.showOutput("The following divisions exist:");
            display();

            ScreenOutput.showOutput("Modify or exit? (M,e)");
            String command = CLIinput.getCommand(TITLE);

            //Only continue if the user wants to
            if (command.equals("m")) {
                createDivisions();
                
            }else if(!command.equals("e")){
                System.out.println("Warning> Invalid Input.");
            }


        } else {
            ScreenOutput.showOutput("No divisions exist. Add new ones? (y/n)");

            String command = CLIinput.getCommand(TITLE);

            if (command.equals("y")) {                
                createDivisions();
            }
        }
    }

    /**
     * Simply displays the different existing divisions.
     */
    private void display() {
        String query = "SELECT * FROM APP.divisions";
        try {
            //Get the results
            ResultSet rs = stmt.executeQuery(query);

            //Header
            System.out.println("> ID\tName");

            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                System.out.println("> " + id + "\t " + name);
            }
        } catch (SQLException ex) {
            ScreenOutput.showOutput(2, "Database Connection Error.");
        }
    }

    /**
     * Makes a new division
     * @param input the user's input
     */
    private void createDivisions(){
        ScreenOutput.showOutput(
                "Input as follows. ID must be integer. Do not use a trailing ';'"+
                "\n> id name; id name");
        String input = CLIinput.getRawCommand(TITLE + "/nameDivisions");
        ArrayList<DivisionModel> divisions = DivisionModel.makeList(input);
        Database.writeDivisions(divisions, stmt, conn);
        display();
    }
}
