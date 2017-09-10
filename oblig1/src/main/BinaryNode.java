package main;

public class BinaryNode {

    public String value;
    public BinaryNode left;
    public BinaryNode right;

    BinaryNode(String value){
        this.value = value;
    }


    public void insert(BinaryNode node){

        if (node.value.compareTo(value) < 0){
            if (left != null) {
                left.insert(node);
            } else {
                left = node;
            }
        } else {
            if (right != null) {
                right.insert(node);
            } else {
                right = node;
            }
        }


    }


    public int getHeight(){
        if (isLeafNode()) {
            return 0;
        }
        return Math.max(
                (left == null ? 0 : left.getHeight()),
                (right == null ? 0 : right.getHeight())
        ) + 1;
    }

    public Boolean isLeafNode(){
        return (left == null && right == null);
    }

    public int getDirectChildrenCount(){
        return  (left == null ? 0 : 1) + (right == null ? 0 : 1);
    }



}


