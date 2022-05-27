import java.util.Scanner;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("Вводите данные через пробел;");
        System.out.println("используйте арабские числа - от 1 до 10,");
        System.out.println("римские - от I до X c регистром прописных букв");
        System.out.println("и математические операции - '+','-','*','/'.");
        System.out.println("Input:");

        Scanner in = new Scanner(System.in);
        String num1 = in.next();
        String oper = in.next();
        String num2 = in.next();
        in.close();

        String input = ("" + " " + num1 + " " + oper + " " + num2);

        System.out.println("Output:");
        System.out.println(calc(input));
    }

    public static String calc(String input) throws Exception {

        String[] subInput = input.split(" ");
        String num1 = subInput[1];
        String oper = subInput[2];
        String num2 = subInput[3];

        List<String> allVarNum = Arrays.asList(
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X");
        List<String> allVarOper = Arrays.asList("+", "-", "*", "/");

        if (!((allVarNum.contains(num1)) && (allVarOper.contains(oper)) && (allVarNum.contains(num2))))
            throw new Exception("throws Exception: некорректный ввод данных");

        if ((!((allVarNum.subList(0, 10).contains(num1)) && (allVarNum.subList(0, 10).contains(num2)) ||
                (allVarNum.subList(10, 20).contains(num1)) && (allVarNum.subList(10, 20).contains(num2)))))
            throw new Exception("throws Exception: используются числа разных систем счистления");

        String result = "";

        if (allVarNum.subList(0, 10).contains(num1)) {

            int a = Integer.valueOf(num1);
            int b = Integer.valueOf(num2);

            if (allVarOper.subList(0, 1).contains(oper))
                result = Integer.toString(a + b);
            if (allVarOper.subList(1, 2).contains(oper))
                result = Integer.toString(a - b);
            if (allVarOper.subList(2, 3).contains(oper))
                result = Integer.toString(a * b);
            if (allVarOper.subList(3, 4).contains(oper))
                result = Integer.toString(a / b);
        }

        if (allVarNum.subList(10, 20).contains(num1)) {

            int a = Integer.valueOf((allVarNum.indexOf(num1)) - 9);
            int b = Integer.valueOf((allVarNum.indexOf(num2)) - 9);

            int res = 0;
            if (allVarOper.subList(0, 1).contains(oper))
                res = a + b;
            if (allVarOper.subList(1, 2).contains(oper)) {
                res = a - b;
                if (a <= b)
                    throw new Exception("throws Exception: полученный результат меньше или равен нулю");
            }
            if (allVarOper.subList(2, 3).contains(oper))
                res = a * b;
            if (allVarOper.subList(3, 4).contains(oper)) {
                res = a / b;
                if (res == 0)
                    throw new Exception("throws Exception: полученный результат равен нулю");
            }
            List<String> romNum = Arrays.asList(
                    "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I");
            List<String> arabNum = Arrays.asList(
                    "100", "90", "50", "40", "10", "9", "5", "4", "1");

            while (res > 0) {
                for (int i = 0; i < arabNum.size(); i++) {
                    if (res >= Integer.valueOf(arabNum.get(i))) {
                        res = res - Integer.valueOf(arabNum.get(i));
                        result = result + romNum.get(i);
                        break;
                    }
                }
            }
        }
        return result;
    }
}