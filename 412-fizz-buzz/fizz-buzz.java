import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>(n);
        
        for (int i = 1; i <= n; i++) {
            boolean divBy3 = (i % 3 == 0);
            boolean divBy5 = (i % 5 == 0);
            
            if (divBy3 && divBy5) {
                result.add("FizzBuzz");
            } else if (divBy3) {
                result.add("Fizz");
            } else if (divBy5) {
                result.add("Buzz");
            } else {
                result.add(String.valueOf(i));
            }
        }
        
        return result;
    }
}