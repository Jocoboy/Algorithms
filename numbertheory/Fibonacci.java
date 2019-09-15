package numbertheory;

import java.util.Scanner;
import java.math.BigInteger;

/**
 * Fast power of matrix version using high-precision.
 */

public class Fibonacci {

    private static final int N = 2;

    public static BigInteger[][] multi(BigInteger[][] a, BigInteger[][] b) {
        BigInteger[][] ret = new BigInteger[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ret[i][j] = BigInteger.ZERO;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    ret[i][j] = ret[i][j].add(a[i][k].multiply(b[k][j]));
                }
            }
        }
        return ret;
    }

    public static BigInteger fpow(BigInteger[][] baseMatrix, BigInteger n) {
        BigInteger[][] identityMatrix = new BigInteger[][] { { BigInteger.ONE, BigInteger.ZERO },
                { BigInteger.ZERO, BigInteger.ONE } };
        while (n.compareTo(BigInteger.ZERO) > 0) {
            if (n.remainder(BigInteger.TWO).compareTo(BigInteger.ONE) == 0) {
                identityMatrix = multi(identityMatrix, baseMatrix);
            }
            baseMatrix = multi(baseMatrix, baseMatrix);
            n = n.divide(BigInteger.TWO);
        }
        return identityMatrix[0][1];
    }

    public static void run() {

        Scanner scanner = new Scanner(System.in);
        BigInteger n = scanner.nextBigInteger();
        BigInteger[][] baseMatrix = new BigInteger[][] { { BigInteger.ONE, BigInteger.ONE },
                { BigInteger.ONE, BigInteger.ZERO } };
        System.out.println(fpow(baseMatrix, n));
        scanner.close();
    }

    public static void main(String[] args) {

        run();
    }
}