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
    public boolean isBalanced(TreeNode root) {
        // A height of -1 means the tree is not balanced.
        // Otherwise, it returns the actual height of the tree.
        return checkHeight(root) != -1;
    }

    private int checkHeight(TreeNode node) {
        // Base case: an empty tree is balanced and has a height of 0
        if (node == null) {
            return 0;
        }

        // Recursively get the height of the left subtree
        int leftHeight = checkHeight(node.left);
        // If the left subtree is unbalanced, the entire tree is unbalanced
        if (leftHeight == -1) {
            return -1;
        }

        // Recursively get the height of the right subtree
        int rightHeight = checkHeight(node.right);
        // If the right subtree is unbalanced, the entire tree is unbalanced
        if (rightHeight == -1) {
            return -1;
        }

        // If the current node's subtrees differ in height by more than 1, it's unbalanced
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        // Return the height of the current subtree
        return Math.max(leftHeight, rightHeight) + 1;
    }
}