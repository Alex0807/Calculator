package calculator;

import java.io.IOException;
import java.util.Objects;


public class Num {
    enum Type {ARA, ROM}

    private static final String[] romanList = {"I","II","III","IV","V","VI","VII","VIII","IX","X"};
    private static final char[] romanSym = {'I', 'V', 'X', 'L', 'C'};
    private static final int[] romanEq = {1, 5, 10, 50, 100};

    private Type type;
    private int x;


    private void init(int a) throws IOException {
        if (a <= 0 && type == Type.ROM)
            throw new IOException("Error: can't write the number");
        x = a;
    }

    Num (String str) throws IOException {
        int a;
        try {
            a = Integer.parseInt(str);
            type = Type.ARA;
        } catch (NumberFormatException e){
            a = parseRoman(str);
            type = Type.ROM;
        }
        if (a < 1 || a > 10)
            throw new IOException("Error: out of range [1, 10], num = "+x);
        init(a);
    }

    Num (int a, Type t) throws IOException {
        type = t;
        init(a);
    }

    public void setType(Num a) { type = a.type; }

    public Type getType() { return type; }
    public int getArabic() { return x; }
    public String getRoman() { return calcRoman(x); }
    public String getString() {
        if (type == Type.ARA)
            return Integer.toString(x);
        else
            return getRoman();
    }


    private String calcRoman(int t){
        if (t <= 10)
            return romanList[t-1];

        StringBuffer s = new StringBuffer();

        for (int i = romanSym.length-1; i >= 0; i--){
            while (t >= romanEq[i]){
                t -= romanEq[i];
                s.append(romanSym[i]);
            }
            if (i > 0){
                int j = i - 1 - (i-1)%2;
                if (t >= romanEq[i]-romanEq[j]){
                    s.append(romanSym[j]);
                    s.append(romanSym[i]);
                    t -= romanEq[i]-romanEq[j];
                }
            }
        }
        return s.toString();
    }

    public boolean equalType(Num y){    /// для проверки на однотипность чисел
        return type == y.type;
    }


    public static int parseRoman(String str) {

        for(int i=0; i < romanList.length; i++){
            if (Objects.equals(str, romanList[i])){
                return i+1;
            }
        }
        return 0;
    }
}
