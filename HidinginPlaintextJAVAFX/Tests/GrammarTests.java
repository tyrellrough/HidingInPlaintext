import com.example.hidinginplaintextjavafx.FileOperations;
import com.example.hidinginplaintextjavafx.Grammar;
import com.example.hidinginplaintextjavafx.StringOperations;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

public class GrammarTests {


    @Test
    /**
     * Manual test. Just checking if the grammar printout looks the same as
     * the text file. The productions are stored in a hashtable, so they print out
     * not in order.
     */
    public void ReadGrammarFileNotEmpty() throws FileNotFoundException {
        Grammar g = FileOperations.parseGrammarTextFile("ListGrammar.txt");
        System.out.println(g);
    }

    @Test
    public void checkGrammarValidTrue() throws FileNotFoundException {
        Grammar g = FileOperations.parseGrammarTextFile("ListGrammar.txt");
        String s = g.checkGrammar();
        Assert.assertEquals("",s);
    }

    @Test
    public void checkGrammarValidFalse() throws FileNotFoundException {
        Grammar g = FileOperations.parseGrammarTextFile("InvalidGrammar.txt");
        String s = g.checkGrammar();
        System.out.println(s);
        //Assert.assertTrue(!isValid);
    }
}
