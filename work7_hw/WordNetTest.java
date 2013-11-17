import org.junit.Test;

public class WordNetTest {

    @Test
    public void testIsNoun() {
        WordNet wordnet = new WordNet("synsets6.txt", "hypernyms6TwoAncestors.txt");

        assert wordnet.isNoun("a") == true;
        assert wordnet.isNoun("b") == true;
        assert wordnet.isNoun("c") == true;
        assert wordnet.isNoun("d") == true;
        assert wordnet.isNoun("e") == true;
        assert wordnet.isNoun("f") == true;
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testIsNotDAG() {
        new WordNet("synsets6.txt", "notDAG.txt");
    }

    @Test
    public void testPath() {
        WordNet wordnet = new WordNet("synsets6.txt", "hypernyms6TwoAncestors.txt");

        assert wordnet.distance("a", "c") == 2;
        assert wordnet.distance("a", "d") == 3;
        assert wordnet.distance("a", "e") == 2;
        assert wordnet.distance("a", "f") == 1;

    }
}
