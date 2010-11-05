/*

 * PersonInformation.java
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
import database.PersonModel;
import input.TextReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import output.ScreenOutput;
import sun.awt.X11.Screen;

/**
 *
 * @author Travis Olbrich <travis at tapeandcode.com>
 */
class PersonInformation {
    final String TITLE = "personInformation";


    /**
     * Basically starts and runs the tool that gets person information
     * @param conn the connection to the database
     * @return false if fail.
     */
    boolean runPersonInformation(Connection conn) {
        ScreenOutput.showOutput("Enter some or all of the last name only.");
        String lname = TextReader.getCommand(TITLE);
        ArrayList<PersonModel> people = null;

        //Search for people fitting the description
        try {
            people = Database.findPersons(
                    conn.createStatement(), lname);
            //if(people.size()<=0){
            //    throw new Exception("No people found");
            //}
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }

        PersonModel person = null;
        
        //List all the people
        for(int i = 0; i < people.size(); i++){
            person = people.get(i);
            ScreenOutput.showOutput("["+i+"] "+PersonModel.getSummary(person));
        }

        //Have the person select their choice
        int id = TextReader.getValidID(people.size(), TITLE);
        person = people.get(id);

        System.out.println(PersonModel.getSummary(person));

        return true;
    }

}
