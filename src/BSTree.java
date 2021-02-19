/*
 * Name: Michael Nodini
 * PID:  A16007357
 */

import java.util.*;

/**
 * Binary search tree implementation.
 * 
 * @author Michael Nodini
 * @since  2/16/2021
 */
public class BSTree<T extends Comparable<? super T>> implements Iterable {

    /* * * * * BST Instance Variables * * * * */

    private int nelems; // number of elements stored
    private BSTNode root; // reference to root node

    /* * * * * BST Node Inner Class * * * * */

    protected class BSTNode {

        T key;
        LinkedList<T> dataList;
        BSTNode left;
        BSTNode right;

        /**
         * A constructor that initializes the BSTNode instance variables.
         *
         * @param left     Left child
         * @param right    Right child
         * @param dataList Linked list of related info
         * @param key      Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, LinkedList<T> dataList, T key) {
            this.left = left;
            this.right = right;
            this.dataList = dataList;
            this.key = key;
        }

        /**
         * A constructor that initializes BSTNode variables. Note: This constructor is
         * used when you want to add a key with no related information yet. In this
         * case, you must create an empty LinkedList for the node.
         *
         * @param left  Left child
         * @param right Right child
         * @param key   Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, T key) {
            this.dataList = new LinkedList<>();
            this.left = left;
            this.right = right;
            this.key = key;
        }

        /**
         * Return the key
         *
         * @return The key
         */
        public T getKey() {
            return this.key;
        }

        /**
         * Return the left child of the node
         *
         * @return The left child of the node
         */
        public BSTNode getLeft() {
            return this.left;
        }

        /**
         * Return the right child of the node
         *
         * @return The right child of the node
         */
        public BSTNode getRight() {
            return this.right;
        }

        /**
         * Return the linked list of the node
         *
         * @return The linked list of the node
         */
        public LinkedList<T> getDataList() {
            return this.dataList;
        }

        /**
         * Setter for left child of the node
         *
         * @param newleft New left child
         */
        public void setleft(BSTNode newleft) {
            this.left = newleft;
        }

        /**
         * Setter for right child of the node
         *
         * @param newright New right child
         */
        public void setright(BSTNode newright) {
            this.right = newright;
        }

        /**
         * Setter for the linked list of the node
         *
         * @param newData New linked list
         */
        public void setDataList(LinkedList<T> newData) {
            this.dataList = newData;
        }

        /**
         * Append new data to the end of the existing linked list of the node
         *
         * @param data New data to be appended
         */
        public void addNewInfo(T data) {
            this.dataList.add(data);
        }

        /**
         * Remove 'data' from the linked list of the node and return true. If the linked
         * list does not contain the value 'data', return false.
         *
         * @param data Info to be removed
         * @return True if data was found, false otherwise
         */
        public boolean removeInfo(T data) {
            return this.dataList.remove(data);
        }
    }

    /* * * * * BST Methods * * * * */

    /**
     * 0-arg constructor that initializes root to null and nelems to 0
     */
    public BSTree() {
        this.root = null;
        this.nelems = 0;
    }

    /**
     * Return the root of BSTree. Returns null if the tree is empty
     *
     * @return The root of BSTree, null if the tree is empty
     */
    public BSTNode getRoot() {
        if(this.nelems == 0){
            return null;
        }
        else{
            return this.root;
        }
    }

    /**
     * Return the BST size
     *
     * @return The BST size
     */
    public int getSize() {
        return this.nelems;
    }

    /**
     * Insert a key into BST
     * 
     * @param key
     * @return true if insertion is successful and false otherwise
     */
    public boolean insert(T key) {
        if(key == null){
            throw new NullPointerException();
        }
        //When tree is empty
        if(this.root == null){
            this.root = new BSTNode(null, null, key);
            this.nelems += 1;
            return true;
        }
        else{
            return addHelper(this.root, key);
        }
    }

    /**
     * Return true if the 'key' is found in the tree, false otherwise
     *
     * @param key To be searched
     * @return True if the 'key' is found, false otherwise
     * @throws NullPointerException If key is null
     */
    public boolean findKey(T key) {
        if(key == null){
            throw new NullPointerException();
        }
        else{
            return findHelper(this.root,key);
        }
    }

    /**
     * Insert 'data' into the LinkedList of the node whose key is 'key'
     *
     * @param key  Target key
     * @param data To be added to key's LinkedList
     * @throws NullPointerException     If eaither key or data is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public void insertData(T key, T data) {
        if(key == null || data == null){
            throw new NullPointerException();
        }
        //Check if node is inTree
        boolean inTree = findHelper(this.root,key);
        //If key is not in tree
        if(inTree == false){
            throw new IllegalArgumentException();
        }
        else{
            BSTNode node = findHelperNode(this.root, key);
            node.addNewInfo(data);
        }
    }

    /**
     * Return the LinkedList of the node with key value 'key'
     *
     * @param key Target key
     * @return LinkedList of the node whose key value is 'key'
     * @throws NullPointerException     If key is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public LinkedList<T> findDataList(T key) {
        if(key == null){
            throw new NullPointerException();
        }
        BSTNode node = findHelperNode(this.root, key);
        //If key is not found
        if(node == null){
            throw new IllegalArgumentException();
        }
        return node.getDataList();
    }

    /**
     * Return the height of the tree
     *
     * @return The height of the tree, -1 if BST is empty
     */
    public int findHeight() {
        if(this.nelems == 0){
            return -1;
        }
        else if(this.nelems == 1){
            return 0;
        }
        else{
            return findHeightHelper(this.root);
        }
    }

