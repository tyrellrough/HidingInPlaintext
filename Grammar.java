package com.example.testingproject;

import java.util.HashMap;

/**
 * Author 2011077.
 * Grammar.java.
 * A class representing a single context free grammar. (CFG)
 */
public class Grammar {
    /**
     * A hashmap used to store all productions of the CFG.
     * The key is the name of a production, and each key maps
     * to a single production.
     */
    private HashMap<String, Production> productions = new HashMap<String, Production>();

    public String generatedText = "";
    public String binaryToEncode = "";

    public int counter = 0;

    public void addProduction(String productionName, Production production) {
        this.productions.put(productionName, production);
    }

    public Production getProduction(String productionName) {
        return this.productions.get(productionName);
    }

    public String toString() {
        String output = "";
        for (Production production : productions.values()) {
            output += production.toString();
            output += "\n";
        }
        return output;
    }


    private void runRecursive(String productionName) {
        //get current production
        Production p = this.getProduction(productionName);
        //get the number of choices
        int numberOfChoices = p.getNumberOfChoices();
        int choiceNumber = 0;



        /**
         * 1. Get the account of bits to encode.
         * 2. Check there are enough bits left in the binary to encode string.
         * 3. If amount of bits left is less than amount of bits to encode then amouunt of bits to encode becomes
         * the amount of bits left.
         * 3. Store that amount of bits from the input binary string.
         * 4. Remove that amount of bits from the input binary string.
         */
        int amountOfBitsToEncode = BitOperations.getAmountOfBitsToEncode(numberOfChoices);
        boolean isEnoughBits = amountOfBitsToEncode <= binaryToEncode.length();

        if(!isEnoughBits) {
            amountOfBitsToEncode = binaryToEncode.length();
        }
        String bitsToEncode = binaryToEncode.substring(0,amountOfBitsToEncode);
        binaryToEncode = binaryToEncode.substring(amountOfBitsToEncode);

        //get the choice number using the bitsToEncode.
        choiceNumber = BitOperations.convertBitsToChoiceNumber(bitsToEncode);
        Choice selectedChoice = p.getChoice(choiceNumber);

        //append the terminals to the generated text.
        this.generatedText += selectedChoice.getTerminals();

        boolean isAllBitsEncoded = binaryToEncode == "";
        if(isAllBitsEncoded){
            return;
        }

        boolean currentChoiceHasNonTerminals = p.getChoice(choiceNumber).hasNonTerminals();
        if (!currentChoiceHasNonTerminals) {
            return;
        }

        for (int i = 0; i < selectedChoice.getNonTerminals().size(); i++) {
            isAllBitsEncoded = binaryToEncode == "";
            if(isAllBitsEncoded) {
                return;
            }
            runRecursive(selectedChoice.getNonTerminal(i));
        }

    }

    void run(String productionName, String textToEncode) {
        String binaryToEncode = StringOperations.convertStringToBinaryString(textToEncode);
        this.binaryToEncode = binaryToEncode;
        boolean isBinaryEmpty = binaryToEncode.length() == 0;
        if(!isBinaryEmpty) {
            runRecursive(productionName);
        }


    }






}
