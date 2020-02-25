package Large_Numbers_Multiplication;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static String addNumbers(String a, String b){
        String rem = "0";
        StringBuilder result = new StringBuilder();
        int ptrA = a.length() - 1;
        int ptrB = b.length() - 1;
        while(ptrA >= 0 && ptrB >= 0){
            int s = Integer.parseInt(rem) + Integer.parseInt(a.charAt(ptrA) + "") + Integer.parseInt(b.charAt(ptrB) + "");
            result.append(s%10 + "");
            rem = (s/10) + "";
            ptrA--;
            ptrB--;
        }
        while(ptrA >= 0){
            int s = Integer.parseInt(rem) + Integer.parseInt(a.charAt(ptrA) + "");
            result.append(s%10 + "");
            rem = (s/10) + "";
            ptrA--;
        }
        while(ptrB >= 0){
            int s = Integer.parseInt(rem) + Integer.parseInt(b.charAt(ptrB) + "");
            result.append(s%10 + "");
            rem = (s/10) + "";
            ptrB--;
        }
        if(!rem.equals("0")){
            result.append(rem);
        }
        return result.reverse().toString();
    }

    static boolean isSmaller(String str1, String str2)
    {
        // Calculate lengths of both string
        int n1 = str1.length(), n2 = str2.length();
        if (n1 < n2)
            return true;
        if (n2 < n1)
            return false;

        for (int i = 0; i < n1; i++)
            if (str1.charAt(i) < str2.charAt(i))
                return true;
            else if (str1.charAt(i) > str2.charAt(i))
                return false;

        return false;
    }

    // Function for find difference of larger numbers
    static String subNumbers(String str1, String str2)
    {
        // Before proceeding further, make sure str1
        // is not smaller
        if (isSmaller(str1, str2))
        {
            String t = str1;
            str1 = str2;
            str2 = t;
        }

        // Take an empty string for storing result
        String str = "";

        // Calculate length of both string
        int n1 = str1.length(), n2 = str2.length();

        // Reverse both of strings
        str1 = new StringBuilder(str1).reverse().toString();
        str2 = new StringBuilder(str2).reverse().toString();

        int carry = 0;

        // Run loop till small string length
        // and subtract digit of str1 to str2
        for (int i = 0; i < n2; i++)
        {
            // Do school mathematics, compute difference of
            // current digits
            int sub = ((int)(str1.charAt(i)-'0') -
                    (int)(str2.charAt(i)-'0')-carry);

            // If subtraction is less then zero
            // we add then we add 10 into sub and
            // take carry as 1 for calculating next step
            if (sub < 0)
            {
                sub = sub + 10;
                carry = 1;
            }
            else
                carry = 0;

            str += (char)(sub + '0');
        }

        // subtract remaining digits of larger number
        for (int i = n2; i < n1; i++)
        {
            int sub = ((int)(str1.charAt(i) - '0') - carry);

            // if the sub value is -ve, then make it positive
            if (sub < 0)
            {
                sub = sub + 10;
                carry = 1;
            }
            else
                carry = 0;

            str += (char)(sub + '0');
        }

        // reverse resultant string
        return new StringBuilder(str).reverse().toString();
    }

    public static List<String> splitNumber(String num){
        List<String> splitted = new ArrayList<>();
        splitted.add(num.substring(0,num.length()/2));
        if(splitted.get(0).equals("")){
            splitted.set(0,"0");
        }
        splitted.add(num.substring(num.length()/2));
        if(splitted.get(1).equals("")){
            splitted.set(1,"0");
        }
        return splitted;
    }

    public static String addZeros(String num,int n){
        StringBuilder number = new StringBuilder(num);
        while(n>0){
            number.append("0");
            n--;
        }
        return number.toString();
    }

    public static String multiply(String x, String y){
        if(x.length() == 1 && y.length() == 1){
            return "" + (Integer.parseInt(x)*Integer.parseInt(y));
        }
        List<String> firstSplitted = splitNumber(x);
        List<String> secondSplitted = splitNumber(y);

        String firstPart = addZeros(multiply(firstSplitted.get(0),secondSplitted.get(0)),firstSplitted.get(1).length() + secondSplitted.get(1).length());
        addNumbers(firstSplitted.get(0),firstSplitted.get(1));
        addNumbers(secondSplitted.get(0),secondSplitted.get(1));
        String temp = subNumbers(multiply(addNumbers(firstSplitted.get(0),firstSplitted.get(1)),addNumbers(secondSplitted.get(0),secondSplitted.get(1))),multiply(firstSplitted.get(0),secondSplitted.get(0)));
        String secondPart = subNumbers(temp,multiply(firstSplitted.get(1),secondSplitted.get(1)));
        secondPart = addZeros(secondPart,firstSplitted.get(1).length());
        String thirdPart = multiply(firstSplitted.get(1),secondSplitted.get(1));
        String result = addNumbers(firstPart,secondPart);
        result = addNumbers(result,thirdPart);
        return result;
    }




    public static void main(String[] args){
        System.out.println(multiply("3141592653589793238462643383279502884197169399375105820974944592","2718281828459045235360287471352662497757247093699959574966967627"));
    }
}
