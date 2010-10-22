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

        //See if there are divisions that already exist
        createTable();

        //if there are no divisions in the database
        System.out.println("No divisions exist. Add new ones? (y/n)");
        //If there are divisions in the database
        System.out.println("The following divisions exist:");
        System.out.println("Modify, make new, or exit? (M,N,e)");
    }

    /**
     * Add or modify the different divisions
     */
    private static void modify(){
    }

    private static void display(){
    }

    private void createTable() {
        String divTableSql = "CREATE TABLE APP.divisions (ID INT NOT NULL, name varchar(20) NOT NULL)";
        try{
            stmt.execute(divTableSql);
            System.out.println("> New Divisions table created.");
        } catch (SQLException ex){}
    }
    
}
