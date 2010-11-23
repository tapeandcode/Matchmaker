/*

 * Person.java
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
public class Person {

    public String fname;
    public String lname;
    public int    divID;
    public String gender;
    public String answers;

    /**
     * Makes data about a person into a Person
     * @param f first name
     * @param l last name
     * @param d division ID
     * @param g gender
     * @param a answers
     */
    Person(String f, String l, int d, String g, String a){
        fname = f;
        lname = l;
        divID = d;
        gender = g;
        answers = a;
    }

    Person(Person person){
        fname = person.fname;
        lname = person.lname;
        divID = person.divID;
        gender = person.gender;
        answers = person.answers;
    }

    /**
     * Prints a summary of the person
     * @param person
     * @return the summary of the person
     */
    public static String getSummary(Person person){
        return person.fname + " " + person.lname + " in division " + person.divID;
    }


    /**
     * Gets the opposite gender
     * @param gender
     * @return the opposite gender
     */
    public static String getOppositeGender(String gender) {
        if(gender.equalsIgnoreCase("male"))
            return "female";
        return "male";
    }


}
