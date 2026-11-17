class Solution {
    public String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();
        String[] components = path.split("/");
        
        for (String dir : components) {
            if (dir.equals("") || dir.equals(".")) {
                continue; // Skip empty segments and current directory markers
            } else if (dir.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pollLast(); // Go up to the parent directory
                }
            } else {
                stack.offerLast(dir); // Valid directory/file name
            }
        }
        
        // Reconstruct the simplified canonical path
        StringBuilder result = new StringBuilder();
        for (String dir : stack) {
            result.append("/").append(dir);
        }
        
        return result.length() == 0 ? "/" : result.toString();
    }
}