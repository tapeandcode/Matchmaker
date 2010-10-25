/*

 * Main.java
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

import input.TextOperations;
import java.sql.Connection;
import database.*;

/**
 * Main Matchmaker class, handles top-level operations
 * @author Travis Olbrich <travis at tapeandcode.com>
 */
public class Main {

    /**
     * Sets up the basic interface for the Matchmaker program
     *
     * @param args unused
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //Setup
        boolean quitStatus = false;
        Database db = new Database();
        Connection conn = db.DBInit();

        //Display intro
        displayIntro();

        while (quitStatus == false) {
            String command = TextOperations.getCommand("");

            //Modify Divisions
            if (command.equals("division") || command.equals("d")) {
                Division div = new Division();
                div.runDivisionModifier(conn);
            }

            //Bulk import of data
            else if (command.equals("input") || command.equals("i")) {
                BulkInput bulkIn = new BulkInput();
                bulkIn.runBulkInput(conn);
            }

            //Default error case
            else {
                System.out.println("Warning> Invalid Input");
            }
        }

    }

    /**
     * Displays the basic instructions for the user
     */
    private static void displayIntro() {
        System.out.println("Welcome to Matchmaker \n"
                + "--------------------- \n"
                + "If this is your first time running Matchmaker or if \n"
                + "you would like to set the program back up, type: \n"
                + "setup \n"
                + "Otherwise, type 'help' or any other command.");
    }
}
