import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.*;
public class Main {

    private static String Calc(String input) throws Exception {
        int operationIndex = GetOperationIndex(input);
        char operation = input.charAt(operationIndex);

        String[] splitedInput = input.split("[" + String. valueOf(operation) + "]");

        if(splitedInput.length != 2)
        {
            throw  new Exception("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)"); //Exception-вывод исключения
        }

        String aStr = splitedInput[0];
        String bStr = splitedInput[1];

        boolean isRomanNumbers = IsRomanNumbers(aStr,bStr);
        if(!isRomanNumbers && !IsArabicNumbers(aStr, bStr))
        {

            throw new Exception("throws Exception // т.к используются одновременно разные системы счисления");
        }

        int a = ConvertToArabic(aStr);
        int b = ConvertToArabic(bStr);
        int result = 0;

        if (a < 0 || a > 10 || b < 0 || b > 10){
            throw new Exception("Калькулятор должен принимать на ввод числа от 1 до 10 включительно");
        }

        if (operation == '+') {
            result = a + b;
        } else if (operation == '-') {
            result = a - b;
        } else if (operation == '*') {
            result = a * b;
        } else if (operation == '/') {
            result = a / b;
        }


        if(isRomanNumbers)
        {
            return ConvertToRoman(result);
        }
        else {
            String resultStr = Integer.toString(result);
            return resultStr;
        }
    }

    private static int GetOperationIndex(String input) throws Exception {
        Pattern pattern = Pattern.compile("[\\+\\-\\*\\/]");
        Matcher matcher = pattern.matcher(input);
        if(matcher.find()) {
            return input.indexOf(matcher.group(0));
        }
        throw new Exception("throws Exception //т.к строка не является математической операцией");
    }



    private static int ConvertToArabic(String value) throws Exception {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            String correctValue = value.trim().toUpperCase();

            switch (correctValue) {
                case ("I"):
                    return 1;
                case ("II"):
                    return 2;
                case ("III"):
                    return 3;
                case ("IV"):
                    return 4;
                case ("V"):
                    return 5;
                case ("VI"):
                    return 6;
                case ("VII"):
                    return 7;
                case ("VIII"):
                    return 8;
                case ("IX"):
                    return 9;
                case ("X"):
                    return 10;
                default:
                    throw new Exception("Введено неверное число");
            }
        }
    }



    private static String ConvertToRoman(int input) throws Exception { //throws Exception, для обработки исключений


        if (input < 1 || input > 3999)
            return "throws Exception // т.к в римской системе нет отрицательных чисел";
        String s = "";
        while (input >= 1000) {
            s += "M";
            input -= 1000;
        }
        while (input >= 900) {
            s += "CM";
            input -= 900;
        }
        while (input >= 500) {
            s += "D";
            input -= 500;
        }
        while (input >= 400) {
            s += "CD";
            input -= 400;
        }
        while (input >= 100) {
            s += "C";
            input -= 100;
        }
        while (input >= 90) {
            s += "XC";
            input -= 90;
        }
        while (input >= 50) {
            s += "L";
            input -= 50;
        }
        while (input >= 40) {
            s += "XL";
            input -= 40;
        }
        while (input >= 10) {
            s += "X";
            input -= 10;
        }
        while (input >= 9) {
            s += "IX";
            input -= 9;
        }
        while (input >= 5) {
            s += "V";
            input -= 5;
        }
        while (input >= 4) {
            s += "IV";
            input -= 4;
        }
        while (input >= 1) {
            s += "I";
            input -= 1;
        }
        return s;
    }



    private static boolean IsRomanNumbers(String number1, String number2)
    {
        String correctNumber1 = number1.trim().toUpperCase();
        String correctNumber2 = number2.trim().toUpperCase();

        List<String> romanNumbers = Arrays.asList("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X");
        return romanNumbers.containsAll(Arrays.asList(correctNumber1, correctNumber2));
    }



    private static boolean IsArabicNumbers(String number1, String number2)
    {
        try{
            Integer.parseInt(number1);
            Integer.parseInt(number2);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // System.in-ввод с клавиатуры

        System.out.println("Input:"); // Пишет ввести операцию
        String st = sc.nextLine(); //задается ввод строки
        try {
            String result = Calc(st); // вытягивает переменную result из main, вызов метода Calc
            System.out.println("Output: " + result); //вывод на экран результата
        } catch (Exception e) { // обрабатывает ошибки
            System.out.println(e.getMessage());
        }
    }
}