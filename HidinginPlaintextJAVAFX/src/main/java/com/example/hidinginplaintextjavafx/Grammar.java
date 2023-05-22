package com.example.hidinginplaintextjavafx;

import java.util.HashMap;

/**
 * A class representing a single context free grammar (CFG).
 * @author Tyrell R
 * @version 1.0
 */
public class Grammar {
    /**
     * A hashmap used to store all productions of the CFG.
     * The key is the name of a production, and each key maps
     * to a single production.
     */
    private HashMap<String, Production> productions = new HashMap<String, Production>();

    /**
     * Add a production to this grammar's productions hashmap.
     * @param productionName Key of production.
     * @param production Production to add to productions hashmap.
     */
    public void addProduction(String productionName, Production production) {
        this.productions.put(productionName, production);
    }

    /**
     * Get a production from this grammar's production hashmap.
     * @param productionName Production to get.
     * @return A production.
     */
    public Production getProduction(String productionName) {
        return this.productions.get(productionName);
    }

    /**
     * A method which checks if the grammar is valid. A grammar is valid if each production has
     * 2^n choices where n is a natural number or zero.
     * @return An empty string if the grammar is valid, or string containing the names of
     * invalid productions and the invalid number of choices if not valid.
     */
    public String checkGrammar() {
        String errorMessage = "";
        //loop through every production in the grammar
        for (Production p : productions.values()) {
            int numberOfChoices = p.getNumberOfChoices();
            double result = (Math.log(numberOfChoices) / Math.log(2));
            boolean isValidNumberOfChoices = (result % 1) == 0; //check if result is a whole number
            if(!isValidNumberOfChoices) {
                String messageToAttach = "Production: \"" + p.getProductionName() +
                        "\" Invalid number of choices: \"" + numberOfChoices + "\". ";
                errorMessage += messageToAttach;

            }
        }
        return errorMessage;
    }

    /**
     * Returns the grammar exactly as it appears in its text file as a string.
     * @return This grammar in string form.
     */
    public String toString() {
        String output = "";
        for (Production production : productions.values()) {
            output += production.toString();
            output += "\n";
        }
        return output;
    }







}

