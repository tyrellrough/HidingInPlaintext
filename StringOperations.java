package com.example.testingproject;

import java.nio.charset.Charset;

public class StringOperations {

    public static String convertStringToBinaryString(String string) {
        if(!isOnlyASCII(string)) {
            System.out.println("Contains non ASCII characters");
            System.exit(1);
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

    public static String convertBinaryStringToString(String binaryString) {
        int stringLength = binaryString.length();
        if(stringLength%8 != 0) {
            System.out.println("Not a multiple of 8");
            System.exit(1);
        }
        int numberOfCharacters = stringLength/8;
        String output = "";
        for(int i = 0; i < numberOfCharacters; i++) {
            String strCurrentCharacter = binaryString.substring(i * 8, (i+1)*8);
            int integer = Integer.parseInt(strCurrentCharacter, 2);
            char c = (char) integer;
            output += c;
        }
        return output;
    }

    private static String padString(String string, int desiredLength) {
        String paddedString = String.format("%1$" + desiredLength + "s",string)
                .replace(' ', '0');
        return paddedString;
    }

    public static String rightPadString(String string, int desiredLength) {
        String paddedString = String.format("%1$-" + desiredLength + "s",string)
                .replace(' ', '0');
        return paddedString;
    }

    public static Boolean isOnlyASCII(String string) {
        return Charset.forName("US-ASCII").newEncoder().canEncode(string);
    }

}

