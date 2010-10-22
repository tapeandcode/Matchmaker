/*

 * DivisionModel.java
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

import java.util.ArrayList;

/**
 * The basic model for a division to be used while processing divisions
 * @author Travis Olbrich <travis at tapeandcode.com>
 */
public class DivisionModel {

    private int id;
    private String name;

    /**
     * Creates a new DivisionModel to help manage divisions
     * @param i ID of division
     * @param n Name of division
     */
    public DivisionModel(int i, String n){
        id = i;
        name = n;
    }

    /**
     * Turns the user's input into a list of divisions
     * @param input
     * @return
     */
    public static ArrayList<DivisionModel> makeList(String input) {
        ArrayList<DivisionModel> model = new ArrayList<DivisionModel>();
        String eachDivision[] = input.split(";");

        //Now go through each division and set them up.
        for(int i=0; i<eachDivision.length; i++){
            eachDivision[i] = eachDivision[i].trim();
            int id = (int)eachDivision[i].charAt(0) - 48;
            String name = eachDivision[i].substring(2, eachDivision[i].length());
            System.out.println("debug>"+id+name);
            model.add(new DivisionModel(id, name));
        }
        return model;
    }

    /**
     * Gets the ID
     * @return ID the id of the division
     */
    public int getID(){
        return id;
    }

    /**
     * Gets the name
     * @return name the name of the division
     */
    public String getName(){
        return name;
    }

}
