public class Solution {
    // Structural Node definition for the Segment Tree
    private static class Node {
        int maxLen;
        int prefLen;
        int suffLen;
        char leftChar;
        char rightChar;
    }

    private Node[] tree;
    private char[] chars;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        this.chars = s.toCharArray();
        this.tree = new Node[4 * n];
        
        // 1. Initialize and build the Segment Tree from the input string
        build(1, 0, n - 1);

        int k = queryIndices.length;
        int[] result = new int[k];

        // 2. Process each point mutation query sequentially
        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char ch = queryCharacters.charAt(i);
            
            // Update the leaf node and bubble changes up to the root node
            update(1, 0, n - 1, idx, ch);
            
            // The root node (index 1) always maintains the global answer for the entire string range
            result[i] = tree[1].maxLen;
        }

        return result;
    }

    // Constructs the tree using divide and conquer structure
    private void build(int node, int start, int end) {
        tree[node] = new Node();
        if (start == end) {
            tree[node].maxLen = 1;
            tree[node].prefLen = 1;
            tree[node].suffLen = 1;
            tree[node].leftChar = chars[start];
            tree[node].rightChar = chars[start];
            return;
        }

        int mid = start + (end - start) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);
        
        merge(node, start, end);
    }

    // Modifies a single character position and fixes affected ancestral paths
    private void update(int node, int start, int end, int idx, char ch) {
        if (start == end) {
            chars[idx] = ch;
            tree[node].leftChar = ch;
            tree[node].rightChar = ch;
            return;
        }

        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, ch);
        } else {
            update(2 * node + 1, mid + 1, end, idx, ch);
        }

        merge(node, start, end);
    }

    // Combines left and right children states safely into their corresponding parent node
    private void merge(int node, int start, int end) {
        Node left = tree[2 * node];
        Node right = tree[2 * node + 1];
        Node parent = tree[node];

        int mid = start + (end - start) / 2;
        int leftSize = mid - start + 1;
        int rightSize = end - mid;

        // Propagate boundary identifiers up
        parent.leftChar = left.leftChar;
        parent.rightChar = right.rightChar;

        // Default non-overlapping assignments
        parent.prefLen = left.prefLen;
        parent.suffLen = right.suffLen;
        parent.maxLen = Math.max(left.maxLen, right.maxLen);

        // Seam-matching optimization: Check if middle characters match
        if (left.rightChar == right.leftChar) {
            // Expand prefix boundary if the left child is filled with a single character type
            if (left.prefLen == leftSize) {
                parent.prefLen = leftSize + right.prefLen;
            }
            // Expand suffix boundary if the right child is filled with a single character type
            if (right.suffLen == rightSize) {
                parent.suffLen = rightSize + left.suffLen;
            }
            // Candidate max length includes the overlapping bridge length across the center seam
            parent.maxLen = Math.max(parent.maxLen, left.suffLen + right.prefLen);
        }
    }
}
