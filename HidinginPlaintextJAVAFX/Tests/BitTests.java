import com.example.hidinginplaintextjavafx.BitOperations;
import org.junit.Assert;
import org.junit.Test;

public class BitTests {
    @Test
    public void fixBinary4and6() {
        String expected = "01001100";
        String intial = "0100";
        String bitsToAppend = "001100";
        String result = BitOperations.fixBinary(intial,bitsToAppend);
        Assert.assertEquals(expected,result);
    }
    @Test
    public void fixBinary4and4() {
        String expected = "01001101";
        String intial = "0100";
        String bitsToAppend = "1101";
        String result = BitOperations.fixBinary(intial,bitsToAppend);
        Assert.assertEquals(expected,result);
    }

    @Test
    public void fixBinary6and4() {
        String expected = "01001101";
        String intial = "010011";
        String bitsToAppend = "0001";
        String result = BitOperations.fixBinary(intial,bitsToAppend);
        Assert.assertEquals(expected,result);
    }

    @Test
    public void fixBinary5and4() {
        String expected = "01001101";
        String intial = "010011";
        String bitsToAppend = "0001";
        String result = BitOperations.fixBinary(intial,bitsToAppend);
        Assert.assertEquals(expected,result);
    }

    @Test
    public void fixBinary7and2() {
        String expected = "01001101";
        String intial = "0100110";
        String bitsToAppend = "01";
        String result = BitOperations.fixBinary(intial,bitsToAppend);
        Assert.assertEquals(expected,result);
    }

