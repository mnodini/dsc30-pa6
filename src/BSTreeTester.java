import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.*;

public class BSTreeTester {

    BSTree<Integer> test;
    BSTree<Integer> test1;
    BSTree<Integer> test2;
    BSTree<Integer> myTree;
    BSTree<Integer> EC;
    BSTree<Integer> EC2;

    @org.junit.Before
    public void setUp() throws Exception {
        test = new BSTree<>();
        test1 = new BSTree<>();
        test2 = new BSTree<>();
        EC = new BSTree<>();
        EC2 = new BSTree<>();
        test1.insert(50);
        test2.insert(5);
        //myTree
        myTree = new BSTree<>();
        myTree.insert(8);
        myTree.insert(3);
        myTree.insert(10);
        myTree.insert(1);
        myTree.insert(6);
        myTree.insert(4);
        myTree.insert(7);
        myTree.insert(10);
        myTree.insert(14);
        myTree.insert(13);
        //EC Tree
        EC.insert(5);
        EC.insert(1);
        EC.insert(10);
        EC.insert(0);
        EC.insert(4);
        EC.insert(7);
        EC.insert(9);
        //EC2 Tree
        EC2.insert(10);
        EC2.insert(20);
        EC2.insert(7);
        EC2.insert(4);
        EC2.insert(9);

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
        test1.insert(1);
        test1.insert(1);
        test1.insert(50);
        test1.insert(50);
        test1.insert(1);
        test1.insert(1);
        test1.insert(75);
        assertEquals(3,test1.getSize());
        assertEquals(9,myTree.getSize());
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
        assertTrue(myTree.findKey(7));
        assertTrue(myTree.findKey(13));
    }

    @org.junit.Test
    public void insertData() {
        test1.insert(26);
        test.insert(2);
        test1.insert(76);
        test1.insertData(26,1);
        test.insertData(2,1);
        test1.insertData(76,1);
        int test1int = test1.findDataList(26).getFirst();
        assertEquals(1,test1int);
        int test2int = test.findDataList(2).getFirst();
        assertEquals(1,test2int);
        int test3int = test1.findDataList(76).getFirst();
        assertEquals(1,test3int);
        myTree.insertData(7,100);
        myTree.insertData(13,10);
        int myTreeint1 = myTree.findDataList(7).getFirst();
        int myTreeint2 = myTree.findDataList(13).getFirst();
        assertEquals(100,myTreeint1);
        assertEquals(10,myTreeint2);

    }

    @org.junit.Test
    public void findDataList() {
        test1.insert(27);
        test.insert(3);
        test1.insert(77);
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
        assertEquals(3,myTree.findHeight());

    }

    @org.junit.Test
    public void iterator() {
        Iterator<Integer> iter = myTree.iterator();
        assertEquals(new Integer(1),iter.next());
        assertEquals(new Integer(3),iter.next());
        assertEquals(new Integer(4),iter.next());
        assertEquals(new Integer(6),iter.next());
        assertEquals(new Integer(7),iter.next());
        assertEquals(new Integer(8),iter.next());
        assertEquals(new Integer(10),iter.next());
        assertEquals(new Integer(13),iter.next());
        assertEquals(new Integer(14),iter.next());
        assertFalse(iter.hasNext());

    }

    @org.junit.Test
    public void intersection() {
        Iterator<Integer> iter1 = EC.iterator();
        Iterator<Integer> iter2 = EC2.iterator();
        Iterator<Integer> iter3 = EC.iterator();
        Iterator<Integer> iter4 = EC2.iterator();
        Iterator<Integer> iter5 = EC.iterator();
        Iterator<Integer> iter6 = EC2.iterator();
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(4);
        ans.add(7);
        ans.add(9);
        ans.add(10);
        assertEquals(ans,EC.intersection(iter1,iter2));
        assertEquals(ans,EC.intersection(iter3,iter4));
        assertEquals(ans,EC.intersection(iter5,iter6));


    }

    @org.junit.Test (expected = NullPointerException.class)
    public void testExc() {
        test.insert(null);
        test.findKey(null);
        test.insertData(null,null);
        test.insertData(1,null);
        test.findDataList(null);
    }

    @org.junit.Test (expected = IllegalArgumentException.class)
    public void testExc2() {
        test.insertData(-10,1);
        test.findDataList(-10);
    }

}