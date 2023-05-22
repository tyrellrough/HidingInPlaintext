import com.example.hidinginplaintextjavafx.StringOperations;
import org.junit.Assert;
import org.junit.Test;

public class StringTests {
    @Test
    public void convertToBinaryApple() {
        String stringToConvert = "Apple";
        String expectedBits = "0100000101110000011100000110110001100101";
        String result = StringOperations.convertStringToBinaryString(stringToConvert);
        Assert.assertEquals(expectedBits,result);
    }
    @Test
    public void convertToStringAppleBinary() {
        String bitsToConvert = "0100000101110000011100000110110001100101";
        String expectedString = "Apple";
        String result = StringOperations.convertBinaryStringToString(bitsToConvert);
        Assert.assertEquals(expectedString,result);
    }
    @Test
    public void convertToBinaryEmpty() {
        String stringToConvert = "";
        String expectedBits = "";
        String result = StringOperations.convertStringToBinaryString(stringToConvert);
        Assert.assertEquals(expectedBits,result);
    }
    @Test
    public void convertToStringEmpty() {
        String bitsToConvert = "";
        String expectedString = "";
        String result = StringOperations.convertBinaryStringToString(bitsToConvert);
        Assert.assertEquals(expectedString,result);
    }
    @Test
    public void convertToStringNotMultipleOf8() {
        String bitsToConvert = "01000001011100000111000001101100011001";
        String expectedString = "-1(ERROR)";
        String result = StringOperations.convertBinaryStringToString(bitsToConvert);
        Assert.assertEquals(expectedString,result);
    }

    @Test
    public void checkIfASCIIEmpty() {
        String stringToCheck = "";
        boolean result =  StringOperations.isOnlyASCII(stringToCheck);
        Assert.assertTrue(result);
    }
    @Test
    public void checkIfASCIITrue() {
        String stringToCheck = "APPLE";
        boolean result =  StringOperations.isOnlyASCII(stringToCheck);
        Assert.assertTrue(result);
    }
    @Test
    public void checkIfASCIIFalse() {
        String stringToCheck = "§";
        boolean result = StringOperations.isOnlyASCII(stringToCheck);
        Assert.assertTrue(!result);
    }
    @Test
    public void checkIfASCIIMixedFalse() {
        String stringToCheck = "b§a";
        boolean result = StringOperations.isOnlyASCII(stringToCheck);
        Assert.assertTrue(!result);
    }
    @Test
    public void padStringLeft8() {
        String stringToPad = "11111";
        String expected = "00011111";
        String result = StringOperations.padString(stringToPad,8);
        Assert.assertEquals(expected,result);
    }
    @Test
    public void padStringLeftEmpty8() {
        String stringToPad = "";
        String expected = "00000000";
        String result = StringOperations.padString(stringToPad,8);
        Assert.assertEquals(expected,result);
    }
    @Test
    public void padStringRight8() {
        String stringToPad = "11111";
        String expected = "11111000";
        String result = StringOperations.rightPadString(stringToPad,8);
        Assert.assertEquals(expected,result);
    }
    @Test
    public void padStringRightEmpty8() {
        String stringToPad = "";
        String expected = "00000000";
        String result = StringOperations.rightPadString(stringToPad,8);
        Assert.assertEquals(expected,result);
    }





}
