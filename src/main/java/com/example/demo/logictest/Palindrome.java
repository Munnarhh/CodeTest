package com.example.demo.logictest;

public class Palindrome {
    public static boolean isPalindrome(int x) {
        // Negative numbers and multiples of 10 (except zero) are not palindromes
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        // Convert integer to string
        String str = Integer.toString(x);

        // Initialize pointers
        int left = 0;
        int right = str.length() - 1;

        // Check for palindrome
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        // Example usage
        System.out.println(isPalindrome(121)); // Output: true
        System.out.println(isPalindrome(-121)); // Output: false
        System.out.println(isPalindrome(10)); // Output: false
        System.out.println(isPalindrome(12321)); // Output: true
    }
}
