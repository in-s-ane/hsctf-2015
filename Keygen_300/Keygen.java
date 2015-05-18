import java.io.PrintStream;
import java.util.Scanner;

public class Keygen
{
  public Keygen()
  {
    Scanner scanner = new Scanner(System.in);
    String input = scanner.next();
    if (keygen(input))
      System.out.println("Looks like you got it!");
    else
      System.out.println("Try again!");
  }

  private boolean keygen(String s)
  {
    if (s.length() != 36) {
      return false;
    }

    for (int i = 0; i < s.length(); i++) {
      if ((s.charAt(i) != '_') && (('a' > s.charAt(i)) || (s.charAt(i) > 'z'))) { // Only accepts a-z and _
        return false;
      }
    }

    int sum = 0;
    for (int i = 0; i < s.length(); i++) {
      sum += s.charAt(i);
    }

    if (sum != 3842) {
      return false;
    }

    if (s.charAt(0) - s.charAt(1) != -3) {
      return false;
    }
    if (s.charAt(1) - s.charAt(2) != 0) {
      return false;
    }
    if (s.charAt(2) - s.charAt(3) != 4) {
      return false;
    }
    if (s.charAt(3) - s.charAt(4) != -8) {
      return false;
    }
    if (s.charAt(4) - s.charAt(5) != 20) {
      return false;
    }
    if (s.charAt(5) - s.charAt(6) != -13) {
      return false;
    }
    if (s.charAt(6) - s.charAt(7) != 3) {
      return false;
    }
    if (s.charAt(7) - s.charAt(8) != -2) {
      return false;
    }
    if (s.charAt(8) - s.charAt(9) != 6) {
      return false;
    }
    if (s.charAt(9) - s.charAt(10) != 6) {
      return false;
    }
    if (s.charAt(10) - s.charAt(11) != -19) {
      return false;
    }
    if (s.charAt(11) - s.charAt(12) != 13) {
      return false;
    }
    if (s.charAt(12) - s.charAt(13) != -17) {
      return false;
    }
    if (s.charAt(13) - s.charAt(14) != 17) {
      return false;
    }
    if (s.charAt(14) - s.charAt(15) != -13) {
      return false;
    }
    if (s.charAt(15) - s.charAt(16) != -1) {
      return false;
    }
    if (s.charAt(16) - s.charAt(17) != 10) {
      return false;
    }
    if (s.charAt(17) - s.charAt(18) != -5) {
      return false;
    }
    if (s.charAt(18) - s.charAt(19) != 7) {
      return false;
    }
    if (s.charAt(19) - s.charAt(20) != 8) {
      return false;
    }
    if (s.charAt(20) - s.charAt(21) != -10) {
      return false;
    }
    if (s.charAt(21) - s.charAt(22) != -10) {
      return false;
    }
    if (s.charAt(22) - s.charAt(23) != 20) {
      return false;
    }
    if (s.charAt(23) - s.charAt(24) != -26) {
      return false;
    }
    if (s.charAt(24) - s.charAt(25) != 10) {
      return false;
    }
    if (s.charAt(25) - s.charAt(26) != -6) {
      return false;
    }
    if (s.charAt(26) - s.charAt(27) != 3) {
      return false;
    }
    if (s.charAt(27) - s.charAt(28) != 19) {
      return false;
    }
    if (s.charAt(28) - s.charAt(29) != -4) {
      return false;
    }
    if (s.charAt(29) - s.charAt(30) != 2) {
      return false;
    }
    if (s.charAt(30) - s.charAt(31) != -11) {
      return false;
    }
    if (s.charAt(31) - s.charAt(32) != 0) {
      return false;
    }
    if (s.charAt(32) - s.charAt(33) != 3) {
      return false;
    }
    if (s.charAt(33) - s.charAt(34) != -5) {
      return false;
    }
    if (s.charAt(34) - s.charAt(35) != 7) {
      return false;
    }

    return true;
  }

  public static void main(String[] args)
  {
    new Keygen();
  }
}
