package com.example.hidinginplaintextjavafx;

/**
 * A class which provides encoding functionality. It takes a secret message and a grammar
 * and creates text which encodes the secret message.
 * @author Tyrell R
 * @version 1.0
 */
public class Encoder {

    /**
     * Grammar to use.
     */
    private Grammar grammar;
    /**
     * Text which has been generated.
     */
    public String generatedText = "";
    /**
     * Binary form of the secret message to be encoded.
     */
    public String binaryToEncode = "";

    /**
     * A method to start the recursive encoding method. It sets the binary attribute and grammar
     * attribute of this class then calls the runRecursive method which encodes the binary.
     * @param productionName Name of the starting production.
     * @param textToEncode Secret message to encode.
     * @param g Grammar to use.
     * @sideeffect this.binaryToEncode Binary to encode is removed after encoding.
     * @sideeffect this.generatedText Generated text is appended to here.
     */
    public void run(String productionName, String textToEncode, Grammar g) {
        this.grammar = g;
        String binaryToEncode = StringOperations.convertStringToBinaryString(textToEncode);
        this.binaryToEncode = binaryToEncode;
        boolean isBinaryEmpty = binaryToEncode.length() == 0;
        if(!isBinaryEmpty) {
            runRecursive(productionName);
        }

    }

    /**
     * Converts binary to text using a grammar.
     *
     * Pre-order traversal of a grammar where the path changes depending
     * on the bits which need to be encoded. Each production is a node,
     * recall a production has multiple choices, this is where the path
     * can vary.
     *
     * Steps:
     * 1. Get the number of bits to encode.
     * 2. Check if there are enough bits left to encode, if not then
     *      change the amount of bits to encode to length of bits left.
     * 3. Get that number of bits from front of binaryToEncode string.
     *      Then remove them from binary string.
     * 4. Calculate choice number using that removed bit string then get
     *      and store that choice temporarily.
     * 5. Get the terminals from the selected choice and append then to
     *      generatedText.
     * 6. If all binary has been encoded or the choice contains zero
     *      non-terminals then return (BASE CASE).
     * 7. Loop through all non-terminals of the selected choice and
     *      return if all the binary has already been encoded, otherwise
     *      call this function (runRecursive()) on each non-terminal.
     *
     *
     * @param productionName production to run
     * @sideEffect binaryToEncode, each recursion removes binary until empty.
     * @sideEffect generatedText, each recursion attempts to append terminals.
     */
    private void runRecursive(String productionName) {
        Production p = grammar.getProduction(productionName); //get current production.
        int numberOfChoices = p.getNumberOfChoices(); //get the number of choices in that production.
        int choiceNumber = 0; //initialise to default choice 0.

        int amountOfBitsToEncode = BitOperations.getAmountOfBitsToEncode(numberOfChoices);

        boolean isEnoughBits = amountOfBitsToEncode <= binaryToEncode.length();
        if(!isEnoughBits) {
            amountOfBitsToEncode = binaryToEncode.length();
        }
        String bitsToEncode = binaryToEncode.substring(0,amountOfBitsToEncode);
        binaryToEncode = binaryToEncode.substring(amountOfBitsToEncode);

        //calculate the choice to be taken using the bitsToEncode.
        choiceNumber = BitOperations.convertBitsToChoiceNumber(bitsToEncode);
        Choice selectedChoice = p.getChoice(choiceNumber);

        this.generatedText += selectedChoice.getTerminals();  //append the terminals to the generated text.

        /*
         * If all the binary has been encoded or
         * the current choice does not have non-terminals
         * then return.
         */
        boolean isAllBitsEncoded = binaryToEncode == "";
        boolean currentChoiceHasNonTerminals = p.getChoice(choiceNumber).hasNonTerminals();
        if(isAllBitsEncoded || !currentChoiceHasNonTerminals){
            return;
        }

        /*
         * Loop through each non-terminal in the current choice and pass it
         * to the runRecursive() method.
         */
        for (int i = 0; i < selectedChoice.getNonTerminals().size(); i++) {
            isAllBitsEncoded = binaryToEncode == "";
            if(isAllBitsEncoded) {
                return;
            }
            runRecursive(selectedChoice.getNonTerminal(i));
        }

    }

}
