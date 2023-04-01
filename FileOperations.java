package com.example.testingproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A helper class providing (.txt) file operations including
 * reading grammars.
 */
public class FileOperations {

    static final String PRODUCTION_NAME_SPLITTER = "->";
    static final String CHOICE_SPLITTER = "\\|\\|";

    static final String TERMINAL_NONTERMINAL_SPLITTER = "¬";

    static final String NONTERMINAL_SPLITTER = ",";

    static public Grammar parseGrammarTextFile(String textFile) throws FileNotFoundException {
        /**
         * Grammar to be returned.
         */
        Grammar grammar = new Grammar();
        /**
         * Grammar text file scanner.
         */
        Scanner grammarFileScanner;

        /**
         * Attempt to create a grammar file and pass it
         * to the scanner.
         */
        try {
            File grammarFile = new File(textFile);
            grammarFileScanner = new Scanner(grammarFile);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("FileOperations.parseGrammarTextFile. Could not find file:\""
                    + textFile + "\"");
        }

        while (grammarFileScanner.hasNextLine()) {
            //Each line has a production.
            Production production = new Production();

            //Get the next line.
            String currentLine = grammarFileScanner.nextLine();

            /**
             * Check if the current line contains the PRODUCTION_NAME_SPLITTER
             */
            boolean hasProductionNameSplitter = currentLine.contains(PRODUCTION_NAME_SPLITTER);
            if(!hasProductionNameSplitter) {
                String errorMessage = "Current line does not contain production name splitter \"" +
                        PRODUCTION_NAME_SPLITTER +"\". Current line: " + currentLine;
                throw new RuntimeException(errorMessage);
            }

            /**
             * Get the index of the production name splitter in the current line then
             * split the current line into two sections. One string containing all characters
             * before the production line splitter, and another string containing all characters
             * after the production name splitter.
             * e.g. Before: "START->terminals NONTEMRINAL"
             * After: "START" and "terminals NONTERMINAL"
             */
            int productionNameSplitterIndex = currentLine.indexOf("->");
            String productionName = currentLine.substring(0,productionNameSplitterIndex);
            String productionChoices = currentLine.substring(productionNameSplitterIndex +
                    PRODUCTION_NAME_SPLITTER.length());

            /**
             * Split the productions' choices into an array then loop through
             * the choices array.
             * A choice is of the format:
             * "terminals ¬NONTERMINAL1,NONTERMINAL2" OR
             * "terminals" OR
             * "terminal ¬NONTERMIAL1" OR
             * "¬NONTERMINAL1" OR
             * "¬NONTERMINAL1,NONTERMINAL2"
             *
             */
            String[] choicesArray = productionChoices.split(CHOICE_SPLITTER);
            for(int i = 0; i < choicesArray.length; i++) {
                Choice choice = new Choice();
                //System.out.println(choicesArray[i]);
                /**
                 * Split the choice into its terminal symbols and non-terminal symbols.
                 */
                String currentChoice = choicesArray[i];
                String terminalsNonTerminalsArray[] = currentChoice.split(TERMINAL_NONTERMINAL_SPLITTER);

                /**
                 * terminalNonTerminalsArray[0] contains the choices' terminal symbols.
                 * terminalNonTerminalsArray[1] contains the choices' non-terminal symbols.
                 */
                String terminals = terminalsNonTerminalsArray[0];
                choice.setTerminals(terminals);

                boolean hasNonTerminals = terminalsNonTerminalsArray.length > 1;
                if(hasNonTerminals) {
                    String nonTerminals = terminalsNonTerminalsArray[1];
                    /**
                     * Each choice can have none, one, or many non-terminals.
                     * Non-terminals are separated by commas.
                     */
                    boolean hasMultipleNonTerminals = nonTerminals.contains(NONTERMINAL_SPLITTER);
                    if(hasMultipleNonTerminals) {
                        String nonTerminalsArray[] = nonTerminals.split(NONTERMINAL_SPLITTER);
                        for(int j = 0; j < nonTerminalsArray.length; j++) {
                            String nonTerminal = nonTerminalsArray[j];
                            choice.addNonTerminal(nonTerminal);
                        }
                    } else if (!hasMultipleNonTerminals) {
                        String nonTerminal = nonTerminals;
                        choice.addNonTerminal(nonTerminal);
                    }
                } //end of non-terminal if statement.



                production.addChoice(choice);
                production.setProductionName(productionName);
            } //end of choice loop.
            grammar.addProduction(productionName,production);

        } //end of get line loop.
        return grammar;
    } //end of parse grammar loop.
}
