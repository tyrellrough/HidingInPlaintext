package com.example.hidinginplaintextjavafx;

import java.util.ArrayList;

/**
 * A class representing a single production.
 * A production is a single rule in a context free grammar.
 * Each line of a grammar text file is one production.
 * E.G. "START->The ¬SUNMOON,SETS".
 * @author Tyrell R
 * @version 1.0
 */
public class Production
{
    /**
     * Name of the production.
     * Sourced from before the "=" sign in each line of a
     * grammar text file.
     * e.g. "START", "NAME", "DAY".
     */
    private String productionName;

    /**
     * An array list of choice objects.
     * A choice is indicated by the "|" vertical bar character in a
     * grammar text file.
     */
    private ArrayList<Choice> choices = new ArrayList<>();

    /**
     * An empty Production constructor.
     */
    Production()
    {

    }

    /**
     * Sets the productionName attribute of a production.
     * @param productionName Name to set.
     */
    public void setProductionName(String productionName)
    {
        this.productionName = productionName;
    }

    /**
     * Gets the name of this production/
     * @return The name of this production as a String.
     */
    public String getProductionName()
    {
        return this.productionName;
    }

    /**
     * Gets a choice from the choices arraylist.
     * @param i Index of choice to get from the choices arraylist.
     * @return A choice from the choices arraylist.
     */
    public Choice getChoice(final int i)
    {
        return this.choices.get(i);
    }

    /**
     * Gets the number of choices in the choices arraylist.
     * @return Number of choices in the choices arraylist as an int.
     */
    public int getNumberOfChoices()
    {
        return this.choices.size();
    }

    /**
     * Adds a choice to the choices arraylist.
     * @param choice Choice to add.
     */
    public void addChoice(Choice choice)
    {
        this.choices.add(choice);
    }

    /**
     * Converts a production to a String exactly as it is in the grammar text file.
     * @return The production in string form.
     */
    public String toString()
    {
        String output = productionName + FileOperations.PRODUCTION_NAME_SPLITTER;

        for(int i = 0; i < choices.size(); i++)
        {
            output+= choices.get(i);
            boolean isTheLastChoice = i == choices.size() - 1;
            if(!isTheLastChoice)
            {
                output += "||";
            }

        }
        return output;
    }
}
