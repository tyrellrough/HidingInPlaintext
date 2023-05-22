package com.example.hidinginplaintextjavafx;

import java.nio.charset.Charset;

/**
 * A class which provides operations on Strings.
 * @author Tyrell R
 * @version 1.0
 */
public class StringOperations {

    /**
     * Converts a string of ASCII characters to binary.
     * @param string String to convert.
     * @return String of bits.
     */
    public static String convertStringToBinaryString(String string) {
        if(!isOnlyASCII(string)) {
            System.out.println("Contains non ASCII characters");
            return "-1(ERROR)";
        }
        char[] chars = string.toCharArray();
        String strBinary = "";
        for (char c : chars) {
            int intRepresentation = (int) c;
            String strBinaryChar= Integer.toBinaryString(intRepresentation);
            String paddedBinaryChar = padString(strBinaryChar, 8);
            strBinary += paddedBinaryChar;

        }
        return strBinary;
    }

    /**
     * Converts a String of bits into a String of ASCII characters.
     * @param binaryString String of binary to convert.
     * @return A string of ASCII characters.
     */
    public static String convertBinaryStringToString(String binaryString) {
        int stringLength = binaryString.length();
        if(stringLength%8 != 0) {
            System.out.println("Not a multiple of 8");
            return "-1(ERROR)";
        }
        int numberOfCharacters = stringLength / 8;
        String output = "";
        for(int i = 0; i < numberOfCharacters; i++) {
            String strCurrentCharacter = binaryString.substring(i * 8, (i+1)*8);
            int integer = Integer.parseInt(strCurrentCharacter, 2);
            char c = (char) integer;
            output += c;
        }
        return output;
    }

    /**
     * Pads the left side of a String with zeros.
     * @param string String to pad.
     * @param desiredLength Size of the string after padding.
     * @return A String padded with zeros to the left.
     */
    public static String padString(String string, int desiredLength) {
        String paddedString = String.format("%1$" + desiredLength + "s",string)
                .replace(' ', '0');
        return paddedString;
    }

    /**
     * Pads the right side of a String with zeros.
     * @param string String to pad.
     * @param desiredLength Size of the string after padding.
     * @return A String padded with zeros to the right.
     */
    public static String rightPadString(String string, int desiredLength) {
        String paddedString = String.format("%1$-" + desiredLength + "s",string)
                .replace(' ', '0');
        return paddedString;
    }

    /**
     * Checks if a string only contains ASCII characters.
     * @param string String to check.
     * @return Boolean True if only contains ASCII, Boolean false otherwise.
     */
    public static Boolean isOnlyASCII(String string) {
        return Charset.forName("US-ASCII").newEncoder().canEncode(string);
    }

}


