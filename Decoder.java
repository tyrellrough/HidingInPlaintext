package com.example.hidinginplaintextjavafx;

/**
 * A class for decoding previously generated text using the same grammar used to encode.
 * It tries to retrieve a decoded message from some text.
 * @author Tyrell R
 * @version 1.0
 */
public class Decoder {
    /**
     * Grammar to use.
     */
    private Grammar grammar;
    /**
     * Text to decode.
     */
    public String text;
    /**
     * Bits decoded from text using the grammar.
     */
    public String bitsGenerated = "";

    /**
     * The method to start the recursive decoding method, it assigns the grammar and text
     * attributes of this class for use by the recursive decoding method then calls
     * the recursive decoding method.
     * @param textToDecode The text to be decoded.
     * @param grammarToUse The grammar to use for decoding.
     */
    public void decode(String textToDecode, Grammar grammarToUse) {
        this.text = textToDecode.replaceAll("\\s+",""); //remove whitespace
        grammar = grammarToUse;

        boolean isTextEmpty = text.length() == 0;
        if(!isTextEmpty) {
            recursiveDecode("START");
        }
    }

    /**
     * Coverts text to binary.
     *
     * It's recursive parsing algorithm which converts text to binary using a grammar.
     * Grammars are in Greibach Normal Form (GNF) which makes parsing easier.
     * For a grammar to be in GNF, in each production all terminals must come
     * before all non-terminals.
     *
     * Steps:
     * 1. Find the choice that taken by comparing the terminals in each choice
     *      to the start of the text. The longest matching choice is the choice that
     *      was taken.
     * 2. If there was no matching choice then exit.
     * 3. Remove the matching text from the text input.
     * 4. Calculate the decoded binary using the choice number and number of choices
     *      in the production.
     * 5. Check if we are decoding the last character of the text. If so we need to check
     *      how long the decoded binary is. If the last character in the binary is longer
     *      than eight characters then it will not be valid binary. To fix this remove enough zeros
     *      from the middle of the last character. This occurs because the number of bits a production
     *      can encode depends on the number of choices, and it doesn't always add up to a multiple of
     *      eight.
     * 6. For every non-terminal in the valid choice, check if the text is empty, if so then return (base case).
     *      Otherwise, call recursiveDecode(string) on each non-terminal (recursive case).
     *
     *
     * @param productionName The production to check.
     * @sideEffect bitsGenerated Appends the decoded binary to bitsGenerated.
     * @sideEffect text Removes the matching terminals from front of this text.
     */
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
            terminals = terminals.replaceAll("\\s+","");
            int index = text.indexOf(terminals);
            foundTerminals = index == 0;

            boolean foundLongerMatchingTerminals = terminals.length() >= longestMatchingLength;
            if(foundTerminals & foundLongerMatchingTerminals){
                choiceTaken = i;
                longestMatchingLength = terminals.length();
            }

        }
        if(choiceTaken < 0) {
            //THIS CAN BE CHANGED LATER. ITS FINE LIKE THIS FOR NOW.
            System.out.println("DECODE, RECURSIVE DECODER: TEXT DOES NOT MATCH WITH CHOICE IN PRODUCTION " + productionName);
           // System.exit(1);
            return;
        }

        Choice validChoice = production.getChoice(choiceTaken);
        String validChoiceTerminals = validChoice.getTerminals();
        validChoiceTerminals = validChoiceTerminals.replaceAll("\\s+","");

        text = text.substring(validChoiceTerminals.length());

        /*
         * Calculate the bits which were extracted and append them to the
         * binary string.
         */
        int numberOfChoices = production.getNumberOfChoices();
        String decodedBinary = BitOperations.convertChoiceNumberToBits(choiceTaken, numberOfChoices);

        /*
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
