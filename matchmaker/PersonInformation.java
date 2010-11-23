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

import compare.Comparisons;
import database.Database;
import database.Person;
import input.CLIinput;
import java.sql.Connection;
import java.util.ArrayList;
import output.ScreenOutput;

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
        String lname = CLIinput.getCommand(TITLE);
        ArrayList<Person> people = null;

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

        Person person = null;
        
        //List all the people
        for(int i = 0; i < people.size(); i++){
            person = people.get(i);
            ScreenOutput.showOutput("["+i+"] "+Person.getSummary(person));
        }

        //Have the person select their choice
        int id = CLIinput.getValidID(people.size(), TITLE);
        person = people.get(id);

        //Check for comparison choice
        //String comparisonChoice = Comparisons.getChoice(TITLE);
        String  comparisonChoice="normal";

        //Display the person's results
        Comparisons.writeResults(person, comparisonChoice);

        return true;
    }

}
