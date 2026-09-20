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
    private int postorderIndex;
    private Map<Integer, Integer> inorderIndexMap;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // Start from the last element of postorder array
        postorderIndex = postorder.length - 1;
        inorderIndexMap = new HashMap<>();
        
        // Build a hashmap to store the index of each value in the inorder array
        // This allows O(1) lookups to divide the left and right subtrees
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        
        return arrayToTree(postorder, 0, inorder.length - 1);
    }

    private TreeNode arrayToTree(int[] postorder, int left, int right) {
        // Base case: if there are no elements to construct the tree
        if (left > right) {
            return null;
        }

        // The current element in the postorder sequence is the root
        int rootValue = postorder[postorderIndex--];
        TreeNode root = new TreeNode(rootValue);

        // Find the index of the root in the inorder traversal
        int rootIndex = inorderIndexMap.get(rootValue);

        // Recursively build the right and left subtrees.
        // It is CRUCIAL to build the RIGHT subtree first because we are traversing 
        // the postorder array backwards (Root -> Right -> Left).
        root.right = arrayToTree(postorder, rootIndex + 1, right);
        root.left = arrayToTree(postorder, left, rootIndex - 1);
        
        return root;
    }
}