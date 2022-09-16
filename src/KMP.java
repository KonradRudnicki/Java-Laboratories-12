import java.util.LinkedList;

public class KMP implements IStringMatcher {

    @Override
    public LinkedList<Integer> validShifts(String pattern, String text) {
        LinkedList<Integer> result = new LinkedList<>();

        int m = pattern.length();
        int n = text.length();
        int[] pi = computePiArray(pattern);

        int piIterator = 0;
        int stringIterator = 0;

        while (stringIterator < n) {
            if (pattern.charAt(piIterator) == text.charAt(stringIterator)) {
                piIterator++;
                stringIterator++;
            }

            if (piIterator == m) {
                result.add(stringIterator - piIterator);
                piIterator = pi[piIterator - 1];

            } else if (stringIterator < n && pattern.charAt(piIterator) != text.charAt(stringIterator)) {
                if (piIterator != 0) piIterator = pi[piIterator - 1];
                else stringIterator++;
            }
        }
        return result;

//        LinkedList<Integer> result = new LinkedList<>();
//
//        int m = pattern.length();
//        int n = text.length();
//        int q = 0;
//
//        int[] pi = computePiArray(pattern);
//
//        for (int i = 0; i < n; i++) {
//            while (q > 0 && pattern.charAt(q + 1) != text.charAt(i)) {
//                q = pi[q];
//            }
//
//            if (pattern.charAt(q + 1) == text.charAt(i)) {
//                q++;
//            }
//
//            if (q == m) {
//                result.add(i - m);
//                q = pi[q];
//            }
//        }
    }

    int[] computePiArray(String pattern) {
        int m = pattern.length();
        int prevPrefixLength = 0;
        int[] pi = new int[pattern.length()];
        pi[0] = 0;

        for (int q = 1; q < m; q++) {
            if (pattern.charAt(q) == pattern.charAt(prevPrefixLength)) {
                prevPrefixLength++;
                pi[q] = prevPrefixLength;

            }else if (prevPrefixLength != 0) {
                prevPrefixLength = pi[prevPrefixLength - 1];

            }else {
                pi[q] = prevPrefixLength;
            }
        }
        return pi;

//        int[] pi = new int[pattern.length()];
//
//        if (pattern.length() == 0) return pi;
//
//        pi[0] = 0;
//        int k = 0;
//
//        for (int q = 1; q < pattern.length(); q++) {
//            while (k > 0 && pattern.charAt(k + 1) != pattern.charAt(q)) {
//                k = pi[k];
//            }
//
//            if (pattern.charAt(k + 1) == pattern.charAt(q)) {
//                k++;
//            }
//
//            pi[q] = k;
//        }
    }
}