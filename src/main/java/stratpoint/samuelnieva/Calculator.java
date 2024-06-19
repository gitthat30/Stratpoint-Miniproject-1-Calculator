package stratpoint.samuelnieva;

import java.util.Stack;

public class Calculator {

    public double addNum(double x, double y) {
        return x + y;
    }

    public double subNum(double x, double y) {
        return x - y;
    }

    public double divNum(double x, double y) {
        return x / y;
    }

    public double mulNum(double x, double y) {
        return x * y;
    }

    public double calculateString(String e) {
        double total = 0;

        Stack<Double> nums = new Stack<>();
        Stack<Character> symbols = new Stack<>();
        char[] chars = e.toCharArray();
        boolean calc = false;

        for (int i = 0;i < chars.length;i++) {
            boolean numFlag = Character.getNumericValue(chars[i]) != -1;
            boolean symFlag1 = chars[i] == '+' || chars[i] == '-';
            boolean symFlag2 = chars[i] == '*' || chars[i] == '/';

            if(calc) {
                //If calc is true, guaranteed it's a number
                Double temp = nums.pop();
                Double temp2;

                if(i != chars.length - 1 && Character.getNumericValue(chars[i+1]) != -1) {
                    System.out.println("Here?");
                    String temp3 = "" + chars[i];
                    while(Character.getNumericValue(chars[i+1]) != -1) {
                        i++;
                        temp3 += chars[i];
                    }

                    temp2 = Double.parseDouble(temp3);
                }
                else {
                    temp2 = (double) Character.getNumericValue(chars[i]);
                }

                Character op = symbols.pop();

                if(op == '*') {
                    nums.push(this.mulNum(temp, temp2));
                }
                else {
                    nums.push(this.divNum(temp, temp2));
                }

                calc = false;
            }
            else {
                if(numFlag) {
                    if(i != chars.length - 1 && Character.getNumericValue(chars[i+1]) != -1) {
                        String temp = "" + chars[i];
                        while(Character.getNumericValue(chars[i+1]) != -1) {
                            i++;
                            temp += chars[i];
                        }

                        nums.push(Double.parseDouble(temp));
                    }
                    else
                        nums.push((double) Character.getNumericValue(chars[i]));
                }

                else if(symFlag1 || symFlag2) {
                    symbols.push(chars[i]);
                }

                if(symFlag2)
                    calc = true;
            }
        }

        //Last pass
        total = nums.pop();

        //Operations done from right to left (Stack)
        while(nums.size() > 0 && symbols.size() > 0) {
            switch(symbols.pop()) {
                case '+':
                    total = this.addNum(total, nums.pop());
                    break;
                case '-':
                    total = this.subNum(nums.pop(), total);
                    break;
            }
        }

        return total;
    }
}
