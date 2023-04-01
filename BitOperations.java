package com.example.testingproject;

import java.util.ArrayList;

public class BitOperations {
    public static int getAmountOfBitsToEncode(int numberOfChoices) {
        int numberOfBitsToEncode = (int)(Math.log(numberOfChoices) / Math.log(2));
        return numberOfBitsToEncode;
    }

    public static int getNumberOfChoicesFromBits(String bits) {
        int length = bits.length();
        int numberOfChoices = (int) Math.pow(2,length);
        return numberOfChoices;
    }

    public static int convertBitsToChoiceNumber(String bits) {
        int choiceNumber = Integer.parseInt(bits,2);

        return choiceNumber;
    }

    public static String convertChoiceNumberToBits(int choiceNumber, int numberOfChoices) {
        String binary = Integer.toBinaryString(choiceNumber);
        int amountOfBits = BitOperations.getAmountOfBitsToEncode(numberOfChoices);
        //append zeros to the front so the binary string is of the length amountOfBits.
        binary = String.format("%1$" + amountOfBits + "s", binary).replace(' ', '0');

        return binary;
    }

    public static String fixBinary(String currentBinaryString, String binaryStringToAppend) {
        String fixedBinary = "";
        int currentStrLastOneIndex = currentBinaryString.lastIndexOf("1");
        int appendStrFirstOneIndex = binaryStringToAppend.indexOf("1");
        if(currentStrLastOneIndex != -1) {
            currentBinaryString = currentBinaryString.substring(0,currentStrLastOneIndex + 1);

        }
        if(appendStrFirstOneIndex != -1) {
            binaryStringToAppend = binaryStringToAppend.substring(appendStrFirstOneIndex, binaryStringToAppend.length());

        }
        int amountOfZerosNeeded = 8 - (binaryStringToAppend.length() + currentBinaryString.length());

        if(amountOfZerosNeeded > 0) {
            fixedBinary = StringOperations.rightPadString(currentBinaryString,amountOfZerosNeeded + currentBinaryString.length());
            fixedBinary += binaryStringToAppend;

        }

        return fixedBinary;

    }
}
