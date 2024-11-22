package assignments;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        String s1 = "", s2 = "";
        int ch = 0;
        int cho;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("_______________________________________________________________________________________");
            System.out.println("Select From Menu : ");
            System.out.println("1. Addition \n2. Subtraction \n3. Multiplication \n4. Square \n5. Exit");
            System.out.println("_______________________________________________________________________________________");
            System.out.print("Enter Your Choice: ");
            cho = sc.nextInt();
            sc.nextLine(); // Consume newline
            switch (cho) {
                case 1:
                    System.out.print("Enter First Number: ");
                    s1 = sc.nextLine();
                    System.out.print("Enter Second Number: ");
                    s2 = sc.nextLine();
                    System.out.println("Addition of " + s1 + " and " + s2 + " is: " + findSum(s1, s2));
                    break;
                case 2:
                    System.out.print("Enter First Number: ");
                    s1 = sc.nextLine();
                    System.out.print("Enter Second Number: ");
                    s2 = sc.nextLine();
                    System.out.println("Subtraction of " + s1 + " and " + s2 + " is: " + findDiff(s1, s2));
                    break;
                case 3:
                    System.out.print("Enter First Number: ");
                    s1 = sc.nextLine();
                    System.out.print("Enter Second Number: ");
                    s2 = sc.nextLine();
                    System.out.println("Multiplication of " + s1 + " and " + s2 + " is: " + findMulti(s1, s2));
                    break;
                case 4:
                    System.out.print("Enter Number: ");
                    s1 = sc.nextLine();
                    System.out.println("Square of " + s1 + " is: " + findMulti(s1, s1));
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
            System.out.println("______________________________________________________________________________________");
            System.out.print("Do you want to continue? (1 for Yes / 0 for No): ");
            ch = sc.nextInt();
            sc.nextLine(); // Consume newline
        } while (ch == 1 && cho != 5);
        sc.close();
    }

    private static String findMulti(String A, String B) {
// base case
        if (A.isEmpty() || B.isEmpty()) {
            return "0";
        }
        if (A.equals("0") || B.equals("0")) {
            return "0";
        }
// swapping for correct condition
        if (A.length() < B.length()) {
            String temp = A;
            A = B;
            B = temp;
        }
        int len = A.length();
// append zeros to make the length of numbers same
        while (B.length() < len) {
            B = '0' + B;
        }
// base case for recursion
        if (len == 1) {
            int ans = Integer.parseInt(A) * Integer.parseInt(B);
            return Integer.toString(ans);
        }
// if the length is odd then for dividing simplicity
        if (len % 2 == 1) {
            A = '0' + A;
            B = '0' + B;
            len++;
        }
// Dividing the numbers into subparts
        String Al = A.substring(0, len / 2);
        String Ar = A.substring(len / 2, len);
        String Bl = B.substring(0, len / 2);
        String Br = B.substring(len / 2, len);
// finding small components
        String p = findMulti(Al, Bl);
        String q = findMulti(Ar, Br);
        String r = findMulti(findSum(Al, Ar), findSum(Bl, Br));
// finding out the subpart of the actual formula
        String subres = findDiff(r, findSum(p, q));
// Multiply the p by 10^(n)
        p += "0".repeat(len);
// Multiply the r by 10^(n/2)
        subres += "0".repeat(len / 2);
// Final result calculation
        String Res = findSum(findSum(p, subres), q);
        return Res.replaceFirst("^0+(?!$)", "");
    }

    private static String findDiff(String s1, String s2) {
        if (s1.isEmpty())
            return s2;
        if (s2.isEmpty())
            return s1;
        if (s1.length() < s2.length() || (s1.length() == s2.length() && s1.compareTo(s2) < 0)) {
            String temp = s1;
            s1 = s2;
            s2 = temp;
        }
        StringBuilder result = new StringBuilder();

        String str1 = new StringBuilder(s1).reverse().toString();
        String str2 = new StringBuilder(s2).reverse().toString();

        int len1 = s1.length();
        int len2 = s2.length();

        int borrow = 0;

        for (int i = 0; i < len1; i++) {
            int digit1 = str1.charAt(i) - '0';
            int digit2 = (i < len2) ? str2.charAt(i) - '0' : 0;
            int diff = digit1 - digit2 - borrow;
            if (diff < 0) {
                diff += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            result.append(diff);
        }
        return result.reverse().toString().replaceFirst("^0+(?!$)", "");
    }

    private static String findSum(String s1, String s2) {
        if (s1.isEmpty())
            return s2;
        if (s2.isEmpty())
            return s1;
        if (s1.length() > s2.length()) {
            String temp = s1;
            s1 = s2;
            s2 = temp;
        }
        int n1 = s1.length();
        int n2 = s2.length();

        StringBuilder result = new StringBuilder();

        String str1 = new StringBuilder(s1).reverse().toString();
        String str2 = new StringBuilder(s2).reverse().toString();
        int carry = 0;

        for (int i = 0; i < n1; i++) {
            int sum = (str1.charAt(i) - '0') + (str2.charAt(i) - '0') + carry;
            carry = sum / 10;
            result.append(sum % 10);
        }
        for (int i = n1; i < n2; i++) {
            int sum = (str2.charAt(i) - '0') + carry;
            result.append(sum % 10);
            carry = sum / 10;
        }
        if (carry != 0) {
            result.append(carry);
        }
        return result.reverse().toString().replaceFirst("^0+(?!$)", "");
    }
}