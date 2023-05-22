package com.example.hidinginplaintextjavafx;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.Scanner;

/**
 * A helper class providing (.txt) file operations including reading grammars.
 * @author Tyrell R
 * @version 1.0
 */
public class FileOperations {

    /**
     * String used to split the production name from the rest of the
     * production.
     */
    static final String PRODUCTION_NAME_SPLITTER = "->";
    /**
     * String "||" used to split choices.
     */
    static final String CHOICE_SPLITTER = "\\|\\|";

    /**
     * String used to split terminals from non-terminals.
     */
    static final String TERMINAL_NONTERMINAL_SPLITTER = "¬";

    /**
     * String used to split non-terminal from each-other.
     */
    static final String NONTERMINAL_SPLITTER = ",";

    /**
     * A method which parses a grammar text file.
     * @param textFile Name of grammar file to parse.
     * @return A grammar constructed as specified in the given grammar text file.
     * @throws FileNotFoundException Thrown if grammar file does not exist.
     */
    static public Grammar parseGrammarTextFile(String textFile) throws FileNotFoundException {
        Grammar grammar = new Grammar(); // Grammar to be returned.
        Scanner grammarFileScanner; // Grammar text file scanner.
        String path = new File("").getAbsolutePath();
        path += "\\Grammars\\";
        path += textFile;

        /*
         * Attempt to create a grammar file and pass it
         * to the scanner.
         */
        try {
            File grammarFile = new File(path);
            grammarFileScanner = new Scanner(grammarFile);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("FileOperations.parseGrammarTextFile. Could not find file:\""
                    + textFile + "\"");
        }

        //loop through each line of the text file.
        while (grammarFileScanner.hasNextLine()) {
            Production production = new Production(); //Each line has a production.

            //Get the next line.
            String currentLine = grammarFileScanner.nextLine();

            /**
             * Check if the current line contains the PRODUCTION_NAME_SPLITTER
             */
            boolean hasProductionNameSplitter = currentLine.contains(PRODUCTION_NAME_SPLITTER);
            if(!hasProductionNameSplitter) {
                System.out.println(currentLine);
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

    /**
     * A method to get all grammar text files in the \Grammars relative directory.
     * @return An array of grammar (.txt) files.
     */
    public static File[] getGrammarFiles() {
        String path = new File("").getAbsolutePath();
        path += "\\Grammars";
        File dir = new File(path);

        File[] matches = dir.listFiles(new FilenameFilter()
        {
            public boolean accept(File dir, String name)
            {
                return name.endsWith(".txt");
            }
        });
        return matches;
    }


}
