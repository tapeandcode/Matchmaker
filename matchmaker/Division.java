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

import java.sql.*;

/**
 * Handles modification of divisions
 * @author Travis Olbrich <travis at tapeandcode.com>
 */
public class Division {
    Connection conn;
    Statement stmt;

    /**
     * First called method, checks if divisions already exist
     * @param c the connection to the database
     * @throws SQLException
     */
    public void divisionInit(Connection c) throws SQLException{
        //Set us up to use the database
        conn = c;
        stmt = conn.createStatement();

        //Create the divisions table if it does not exist
        createTable();

        //Check for the existence of at least one division
        if(hasDivision()){
            System.out.println("> The following divisions exist:");
            System.out.println("> Modify, make new, or exit? (M,N,e)");

        }else{
            System.out.println("> No divisions exist. Add new ones? (y/n)");
        }

        String command = input.TextOperations.getCommand("divisions");
    }

    /**
     * Add or modify the different divisions
     */
    private static void modify(){
    }

    /**
     * Simply displays the different existing divisions.
     */
    private static void display(){
    }

    /**
     * Create a table. If the table already exists nothing will happen.
     */
    private void createTable() {
        String divTableSql = "CREATE TABLE APP.divisions (ID INT NOT NULL, name varchar(20) NOT NULL)";
        try{
            stmt.execute(divTableSql);
            System.out.println("> New Divisions table created.");
        } catch (SQLException ex){}
    }

    /**
     * Checks to see if there already are division(s) in the database
     * @return true if there are divisions, false if not
     * @throws SQLException
     */
    private boolean hasDivision() throws SQLException {
        String query = "SELECT id FROM APP.divisions";
        ResultSet result = stmt.executeQuery(query);

        //See if at least the first result can be loaded
        if(result.next()){
            return true;
        }
        return false;
    }
    
}
