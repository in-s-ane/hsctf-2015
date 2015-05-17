import java.util.*;

public class Vigenere {
    private char[][] board;
    private final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private String plaintext;
    private String cipherText;

    private void genBoard() {
        board = new char[alphabet.length()][alphabet.length()];
        for (int i = 0 ; i < alphabet.length() ; i++) {
            for (int j = 0 ; j < alphabet.length() ; j++) {
                board[i][j] = alphabet.charAt((j + i) % 26);
            }
        }
    }

    public Vigenere(String s) {
        genBoard();
        cipherText = s;
    }

    public String decrypt() {
        ArrayList<Character> plaintext = new ArrayList<Character>();
        ArrayList<Character> ciphertext = new ArrayList<Character>();
        for (char i : cipherText.toCharArray()) {
            ciphertext.add(i);
        }
        ArrayList<Character> key = new ArrayList<Character>();
        key.add('a');
        for (int i = 0 ; i < ciphertext.size() ; i++) {
            plaintext.add((char)(getIndex(board[(int)key.get(i) - 97], ciphertext.get(i)) + 97));
            key.add(plaintext.get(i));
        }
        for (int i = 0 ; i < plaintext.size() ; i++) {
            System.out.print(plaintext.get(i));
        }
        return plaintext.toString();
    }

    private int getIndex(char[] arr, char el) {
        for (int i = 0 ; i < arr.length ; i++) {
            if (arr[i] == el) {
                return i;
            }
        }
        return -1;
    }

    public int indexOf(char[] arr, char el) {
        for (int i = 0 ; i < arr.length ; i++) {
            if (arr[i] == el) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Vigenere tr1 = new Vigenere("hlpwzyombaaapaaasbjohmzqmytyxbqmhamkombxesamvkhrhgtpniytfpminapahprkbagbyjflvvxhodgzkfuvmmavtajhhpcgakfuvklnzkmvkwrvcmicimsxvetavdtxhgwicmimgcbozralawyraljqlgoautpnihwayxvet");
        tr1.decrypt();
    }
}

