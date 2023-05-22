import com.example.hidinginplaintextjavafx.Encoder;
import com.example.hidinginplaintextjavafx.FileOperations;
import com.example.hidinginplaintextjavafx.Grammar;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;


public class EncodingTests {
    @Test
    public void encodeHello() throws FileNotFoundException {
        Grammar g = FileOperations.parseGrammarTextFile("PoetryGrammar.txt");
        Encoder e = new Encoder();
        String productionName = "START";
        String textToEncode = "Hello";
        e.run(productionName,textToEncode, g);
        String result = e.generatedText;
        String expected = "The night falls softly, like a feather, A time of reflection, a moment to last, The moon is a conductor, leading the way, As the night unfolds, in its darkness and fervor The night is a canvas, painted with dark hues, A moment of solace, where worries can be beat. The city lights twinkle, in the distance far, And in this stillness, we can find our own might. The night is a sanctuary, a place to find rest, To places unseen, where new worlds dawn.";
        Assert.assertEquals(expected,result);
    }
    @Test
    public void encodeEmpty() throws FileNotFoundException {
        Grammar g = FileOperations.parseGrammarTextFile("PoetryGrammar.txt");
        Encoder e = new Encoder();
        String productionName = "START";
        String textToEncode = "";
        e.run(productionName,textToEncode, g);
        String result = e.generatedText;
        String expected = "";
        Assert.assertEquals(expected,result);
    }
    @Test
    public void encodeALowercase() throws FileNotFoundException {
        Grammar g = FileOperations.parseGrammarTextFile("PoetryGrammar.txt");
        Encoder e = new Encoder();
        String productionName = "START";
        String textToEncode = "a";
        e.run(productionName,textToEncode, g);
        String result = e.generatedText;
        String expected = "The night is a symphony, a melody of dreams, In shadows deep and dark and grand";
        Assert.assertEquals(expected,result);
    }
    @Test
    public void encodeAUppercase() throws FileNotFoundException {
        Grammar g = FileOperations.parseGrammarTextFile("PoetryGrammar.txt");
        Encoder e = new Encoder();
        String productionName = "START";
        String textToEncode = "A";
        e.run(productionName,textToEncode, g);
        String result = e.generatedText;
        String expected = "The night falls softly, like a feather, In shadows deep and dark and grand";
        Assert.assertEquals(expected,result);
    }

}
