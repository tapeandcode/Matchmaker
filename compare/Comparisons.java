/*

 * Comparisons.java
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

package compare;

import database.Database;
import database.Person;
import input.CLIinput;
import output.ScreenOutput;

/**
 *
 * @author Travis Olbrich <travis at tapeandcode.com>
 */
public class Comparisons {

    static final String TITLE = "compare";

    /**
     * Calls all the other methods needed to run a comparison
     */
    public static void doMultiComparison(){
        //First get the selection choice
        String choiceString = getChoice();

        //Check if there is an existing comparison result table
        boolean tableExists = Database.checkForTable("results");

        //Wipe the table or leave it as is
        boolean append
    }
            
    
    /**
     * Gets the person's choice of comparison
     * First digit is type of comparison
     * Second digit is gender choice
     * @return the ID of the comparison type
     */
    private static String getChoice() {
        String choice = "";
        int input;

        ScreenOutput.showOutput("Enter the ID of the type of comparison to run:");
        ScreenOutput.showOutput("[0] Both");
        ScreenOutput.showOutput("[1] Same Division Only");
        ScreenOutput.showOutput("[2] All Other Divisions Only");
        input = CLIinput.getValidID(2, TITLE);

        //Add the input to the choice stream
        choice+=input;

        //Gender choice
        ScreenOutput.showOutput("Enter the ID of the gender to compare to:");
        ScreenOutput.showOutput("[0] Opposite Gender");
        ScreenOutput.showOutput("[1] Same Gender");
        input = CLIinput.getValidID(2, TITLE);

        //Add the input to the choice stream
        choice+=input;

        return choice;
    }

    public static void writeResults(Person person, String comparisonChoice) {
        int sameDivision = person.divID;
        boolean allDivisions = true;
        String genderTo = Person.getOppositeGender(person.gender);

        //No change under normal comparison

        //Get a list of all of the people that match the conditions
        
    }

}
