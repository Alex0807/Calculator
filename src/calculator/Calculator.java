package calculator;

import java.io.IOException;


public class Calculator {
    private Num a, b;
    private Oper oper;


    public void init(String str) throws IOException {
        str = str.replaceAll("\\s+","");

        oper = new Oper(str);
        int ind = str.indexOf(oper.getSymbol());

        a = new Num(str.substring(0, ind));
        b = new Num(str.substring(ind+1));

        if (!a.equalType(b))
            throw new IOException("Error: not equal types of numbers");
    }

    public void showResult() throws IOException {
        Num res = new Num(oper.getResult(a, b), a.getType());
        System.out.println(a.getString() +' '+ oper.getSymbol() +' '+ b.getString() + " = "+ res.getString());
    }
}
