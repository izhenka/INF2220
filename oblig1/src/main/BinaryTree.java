package main;

public class BinaryTree {

    BinaryNode root = null;

    public void insert(BinaryNode node) {
        //first node
        if(root==null){
            root = node;
            return;
        }
        root.insert(node);
    }


    public int getHeight(){

        if(root!=null){
            return root.getHeight();
        }
        return 0;
    }
}
