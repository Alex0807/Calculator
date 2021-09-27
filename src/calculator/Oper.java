package calculator;

import java.io.IOException;


public class Oper {
    enum mathOperator {
        ADD, DIF, MUL, DIV
    }
    private mathOperator op;
    private static final char[] operList = {'+', '-', '*', '/'};


    Oper(String str) throws IOException {
        int k = findOper(str, 0);
        if (k == -1)
            throw new IOException("Error: have no operator");

        int l = findOper(str, k+1);
        if (l != -1)
            throw new IOException("Error: more then one operator");

        op = getOperator(str.charAt(k));
    }
    public char getSymbol() { return operList[op.ordinal()]; }
    public int getResult(Num a, Num b) { return calc(a.getArabic(), b.getArabic()); }


    public static int findOper(String str, int k) {
        for (mathOperator op: mathOperator.values()) {
            int i = op.ordinal();
            int j = str.indexOf(operList[i], k);

            if ( j != -1)
                return j;
        }
        return -1;
    }


    public mathOperator getOperator(char c) throws IOException {
        int i;

        for (i = 0; i < operList.length; i++)
            if (c == operList[i])
                break;

        for (mathOperator oper : mathOperator.values()) {
            if (oper.ordinal() == i)
                return oper;
        }

        throw new IOException("Err: not correct symbol");
    }

    private int calc(int a, int b) {
        switch (op){
            case ADD: return a + b;
            case DIF: return a - b;
            case MUL: return a * b;
            case DIV: return a / b;
        }
        return 0;
    }
}
