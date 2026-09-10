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
    private int matchCount = 0;

    public int averageOfSubtree(TreeNode root) {
        postOrder(root);
        return matchCount;
    }

    // Helper method returns an array: {sum_of_subtree, number_of_nodes_in_subtree}
    private int[] postOrder(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        // Traverse left and right subtrees
        int[] left = postOrder(node.left);
        int[] right = postOrder(node.right);

        // Calculate current subtree's sum and node count
        int currentSum = node.val + left[0] + right[0];
        int currentCount = 1 + left[1] + right[1];

        // Check if the current node satisfies the average condition
        if (currentSum / currentCount == node.val) {
            matchCount++;
        }

        // Return the sum and count to the parent node
        return new int[]{currentSum, currentCount};
    }
}