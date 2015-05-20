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
                BufferedReader br1 = new BufferedReader(new FileReader("words.txt"));
                int counter = 0;
                while ((s = br1.readLine()) != null)
                    if (s.length() > 3 && new String(current).contains(s))
                        counter++;
                if (counter >= 4)
                    System.out.println(new String(current));
                previous = current.clone();
                br.read(current);
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
