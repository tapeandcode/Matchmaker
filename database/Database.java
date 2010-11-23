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
import java.util.ArrayList;
import output.ScreenOutput;
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
            ScreenOutput.showOutput(2, "Database Connection Failure. Ensure no\n"+
                    "> other copy of Matchmaker is running.");
            System.out.println(ex.getMessage());
            System.exit(0);
        }
        return null;
    }

    /**
     * Writes the divisions to the database
     * @param divisions the list of divisions to add
     * @param stmt the statement
     * @param conn the database connection
     */
    public static void writeDivisions(ArrayList<DivisionModel> divisions, Statement stmt, Connection conn){
        try{
            String deleteQuery = "DELETE FROM APP.divisions";
            stmt.execute(deleteQuery);

            //Write information for each person
            for(DivisionModel model : divisions){
                String insertQuery = "INSERT INTO APP.divisions \n";
                insertQuery+="VALUES("+model.getID()+", '"+model.getName()+"')";
                stmt.execute(insertQuery);
            }
        }catch (SQLException ex){
            ScreenOutput.showOutput(2, "Database Connection Issue");
            System.out.println(ex.getMessage());
        }
    }

        /**
     * Checks to see if there already are division(s) in the database
     * @param stmt the statement
     * @return true if there are divisions, false if not
     * @throws SQLException
     */
    public static boolean hasDivision(Statement stmt) throws SQLException {
        String query = "SELECT id FROM APP.divisions";
        ResultSet result = stmt.executeQuery(query);

        //See if at least the first result can be loaded
        if(result.next()){
            return true;
        }
        return false;
    }


    /**
     * Drops all the database tables
     * @param conn the connection to the database
     * @throws SQLException
     */
    public static void destroy(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute("DROP TABLE APP.divisions");
        stmt.execute("DROP TABLE APP.persons");
        System.out.println("> All tables dropped");
    }


    /**
     * Searches the database for people with the same last name as lname
     * @param stmt The statement for the database connection
     * @param lname Last name of person to find
     * @return an ArrayList of Person with each result
     * @throws SQLException 
     */
    public static ArrayList<Person> findPersons(Statement stmt, String lname) throws SQLException {
        String query = "SELECT * FROM APP.persons WHERE lower(lname) = '"+lname+"'";
        return getResults(query, stmt);
    }

    /**
     * Retrieves a list of all the persons that are of opposite gender
     * @param stmt
     * @param gender
     * @return
     * @throws SQLException
     */
    public static ArrayList<Person> findOpposites(Statement stmt, String gender) throws SQLException{
        String query = "SELECT * FROM APP.persons WHERE gender = '"+gender+"'";
        return getResults(query, stmt);
    }

    /**
     * Gets the results from a query
     * @param query
     * @param stmt
     * @return a list of people
     * @throws SQLException
     */
    private static ArrayList<Person> getResults(String query, Statement stmt) throws SQLException{
        ArrayList<Person> people = new ArrayList<Person>();
        ResultSet rs = stmt.executeQuery(query);

        //Add each person to the list
        while(rs.next()){
            Person person = new Person(rs.getString("fname"), rs.getString("lname"),
                    rs.getInt("divID"), rs.getString("gender"), rs.getString("answers"));
            //System.out.println(Person.getSummary(person));
            people.add(person);
        }

        return people;
    }

    public static void create(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        createDivisionsTable(stmt, conn);
        createPersonsTable(stmt, conn);
    }

    /**
     * Create a table for divisions. If the table already exists nothing will happen.
     * @param stmt Statement
     * @param conn Database Connection
     */
    private static void createDivisionsTable(Statement stmt, Connection conn) {
        String divTableSql = "CREATE TABLE APP.divisions (ID INT NOT NULL, name varchar(20) NOT NULL)";
        try{
            stmt.execute(divTableSql);
            ScreenOutput.showOutput("New divisions table created.");
        } catch (SQLException ex){}
    }

    /**
     * Creates the table to hold people. If the table already exists nothing will happen.
     * @param stmt
     * @param conn
     */
    private static void createPersonsTable(Statement stmt, Connection conn) {
        String destroy = "DROP TABLE APP.persons";
        String divTableSql =
                "CREATE TABLE APP.persons (ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "+
                "fname varchar(20) NOT NULL, "+
                "lname varchar(20) NOT NULL, "+
                "divID integer NOT NULL,"+
                "gender varchar(6) NOT NULL, "+
                "answers varchar(200) NOT NULL)";
        try{
            //stmt.execute(destroy);
            stmt.execute(divTableSql);
            ScreenOutput.showOutput("New persons table created.");
        } catch (SQLException ex){}
    }
}
