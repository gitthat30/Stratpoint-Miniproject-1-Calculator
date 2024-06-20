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
        //If parenthesis
        boolean parFlag = false;
        Stack<Character> par = new Stack<>();
        String parString = new String("");

        char[] chars = e.toCharArray();
        boolean calc = false;


        try {
            for (int i = 0;i < chars.length;i++) {
                boolean numFlag = Character.getNumericValue(chars[i]) != -1;
                boolean symFlag1 = chars[i] == '+' || chars[i] == '-';
                boolean symFlag2 = chars[i] == '*' || chars[i] == '/';

                if(chars[i] == '(')
                    parFlag = true;

                if(parFlag) {
                    if(chars[i] == '(')
                        par.push(chars[i]);
                    else if(chars[i] == ')')
                        par.pop();

                    parString += chars[i];



                    if(par.isEmpty()) {
                        parFlag = false;
                        parString = parString.substring(1, parString.length()-1);

                        if(calc) {
                            char op = symbols.pop();
                            double temp = nums.pop();
                            double temp2 = this.calculateString(parString);

                            if(op == '*') {
                                nums.push(this.mulNum(temp, temp2));
                            }
                            else {
                                nums.push(this.divNum(temp, temp2));
                            }
                        }
                        else
                            nums.push(this.calculateString(parString));
                    }
                }
                else if(calc) {
                    //If calc is true, guaranteed it's a number
                    Double temp = nums.pop();
                    Double temp2;

                    if(i != chars.length - 1 && (chars[i+1] == '.' || Character.getNumericValue(chars[i+1]) != -1)) {
                        String temp3 = "" + chars[i];
                        while(i != chars.length - 1 && (chars[i+1] == '.' || Character.getNumericValue(chars[i+1]) != -1)) {
                            i++;

                            if(chars[i] == '.' && temp3.contains("."))
                                throw new Exception("Invalid input: Multiple decimal points found");

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
                        if(i != chars.length - 1  && ((Character.getNumericValue(chars[i+1]) != -1) || chars[i+1] == '.')) {
                            String temp = "" + chars[i];

                            while(i != chars.length - 1 && ((Character.getNumericValue(chars[i+1]) != -1) || chars[i+1] == '.')) {
                                i++;
                                if(chars[i] == '.' && temp.contains("."))
                                    throw new Exception("Invalid input: Multiple decimal points found");

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
            while(!nums.isEmpty() && !symbols.isEmpty()) {
                switch(symbols.pop()) {
                    case '+':
                        total = this.addNum(total, nums.pop());
                        break;
                    case '-':
                        total = this.subNum(nums.pop(), total);
                        break;
                }
            }
        }
        catch(Exception ex) {
            System.out.println();
            System.out.println(ex.getMessage());
        }



        return total;
    }
}
