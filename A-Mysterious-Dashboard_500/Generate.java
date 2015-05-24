import java.util.*;
import java.io.*;
import java.lang.Math;

public class Solve {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("test.txt"));
        String[] data = in.readLine().split(" ");
        int numMinutes = Integer.parseInt(data[0]);
        int[] trackingLights = new int[Integer.parseInt(data[1])];
        int[] lights = new int[50];
        data = in.readLine().split(" ");
        for (int i = 0; i < data.length; ++i) {
            //lights[Integer.parseInt(data[i])] = 1; // Comment this to see configurations without any starting lights
        }
        data = in.readLine().split(" ");
        for (int i = 0; i < data.length; ++i) {
            trackingLights[i] = Integer.parseInt(data[i]);
        }

        System.out.println(Arrays.toString(lights));
        for (int t=1; t<=numMinutes; t++) {
            runLights(lights);
            if ((t-22)%64==0) {
                System.out.println(t);
                int count = 0;
                for (int i=0;i<lights.length;i++){
                    if (lights[i]==1){
                        count++;
                    }
                }
                if (count > 5000) {
                    System.out.println(Arrays.toString(lights));
                }
            }
            System.out.println(Arrays.toString(lights));
        }

        int sum = 0;
        for (int i = 0; i < trackingLights.length; ++i) {
            if (lights[trackingLights[i]] == 1)
                sum += i + 1;
        }
        System.out.println("Answer: " + sum);
    }

    public static void runLights(int[] lights) {
        int count;
        for (int i=lights.length-1; i>2; i--) {
            count = 0;
            count += lights[i] + lights[i-1] + lights[i-2] + lights[i-3];
            if (count == 1 || count == 3) {
                lights[i] = 1;
            }
            else {
                lights[i] = 0;
            }
        }
        count = lights[0] + lights[1] + lights[2];
        if (count == 1 || count == 3) {
            lights[2] = 1;
        }
        else {
            lights[2] = 0;
        }
        count = lights[0] + lights[1];
        if (count == 1) {
            lights[1] = 1;
        }
        else {
            lights[1] = 0;
        }
        lights[0] = 1;
    }
}

