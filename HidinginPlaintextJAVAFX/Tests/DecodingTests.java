import com.example.hidinginplaintextjavafx.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

public class DecodingTests {
    @Test
    public void decodeAUppercase() throws FileNotFoundException {
        Grammar g = FileOperations.parseGrammarTextFile("PoetryGrammar.txt");
        Decoder d = new Decoder();
        String textToDecode = "The night falls softly, like a feather, In shadows deep and dark and grand";
        d.decode(textToDecode,g);
        String resultBinary = d.bitsGenerated;
        String resultText = StringOperations.convertBinaryStringToString(resultBinary);
        String expected = "A";
        Assert.assertEquals(expected,resultText);
    }
    @Test
    public void decodeALowercase() throws FileNotFoundException {
        Grammar g = FileOperations.parseGrammarTextFile("PoetryGrammar.txt");
        Decoder d = new Decoder();
        String textToDecode = "The night is a symphony, a melody of dreams, In shadows deep and dark and grand";
        d.decode(textToDecode,g);
        String resultBinary = d.bitsGenerated;
        String resultText = StringOperations.convertBinaryStringToString(resultBinary);
        String expected = "a";
        Assert.assertEquals(expected,resultText);
    }
    @Test
    public void decodeEmpty() throws FileNotFoundException {
        Grammar g = FileOperations.parseGrammarTextFile("PoetryGrammar.txt");
        Decoder d = new Decoder();
        String textToDecode = "";
        d.decode(textToDecode,g);
        String resultBinary = d.bitsGenerated;
        String resultText = StringOperations.convertBinaryStringToString(resultBinary);
        String expected = "";
        Assert.assertEquals(expected,resultText);
    }
    @Test
    public void decodeHello() throws FileNotFoundException {
        Grammar g = FileOperations.parseGrammarTextFile("PoetryGrammar.txt");
        Decoder d = new Decoder();
        String textToDecode = "The night falls softly, like a feather, A time of reflection, a moment to last, The moon is a conductor, leading the way, As the night unfolds, in its darkness and fervor The night is a canvas, painted with dark hues, A moment of solace, where worries can be beat. The city lights twinkle, in the distance far, And in this stillness, we can find our own might. The night is a sanctuary, a place to find rest, To places unseen, where new worlds dawn.";
        d.decode(textToDecode,g);
        String resultBinary = d.bitsGenerated;
        String resultText = StringOperations.convertBinaryStringToString(resultBinary);
        String expected = "Hello";
        Assert.assertEquals(expected,resultText);
    }
    @Test
    public void decodeANoWhitespace() throws FileNotFoundException {
        Grammar g = FileOperations.parseGrammarTextFile("PoetryGrammar.txt");
        Decoder d = new Decoder();
        String textToDecode = "Thenightisasymphony,a melodyofdreams,Inshadows deepanddarkandgrand";
        d.decode(textToDecode,g);
        String resultBinary = d.bitsGenerated;
        String resultText = StringOperations.convertBinaryStringToString(resultBinary);
        String expected = "a";
        Assert.assertEquals(expected,resultText);
    }
}
