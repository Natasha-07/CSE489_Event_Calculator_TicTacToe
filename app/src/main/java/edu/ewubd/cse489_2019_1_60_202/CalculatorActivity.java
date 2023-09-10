package edu.ewubd.cse489_2019_1_60_202;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.content.Context;

import java.util.Stack;

public class CalculatorActivity extends AppCompatActivity {

    private TextView tvEquation;
    private TextView tv;
    private Button btnDel, btn9, btn8, btn7, btn6, btn5, btn4, btn3, btn2, btn1, btn0, btnDiv, btnMul, btnSub, btnAdd, btnEqual, btnPoint, btnPrev, btnNext;


    boolean check_Operator = true;
    boolean check_Dot = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        tvEquation = findViewById(R.id.tvEquation);
        tv = findViewById(R.id.tv);

        btnDel = findViewById(R.id.btnDel);
        btn9 = findViewById(R.id.btn9);
        btn8 = findViewById(R.id.btn8);
        btn7 = findViewById(R.id.btn7);
        btn6 = findViewById(R.id.btn6);
        btn5 = findViewById(R.id.btn5);
        btn4 = findViewById(R.id.btn4);
        btn3 = findViewById(R.id.btn3);
        btn2 = findViewById(R.id.btn2);
        btn1 = findViewById(R.id.btn1);
        btn0 = findViewById(R.id.btn0);
        btnDiv = findViewById(R.id.btnDiv);
        btnMul = findViewById(R.id.btnMul);
        btnSub = findViewById(R.id.btnSub);
        btnAdd = findViewById(R.id.btnAdd);
        btnEqual = findViewById(R.id.btnEqual);
        btnPoint = findViewById(R.id.btnPoint);
        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);

        SharedPreferences sharedPreferences = getSharedPreferences("history", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("currentHistCount", 0);
        editor.apply();



        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = tvEquation.getText().toString();
                if(text == null || text.isEmpty()){
                    return;
                }
                tvEquation.setText(text.substring(0, text.length() - 1));
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = tvEquation.getText().toString();
                if(text == null){
                    text = "";
                }
                tvEquation.setText(text+9);
                check_Operator = false;
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = tvEquation.getText().toString();
                if(text == null){
                    text = "";
                }
                tvEquation.setText(text+8);
                check_Operator = false;
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = tvEquation.getText().toString();
                if(text == null){
                    text = "";
                }
                tvEquation.setText(text+7);
                check_Operator = false;
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = tvEquation.getText().toString();
                if(text == null){
                    text = "";
                }
                tvEquation.setText(text+6);
                check_Operator = false;
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = tvEquation.getText().toString();
                if(text == null){
                    text = "";
                }
                tvEquation.setText(text+5);
                check_Operator = false;
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = tvEquation.getText().toString();
                if(text == null){
                    text = "";
                }
                tvEquation.setText(text+4);
                check_Operator = false;
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = tvEquation.getText().toString();
                if(text == null){
                    text = "";
                }
                tvEquation.setText(text+3);
                check_Operator = false;
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = tvEquation.getText().toString();
                if(text == null){
                    text = "";
                }
                tvEquation.setText(text+2);
                check_Operator = false;
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = tvEquation.getText().toString();
                if(text == null){
                    text = "";
                }
                tvEquation.setText(text+1);
                check_Operator = false;
            }
        });
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = tvEquation.getText().toString();
                if(text == null){
                    text = "";
                }
                tvEquation.setText(text+0);
                check_Operator = false;
            }
        });
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!check_Operator)
                {
                    tvEquation.setText(tvEquation.getText() + "/");
                    check_Operator = true;
                    check_Dot = false;
                }
                else if(tvEquation.getText().toString().isEmpty())
                {
                    tvEquation.setText(tvEquation.getText()+"0/");
                    check_Operator = true;
                    check_Dot = true;
                }
            }
        });
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!check_Operator)
                {
                    tvEquation.setText(tvEquation.getText() + "*");
                    check_Operator = true;
                    check_Dot = false;
                }
                else if(tvEquation.getText().toString().isEmpty())
                {
                    tvEquation.setText(tvEquation.getText()+"0*");
                    check_Operator = true;
                    check_Dot = true;
                }
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!check_Operator)
                {
                    tvEquation.setText(tvEquation.getText() + "-");
                    check_Operator = true;
                    check_Dot = false;
                }
                else if(tvEquation.getText().toString().isEmpty())
                {
                    tvEquation.setText(tvEquation.getText()+"0-");
                    check_Operator = true;
                    check_Dot = true;
                }
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!check_Operator)
                {
                    tvEquation.setText(tvEquation.getText()+"+");
                    check_Operator = true;
                    check_Dot = false;
                }
                else if(tvEquation.getText().toString().isEmpty())
                {
                    tvEquation.setText(tvEquation.getText()+"0+");
                    check_Operator = true;
                    check_Dot = true;
                }
            }
        });
        btnPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvEquation.getText().toString().isEmpty() || check_Operator)
                {
                    tvEquation.setText(tvEquation.getText()+"0.");
                    check_Operator = true;
                    check_Dot = true;
                }
                else if(!check_Dot)
                {
                    tvEquation.setText(tvEquation.getText()+".");
                    check_Operator = true;
                    check_Dot = true;
                }

            }
        });
        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String clck = (String)tvEquation.getText();
                int count = 0;

                if(clck.isEmpty() || check_Operator){

                    Toast.makeText(getApplicationContext(),"Input the equation!",Toast.LENGTH_SHORT).show();
                }
                else{
                    String postfix = Calculator.infixToPostfix(clck);
                    double result = Calculator.evalPostfix(postfix);
                    System.out.println(result);
                    if((int) result == (double) result){
                        tv.setText(" "+ (int)result);
                    }
                    else tv.setText(" "+ result);
                    tvEquation.setText("");
                    System.out.println(result);
                    if (sharedPreferences.contains("histCount")) {
                        count = sharedPreferences.getInt("histCount", 0);
                    }

                    count++;

                    if (count > 10) {
                        count = 10;

                        for (int i = 1; i < 10; i++) {
                            String histEq = "eq" + i;
                            String histRes = "res" + i;

                            int j = i + 1;

                            String histEqNext = "eq" + j;
                            String histResNext = "res" + j;

                            String histEqNextValue = sharedPreferences.getString(histEqNext, "Not Found!");
                            String histResNextValue = sharedPreferences.getString(histResNext, "Not Found!");

                            editor.putString(histEq, histEqNextValue);
                            editor.putString(histRes, histResNextValue);
                            editor.putInt("histCount", count);
                            editor.putInt("currentHistCount", count);
                            editor.apply();
                        }

                    } else {
                        String histEq = "eq" + count;
                        String histRes = "res" + count;

                        editor.putString(histEq, clck);
                        editor.putString(histRes, String.valueOf(result));

                        editor.putInt("histCount", count);
                        editor.putInt("currentHistCount", count);
                        editor.apply();
                    }

                    check_Operator = true;
                    check_Dot = false;
                }

            }
        });


        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hCount = sharedPreferences.getInt("currentHistCount", 0);

                if (hCount > 0) {
                    int c = hCount;
                    c--;

                    if (c > 0) {
                        String histEq = "eq" + c;
                        String histRes = "res" + c;

                        String histEqValue = sharedPreferences.getString(histEq, "Not Found!");
                        String histResValue = sharedPreferences.getString(histRes, "Not Found!");

                        tvEquation.setText(histEqValue);
                        tv.setText(histResValue);

                        editor.putInt("currentHistCount", c);
                        editor.apply();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "No More History Found", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "No History Found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hCount = sharedPreferences.getInt("currentHistCount", 0);

                if (hCount > 0) {
                    int c = hCount;
                    c++;

                    if (c <= 10) {
                        String histEq = "eq" + c;
                        String histRes = "res" + c;

                        String histEqValue = sharedPreferences.getString(histEq, "Not Found!");
                        String histResValue = sharedPreferences.getString(histRes, "Not Found!");

                        tvEquation.setText(histEqValue);
                        tv.setText(histResValue);


                        editor.putInt("currentHistCount", c);
                        editor.apply();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "No More History Found", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "No History Found", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

class Calculator{


    public static double evalPostfix(String postfixExp){

        int index = 0;
        Stack<Double> arr = new Stack<Double>();
        double result = 0;
        //traversing throughout the string postfixExp
        for(int i = 0; i < postfixExp.length(); i++)
        {
            char c = postfixExp.charAt(i);
            //This checks for spaces and skips over them
            if(c == ' ')
                continue;
                //Checking for double digits, so if it's a digit, then continue to take in and add the value
            else if(Character.isDigit(c))
            {
                double x = 0.0;
                boolean check = true;
                //Checking for decimal places in case of a double
                while(Character.isDigit(c) || c == '.')
                {
                    //Calculate the value of the numbers before the decimal point
                    if(c!= '.' && check == true){
                        x = x*10 + (double)(c-'0');
                        i++;
                        c = postfixExp.charAt(i);
                    }
                    //Calculate the value of the numbers after the decimal point
                    else{
                        check = false;
                        x = x + 0.1 + (double)(c-'0')/10;
                        i++;
                        c = postfixExp.charAt(i);
                    }
                }
                i--;
                arr.push(x);
            }
            else
            {

                double val1 = arr.pop();
                double val2 = arr.pop();
                switch(c)
                {
                    case '+':
                        arr.push(val2+val1);
                        break;

                    case '-':
                        arr.push(val2- val1);
                        break;

                    case '/':
                        if (val1 == 0)throw new ArithmeticException();
                        arr.push(val2/val1);
                        break;

                    case '*':
                        arr.push(val2*val1);
                        break;

                    default:
                        throw new ArithmeticException();
                }
            }
        }

        return arr.pop();
    }

    public static String infixToPostfix(String exp){
        String result = "";
        Stack<Character> arr = new Stack<>();
        for (int i = 0; i <exp.length() ; i++) {
            char c = exp.charAt(i);
            //check if operator is char
            if(precedence(c)>0){
                //take off the top of the stack in order to check its precedence
                while(arr.isEmpty()==false && precedence(arr.peek())>=precedence(c)){
                    result += arr.pop();
                }
                result += ' ';
                arr.push(c);
            }

            else{

                result += c;
            }
        }
        for (int i = 0; i <=arr.size() ; i++) {
            result += arr.pop();
        }
        return result;
    }

    //Precedence of the operator
    static int precedence(char c){
        switch (c){
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return -1;
    }

}