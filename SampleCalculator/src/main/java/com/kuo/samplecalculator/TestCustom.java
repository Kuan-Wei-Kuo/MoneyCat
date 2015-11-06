package com.kuo.samplecalculator;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kuo on 2015/11/6.
 */
public class TestCustom extends LinearLayout {

    private ArrayList<TextView> textViews = new ArrayList<>();

    private String number_1 = "";
    private String number_2 = "";
    private String operator = "";
    private String answer = "";

    private int numberBackground = R.drawable.background_selector;
    private int numberTextColor = Color.parseColor("#212121");
    private int textViewCount = 0;

    private OnItemCliclListener onItemCliclListener;

    public interface OnItemCliclListener {
        void onClick(String text);
    }

    public TestCustom(Context context) {
        super(context);

        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.calculator_layout, this);

        findView();
    }

    public TestCustom(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.calculator_layout, this);

        findView();
    }

    private void findView() {

        int[] textIds = {R.id.plusText, R.id.minusText, R.id.times, R.id.dividedText, R.id.num_1,
                R.id.num_2, R.id.num_3, R.id.num_4, R.id.num_5, R.id.num_6, R.id.num_7,
                R.id.num_8, R.id.num_9, R.id.pointText, R.id.num_0, R.id.clearText, R.id.clearAllText, R.id.okText};

        for(int i = 0 ; i < 18 ; i++) {
            TextView textView = (TextView) findViewById(textIds[i]);
            textView.setTextColor(getResources().getColor(R.color.DeepOrange_500));
            textView.setBackgroundResource(R.drawable.background_selector);
            textView.setOnClickListener(onClickListener);
            textViews.add(textView);
        }

    }

    public void setOnItemCliclListener(OnItemCliclListener onItemCliclListener) {
        this.onItemCliclListener = onItemCliclListener;
    }

    private TextView.OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {

            if(onItemCliclListener != null) {
                int i = view.getId();
                if (i == R.id.plusText) {
                    textViews.get(textViews.size() - 1).setText("=");
                    operator = "+";
                    onNumberClick(answer);
                    onItemCliclListener.onClick(number_1 + operator + number_2);

                } else if (i == R.id.minusText) {
                    textViews.get(textViews.size() - 1).setText("=");
                    onNumberClick(answer);
                    operator = "-";
                    onItemCliclListener.onClick(number_1 + operator + number_2);

                } else if (i == R.id.times) {
                    textViews.get(textViews.size() - 1).setText("=");
                    onNumberClick(answer);
                    operator = "×";
                    onItemCliclListener.onClick(number_1 + operator + number_2);

                } else if (i == R.id.dividedText) {
                    textViews.get(textViews.size() - 1).setText("=");
                    onNumberClick(answer);
                    operator = "÷";
                    onItemCliclListener.onClick(number_1 + operator + number_2);

                } else if (i == R.id.num_1) {
                    computting("1");
                    onItemCliclListener.onClick(number_1 + operator + number_2);

                } else if (i == R.id.num_2) {
                    computting("2");
                    onItemCliclListener.onClick(number_1 + operator + number_2);

                } else if (i == R.id.num_3) {
                    computting("3");
                    onItemCliclListener.onClick(number_1 + operator + number_2);

                } else if (i == R.id.num_4) {
                    computting("4");
                    onItemCliclListener.onClick(number_1 + operator + number_2);

                } else if (i == R.id.num_5) {
                    computting("5");
                    onItemCliclListener.onClick(number_1 + operator + number_2);

                } else if (i == R.id.num_6) {
                    computting("6");
                    onItemCliclListener.onClick(number_1 + operator + number_2);

                } else if (i == R.id.num_7) {
                    computting("7");
                    onItemCliclListener.onClick(number_1 + operator + number_2);

                } else if (i == R.id.num_8) {
                    computting("8");
                    onItemCliclListener.onClick(number_1 + operator + number_2);

                } else if (i == R.id.num_9) {
                    computting("9");
                    onItemCliclListener.onClick(number_1 + operator + number_2);

                } else if (i == R.id.pointText) {
                    computting(".");
                    onItemCliclListener.onClick(number_1 + operator + number_2);

                } else if (i == R.id.num_0) {
                    computting("0");
                    onItemCliclListener.onClick(number_1 + operator + number_2);

                } else if (i == R.id.clearText) {
                } else if (i == R.id.clearAllText) {
                    allClearCalculate();
                    answer = "";
                    onItemCliclListener.onClick("0");

                } else if (i == R.id.okText) {
                    answer = onCalculate(number_1, number_2, operator);
                    onItemCliclListener.onClick(answer);
                    allClearCalculate();

                }
            }

        }
    };

    private void computting(String string) {

        if(!operator.equals("")) {
            number_2 += string;
        } else {
            number_1 += string;
        }
    }

    private void allClearCalculate() {
        number_1 = "";
        number_2 = "";
        operator = "";
    }

    private void onNumberClick(String answer) {

        if(!answer.equals("") && number_1.equals("")) {
            this.number_1 = answer;
        }
    }

    private String onCalculate(String number_1, String number_2, String operator) {

        if(textViews.get(textViews.size()-1).getText().toString().equals("=")) {

            double number1 = 0;
            double number2 = 0;
            double answer = 0;

            if(!operator.equals("")) {

                if(number_1.equals("")) {
                    number1 = 0;
                } else {
                    number1 = Double.valueOf(number_1);
                }

                if(number_2.equals("")) {
                    number2 = 0;
                } else {
                    number2 = Double.valueOf(number_2);
                }

                switch (operator) {
                    case "+":
                        answer = number1 + number2 ;
                        break;
                    case "-":
                        answer = number1 - number2 ;
                        break;
                    case "×":
                        answer = number1 * number2 ;
                        break;
                    case "÷":
                        answer = number1 / number2 ;
                        break;
                }
            }
            textViews.get(textViews.size()-1).setText("OK");
            return (int) answer + "";
        } else {
            if(!answer.equals("") && number_1.equals("")) {
                return answer;
            } else {
                return number_1;
            }
        }

    }

}