    @Test
    public void amountOfBitsToEncode0() {
        //2^4 = 16
        int numberOfChoices = 1;
        int expectedNumberOfBits = 0;
        int result = BitOperations.getAmountOfBitsToEncode(numberOfChoices);
        Assert.assertEquals(expectedNumberOfBits,result);
    }
    @Test
    public void amountOfBitsToEncode1() {
        //2^4 = 16
        int numberOfChoices = 2;
        int expectedNumberOfBits = 1;
        int result = BitOperations.getAmountOfBitsToEncode(numberOfChoices);
        Assert.assertEquals(expectedNumberOfBits,result);
    }
    @Test
    public void amountOfBitsToEncode2() {
        //2^4 = 16
        int numberOfChoices = 4;
        int expectedNumberOfBits = 2;
        int result = BitOperations.getAmountOfBitsToEncode(numberOfChoices);
        Assert.assertEquals(expectedNumberOfBits,result);
    }
    @Test
    public void amountOfBitsToEncode3() {
        //2^3 = 8
        int numberOfChoices = 8;
        int expectedNumberOfBits = 3;
        int result = BitOperations.getAmountOfBitsToEncode(numberOfChoices);
        Assert.assertEquals(expectedNumberOfBits,result);
    }
    @Test
    public void amountOfBitsToEncode4() {
        //2^4 = 16
        int numberOfChoices = 16;
        int expectedNumberOfBits = 4;
        int result = BitOperations.getAmountOfBitsToEncode(numberOfChoices);
        Assert.assertEquals(expectedNumberOfBits,result);
    }
    @Test
    public void amountOfBitsToEncode5() {
        //2^5 = 32
        int numberOfChoices = 32;
        int expectedNumberOfBits = 5;
        int result = BitOperations.getAmountOfBitsToEncode(numberOfChoices);
        Assert.assertEquals(expectedNumberOfBits,result);
    }
    @Test
    public void amountOfBitsToEncode6() {
        //2^6 = 64
        int numberOfChoices = 64;
        int expectedNumberOfBits = 6;
        int result = BitOperations.getAmountOfBitsToEncode(numberOfChoices);
        Assert.assertEquals(expectedNumberOfBits,result);
    }
    @Test
    public void convertChoiceNumber0and2() {
        //choices are zero indexed
        int choiceNumber = 0;
        int numberOfChoices = 2;
        String expectedBits = "0";
        String result = BitOperations.convertChoiceNumberToBits(choiceNumber,numberOfChoices);
        Assert.assertEquals(expectedBits,result);
    }
    @Test
    public void convertChoiceNumber1and2() {
        int choiceNumber = 1;
        int numberOfChoices = 2;
        String expectedBits = "1";
        String result = BitOperations.convertChoiceNumberToBits(choiceNumber,numberOfChoices);
        Assert.assertEquals(expectedBits,result);
    }
    @Test
    public void convertChoiceNumber0and4() {
        int choiceNumber = 0;
        int numberOfChoices = 4;
        String expectedBits = "00";
        String result = BitOperations.convertChoiceNumberToBits(choiceNumber,numberOfChoices);
        Assert.assertEquals(expectedBits,result);
    }
    @Test
    public void convertChoiceNumber1and4() {
        int choiceNumber = 1;
        int numberOfChoices = 4;
        String expectedBits = "01";
        String result = BitOperations.convertChoiceNumberToBits(choiceNumber,numberOfChoices);
        Assert.assertEquals(expectedBits,result);
    }
    @Test
    public void convertChoiceNumber4and4() {
        int choiceNumber = 3;
        int numberOfChoices = 4;
        String expectedBits = "11";
        String result = BitOperations.convertChoiceNumberToBits(choiceNumber,numberOfChoices);
        Assert.assertEquals(expectedBits,result);
    }
    @Test
    public void convertChoiceNumber0and8() {
        int choiceNumber = 0;
        int numberOfChoices = 8;
        String expectedBits = "000";
        String result = BitOperations.convertChoiceNumberToBits(choiceNumber,numberOfChoices);
        Assert.assertEquals(expectedBits,result);
    }
    @Test
    public void convertChoiceNumber3and8() {
        int choiceNumber = 3;
        int numberOfChoices = 8;
        String expectedBits = "011";
        String result = BitOperations.convertChoiceNumberToBits(choiceNumber,numberOfChoices);
        Assert.assertEquals(expectedBits,result);
    }
    @Test
    public void convertChoiceNumber8and8() {
        int choiceNumber = 7;
        int numberOfChoices = 8;
        String expectedBits = "111";
        String result = BitOperations.convertChoiceNumberToBits(choiceNumber,numberOfChoices);
        Assert.assertEquals(expectedBits,result);
    }
    @Test
    public void convertChoiceNumber0and16() {
        int choiceNumber = 0;
        int numberOfChoices = 16;
        String expectedBits = "0000";
        String result = BitOperations.convertChoiceNumberToBits(choiceNumber,numberOfChoices);
        Assert.assertEquals(expectedBits,result);
    }
    @Test
    public void convertChoiceNumber5and16() {
        int choiceNumber = 4;
        int numberOfChoices = 16;
        String expectedBits = "0100";
        String result = BitOperations.convertChoiceNumberToBits(choiceNumber,numberOfChoices);
        Assert.assertEquals(expectedBits,result);
    }
    @Test
    public void convertChoiceNumber15and16() {
        int choiceNumber = 15;
        int numberOfChoices = 16;
        String expectedBits = "1111";
        String result = BitOperations.convertChoiceNumberToBits(choiceNumber,numberOfChoices);
        Assert.assertEquals(expectedBits,result);
    }
    @Test
    public void convertBinary0() {
        String binary = "0";
        int expectedChoice = 0;
        int result = BitOperations.convertBitsToChoiceNumber(binary);
        Assert.assertEquals(expectedChoice,result);
    }
    @Test
    public void convertBinary0000() {
        String binary = "0000";
        int expectedChoice = 0;
        int result = BitOperations.convertBitsToChoiceNumber(binary);
        Assert.assertEquals(expectedChoice,result);
    }
    @Test
    public void convertBinary001() {
        String binary = "001";
        int expectedChoice = 1;
        int result = BitOperations.convertBitsToChoiceNumber(binary);
        Assert.assertEquals(expectedChoice,result);
    }
    @Test
    public void convertBinary0100() {
        String binary = "0100";
        int expectedChoice = 4;
        int result = BitOperations.convertBitsToChoiceNumber(binary);
        Assert.assertEquals(expectedChoice,result);
    }
    @Test
    public void convertBinary0000100() {
        String binary = "0000100";
        int expectedChoice = 4;
        int result = BitOperations.convertBitsToChoiceNumber(binary);
        Assert.assertEquals(expectedChoice,result);
    }
    @Test
    public void convertBinary010() {
        String binary = "010";
        int expectedChoice = 2;
        int result = BitOperations.convertBitsToChoiceNumber(binary);
        Assert.assertEquals(expectedChoice,result);
    }
    @Test
    public void convertBinary00010() {
        String binary = "00010";
        int expectedChoice = 2;
        int result = BitOperations.convertBitsToChoiceNumber(binary);
        Assert.assertEquals(expectedChoice,result);
    }














}
