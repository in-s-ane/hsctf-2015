import java.util.*;
import java.io.*;
import java.lang.Math;

public class Solve {
    public static boolean binSearch(ArrayList<Integer> l, int i) {
        int low = 0;
        int high = l.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int midEl = l.get(mid);
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
        int numMinutes = Integer.parseInt(data[0]);
        int[] trackingLights = new int[Integer.parseInt(data[1])];
        ArrayList<Integer> lightsOn = new ArrayList<Integer>();
        data = in.readLine().split(" ");
        for (int i = 0; i < data.length; ++i) {
            lightsOn.add(Integer.parseInt(data[i]));
        }
        data = in.readLine().split(" ");
        for (int i = 0; i < data.length; ++i) {
            trackingLights[i] = Integer.parseInt(data[i]);
        }
        System.out.println("Num minutes: " + numMinutes);
        System.out.println("Initial lights: " + lightsOn.toString());
        System.out.println("Tracking lights: " + Arrays.toString(trackingLights));
        for (int minute = 0; minute < numMinutes; ++minute) {
            ArrayList<Integer> newLightsOn = new ArrayList<Integer>();
            int k_3 = 0;
            int k_2 = 0;
            int k_1 = 0;
            int k = binSearch(lightsOn, 0) ? 1 : 0;
            int upperLimit = lightsOn.get(lightsOn.size() - 1) + 4;
            for (int i = 0; i < upperLimit; ++i) {
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
            if ((minute+1) % 1000 == 0) {
                System.out.println("Minute: " + Integer.toString(minute+1));
            }
            //System.out.println("Lights on: " + lightsOn.toString());
        }
        int sum = 0;
        for (int i = 0; i < trackingLights.length; ++i) {
            if (binSearch(lightsOn, trackingLights[i]))
                sum += i + 1;
        }
        System.out.println("Answer: " + Integer.toString(sum));
    }
}

