package org.example;
import java.util.*;
import java.lang.String;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        try{
            String expression = scan.nextLine();

            String[]numbers=findNumbers(expression);
            String[]operators=findOperators(expression);
            if(operators.length>=numbers.length)throw new Exception("Не вірно введені дані");
            expression = doMulty(numbers, operators);
            numbers=findNumbers(expression);
            operators=findOperators(expression);
            Operations(numbers,operators);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }

    public static String[] findNumbers(String n){
        String[] numbers = n.split("[\\+\\-\\*\\/]");
        return numbers;
    }
    public static String[] findOperators(String o){
        int count = 0;
        for (var item : o.toCharArray()) {
            if (item == '+' || item == '-' || item == '*' || item == '/') {
                count++;
            }
        }

        String[] operators = new String[count];
        count = 0;
        for (var item : o.toCharArray()) {
            if (item == '+' || item == '-' || item == '*' || item == '/') {
                operators[count] = String.valueOf(item);
                count++;
            }
        }
        return operators;
    }
    public static String doMulty(String[] num, String[] op) {
            String exp="";
            int j = 0;
            boolean end = false;
            for (int i =j;i<num.length;i++){
                if(op[j].equals("*") && i+1!=num.length && end==false){

                    exp+=Integer.parseInt(num[i]) * Integer.parseInt(num[i + 1]);
                    if(j+1!=op.length)exp+=op[j+1];
                    end=true;
                    if(j<op.length-2)j+=2;
                    if(i<num.length-1)i++;

                }
                else{
                    exp+=num[i];
                    if(i<num.length-1)exp+=op[j];
                    if(j<op.length-1)j++;
                }
            }
            for (var item : exp.toCharArray()){
                if(item=='*'){
                    String[]numbers=findNumbers(exp);
                    String[]operators=findOperators(exp);
                    exp = doMulty(numbers,operators);

                }
            }
        return exp;
    }
    public static void Operations(String[] num, String[] op) throws Exception {
        double result = Double.parseDouble(num[0]);
        int j =0;
        for (int i =1;i<num.length;i++){
            if(op[j].equals("+")){
                result+=Double.parseDouble(num[i]);
            }
            if(op[j].equals("-")){
                result-=Double.parseDouble(num[i]);
            }
            if(op[j].equals("/")){
                if(Double.parseDouble(num[i]) != 0)
                    result/=Double.parseDouble(num[i]);
                else throw new Exception("Не можна ділити на 0");
            }
            j++;
        }
        System.out.println(result);
    }
}
