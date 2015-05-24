import java.util.*;
import java.io.*;
import java.lang.Math;

public class Solve {
    public static boolean binSearch(ArrayList<Long> l, long i) {
        int low = 0;
        int high = l.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            long midEl = l.get(mid);
            if (midEl < i) {
                low = mid + 1;
            }
            else if (midEl > i) {
                high = mid - 1;
            }
            else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("input.txt"));
        String[] data = in.readLine().split(" ");
        long numMinutes = Long.parseLong(data[0]);
        long[] trackingLights = new long[Integer.parseInt(data[1])];
        ArrayList<Long> lightsOn = new ArrayList<Long>();
        data = in.readLine().split(" ");
        for (int i = 0; i < data.length; ++i) {
            lightsOn.add(Long.parseLong(data[i]));
        }
        data = in.readLine().split(" ");
        for (int i = 0; i < data.length; ++i) {
            trackingLights[i] = Long.parseLong(data[i]);
        }
        System.out.println("Num minutes: " + numMinutes);
        System.out.println("Initial lights: " + lightsOn.toString());
        System.out.println("Tracking lights: " + Arrays.toString(trackingLights));
        for (int minute = 1; minute <= numMinutes; ++minute) {
            ArrayList<Long> newLightsOn = new ArrayList<Long>();
            int k_3 = 0;
            int k_2 = 0;
            int k_1 = 0;
            int k = binSearch(lightsOn, 0) ? 1 : 0;
            long upperLimit = lightsOn.get(lightsOn.size() - 1) + 4;
            for (long i = 0; i < upperLimit; ++i) {
                int numPrecedingOn = k_3+k_2+k_1+k;
                k_3 = k_2;
                k_2 = k_1;
                k_1 = k;
                k = binSearch(lightsOn, i+1) ? 1 : 0;
                if (numPrecedingOn == 1 || numPrecedingOn == 3) {
                    newLightsOn.add(i);
                }
            }
            lightsOn = newLightsOn;
            // Rules:
            // Every 5000 minutes, the last light increases by 15000
            // Every 6400 minutes, the last light increases by 19200
            // The last 46 numbers are the same every time
            // Every 64 minutes, I know the last 46 numbers
            // Every 12800 minutes, I know the last 100 numbers
            // Formula for the last light at each cycle:
            // Last light = 9984 + (minute * 3)
            // At 25622 minutes, the last light is 86850
            // At 32022 minutes, the last light is 106050
            // Let's treat blocks as diffs from the starting number and the
            // ending number (calculable)
            // At 6422 (22 * 6400 * 1), we have two identical blocks separated by 3519
            // At 12822 (22 * 6400 * 2), we have two identical blocks separated by 17087
            if ((minute-22) % 6400 == 0) {
                int lightsSize = lightsOn.size();
                int index = lightsSize;
                long lastLight = lightsOn.get(lightsSize-1);
                long last = lightsOn.get(0);
                System.out.print("[");
                for (Long l : lightsOn) {
                    System.out.print(Long.toString(l-last) + ", ");
                    last = l;
                }
                System.out.print("]\n");
                //for (index = lightsSize-200; index < lightsSize; ++index) {
                //    System.out.print(Long.toString(lightsOn.get(index)) + ", ");
                //}
                System.out.println("Minute: " + Long.toString(minute));
                System.out.println("Lights on: " + Long.toString(lightsSize));
                System.out.println("Last light: " + Long.toString(lastLight));
            }
            //System.out.println("Lights on: " + lightsOn.toString());
        }
        int sum = 0;
        for (int i = 0; i < trackingLights.length; ++i) {
            if (binSearch(lightsOn, trackingLights[i]))
                sum += i + 1;
        }
        System.out.println("Answer: " + Long.toString(sum));
    }
}

