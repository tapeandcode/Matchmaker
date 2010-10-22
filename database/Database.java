/*

 * DBInit.java
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

package database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Sets up a connection to the database
 * @author Travis Olbrich <travis at tapeandcode.com>
 */
public class Database {
    final String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    final String DB_URL = "jdbc:derby:matchmaker;create=true";
    final String USER = "dbuser";
    final String PASS = "pwd!";
    
    /**
     * Because you gotta initialize the database somewhere... Actually, this
     * simply drives the rest of the initilization stuff.
     * 
     * @return a connection to the database
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public Connection DBInit() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        Class.forName(JDBC_DRIVER).newInstance();
        try {
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException ex) {
            System.out.println("> ERROR: Database Connection Failure");
            System.out.println(ex.getMessage());
            System.exit(0);
        }
        return null;
    }
}
