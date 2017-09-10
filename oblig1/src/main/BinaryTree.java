package main;

import java.util.ArrayList;

public class BinaryTree {

    BinaryNode root = null;

    public void insert(BinaryNode node) {
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


    public int[] getNodesPerDepth(){
        if(root==null){
            return null;
        }
        int[] result = new int[getHeight()+1];
        java.util.Arrays.fill(result, 0);
        result[0] = 1;

        countNodesPerDepth(result, root, 1);

        return result;
    }


    public void countNodesPerDepth(int[] result, BinaryNode curParentNode, int depth){
        if (curParentNode.isLeafNode()){
            return;
        }

        result[depth] = result[depth] + curParentNode.getDirectChildrenCount();

        if (curParentNode.left != null){
            countNodesPerDepth(result, curParentNode.left, depth + 1);
        }
        if (curParentNode.right != null){
            countNodesPerDepth(result, curParentNode.right, depth + 1);
        }
    }

    public int getChildrenOfChildrenCount(BinaryNode node){
        return  (node.left == null ? 0 : node.right.getDirectChildrenCount())
                + (node.left == null ? 0 : node.right.getDirectChildrenCount());
    }

    public String getMin(){
        BinaryNode curNode = root;
        String result = null;
        while (curNode != null){
            result = curNode.value;
            curNode = curNode.left;
        }
        return result;
    }

    public String getMax(){
        BinaryNode curNode = root;
        String result = null;
        while (curNode != null){
            result = curNode.value;
            curNode = curNode.right;
        }
        return result;
    }


}
