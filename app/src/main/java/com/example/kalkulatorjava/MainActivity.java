package com.example.kalkulatorjava;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener
{
    private boolean dotUsed = false;

    private boolean equalClicked = false;
    private String lastExpression = "";

    private final static int EXCEPTION = -1;
    private final static int IS_NUMBER = 0;
    private final static int IS_OPERAND = 1;
    private final static int IS_DOT = 4;

    Button buttonNumber0;
    Button buttonNumber1;
    Button buttonNumber2;
    Button buttonNumber3;
    Button buttonNumber4;
    Button buttonNumber5;
    Button buttonNumber6;
    Button buttonNumber7;
    Button buttonNumber8;
    Button buttonNumber9;

    Button buttonClear;
    Button buttonBackspace;
    Button buttonPercent;
    Button buttonDivision;
    Button buttonMultiplication;
    Button buttonSubtraction;
    Button buttonAddition;
    Button buttonEqual;
    Button buttonDot;

    TextView textViewInputNumbers;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViewVariables();
        setOnClickListeners();
        setOnTouchListener();
    }

    private void initializeViewVariables()
    {
        buttonNumber0 = (Button) findViewById(R.id.button_zero);
        buttonNumber1 = (Button) findViewById(R.id.button_one);
        buttonNumber2 = (Button) findViewById(R.id.button_two);
        buttonNumber3 = (Button) findViewById(R.id.button_three);
        buttonNumber4 = (Button) findViewById(R.id.button_four);
        buttonNumber5 = (Button) findViewById(R.id.button_five);
        buttonNumber6 = (Button) findViewById(R.id.button_six);
        buttonNumber7 = (Button) findViewById(R.id.button_seven);
        buttonNumber8 = (Button) findViewById(R.id.button_eight);
        buttonNumber9 = (Button) findViewById(R.id.button_nine);

        buttonClear = (Button) findViewById(R.id.button_clear);
        buttonBackspace = (Button) findViewById(R.id.button_backspace);
        buttonPercent = (Button) findViewById(R.id.button_percent);
        buttonDivision = (Button) findViewById(R.id.button_division);
        buttonMultiplication = (Button) findViewById(R.id.button_multiplication);
        buttonSubtraction = (Button) findViewById(R.id.button_subtraction);
        buttonAddition = (Button) findViewById(R.id.button_addition);
        buttonEqual = (Button) findViewById(R.id.button_equal);
        buttonDot = (Button) findViewById(R.id.button_dot);
        textViewInputNumbers = (TextView) findViewById(R.id.textView_input_numbers);
    }

    private void setOnClickListeners()
    {
        buttonNumber0.setOnClickListener(this);
        buttonNumber1.setOnClickListener(this);
        buttonNumber2.setOnClickListener(this);
        buttonNumber3.setOnClickListener(this);
        buttonNumber4.setOnClickListener(this);
        buttonNumber5.setOnClickListener(this);
        buttonNumber6.setOnClickListener(this);
        buttonNumber7.setOnClickListener(this);
        buttonNumber8.setOnClickListener(this);
        buttonNumber9.setOnClickListener(this);

        buttonClear.setOnClickListener(this);
        buttonBackspace.setOnClickListener(this);
        buttonPercent.setOnClickListener(this);
        buttonDivision.setOnClickListener(this);
        buttonMultiplication.setOnClickListener(this);
        buttonSubtraction.setOnClickListener(this);
        buttonAddition.setOnClickListener(this);
        buttonEqual.setOnClickListener(this);
        buttonDot.setOnClickListener(this);
    }

    private void setOnTouchListener()
    {
        buttonNumber0.setOnTouchListener(this);
        buttonNumber1.setOnTouchListener(this);
        buttonNumber2.setOnTouchListener(this);
        buttonNumber3.setOnTouchListener(this);
        buttonNumber4.setOnTouchListener(this);
        buttonNumber5.setOnTouchListener(this);
        buttonNumber6.setOnTouchListener(this);
        buttonNumber7.setOnTouchListener(this);
        buttonNumber8.setOnTouchListener(this);
        buttonNumber9.setOnTouchListener(this);

        buttonClear.setOnTouchListener(this);
        buttonBackspace.setOnTouchListener(this);
        buttonPercent.setOnTouchListener(this);
        buttonDivision.setOnTouchListener(this);
        buttonMultiplication.setOnTouchListener(this);
        buttonSubtraction.setOnTouchListener(this);
        buttonAddition.setOnTouchListener(this);
        buttonDot.setOnTouchListener(this);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        if (viewId == R.id.button_zero) {
            if (addNumber("0")) equalClicked = false;
        } else if (viewId == R.id.button_one) {
            if (addNumber("1")) equalClicked = false;
        } else if (viewId == R.id.button_two) {
            if (addNumber("2")) equalClicked = false;
        } else if (viewId == R.id.button_three) {
            if (addNumber("3")) equalClicked = false;
        } else if (viewId == R.id.button_four) {
            if (addNumber("4")) equalClicked = false;
        } else if (viewId == R.id.button_five) {
            if (addNumber("5")) equalClicked = false;
        } else if (viewId == R.id.button_six) {
            if (addNumber("6")) equalClicked = false;
        } else if (viewId == R.id.button_seven) {
            if (addNumber("7")) equalClicked = false;
        } else if (viewId == R.id.button_eight) {
            if (addNumber("8")) equalClicked = false;
        } else if (viewId == R.id.button_nine) {
            if (addNumber("9")) equalClicked = false;
        } else if (viewId == R.id.button_addition) {
            if (addOperand("+")) equalClicked = false;
        } else if (viewId == R.id.button_subtraction) {
            if (addOperand("-")) equalClicked = false;
        } else if (viewId == R.id.button_multiplication) {
            if (addOperand("x")) equalClicked = false;
        } else if (viewId == R.id.button_division) {
            if (addOperand("\u00F7")) equalClicked = false;
        } else if (viewId == R.id.button_percent) {
            if (addOperand("%")) equalClicked = false;
        } else if (viewId == R.id.button_dot) {
            if (addDot()) equalClicked = false;
        } else if (viewId == R.id.button_backspace) {
            if (addBackspace()) equalClicked = false;
        } else if (viewId == R.id.button_clear) {
            textViewInputNumbers.setText("");
            dotUsed = false;
            equalClicked = false;
        } else if (viewId == R.id.button_equal) {
            if (textViewInputNumbers.getText().toString() != null && !textViewInputNumbers.getText().toString().equals("")) {
                calculate(textViewInputNumbers.getText().toString());
            }
        }
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent)
    {
        switch (motionEvent.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            {
                view.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                view.invalidate();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                view.getBackground().clearColorFilter();
                view.invalidate();
                break;
            }
        }
        return false;
    }

    private boolean addDot()
    {
        boolean done = false;

        if (textViewInputNumbers.getText().length() == 0)
        {
            textViewInputNumbers.setText("0.");
            dotUsed = true;
            done = true;
        } else if (dotUsed == true)
        {
        } else if (defineLastCharacter(textViewInputNumbers.getText().charAt(textViewInputNumbers.getText().length() - 1) + "") == IS_OPERAND)
        {
            textViewInputNumbers.setText(textViewInputNumbers.getText() + "0.");
            done = true;
            dotUsed = true;
        } else if (defineLastCharacter(textViewInputNumbers.getText().charAt(textViewInputNumbers.getText().length() - 1) + "") == IS_NUMBER)
        {
            textViewInputNumbers.setText(textViewInputNumbers.getText() + ".");
            done = true;
            dotUsed = true;
        }
        return done;
    }

    private boolean addOperand(String operand)
    {
        boolean done = false;
        int operationLength = textViewInputNumbers.getText().length();
        if (operationLength > 0)
        {
            String lastInput = textViewInputNumbers.getText().charAt(operationLength - 1) + "";

            if ((lastInput.equals("+") || lastInput.equals("-") || lastInput.equals("*") || lastInput.equals("\u00F7") || lastInput.equals("%")))
            {
                Toast.makeText(getApplicationContext(), "Wrong format", Toast.LENGTH_LONG).show();
            } else if (operand.equals("%") && defineLastCharacter(lastInput) == IS_NUMBER)
            {
                textViewInputNumbers.setText(textViewInputNumbers.getText() + operand);
                dotUsed = false;
                equalClicked = false;
                lastExpression = "";
                done = true;
            } else if (!operand.equals("%"))
            {
                textViewInputNumbers.setText(textViewInputNumbers.getText() + operand);
                dotUsed = false;
                equalClicked = false;
                lastExpression = "";
                done = true;
            }
        } else
        {
            Toast.makeText(getApplicationContext(), "Wrong Format. Operand Without any numbers?", Toast.LENGTH_LONG).show();
        }
        return done;
    }

    private boolean addNumber(String number)
    {
        boolean done = false;
        int operationLength = textViewInputNumbers.getText().length();
        if (operationLength > 0)
        {
            String lastCharacter = textViewInputNumbers.getText().charAt(operationLength - 1) + "";
            int lastCharacterState = defineLastCharacter(lastCharacter);

            if (operationLength == 1 && lastCharacterState == IS_NUMBER && lastCharacter.equals("0"))
            {
                textViewInputNumbers.setText(number);
                done = true;
            } else if (lastCharacterState == IS_NUMBER || lastCharacterState == IS_OPERAND || lastCharacterState == IS_DOT)
            {
                textViewInputNumbers.setText(textViewInputNumbers.getText() + number);
                done = true;
            }
        } else
        {
            textViewInputNumbers.setText(textViewInputNumbers.getText() + number);
            done = true;
        }
        return done;
    }


    private void calculate(String input) {
        try {
            // Replace characters for safe evaluation
            String expression = input.replaceAll("%", "/100").replaceAll("x", "*").replaceAll("[^\\x00-\\x7F]", "/");

            // Evaluate the expression
            BigDecimal result = evaluateExpression(expression);

            // Format the result
            String formattedResult = formatResult(result);

            // Update the input field with the result
            textViewInputNumbers.setText(formattedResult);
            equalClicked = true;
        } catch (ArithmeticException e) {
            Toast.makeText(getApplicationContext(), "Division by zero is not allowed", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Wrong Format", Toast.LENGTH_SHORT).show();
        }
    }

    private BigDecimal evaluateExpression(String expression) {
        String[] tokens = expression.split("\\+|-|\\*|/");

        BigDecimal result = new BigDecimal(tokens[0]);
        char operator = ' ';

        for (int i = 1; i < tokens.length; i++) {
            if (expression.charAt(tokens[i - 1].length()) == '+') {
                operator = '+';
            } else if (expression.charAt(tokens[i - 1].length()) == '-') {
                operator = '-';
            } else if (expression.charAt(tokens[i - 1].length()) == '*') {
                operator = '*';
            } else if (expression.charAt(tokens[i - 1].length()) == '/') {
                operator = '/';
            }

            BigDecimal operand = new BigDecimal(tokens[i]);

            switch (operator) {
                case '+':
                    result = result.add(operand);
                    break;
                case '-':
                    result = result.subtract(operand);
                    break;
                case '*':
                    result = result.multiply(operand);
                    break;
                case '/':
                    if (operand.compareTo(BigDecimal.ZERO) == 0) {
                        throw new ArithmeticException("Division by zero");
                    }
                    result = result.divide(operand, 8, BigDecimal.ROUND_HALF_UP);
                    break;
            }
        }

        return result;
    }

    private String formatResult(BigDecimal result) {
        String formattedResult = result.toPlainString();
        if (formattedResult.endsWith(".0")) {
            formattedResult = formattedResult.substring(0, formattedResult.length() - 2);
        }
        return formattedResult;
    }




    private int defineLastCharacter(String lastCharacter)
    {
        try
        {
            Integer.parseInt(lastCharacter);
            return IS_NUMBER;
        } catch (NumberFormatException e)
        {
        }

        if ((lastCharacter.equals("+") || lastCharacter.equals("-") || lastCharacter.equals("x") || lastCharacter.equals("\u00F7") || lastCharacter.equals("%")))
            return IS_OPERAND;

        if (lastCharacter.equals("."))
            return IS_DOT;

        return -1;
    }

    private boolean addBackspace() {
        boolean done = false;
        int operationLength = textViewInputNumbers.getText().length();

        if (operationLength > 0) {
            String currentText = textViewInputNumbers.getText().toString();
            String newText = currentText.substring(0, operationLength - 1);
            textViewInputNumbers.setText(newText);
            done = true;
        }

        return done;
    }



}
