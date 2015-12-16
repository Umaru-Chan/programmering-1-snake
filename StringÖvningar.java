import java.util.Scanner;

/**
 * Created by alexander on 15-11-24.
 */
public class StringÖvningar {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        // 1

        String str = "Tjeckoslovakien";
        System.out.println(str.substring(5, 9)+"\n");

        // 2

        System.out.println("skriv de 2 namn som ska jämföras");
        String str1 = in.nextLine();
        String str2 = in.nextLine();

        if(str1.compareTo(str2) == 0) //str1.equals(str2) ...
            System.out.println("Ni har samma namn!\n");
        else{
            System.out.println(str1.compareTo(str2) < 0 ? "Ditt namn är MINDRE än \n" : "Ditt namn är STÖRRE än\n");
        }

        // 3a

        System.out.println("skriv in en sträng som du vill ha utskriven utan mellanslag.");
        String str3 = in.nextLine().trim();

        for(int i = 0; i < str3.length(); i++)
                System.out.print(str3.charAt(i) != ' ' ? str3.charAt(i) : "");

        System.out.print("\n\n");

        // 3b

        System.out.println("skriv in en sträng som du vill ha konverterad till camelcase");
        String toCamelCase = in.nextLine().trim();
        toCamelCase = toCamelCase.toLowerCase();

        for(int i = 0; i < toCamelCase.length(); i++){
            if(toCamelCase.charAt(i) != ' '){
                System.out.print(toCamelCase.charAt(i));
                continue;
            }
            for(int j = i; j < toCamelCase.length(); j++){
                if(toCamelCase.charAt(j) != ' '){
                    System.out.print(Character.toUpperCase(toCamelCase.charAt(j)));
                    i = j;
                    break;
                }
            }
        }
    }
}
