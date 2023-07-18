package CalcPack;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TreeMap;

    public class RomanArabianCalc {
        static Scanner sc = new Scanner(System.in);
        static int numb1, numb2;
        static char op_r;
        static int result;

        public static void main(String[] args) {
            System.out.println("Введите выражение");
            String inputStr = sc.nextLine();
            if (inputStr.split(" ").length != 3) {
                for (int i = 0; i < inputStr.length(); i++) {
                    if (inputStr.charAt(i) == '+' ||
                            inputStr.charAt(i) == '-' ||
                            inputStr.charAt(i) == '/' ||
                            inputStr.charAt(i) == '*') {
                        op_r = inputStr.charAt(i);
                        break;
                    }
                }
                String[] twoNumber = inputStr.split("[+-/*]");
                processCalc(twoNumber);
            } else {
                String[] primer = inputStr.split(" ");
                op_r = primer[1].charAt(0);
                processCalc(new String[]{primer[0].toUpperCase(), primer[2].toUpperCase()});
            }
        }

        public static int RToN(String roman) {
            try {
                if (roman.equals("I")) {
                    return 1;
                } else if (roman.equals("II")) {
                    return 2;
                } else if (roman.equals("III")) {
                    return 3;
                } else if (roman.equals("IV")) {
                    return 4;
                } else if (roman.equals("V")) {
                    return 5;
                } else if (roman.equals("VI")) {
                    return 6;
                } else if (roman.equals("VII")) {
                    return 7;
                } else if (roman.equals("VIII")) {
                    return 8;
                } else if (roman.equals("IX")) {
                    return 9;
                } else if (roman.equals("X")) {
                    return 10;
                }

            } catch (InputMismatchException e) {
                throw new InputMismatchException("Неверный формат ввода");
            }
            return -1;
        }

        public static int calc(int numb1, int numb2, char op_r) {
            switch (op_r) {
                case '+':
                    result = numb1 + numb2;
                    break;
                case '-':
                    result = numb1 - numb2;
                    break;
                case '*':
                    result = numb1 * numb2;
                    break;
                case '/':
                    try {
                        result = numb1 / numb2;
                    } catch (ArithmeticException | InputMismatchException e) {
                        System.out.println("Exception: " + e);
                        break;
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Неверный знак операции");
            }
            return result;
        }

        public static String convertNToR(int number) {
            TreeMap<Integer, String> map = new TreeMap<Integer, String>();
            map.put(1000, "M");
            map.put(900, "CM");
            map.put(500, "D");
            map.put(400, "CD");
            map.put(100, "C");
            map.put(90, "XC");
            map.put(50, "L");
            map.put(40, "XL");
            map.put(10, "X");
            map.put(9, "IX");
            map.put(5, "V");
            map.put(4, "IV");
            map.put(1, "I");
            int l = map.floorKey(number);
            if (number == l) {
                return map.get(number);
            }
            return map.get(l) + convertNToR(number - l);
        }

        public static boolean isArab(String a) {
            try {
                Integer.parseInt(a);
            } catch (Exception e) {
                return false;
            }
            return true;
        }

        public static void processCalc(String[] twoNumber) {
            if (isArab(twoNumber[0]) && isArab(twoNumber[1])) {
                numb1 = Integer.parseInt(twoNumber[0]);
                numb2 = Integer.parseInt(twoNumber[1]);
                calc(numb1, numb2, op_r);
                System.out.println("Результать в арабской системе счисления");
                System.out.println(numb1 + " " + op_r + " " + numb2 + " " + " = " + result);
            } else {
                numb1 = RToN(twoNumber[0]);
                numb2 = RToN(twoNumber[1]);
                calc(numb1, numb2, op_r);
                System.out.println("Результат в римской системе счисления");
                System.out.println(convertNToR(numb1) + " " + op_r + " " + convertNToR(numb2) + " = " + convertNToR(result));
            }
        }
    }


