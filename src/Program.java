import calculator.Calculator;

import java.io.IOException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws IOException {
        Calculator calc = new Calculator();
        Scanner in = new Scanner(System.in);

        System.out.println("Введите выражение с одним действием и арабскими или римскими числами от 1 до 10:");
        while (true) {

            String str = in.nextLine();

            calc.init(str);
            calc.showResult();
        }
    }
}
