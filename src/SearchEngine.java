/*
 * Name: Michael Nodini
 * PID:  A16007357
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Search Engine implementation.
 * 
 * @author Michael Nodini
 * @since  2/18/2021
 */
public class SearchEngine {

    private static final int INTERSECTION_MIN = 2;

    /**
     * Populate BSTrees from a file
     * 
     * @param movieTree  - BST to be populated with actors
     * @param studioTree - BST to be populated with studios
     * @param ratingTree - BST to be populated with ratings
     * @param fileName   - name of the input file
     * @returns false if file not found, true otherwise
     */
    public static boolean populateSearchTrees(
            BSTree<String> movieTree, BSTree<String> studioTree,
            BSTree<String> ratingTree, String fileName
    ) {
        // open and read file
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                // read 5 lines per batch:
                // movie, cast, studios, rating, trailing hyphen
                String movie = scanner.nextLine().trim();
                String cast[] = scanner.nextLine().split(" ");
                String studios[] = scanner.nextLine().split(" ");
                String rating = scanner.nextLine().trim();
                scanner.nextLine();
                // populate three trees with the information you just read
                treeBuilder(movieTree, cast, movie);
                treeBuilder(studioTree, studios, movie);
                treeBuilder(ratingTree, cast, rating);
                // hint: create a helper function and reuse it to build all three trees
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    /**
     * Tree Builder helper method for populateSearchTrees
     * Takes in tree to be modified, an array of keys, and a value
     * Does not add duplicates keys
     * Does not add duplicate values
     * All keys/values are lowercase
     * @param tree tree to be created
     * @param keys list of keys to be added
     * @param value value to be added to keys
     */
    private static void treeBuilder(BSTree<String> tree, String[] keys, String value){
        for(String key: keys){
            //Change key to lowercase
            key = key.toLowerCase();
            //BSTree.insert() already checks if the key is in the tree
            boolean inserted = tree.insert(key);
            //If the key is already in the tree check if value is already in the data list
            if(!tree.findDataList(key).contains(value)){
                tree.insertData(key,value);
            }
        }
    }

    /**
     * Search a query in a BST
     * 
     * @param searchTree - BST to be searched
     * @param query      - query string
     */
    public static void searchMyQuery(BSTree<String> searchTree, String query) {
        // process query
        String[] keys = query.toLowerCase().split(" ");
        LinkedList<String> documents = new LinkedList<>();
        LinkedList<String> temp;
        if(keys.length >= INTERSECTION_MIN){
            //Add the contents of the documents from the first key
            try {
                documents.addAll(searchTree.findDataList(keys[0]));
                // search and output intersection results
                for (String key : keys) {
                    //Add contents of documents to temp
                    temp = new LinkedList<>();
                    temp.addAll(searchTree.findDataList(key));
                    //Only keep the overlap between the documents and temp
                    documents.retainAll(temp);
                }
            } catch (Exception e){

            } finally{
                SearchEngine.print(query,documents);
            }
        }
        // search and output individual results
        //If documents is not empty and there is more than 1 key
        for(String key : keys){
            temp = new LinkedList<>();
            try {
                temp.addAll(searchTree.findDataList(key));
            } catch (Exception e){ }
            //If intersection does not contain all query mentions
            temp.removeIf(documents::contains);
            if(documents.isEmpty() || !temp.isEmpty()){
                SearchEngine.print(key, temp);
            }
            documents.addAll(temp);
        }
    }

    /**
     * Print output of query
     * 
     * @param query     Query used to search tree
     * @param documents Output of documents from query
     */
    public static void print(String query, LinkedList<String> documents) {
        if (documents == null || documents.isEmpty())
            System.out.println("The search yielded no results for " + query);
        else {
            Object[] converted = documents.toArray();
            Arrays.sort(converted);
            System.out.println("Documents related to " + query
                    + " are: " + Arrays.toString(converted));
        }
    }

    /**
     * Main method that processes and query the given arguments
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // initialize search trees
        BSTree<String> movieTree = new BSTree<>();
        BSTree<String> studioTree = new BSTree<>();
        BSTree<String> ratingTree = new BSTree<>();
        ArrayList<BSTree<String>> trees = new ArrayList<>();
        trees.add(movieTree);
        trees.add(studioTree);
        trees.add(ratingTree);
        // process command line arguments
        String fileName = args[0];
        int searchKind = Integer.parseInt(args[1]);
        // populate search trees
        SearchEngine.populateSearchTrees(movieTree, studioTree, ratingTree, fileName);
        // choose the right tree to query
        String query = "";
        for(int i = INTERSECTION_MIN; i < args.length; i++){
            query += args[i];
            query += " ";
        }
        SearchEngine.searchMyQuery(trees.get(searchKind),query);
    }
}
