/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int preorderIndex;
    private Map<Integer, Integer> inorderIndexMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preorderIndex = 0;
        inorderIndexMap = new HashMap<>();
        
        // Build a hashmap to store the index of each value in the inorder array
        // This allows O(1) lookups to divide the left and right subtrees
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        
        return arrayToTree(preorder, 0, inorder.length - 1);
    }

    private TreeNode arrayToTree(int[] preorder, int left, int right) {
        // Base case: if there are no elements to construct the tree
        if (left > right) {
            return null;
        }

        // The first element in current preorder sequence is the root
        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);

        // Find the index of the root in the inorder traversal
        int rootIndex = inorderIndexMap.get(rootValue);

        // Recursively build the left and right subtrees.
        // It is crucial to build the left subtree first because the next elements 
        // in the preorder traversal belong to the left subtree.
        root.left = arrayToTree(preorder, left, rootIndex - 1);
        root.right = arrayToTree(preorder, rootIndex + 1, right);
        
        return root;
    }
}