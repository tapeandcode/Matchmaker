/*

 * TextOperations.java
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

package input;

/**
 *
 * @author Travis Olbrich <travis at tapeandcode.com>
 */
public class TextOperations {

    /**
     * Cleans the CLI input from the user.
     * @param input the user's input
     * @return output the cleaned, formatted string
     */
    public static String cleanUserInput(String input){
        String output = null;

        output = input.trim();
        output = input.toLowerCase();

        return output;
    }
}