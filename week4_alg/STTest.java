import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel_Tsurko
 * Date: 10/26/13
 * Time: 11:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class STTest {
    @Test
    public void testSymbolTableULPut() throws Exception {
        STUL<String, Integer> st = new STUL<String, Integer>();
        st.Put("1", 1);
        st.Put("2", 2);

        assert st.Get("1") == 1;
        assert st.Get("2") == 2;
    }

    @Test
    public void testSymbolTableULDelete() throws Exception {
        STUL<String, Integer> st = new STUL<String, Integer>();
        st.Put("1", 1);
        st.Put("2", 2);

        st.Delete("2");

        assert st.Get("1") == 1;
        assert st.Size() == 1;
        assert st.Contains("2") == false;
    }

    @Test
    public void testSymbolTableOAPut() throws Exception {
        STOA<String, Integer> st = new STOA<String, Integer>(10);
        st.Put("1", 1);
        st.Put("2", 2);

        assert st.Get("1") == 1;
        assert st.Get("2") == 2;
    }

    @Test
    public void testSymbolTableOADelete() throws Exception {
        STOA<String, Integer> st = new STOA<String, Integer>(10);
        st.Put("1", 1);
        st.Put("2", 2);

        st.Delete("2");

        assert st.Get("1") == 1;
        assert st.Size() == 1;
        assert st.Contains("2") == false;
    }
}
