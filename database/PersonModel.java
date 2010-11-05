/*

 * PersonModel.java
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

/**
 * The model for a person to be used while processing persons
 * @author Travis Olbrich <travis at tapeandcode.com>
 */
public class PersonModel {
    private String fname;
    private String lname;
    private int    divID;
    private String gender;
    private String answers;

    /**
     * Makes data about a person into a PersonModel
     * @param f first name
     * @param l last name
     * @param d division ID
     * @param g gender
     * @param a answers
     */
    PersonModel(String f, String l, int d, String g, String a){
        fname = f;
        lname = l;
        divID = d;
        gender = g;
        answers = a;
    }

    PersonModel(PersonModel person){
        fname = person.fname;
        lname = person.lname;
        divID = person.divID;
        gender = person.gender;
        answers = person.answers;
    }

    public static String getSummary(PersonModel person){
        return person.fname + " " + person.lname + " in division " + person.divID;
    }

}
