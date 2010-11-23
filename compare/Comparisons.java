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

import database.Person;
import input.CLIinput;
import output.ScreenOutput;

/**
 *
 * @author Travis Olbrich <travis at tapeandcode.com>
 */
public class Comparisons {

    static final String TITLE = "compare";

    //TODO: Create Comparison Customization
    /**
     * Gets the person's choice of comparison
     * First digit is type of comparison
     * Second digit is
     * @return the ID of the comparison type
     */
    public static String getChoice(String parentTitle) {
        String choice = "";
        int input;

        ScreenOutput.showOutput("Enter the ID of the type of comparison to run:");
        ScreenOutput.showOutput("[0] Normal");
        ScreenOutput.showOutput("[1] Same Division Only");
        ScreenOutput.showOutput("[2] Other Divisions Only");
        input = CLIinput.getValidID(2, parentTitle+"/"+TITLE);

        //Add the input to the choice stream
        choice+=input;

        //

        //Do the next
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
