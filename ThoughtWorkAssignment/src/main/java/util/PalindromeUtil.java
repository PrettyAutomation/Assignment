package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PalindromeUtil {

    // Method to create the Palindrome

    public static void palindrome_Word_Formed(int w, int x, int y, int z) {

        Character[] arr1 = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
        Character[] arr2 = {'#', '@', '!', '&', '*', '%', '^', '?', '|', '~'};
        Integer[] arr3 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        List<Character> alphabetList = Arrays.asList(arr1);
        List<Character> specialCharList = Arrays.asList(arr2);
        List<Integer> digitsList = Arrays.asList(arr3);
        String Palindrome = "";

        if (w < 0 || w > 20 || x < 0 || x > w || y < 0 || y > w || z < 0 || z > w) {
            System.out.println("Input is not valid to create a Palindrome");
        } else if ((x % 2 != 0 && y % 2 != 0 && z % 2 != 0) ||
                (x % 2 != 0 && y % 2 != 0) ||
                (x % 2 != 0 && z % 2 != 0) ||
                (y % 2 != 0 && z % 2 != 0)) {
            System.out.println("Input is not valid to create a Palindrome");
        } else if (w == 0) {
            System.out.println("Valid Palindrome - " + "");
        } else if ((x + y + z) > w || (x + y + z) < w) {
            System.out.println("Input is not valid to create a Palindrome");

        } else {
            if (x % 2 == 0 && y % 2 == 0 && z % 2 == 0) {
                List<String> listOfString = listOfWords(x,y,z,alphabetList,digitsList,specialCharList);
                String str = listOfString.get(0);
                String digi = listOfString.get(1);
                String special = listOfString.get(2);
                Palindrome = str + Palindrome + new StringBuffer(str).reverse().toString();
                Palindrome = digi + Palindrome + new StringBuffer(digi).reverse().toString();
                Palindrome = special + Palindrome + new StringBuffer(special).reverse().toString();
                boolean flag = isPalindrome(Palindrome);
                if(flag == true){
                    System.out.println("Valid Palindrome -" + Palindrome);
                }else{
                    throw new RuntimeException("System could not create the valid palindrome");
                }
            }
            if (x % 2 == 0 && y % 2 == 0 && z % 2 != 0) {
                List<String> listOfString = listOfWords(x,y,z,alphabetList,digitsList,specialCharList);
                String str = listOfString.get(0);
                String digi = listOfString.get(1);
                String special = listOfString.get(2);
                if(special.length()== 1){
                    Palindrome = special;
                }else{
                    Palindrome = special;
                    for(int i=special.length()-2; i>=0; i--){
                        Palindrome = Palindrome + special.charAt(i);
                    }
                    Palindrome = digi + Palindrome + new StringBuffer(digi).reverse().toString();
                    Palindrome = str + Palindrome + new StringBuffer(str).reverse().toString();
                    boolean flag = isPalindrome(Palindrome);
                    if(flag == true){
                        System.out.println("Valid Palindrome -" + Palindrome);
                    }else{
                        throw new RuntimeException("System could not create the valid palindrome");
                    }
                }

            }
            if (x % 2 == 0 && y % 2 != 0 && z % 2 == 0) {
                List<String> listOfString = listOfWords(x,y,z,alphabetList,digitsList,specialCharList);
                String str = listOfString.get(0);
                String digi = listOfString.get(1);
                String special = listOfString.get(2);
                if(digi.length() == 1){
                    Palindrome = digi;
                }else{
                    Palindrome = digi;
                    for(int i=digi.length()-2; i>=0; i--){
                        Palindrome = Palindrome + digi.charAt(i);
                    }
                    Palindrome = str + Palindrome + new StringBuffer(str).reverse().toString();
                    Palindrome = special + Palindrome + new StringBuffer(special).reverse().toString();
                    boolean flag = isPalindrome(Palindrome);
                    if(flag == true){
                        System.out.println("Valid Palindrome -" + Palindrome);
                    }else{
                        throw new RuntimeException("System could not create the valid palindrome");
                    }
                }

            }
            if (x % 2 != 0 && y % 2 == 0 && z % 2 == 0) {
                List<String> listOfString = listOfWords(x,y,z,alphabetList,digitsList,specialCharList);
                String str = listOfString.get(0);
                String digi = listOfString.get(1);
                String special = listOfString.get(2);
                if(str.length() == 1){
                    Palindrome = str;
                }else{
                    Palindrome = str;
                    for(int i=str.length()-2; i>=0; i--){
                        Palindrome = Palindrome + str.charAt(i);
                    }
                    Palindrome = digi + Palindrome + new StringBuffer(digi).reverse().toString();
                    Palindrome = special + Palindrome + new StringBuffer(special).reverse().toString();
                    boolean flag = isPalindrome(Palindrome);
                    if(flag == true){
                        System.out.println("Valid Palindrome -" + Palindrome);
                    }else{
                        throw new RuntimeException("System could not create the valid palindrome");
                    }
                }

            }
        }

    }

    // Method to add the list of string as per the given input for x (alphabet), y(digits), z (special char)

    public static List<String> listOfWords(int x, int y, int z,  List<Character> alphabetList,  List<Integer> digitsList, List<Character> specialCharList) {
        List<String> listOfString = new ArrayList<String>();
        String alp = "";
        String digi = "";
        String spechar = "";
        if (x % 2 == 0) {
            for (int i = 0; i < x / 2; i++) {
                alp = alp + alphabetList.get(i);
            }
            listOfString.add(alp);
        } else {
            for (int i = 0; i < (x / 2 + 1); i++) {
                alp = alp + alphabetList.get(i);
            }
            listOfString.add(alp);
        }
        if (y % 2 == 0) {
            for (int i = 0; i < y / 2; i++) {
                digi = digi + digitsList.get(i);
            }
            listOfString.add(digi);
        } else {
            for (int i = 0; i < (y / 2 + 1); i++) {
                digi = digi + digitsList.get(i);
            }
            listOfString.add(digi);
        }

        if (z % 2 == 0) {
            for (int i = 0; i < z / 2; i++) {
                spechar = spechar + specialCharList.get(i);
            }
            listOfString.add(spechar);
        } else {
            for (int i = 0; i < (z / 2 + 1); i++) {
                spechar = spechar + specialCharList.get(i);
            }
            listOfString.add(spechar);
        }
        return listOfString;
    }


    // Method to check the formed palindrome is correct or not

    public static boolean isPalindrome(String str){
        String reverse = new StringBuffer(str).reverse().toString();
        if(str.equals(reverse)){
            return true;
        }else{
            return false;
        }
    }

}
