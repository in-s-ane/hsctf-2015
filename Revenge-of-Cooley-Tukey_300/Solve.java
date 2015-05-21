import java.util.*;
import java.io.*;

//import org.jtransforms.dct.*;
//import org.jtransforms.dst.*;

import org.apache.commons.math3.transform.*;

public class Solve {
    public static void main(String[] args) {

        double[] numbers = new double[] {1653.0, 0.13916760914832338, 58.72838425104149, -29.800988375644806, -64.39696961966996, 77.24677622083472, 29.83855931789015, -62.70175235211251, 42.999999999999986, 65.1382409674247, -21.49541356738255, -67.39554551772046, 54.396969619669974, 40.92032042100787, -39.07153000154913, -15.546218972937488, 65.0};

        //DoubleDCT_1D dct = new DoubleDCT_1D(numbers.length);
        //DoubleDST_1D dst = new DoubleDST_1D(numbers.length);
        //dct.inverse(numbers, false);

        // We are only using Apache Commons Math since it support different types of normalization for FFT
        FastCosineTransformer dct = new FastCosineTransformer(DctNormalization.STANDARD_DCT_I);
        numbers = dct.transform(numbers, TransformType.INVERSE);

        System.out.println(Arrays.toString(numbers));
        for (int i=0; i<numbers.length; i++) {
            System.out.print((char) (numbers[i]+.5 % 256));

        }
    }
}
