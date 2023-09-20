import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static HashMap<String,Integer> romanNumbers;

    public static void main(String[] args) {
        romanNumbers=new HashMap<>();
        romanNumbers.put("I",1);
        romanNumbers.put("II",2);
        romanNumbers.put("III",3);
        romanNumbers.put("IV",4);
        romanNumbers.put("V",5);
        romanNumbers.put("VI",6);
        romanNumbers.put("VII",7);
        romanNumbers.put("VIII",8);
        romanNumbers.put("IX",9);
        romanNumbers.put("X",10);

        while (true) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            System.out.println(calc(input));
        }
    }
    static boolean isRoman(String number) {
        return number.matches("^(I{1,3}|IV|V|VI{1,3}|IX|X)$");
    }

    static int parseRoman(String number){
        return romanNumbers.get(number);
    }

    static String toRoman(int number){
        for (Map.Entry<String,Integer> entry: romanNumbers.entrySet()){
            if (entry.getValue()==number){
                return entry.getKey();
            }
        }
        return null;
    }
    public static String calc(String input){
        String[] tokens = input.split("\\s");
        if (tokens.length != 3) {
            throw new IllegalArgumentException();
        }

        boolean isRoman = false;
        int a, b;
        if (isRoman(tokens[0]) && isRoman(tokens[2])) {
            isRoman=true;
            a = parseRoman(tokens[0]);
            b = parseRoman(tokens[2]);
        } else {
            a = Integer.parseInt(tokens[0]);
            b = Integer.parseInt(tokens[2]);
        }

        if (a < 1 || a > 10 || b < 1 || b > 10) {
            throw new IllegalArgumentException();
        }

        int result = switch (tokens[1]) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> throw new IllegalArgumentException();
        };

        if (isRoman) {
            if (result <= 0) {
                throw new IllegalArgumentException();
            }
            return toRoman(result);
        }
        return Integer.toString(result);
    }
}