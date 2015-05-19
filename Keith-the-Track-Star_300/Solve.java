import java.util.*;
import java.io.*;
import java.lang.Math;

public class Solve {
    public static int[][] grid;
    public static int HURDLE = Integer.MIN_VALUE;
    public static int UNREACHABLE = Integer.MAX_VALUE;
    public static boolean isValidPosition(int r, int c) {
        if (r < 0 || r > grid.length - 1 || c < 0 || c > grid[0].length - 1) { // out of bounds
            return false;
        }
        else if (grid[r][c] == HURDLE) { // hurdle
            return false;
        }
        else {
            return true;
        }
    }
    public static void printRow(int r) {
        String[] res = new String[grid[r].length];
        for (int c = 0; c < grid[r].length; ++c) {
            if (grid[r][c] == HURDLE) {
                res[c] = "H";
            }
            else if (grid[r][c] == UNREACHABLE) {
                res[c] = "X";
            }
            else {
                res[c] = Integer.toString(grid[r][c]);
            }
        }
        System.out.println(Arrays.toString(res));
    }
    public static void main(String[] args) throws IOException {
        for (int num=1; num<=6; num++) {
            BufferedReader in = new BufferedReader(new FileReader("test_cases/track_star_test" + num + ".txt"));
            //BufferedReader in = new BufferedReader(new FileReader("test.txt"));
            String data = in.readLine();
            String[] info = data.split(" ");
            grid = new int[Integer.parseInt(info[0])][Integer.parseInt(info[1])];
            for (int i=0; i<grid.length; i++) {
                for (int j=0; j<grid[0].length; j++) {
                    if (i == 0) {
                        grid[i][j] = 0;
                    }
                    else {
                        grid[i][j] = UNREACHABLE;
                    }
                }
            }
            System.out.println("Rows: " + grid.length);
            System.out.println("Columns: " + grid[0].length);
            for (int j=0; j<Integer.parseInt(info[2]); j++) {
                String hurdle = in.readLine();
                String coordinates[] = hurdle.split(" ");
                grid[Integer.parseInt(coordinates[0])][Integer.parseInt(coordinates[1])] = HURDLE;
            }
            //System.out.println(Arrays.deepToString(grid));
            for (int r = 0; r < grid.length; ++r) {
                for (int c = 0; c < grid[0].length; ++c) {
                    if (isValidPosition(r, c)) {
                        if (isValidPosition(r-1, c-1) && grid[r-1][c-1] != UNREACHABLE) { // Make sure it is possible to get to grid[r-1][c-1]
                            grid[r][c] = Math.min(grid[r][c], 1+grid[r-1][c-1]);
                        }
                        if (isValidPosition(r-1, c) && grid[r-1][c] != UNREACHABLE) { // Make sure it is possible to get to grid[r-1][c]
                            grid[r][c] = Math.min(grid[r][c], grid[r-1][c]);
                        }
                        if (isValidPosition(r-1, c+1) && grid[r-1][c+1] != UNREACHABLE) { // Make sure it is possible to get to grid[r-1][c+1]
                            grid[r][c] = Math.min(grid[r][c], 1+grid[r-1][c+1]);
                        }
                    }
                }
            }
            //printRow(9999);
            int min = Integer.MAX_VALUE;
            for (int c = 0; c < grid[0].length; ++c) {
                if (grid[9999][c] != HURDLE && grid[9999][c] < min) {
                    min = grid[9999][c];
                }
            }
            System.out.println("Shortest time: " + Integer.toString(min));
            // The flag is:
            // 2027;2009;2028;2014;2003;2010
        }
    }
}

