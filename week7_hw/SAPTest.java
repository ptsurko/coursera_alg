import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel_Tsurko
 * Date: 11/17/13
 * Time: 11:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class SAPTest {

    @Test(expected = java.lang.IndexOutOfBoundsException .class)
    public void testInvalidLength1() {
        In in = new In("digraph1.txt");
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);

        sap.length(-1, 0);
    }

    @Test(expected = java.lang.IndexOutOfBoundsException .class)
    public void testInvalidLength2() {
        In in = new In("digraph1.txt");
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);

        sap.length(0, 13);
    }

    @Test(expected = java.lang.IndexOutOfBoundsException .class)
    public void testInvalidLength3() {
        In in = new In("digraph1.txt");
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);

        sap.length(100000, 0);
    }


    @Test
     public void testValidLength() {
        In in = new In("digraph1.txt");
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);

        assert sap.length(0, 0) == 0;
        assert sap.length(12, 12) == 0;
    }

//    @Test
//    public void testSAPImutable() {
//        In in = new In("digraph1.txt");
//        Digraph G = new Digraph(in);
//        SAP sap = new SAP(G);
//    }
}
