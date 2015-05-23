// Stolen code? http://hsctf.com/a_needle_in_an_illiterate_haystack/README.html

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {

    public static void main(String[] args) {
        int length = 150;
        BufferedReader br = null;
        char[] current = new char[length];
        char[] previous = new char[length];

        try {
            br = new BufferedReader(new FileReader(args[0]));
            br.read(current);
            String s = "";
            while (!new String(current).equals(new String(previous))) {
                int counter = 0;
                BufferedReader br1;
                if (args[0].equals("modified4.txt")) {
                    br1 = new BufferedReader(new FileReader("corncob_lowercase.txt"));
                    while ((s = br1.readLine()) != null)
                        if (s.length() > 3 && new String(current).contains(s)) // Only checks for words with more than 3 characters
                            counter++;
                    if (counter >= 6)
                        System.out.println(new String(current));
                    previous = current.clone();
                    br.read(current);
                }
                else {
                    br1 = new BufferedReader(new FileReader("words.txt"));
                    while ((s = br1.readLine()) != null)
                        if (s.length() > 3 && new String(current).contains(s)) // Only checks for words with more than 3 characters
                            counter++;
                    if (counter >= 4)
                        System.out.println(new String(current));
                    previous = current.clone();
                    br.read(current);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