    /**
     * Helper for the findHeight method
     *
     * @param root Root node
     * @return The height of the tree, -1 if BST is empty
     */
    private int findHeightHelper(BSTNode root) {
        if(root == null){
            return -1;
        }
        else{
            int leftHeight = findHeightHelper(root.getLeft());
            int rightHeight = findHeightHelper(root.getRight());

            if(leftHeight > rightHeight){
                return leftHeight+1;
            }
            else{
                return rightHeight+1;
            }
        }
    }

    /**
     * Helper for adding a new key
     * @param currRoot Current "root" of the tree
     * @param toAdd Key to be added
     * @return true if added
     */
    private boolean addHelper(BSTNode currRoot, T toAdd){
        int val = toAdd.compareTo(currRoot.getKey());
        //If the value is in tree return false
        if(val == 0){
            return false;
        }
        //If less check left
        else if(val < 0){
            if(currRoot.getLeft() == null){
                currRoot.setleft(new BSTNode(null, null, toAdd));
            }
            else{
                return addHelper(currRoot.getLeft(), toAdd);
            }
        }
        //If greater check right
        else{
            if(currRoot.getRight() == null){
                currRoot.setright(new BSTNode(null, null, toAdd));
            }
            else{
                return addHelper(currRoot.getRight(), toAdd);
            }
        }
        this.nelems += 1;
        return true;
    }

    /**
     * Helper for finding a key
     * @param toFind key to find
     * @return true if found
     */
    private boolean findHelper(BSTNode node,T toFind){
        if(node == null){
            return false;
        }
        else if(node.getKey().equals(toFind)){
            return true;
        }
        //Check both sides of tree
        boolean r1 = findHelper(node.left, toFind);
        if(r1){
            return true;
        }
        boolean r2 = findHelper(node.right, toFind);
        return r2;
    }

    /**
     * Helper for finding a node
     * @param toFind key to find
     * @return Node who's key is equal to toFind
     */
    private BSTNode findHelperNode(BSTNode node,T toFind){
        if(node == null){
            return null;
        }
        else if(node.getKey().equals(toFind)){
            return node;
        }
        //Check both sides of tree
        BSTNode r1 = findHelperNode(node.left, toFind);
        if(r1 != null && r1.getKey().equals(toFind)){
            return r1;
        }
        BSTNode r2 = findHelperNode(node.right, toFind);
        return r2;
    }

    /* * * * * BST Iterator * * * * */

    public class BSTree_Iterator implements Iterator<T> {

        Stack<BSTNode> stack;

        /**
         * Constructor for BSTree_Iterator
         * Initializes stack with leftmost path of tree
         */
        public BSTree_Iterator() {
            this.stack = new Stack<>();
            BSTNode node = root;
            while(node != null){
                //Push the node onto stack
                this.stack.push(node);
                //Set node to currNode left child
                node = node.getLeft();
            }
        }

        /**
         * Checks if there is another value in iterator
         * @return false if stack is empty, true otherwise
         */
        public boolean hasNext() {
            return !this.stack.isEmpty();
        }

        public T next() {
            //If there is no next element
            if(!this.hasNext()){
                throw new NoSuchElementException();
            }
            //If there is an element
            else{
                //Pop the next node of the stack and get val
                BSTNode popped = this.stack.pop();
                T rVal = popped.getKey();
                //Check if node has any right child
                if(popped.getRight() != null){
                    //Setup right node to be added
                    BSTNode currNode = popped.getRight();
                    //Add left path to stack
                    while(currNode != null){
                        this.stack.push(currNode);
                        currNode = currNode.getLeft();
                    }
                }
                return rVal;
            }
        }
    }

    public Iterator<T> iterator() {
        return new BSTree_Iterator();
    }

    /* * * * * Extra Credit Methods * * * * */

    public ArrayList<T> intersection(Iterator<T> iter1, Iterator<T> iter2) {
        ArrayList<T> tree1 = new ArrayList<>();
        ArrayList<T> tree2 = new ArrayList<>();
        ArrayList<T> r = new ArrayList<>();
        while(iter1.hasNext()){
            tree1.add(iter1.next());
        }
        while(iter2.hasNext()){
            tree2.add(iter2.next());
        }
        for(T data : tree1){
            if(tree2.contains(data)){
                r.add(data);
            }
        }
        return r;
    }

    public int levelCount(int level) {
        /* TODO */
        return -1;
    }
}
