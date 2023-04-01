package com.example.testingproject;

import java.util.ArrayList;

/**
 * Author 2011077
 * A choice is a single group of terminal and non-terminal symbols.
 * A production can contain many choices.
 *
 * In a grammar text file, a choice is indicated by the "|" vertical bar
 * character e.g. START->choice1|choice2|choice3|choice4. This example has 4 choices.
 *
 */
public class Choice {
    //private variables

    /**
     * Terminal symbols are text made up of any ASCII character.
     * They come before the terminal indicator character "¬".
     * e.g. "Hello ¬NAME" where "Hello" is a string of terminals.
     */
    private String terminals;

    /**
     * A non-terminal is a name of a production.
     * e.g.
     * "START->Hello ¬WORLD
     *  WORLD->world."
     * In this example in the first production (first line)
     * the non-terminal would be "WORLD" and it points to
     * the production named "WORLD".
     *
     */
    private ArrayList<String> nonTerminals = new ArrayList<>();

    //Constructors.

    /**
     * Empty choice constructor.
     */
    Choice()
    {

    }

    /**
     * Construct a choice with data.
     * @param terminals String of ASCII characters.
     * @param nonTerminals List of production names to point to.
     */
    Choice(String terminals, ArrayList<String> nonTerminals)
    {

    }

    boolean hasNonTerminals() {
        return !(nonTerminals.size() == 0);
    }


    public void setTerminals(final String terminals)
    {
        this.terminals = terminals;
    }

    public String getTerminals()
    {
        return this.terminals;
    }

    public void addNonTerminal(final String nonTerminal)
    {
        this.nonTerminals.add(nonTerminal);
    }

    public String getNonTerminal(final int index)
    {
        return this.nonTerminals.get(index);
    }

    public ArrayList<String> getNonTerminals() {
        return this.nonTerminals;
    }

    public String toString() {
        String output = terminals;
        boolean containsNonTerminals = nonTerminals.size() != 0;
        if(containsNonTerminals) {
            output += FileOperations.TERMINAL_NONTERMINAL_SPLITTER;
            for(int i = 0; i < nonTerminals.size(); i++) {
                output += nonTerminals.get(i);
                boolean isTheLastNonTerminal = i == nonTerminals.size() - 1;
                if(!isTheLastNonTerminal) {
                    output += ",";
                }
            }
        }

        return output;
    }

}
