import static org.junit.Assert.*;

public class BSTreeTester {

    BSTree<Integer> test;
    BSTree<Integer> test1;
    BSTree<Integer> test2;

    @org.junit.Before
    public void setUp() throws Exception {
        test = new BSTree<>();
        test1 = new BSTree<>();
        test2 = new BSTree<>();
        test1.insert(50);
        test2.insert(5);
    }

    @org.junit.Test
    public void getRoot() {
        assertEquals(null,test.getRoot());
        int test1int = test1.getRoot().getKey();
        assertEquals(50, test1int);
        int test2int = test2.getRoot().getKey();
        assertEquals(5,test2int);
    }

    @org.junit.Test
    public void getSize() {
        assertEquals(0,test.getSize());
        assertEquals(1,test1.getSize());
        assertEquals(1,test2.getSize());
    }

    @org.junit.Test
    public void insert() {
        test1.insert(25);
        test.insert(1);
        test1.insert(75);
        assertTrue(test1.findKey(25));
        assertTrue(test1.findKey(75));
        assertTrue(test.findKey(1));
    }

    @org.junit.Test
    public void findKey() {
        test1.insert(26);
        test.insert(2);
        test1.insert(76);
        assertTrue(test1.findKey(26));
        assertTrue(test1.findKey(76));
        assertTrue(test.findKey(2));
    }

    @org.junit.Test
    public void insertData() {
        test1.insertData(26,1);
        test.insertData(2,1);
        test1.insertData(76,1);
        int test1int = test1.findDataList(26).getFirst();
        assertEquals(1,test1int);
        int test2int = test.findDataList(2).getFirst();
        assertEquals(1,test2int);
        int test3int = test1.findDataList(76).getFirst();
        assertEquals(1,test3int);
    }

    @org.junit.Test
    public void findDataList() {
        test1.insertData(27,1);
        test.insertData(3,1);
        test1.insertData(77,1);
        int test1int = test1.findDataList(27).getFirst();
        assertEquals(1,test1int);
        int test2int = test.findDataList(3).getFirst();
        assertEquals(1,test2int);
        int test3int = test1.findDataList(77).getFirst();
        assertEquals(1,test3int);
    }

    @org.junit.Test
    public void findHeight() {
        assertEquals(-1,test.findHeight());
        assertEquals(0,test1.findHeight());
        test2.insert(1);
        assertEquals(1,test2.findHeight());

    }

    @org.junit.Test
    public void iterator() {
    }

    @org.junit.Test
    public void intersection() {
    }

    @org.junit.Test
    public void levelCount() {
    }
}