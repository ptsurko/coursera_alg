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

    @Test
    public void testIsNoun6() {
        WordNet wordnet = new WordNet("synsets6.txt", "hypernyms6TwoAncestors.txt");

        int count = 0;
        for(String s : wordnet.nouns()) {
            count++;
        }

        assert count == 6;
    }


    @Test
    public void testIsNoun15() {
        WordNet wordnet = new WordNet("synsets15.txt", "hypernymsPath15.txt");

        assert wordnet.isNoun("a") == true;
    }

    @Test
    public void testIsNoun8() {
        WordNet wordnet = new WordNet("synsets15.txt", "hypernymsTree15.txt");

        assert wordnet.isNoun("a") == true;
    }

    @Test
    public void testIsNounAll() {
        WordNet wordnet = new WordNet("synsets.txt", "hypernyms.txt");

        assert wordnet.isNoun("'hood") == true;
    }

    @Test
    public void testNouns() {
        WordNet wordnet = new WordNet("synsets.txt", "hypernyms.txt");

        int count = 0;
        for(String s : wordnet.nouns()) {
            count++;
        }
        assert count == 119188;
    }

    @Test
    public void testNounNotFound() {
        WordNet wordnet = new WordNet("synsets6.txt", "hypernyms6TwoAncestors.txt");

        assert wordnet.isNoun("abced") == false;
    }


    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testIsNotDAG() {
        new WordNet("synsets6.txt", "notDAG.txt");
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testIsNotDAG2() {
        new WordNet("synsets3.txt", "hypernymsInvalidCycle.txt");
    }

    @Test
     public void testPath() {
        WordNet wordnet = new WordNet("synsets6.txt", "hypernyms6TwoAncestors.txt");

        assert wordnet.distance("a", "c") == 4;
        assert wordnet.distance("a", "d") == 3;
        assert wordnet.distance("a", "e") == 2;
        assert wordnet.distance("a", "f") == 1;
        assert wordnet.distance("a", "b") == 1;
    }

    @Test
    public void testPath15() {
        WordNet wordnet = new WordNet("synsets15.txt", "hypernymsPath15.txt");

        wordnet.distance("a", "o");
//        wordnet.distance("a", "d") == 3;
//        assert wordnet.distance("a", "e") == 2;
//        assert wordnet.distance("a", "f") == 1;
    }

    @Test
    public void testSAP() {
        WordNet wordnet = new WordNet("synsets6.txt", "hypernyms6TwoAncestors.txt");

        assert wordnet.sap("a", "c").compareTo("a") == 0;
        assert wordnet.sap("a", "d").compareTo("a") == 0;
        assert wordnet.sap("d", "b").compareTo("a") == 0 || wordnet.sap("d", "b").compareTo("d") == 0;
    }

    @Test
    public void testSAP15() {
        WordNet wordnet = new WordNet("synsets15.txt", "hypernymsPath15.txt");

        assert wordnet.distance("a", "a") == 0;
        String res = wordnet.sap("a", "a");
    }


    @Test
    public void testSAPAll() {
        WordNet wordnet = new WordNet("synsets.txt", "hypernyms.txt");

        assert wordnet.sap("individual", "edible_fruit").compareTo("physical_entity") == 0;
    }

    @Test
    public void testDistance() {
        WordNet wordnet = new WordNet("synsets.txt", "hypernyms.txt");

        assert wordnet.distance("chaparral_pea", "mundaneness") == 14;
    }

    @Test
    public void testDistance2() {
        WordNet wordnet = new WordNet("synsets15.txt", "hypernymsTree15.txt");

        assert wordnet.distance("c", "b") == 2;
    }
}
