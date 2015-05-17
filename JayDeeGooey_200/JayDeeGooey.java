/*
 * Decompiled with CFR 0_100.
 */
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

public class JayDeeGooey {
    private HashMap<Character, Character> map;

    public JayDeeGooey() {
        int i;
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        for (i = 0; i < s.length(); ++i) {
            if ('a' <= s.charAt(i) && s.charAt(i) <= 'z') continue;
            System.out.println("Invalid string.");
            return;
        }
        this.map = new HashMap();
        for (i = 97; i <= 122; i+=2) {
            this.map.put(Character.valueOf((char)i), Character.valueOf((char)(i + 1)));
            this.map.put(Character.valueOf((char)(i + 1)), Character.valueOf((char)i));
        }
        String sub = this.substeetooshun(s);
        System.out.println(sub);
        if (sub.equals("ichvjnvtsaftpgfkoevk")) {
            System.out.println("Yay you entered the flag :D");
        } else {
            System.out.println("Try again next HSCTF!");
        }
    }

    private String substeetooshun(String msg) {
        String s = "";
        for (int i = 0; i < msg.length(); ++i) {
            char c = msg.charAt(i);
            s = s + this.map.get(Character.valueOf(c));
        }
        return s;
    }

    public static void main(String[] args) {
        new JayDeeGooey();
    }
}
