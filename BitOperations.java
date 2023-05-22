package com.example.hidinginplaintextjavafx;

/**
 * BitOperations.java provides methods for manipulating and generating binary.
 * @author Tyrell R
 * @version 1.0
 */
public class BitOperations {

    /**
     * Calculates an amount of bits to encode using a number of choices.
     * @param numberOfChoices Number of choices in the production.
     * @return Integer value of Log2(numberOfChoices)
     */
    public static int getAmountOfBitsToEncode(int numberOfChoices) {
        int numberOfBitsToEncode = (int)(Math.log(numberOfChoices) / Math.log(2));
        return numberOfBitsToEncode;
    }

    /**
     * Takes a string of bits and converts them to an integer which denotes
     * a choice to be taken.
     * @param bits Binary to convert.
     * @return Base 10 integer value of the bits.
     */
    public static int convertBitsToChoiceNumber(String bits) {
        if(bits == "") {
            return 0;
        }
        int choiceNumber = Integer.parseInt(bits,2);

        return choiceNumber;
    }


    public static String convertChoiceNumberToBits(int choiceNumber, int numberOfChoices) {
        if(numberOfChoices == 1) {
            return "";
        }
        String binary = Integer.toBinaryString(choiceNumber);
        int amountOfBits = BitOperations.getAmountOfBitsToEncode(numberOfChoices);
        //append zeros to the front so the binary string is of the length amountOfBits.
        binary = StringOperations.padString(binary,amountOfBits);

        return binary;
    }

    /**
     * When decoding, the bits generated for the last character might exceed eight bits.
     * To fix this, this method takes the string of bits that existed before there was overflow
     * and the string of bits that when appended caused the overflow and tries to remove all zeros
     * in-between these strings, then adds enough zeros in-between so the length is eight.
     * @param currentBinaryString The already existing binary for a character.
     * @param binaryStringToAppend The binary that needs to be appended.
     * @return A string of eight bits (representing an ASCII character).
     */
    public static String fixBinary(String currentBinaryString, String binaryStringToAppend) {
        String fixedBinary = "";
        //these equal -1 if not found.
        //position of last "1" in the first section of the binary.
        int currentStrLastOneIndex = currentBinaryString.lastIndexOf("1");
        //position of first "1" in the second section of the binary.
        int appendStrFirstOneIndex = binaryStringToAppend.indexOf("1");

        //If a "1" is found in the first binary section.
        if(currentStrLastOneIndex != -1) {
            //there are only "0" (zeros) after so get rid of them.
            currentBinaryString = currentBinaryString.substring(0,currentStrLastOneIndex + 1);
        }
        //If a "1" is found in the second binary section.
        if(appendStrFirstOneIndex != -1) {
            //there are only "0" (zeros) before so get rid of them.
            binaryStringToAppend = binaryStringToAppend.substring(appendStrFirstOneIndex, binaryStringToAppend.length());
        }
        //calculate the amount of zeros needed to make those two binary strings 8 bits long when combined.
        int amountOfZerosNeeded = Math.abs(8 - (binaryStringToAppend.length() + currentBinaryString.length()));

        //if there is no "1" in the second section
        if(appendStrFirstOneIndex == -1) {
            //then we can disregard the second section and just append zeros to the first section
            amountOfZerosNeeded = 8 - currentBinaryString.length();
        }


        if(amountOfZerosNeeded > 0) {
            //append the amount of zeros needed
            fixedBinary = StringOperations.rightPadString(currentBinaryString,amountOfZerosNeeded + currentBinaryString.length());
            //if the second section does not contain "1" then we don't need to run code inside the below if statement.
            if(!(fixedBinary.length() == 8)) {
                fixedBinary += binaryStringToAppend;
            }
        }

        //if the binary is doesn't need to be fixed then just append them.
        if(amountOfZerosNeeded == 0) {
            fixedBinary = currentBinaryString;
            fixedBinary += binaryStringToAppend;
        }

        return fixedBinary;

    }
}
