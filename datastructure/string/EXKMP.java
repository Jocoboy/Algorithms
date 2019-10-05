package datastructure.string;

/**
 * Implementation of extend Knuth–Morris–Pratt algorithm.
 */

public class EXKMP {

    private static int[] next;
    private static int[] extend;

    /**
     * Compute the length of the longest common prefix of String T and String
     * T[i...m-1]. Restored in array next.
     * 
     * @param subString - T array
     */
    public static void nextOf(String subString) {
        final int m = subString.length();
        next = new int[m];
        next[0] = m;
        int k = 0;
        int j = 0;
        for (int i = 1; i < m; i++) {
            if (i >= j || i + next[i - k] >= j) {
                if (i >= j) {
                    j = i;
                }
                while (j < m && subString.charAt(j) == subString.charAt(j - i)) {
                    j++;
                }
                next[i] = j - i;
                k = i;
            } else {
                next[i] = next[i - k];
            }
        }
    }

    /**
     * Compute the length of the longest common prefix of String S[i...n-1] and
     * String T. Restored in array extend. Note that if there exists extend[i] equal
     * to m, then pattern successfully matched at index i.
     * 
     * @param S - main string
     * @param T - pattern string
     */
    public static void extendOf(String S, String T) {
        nextOf(T);
        final int n = S.length();
        final int m = T.length();
        extend = new int[n];
        int k = 0;
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (i >= j || i + next[i - k] >= j) {
                if (i >= j) {
                    j = i;
                }
                while (j < n && j - i < m && S.charAt(j) == T.charAt(j - i)) {
                    j++;
                }
                extend[i] = j - i;
                k = i;
            } else {
                extend[i] = next[i - k];
            }
        }
    }

    public static void print() {
        System.out.print("next:  ");
        for (int i = 0; i < next.length; i++) {
            System.out.print(" " + next[i]);
        }
        System.out.println();

        System.out.print("extend:");
        for (int i = 0; i < extend.length; i++) {
            System.out.print(" " + extend[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String S1 = "aaaaabbb";
        String T1 = "aaaaac";
        extendOf(S1, T1);
        print();

        String S2 = "abc";
        String T2 = "def";
        extendOf(S2, T2);
        print();

        String S3 = "aabbabaaab";
        String T3 = "aabb";
        extendOf(S3, T3);
        print();
    }
}
