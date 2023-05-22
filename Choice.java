package com.example.hidinginplaintextjavafx;

import java.util.ArrayList;

/**
 * @Author Tyrell R
 * @version 1.0
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

    /**
     * Empty choice constructor.
     */
    Choice()
    {

    }


    public boolean hasNonTerminals() {
        return !(nonTerminals.size() == 0);
    }

    /**
     * A method to set a choice's terminals.
     * @param terminals Terminal string to set.
     */
    public void setTerminals(final String terminals)
    {
        this.terminals = terminals;
    }

    /**
     * A method to get a choice's terminals.
     * @return The choice's terminals.
     */
    public String getTerminals()
    {
        return this.terminals;
    }

    /**
     * A method that adds a non-Terminal to this choice's non-terminal arraylist.
     * @param nonTerminal Non-terminal string to add to the non-terminal arraylist.
     */
    public void addNonTerminal(final String nonTerminal)
    {
        this.nonTerminals.add(nonTerminal);
    }

    /**
     * A method to get a non-terminal from a choice's non-terminal arraylist.
     * @param index Index of non-terminal to get.
     * @return A non-terminal string.
     */
    public String getNonTerminal(final int index)
    {
        return this.nonTerminals.get(index);
    }

    /**
     * A method which gets a choice's non-terminal arraylist.
     * @return This choice's non-terminal arraylist.
     */
    public ArrayList<String> getNonTerminals() {
        return this.nonTerminals;
    }

    /**
     * Converts a choice to string in this format:
     * terminals ¬NONTERMINAL,NONTERMINAL2
     * E.G. " Of love and hope and endless light¬LINE15"
     * @return
     */
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
