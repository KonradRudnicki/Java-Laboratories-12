import java.util.Arrays;
import java.util.LinkedList;

public class Automaton implements IStringMatcher {
    static int NO_OF_CHARS = 255;

    @Override
    public LinkedList<Integer> validShifts(String pattern, String text) {
        LinkedList<Integer> result = new LinkedList<>();

        int m = pattern.length();
        int n = text.length();

        int[][] states = new int[m + 1][NO_OF_CHARS];

        states = computeStates(pattern, m, states);

        int i, curState = 0;

        for (i = 0; i < n; i++) {
            curState = states[curState][text.charAt(i)];

            if (curState == m)
                result.add(i - m + 1);
        }

        return result;
    }

    int[][] computeStates(String pattern, int m, int[][] states) {
        int curState, curChar;

        for (curState = 0; curState <= m; ++curState)
            for (curChar = 0; curChar < NO_OF_CHARS; ++curChar)
                states[curState][curChar] = getNextState(pattern, m, curState, curChar);

        return states;
    }

    static int getNextState(String pattern, int m, int state, int x) {
        if (state < m && x == pattern.charAt(state))
            return state + 1;

        int nextState, i;

        for (nextState = state; nextState > 0; nextState--) {
            if (pattern.charAt(nextState - 1) == x) {
                for (i = 0; i < nextState - 1; i++)
                    if (pattern.charAt(i) != pattern.charAt(state - nextState + 1 + i)) break;

                if (i == nextState - 1)
                    return nextState;
            }
        }

        return 0;
    }
}
