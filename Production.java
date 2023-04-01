package com.example.testingproject;

import java.util.ArrayList;

/**
 * Author 2011077.
 * A production is a single rule in a context free grammar.
 * Each line of a grammar text file is one production.
 * e.g. "START->The ¬SUNMOON,SETS".
 */
public class Production
{
    //Variables

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

    //Constructors

    /**
     * An empty Production constructor.
     */
    Production()
    {

    }

    public void setProductionName(String productionName)
    {
        this.productionName = productionName;
    }

    public String getProductionName()
    {
        return this.productionName;
    }

    public Choice getChoice(final int i)
    {
        return this.choices.get(i);
    }

    public int getNumberOfChoices()
    {
        return this.choices.size();
    }

    public void addChoice(Choice choice)
    {
        this.choices.add(choice);
    }

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
