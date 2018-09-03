package com.example.kiwan.calculatorapp;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    OperationsHelper helper;
    SQLiteDatabase sqLiteDatabase;
    OperationsAdapter operationsAdapter;
    TextView result, clear, show;
    EditText operat1_2;
    Button clearHistory, add, sub, div, multi, squar, dot, equal, one, two, zero, three, four, five, six, seven, eight, nine, save, x__;
    ImageButton root;
    String text;
    View view;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new OperationsHelper(this);
        sqLiteDatabase = helper.getWritableDatabase();


        // codes for recycler view
        RecyclerView recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setHasFixedSize(true);
        operationsAdapter = new OperationsAdapter(getAllData(), this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(operationsAdapter);


        //Edit text
        operat1_2 = findViewById(R.id.operator);

        // texts
        result = findViewById(R.id.textResult);

        view = findViewById(R.id.includedLayout);

        // buttons
        add = findViewById(R.id.btadd);
        sub = findViewById(R.id.btsub);
        div = findViewById(R.id.btdiv);
        multi = findViewById(R.id.btmulti);
        root = findViewById(R.id.btroot);
        squar = findViewById(R.id.btXsquar);
        dot = findViewById(R.id.btdot);
        equal = findViewById(R.id.btequal);
        x__ = findViewById(R.id.bt__x);
        clear = findViewById(R.id.textClear);
        save = findViewById(R.id.save);
        show = findViewById(R.id.textShow);
        clearHistory = findViewById(R.id.clear_histroy);
        // numbers
        one = findViewById(R.id.bt1);
        two = findViewById(R.id.bt2);
        three = findViewById(R.id.bt3);
        four = findViewById(R.id.bt4);
        five = findViewById(R.id.bt5);
        six = findViewById(R.id.bt6);
        seven = findViewById(R.id.bt7);
        eight = findViewById(R.id.bt8);
        nine = findViewById(R.id.bt9);
        zero = findViewById(R.id.bt0);

        // On click listener for buttons
        operat1_2.setOnClickListener(this);
        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        div.setOnClickListener(this);
        multi.setOnClickListener(this);
        root.setOnClickListener(this);
        squar.setOnClickListener(this);
        dot.setOnClickListener(this);
        equal.setOnClickListener(this);
        x__.setOnClickListener(this);
        clear.setOnClickListener(this);
        save.setOnClickListener(this);
        show.setOnClickListener(this);
        clearHistory.setOnClickListener(this);

        // On click listener for numbers
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);


    }

    @Override
    public void onClick( View v ) {

        String operators = operat1_2.getText().toString();
        int position;
        double num1, num2;
        switch (v.getId()) {

            case R.id.bt1:
                operat1_2.setText(operators + "1");
                break;

            case R.id.bt2:
                operat1_2.setText(operators + "2");
                break;
            case R.id.bt3:
                operat1_2.setText(operators + "3");
                break;

            case R.id.bt4:
                operat1_2.setText(operators + "4");
                break;

            case R.id.bt5:
                operat1_2.setText(operators + "5");
                break;

            case R.id.bt6:
                operat1_2.setText(operators + "6");
                break;

            case R.id.bt7:
                operat1_2.setText(operators + "7");
                break;

            case R.id.bt8:
                operat1_2.setText(operators + "8");
                break;

            case R.id.bt9:
                operat1_2.setText(operators + "9");
                break;

            case R.id.bt0:
                operat1_2.setText(operators + "0");
                break;

            case R.id.btadd:
                operat1_2.setText(operators + "+");
                break;

            case R.id.btsub:
                operat1_2.setText(operators + "-");
                break;

            case R.id.btmulti:
                operat1_2.setText(operators + "*");
                break;

            case R.id.textClear:
                operat1_2.setText(" ");
                result.setText("0.00");
                break;

            case R.id.btdot:
                operat1_2.setText(operators + ".");
                break;

            case R.id.btdiv:
                operat1_2.setText(operators + "/");
                break;

            case R.id.bt__x:
                operat1_2.setText(operators + "!");
                break;

            case R.id.btroot:
                operat1_2.setText(operators + "√");
                break;

            case R.id.btXsquar:
                operat1_2.setText(operators + "^(2)");
                break;

            case R.id.clear_histroy:
                removeAllData();
                break;

            case R.id.textShow:
                if (show.getText() != "Keyboard") {
                    view.setVisibility(View.VISIBLE);
                    show.setText("Keyboard");
                } else {
                    view.setVisibility(View.INVISIBLE);
                    show.setText("history");
                }
                break;

            case R.id.btequal:
                if (operators.contains("+")) {

                    position = operators.indexOf("+");
                    String operator1 = operators.substring(0, position);
                    String operator2 = operators.substring(position + "+".length());

                    num1 = Double.parseDouble(operator1);
                    num2 = Double.parseDouble(operator2);

                    result.setText(Double.toString(num1 + num2));

                    text = operat1_2.getText().toString() + " = " + result.getText();

                    operat1_2.setText(" ");

                } else if (operators.contains("-")) {
                    position = operators.indexOf("-");
                    String operator1 = operators.substring(0, position);
                    String operator2 = operators.substring(position + "-".length());

                    num1 = Double.parseDouble(operator1);
                    num2 = Double.parseDouble(operator2);

                    result.setText(Double.toString(num1 - num2));
                    text = operat1_2.getText().toString() + " = " + result.getText();
                    operat1_2.setText(" ");
                } else if (operators.contains("*")) {
                    position = operators.indexOf("*");
                    String operator1 = operators.substring(0, position);
                    String operator2 = operators.substring(position + "*".length());

                    num1 = Double.parseDouble(operator1);
                    num2 = Double.parseDouble(operator2);

                    result.setText(Double.toString(num1 * num2));
                    text = operat1_2.getText().toString() + " = " + result.getText();
                    operat1_2.setText(" ");
                } else if (operators.contains("/")) {
                    position = operators.indexOf("/");
                    String operator1 = operators.substring(0, position);
                    String operator2 = operators.substring(position + "/".length());

                    num1 = Double.parseDouble(operator1);
                    num2 = Double.parseDouble(operator2);

                    result.setText(Double.toString(num1 / num2));
                    text = operat1_2.getText().toString() + " = " + result.getText();
                    operat1_2.setText(" ");
                } else if (operators.contains("√")) {

                    position = operators.indexOf("√");
                    String operator1 = operators.substring(0, position);

                    try {

                        num1 = Double.parseDouble(operator1);
                        double result1 = Math.sqrt(num1);
                        result.setText(Double.toString(result1));

                        text = operat1_2.getText().toString() + " = " + result.getText();
                        operat1_2.setText(" ");

                    } catch (Exception e) {
                        Toast.makeText(this, "please enter the number before symbol", Toast.LENGTH_LONG).show();
                    }

                } else if (operators.contains("!")) {

                    position = operators.indexOf("!");
                    String operator1 = operators.substring(0, position);
                    try {

                        double num = fun(Double.parseDouble(operator1));
                        result.setText(Double.toString(num));
                        text = operat1_2.getText().toString() + " = " + result.getText();
                        operat1_2.setText(" ");
                    } catch (Exception e) {
                        Toast.makeText(this, "please enter the number before symbol", Toast.LENGTH_LONG).show();
                    }
                } else if (operators.contains("^(2)")) {

                    position = operators.indexOf("^(2)");
                    String operator1 = operators.substring(0, position);

                    try {
                        double num = Double.parseDouble(operator1);
                        result.setText(Double.toString(num * num));
                        text = operat1_2.getText().toString() + " = " + result.getText();
                        operat1_2.setText(" ");

                    } catch (Exception e) {
                        Toast.makeText(this, "please enter the number before symbol", Toast.LENGTH_LONG).show();
                    }
                }

                break;

            case R.id.save:
                ContentValues contentValues = new ContentValues();
                contentValues.put(OperationsContract.OperationEntry.COLUMN_OPERATION, text);

                sqLiteDatabase.insert(OperationsContract.OperationEntry.TABLE_NAME, null, contentValues);
                operationsAdapter.swapCursor(getAllData());
                break;

            default:
                result.setText(operators);
                operat1_2.setText(" ");
                break;
        }
    }

    private double fun( double num ) {
        if (num > 1) {
            return num * fun(num - 1);
        } else {
            return 1;
        }
    }

    private int removeAllData() {
        int count = sqLiteDatabase.delete(OperationsContract.OperationEntry.TABLE_NAME, null, null);
        operationsAdapter.swapCursor(getAllData());
        Toast.makeText(this, "delete all database", Toast.LENGTH_SHORT).show();

        return count;
    }

    private Cursor getAllData() {
        return sqLiteDatabase.query(OperationsContract.OperationEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                OperationsContract.OperationEntry.COLUMN_TIME_STAMP + " DESC");
    }

}
