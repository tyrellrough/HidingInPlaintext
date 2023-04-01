package com.example.testingproject;

public class Decoder {
    private Grammar grammar;
    public String text;
    public String bitsGenerated = "";

   // public int characterCounter = 0;

   // public String currentCharProgress = "";

    public void decode(String textToDecode, Grammar grammarToUse) {
        grammar = grammarToUse;
        text = textToDecode;
        boolean isTextEmpty = text.length() == 0;
        if(!isTextEmpty) {
            recursiveDecode("START");
        }
    }

    private void recursiveDecode(String productionName) {

        Production production = grammar.getProduction(productionName);

        int choiceTaken = -1;
        boolean foundTerminals = false;
        int longestMatchingLength = 0;
        //need to find the choice that was taken.
        for(int i = 0; i < production.getNumberOfChoices(); i++) {
            /**
             * Only the longest matching terminal can be considered valid.
             * Example. Text = "Hello", Choice 1 terminals = "He", Choice 2 terminals = "Hello".
             * The second choice is the correct choice but both would match when using the
             * String.indexOf() function and return index 0.
             */
            String terminals = production.getChoice(i).getTerminals();
            int index = text.indexOf(terminals);
            foundTerminals = index == 0;


            boolean foundLongerMatchingTerminals = terminals.length() > longestMatchingLength;
            if(foundTerminals & foundLongerMatchingTerminals){
                choiceTaken = i;
                longestMatchingLength = terminals.length();
            }

        }
        if(choiceTaken < 0) {
            //THIS CAN BE CHANGED LATER. ITS FINE LIKE THIS FOR NOW.
            System.out.println("DECODE, RECURSIVE DECODER: TEXT DOES NOT MATCH WITH CHOICE IN PRODUCTION " + productionName);
            System.exit(1);
        }

        Choice validChoice = production.getChoice(choiceTaken);
        text = text.substring(validChoice.getTerminals().length());

        /**
         * Calculate the bits which were extracted and append them to the
         * binary string.
         */
        int numberOfChoices = production.getNumberOfChoices();
        String decodedBinary = BitOperations.convertChoiceNumberToBits(choiceTaken, numberOfChoices);

        /**
         * If we are decoding the last character we need to check that it's length is not over 8 bits.
         * If it is then we need to remove enough leading zeros from the decoded binary.
         *
         * We need to keep track of the current char binary and the number of chars we have decoded.
         */

        boolean isFinalChar = text.length() == 0;
        int overFlowAmount = bitsGenerated.length() % 8;
        if(isFinalChar & overFlowAmount > 0) {
            String currentCharProgress = bitsGenerated.substring(bitsGenerated.length() - overFlowAmount);
            decodedBinary = BitOperations.fixBinary(currentCharProgress,decodedBinary);
            bitsGenerated = bitsGenerated.substring(0, bitsGenerated.length() - overFlowAmount);

        }



        bitsGenerated += decodedBinary;

        for (int i = 0; i < validChoice.getNonTerminals().size(); i++) {
            if(text == ""){
                return;
            }
            String nonTerminal = validChoice.getNonTerminal(i);
            recursiveDecode(nonTerminal);
        }

    }


}
