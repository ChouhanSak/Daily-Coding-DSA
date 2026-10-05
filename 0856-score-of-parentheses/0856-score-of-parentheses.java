class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                stack.push(0);
            } else {
                int inside = stack.pop();

                if (inside == 0) {
                    inside = 1;       // "()"
                } else {
                    inside = 2 * inside;  // "(A)"
                }

                int previous = stack.pop();
                stack.push(previous + inside);
            }
        }

        return stack.pop();
    }
}