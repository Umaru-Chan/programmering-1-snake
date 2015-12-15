import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by alexander on 15-11-27.
 */
public class OptionPaneÖvningar {

    public static void main(String args[]) throws MalformedURLException{

        // 4

        String str1 = JOptionPane.showInputDialog(null, "vad heter person a?");
        String str2 = JOptionPane.showInputDialog(null, "vad heter person b?");
        if(str1.equals(str2))
            JOptionPane.showMessageDialog(null, "ni har likadana namn");
        else
            JOptionPane.showMessageDialog(null, str1.compareTo(str2) < 0 ? "a är minde än b" : "b är minde än a");

        // 5

        String str3 = JOptionPane.showInputDialog(null, "skriv in en sträng som du vill ha tillbaks utan mellanslag");
        String result = "";
        if(str3 != null)
            for (int i = 0; i < str3.length(); i++)
                if (str3.charAt(i) != ' ')
                    result += str3.charAt(i);
        JOptionPane.showMessageDialog(null, result);

        // 6

        while(!str1.equals("joptionpane")){
            str1 = JOptionPane.showInputDialog(null, "vad heter klassen som kan skapa dialogrutor? " +
                    "(inte case sensitive)");
            str1 = str1.trim().toLowerCase();
            if(!str1.equals("joptionpane"))
                JOptionPane.showMessageDialog(null, "fel, försök igen");
        }

        ImageIcon img = new ImageIcon(new URL("http://www.sujathapaints.com/image/road-marking-paint-250x250.jpg"));
        String[] options = {"DirectX", "OpenGL", "LWJGL", "Canvas"};
        int ans;

        while(true) {
            ans = JOptionPane.showOptionDialog(null,
                    "Vilken av dessa renderingsmetoder kan java INTE använda?",
                    "En bra fråga",
                    JOptionPane.PLAIN_MESSAGE,  //ingen aning om vad argumentet hät gör
                    JOptionPane.PLAIN_MESSAGE,  //ingen aning om vad argumentet här gör
                    img, options, 0);
            if(JOptionPane.showConfirmDialog(null, "är du säker på ditt svar?") == JOptionPane.YES_OPTION)
                break;
        }
        if(ans != 0)
            JOptionPane.showMessageDialog(null, "FEL, rätt svar är DirectX");
        else
            JOptionPane.showMessageDialog(null,"rätt! grattis.");
    }
}
