import java.util.Scanner;

/**
 * Created by alexander on 15-12-01.
 */
public class StringBuilderÖvning {

    public static void main(String args[]){

        Scanner in = new Scanner(System.in);

        // 7

        System.out.println("skriv in en sträng som du vill ha konverterad till camelcase");
        StringBuilder toCamelCase = new StringBuilder(in.nextLine().trim().toLowerCase());
        StringBuilder result = new StringBuilder(toCamelCase.length());

        for(int i = 0; i < toCamelCase.length(); i++){
            if(toCamelCase.charAt(i) != ' '){
                result.append(toCamelCase.charAt(i));
                continue;
            }
            for(int j = i; j < toCamelCase.length(); j++){
                if(toCamelCase.charAt(j) != ' '){
                    result.append(Character.toUpperCase(toCamelCase.charAt(j)));
                    i = j;
                    break;
                }
            }
        }
        System.out.println(result.toString());

        // 8

        for(int i = 0; i < result.length(); i++){
            if(Character.isUpperCase(result.charAt(i))){
                result.insert(i, ' ');
                result.insert(i+1, Character.toLowerCase(result.charAt(i+1)));
                result.deleteCharAt(i + 2);
            }
        }
        System.out.println("\n"+result);

        // 9

        System.out.println("\n" + result.reverse() + "\n");

        // 10

        result.reverse();
        for(int i = 0; i < result.length(); i++){
            if(result.charAt(i) != 'a' && result.charAt(i) != 'o' && result.charAt(i) != 'u' && result.charAt(i) != 'i'
                    && result.charAt(i) != 'e' && result.charAt(i) != 'y' && result.charAt(i) != 'å' &&
                    result.charAt(i) != 'ä' && result.charAt(i) != 'ö' && result.charAt(i) != ' '){

                result.insert(i+1,'o');
                result.insert(i+2, result.charAt(i));
                i+=2;
            }
        }
        System.out.println(result);

        // 11

        System.out.println("\nskriv in en sträng som ska bli krypterad");
        String str = in.nextLine().trim();
        System.out.println("hur många steg vill du att krypteringen ska ta?");
        int n = Integer.parseInt(in.nextLine().trim());
        result = new StringBuilder(str.length());

        for(int i = 0; i < str.length(); i++)
            if(str.charAt(i) != ' ')
                result.append(Character.toChars(str.charAt(i) + n));

        System.out.println(result);
    }
}
