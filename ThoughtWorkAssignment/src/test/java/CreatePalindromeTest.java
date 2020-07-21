import util.PalindromeUtil;

import java.util.*;

/**
 * Form a palindrome (where its word length 'w') which has
 * 'x' number of alphabets,
 * 'y' number of digits &
 * 'z' number of special characters
 *  where w, x, y and z can be input from the user.
 *
 *  w in the range 0 < w <= 20
 *  x in the range 0 < x <= w
 *  y in the range 0 < y <= w
 *  z in the range 0 < z <= w
 *
 * Based on the above parameters, we have to form and print a word that can be said as Valid Palindrome.
 * If the input given is not providing a valid palindrome, the output should be “Input is not valid to create a Palindrome”.
 *
 ****/


public class CreatePalindromeTest extends PalindromeUtil {

    public static void main(String [] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide the length word : ");
        int w = scanner.nextInt();
        System.out.println("Please provide the no. of alphabet to use: ");
        int x = scanner.nextInt();
        System.out.println("Please provide the no. of digits to use: ");
        int y = scanner.nextInt();
        System.out.println("Please provide the no. of special char to use : ");
        int z = scanner.nextInt();

        palindrome_Word_Formed(w,x,y,z);

    }

}


