package com.kuo.samplecalculator;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Kuo on 2015/10/27.
 */
public class CalculatorView extends LinearLayout {

    private ArrayList<TextView> textViews = new ArrayList<>();
    private Set<CalculatorUntil> calculatorUntils = new HashSet<>();
    private OnItemCliclListener onItemCliclListener;

    private boolean isCreated = false;

    private int autoTextViewWidth;
    private int textViewCount = 0;
    private int numberBackground = R.drawable.background_selector;
    private int numberTextColor = Color.parseColor("#212121");

    public static final int PLUS = 0;
    public static final int MINUS = 1;
    public static final int TIMES = 2;
    public static final int DIVIDED = 3;
    public static final int NUMBER = -1;
    public static final int CLEAR = 15;
    public static final int CLEAR_ALL = 16;
    public static final int OK = 17;

    private String number_1 = "";
    private String number_2 = "";
    private String operator = "";
    private String answer = "";

    public CalculatorView(Context context) {
        super(context);
    }

    public CalculatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if(!isCreated) {
            Log.d("onSizeChanged", "onSizeChanged");
            Log.d("width", "" + w);
            onCreateView(w, h);
            isCreated = true;
        }


    }

    private void onCreateView(int width, int height) {

        Log.d("CalculatorView", "Create View");
        textViewCount = 0;
        autoTextViewWidth = width / 4;

        setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));

        LinearLayout.LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(HORIZONTAL);

        onCreateTextViews();
        this.addView(addOperatorTextView());

        linearLayout.addView(addNumberTextView());
        linearLayout.addView(addContorlTextView());
        this.addView(linearLayout);

    }

    private void onCreateTextViews() {

        LinearLayout.LayoutParams textViewParams = new LayoutParams(autoTextViewWidth, autoTextViewWidth);
        String[] texts = {"+", "-", "×", "÷", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "0", "C", "AC", "OK"};

        for(int i = 0 ; i < texts.length ; i++) {
            textViews.add(getCustomTextView(textViewParams, texts[i], i, numberBackground, numberTextColor));
        }

        for(CalculatorUntil calculatorUntil : calculatorUntils) {
            if(calculatorUntil.getColor() != -1) {
                textViews.get(calculatorUntil.getId()).setTextColor(calculatorUntil.getColor());
            }
            if(calculatorUntil.getBackground() != -1) {
                textViews.get(calculatorUntil.getId()).setBackgroundResource(calculatorUntil.getBackground());
            }
        }

    }

    private LinearLayout addOperatorTextView() {

        LinearLayout.LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(HORIZONTAL);

        for(int i = 0 ; i < 4 ; i++) {
            linearLayout.addView(textViews.get(i));
            textViewCount++;
        }

        return linearLayout;
    }

    private LinearLayout addNumberTextView() {

        LinearLayout.LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        LinearLayout mainLayout = new LinearLayout(getContext());
        mainLayout.setLayoutParams(layoutParams);
        mainLayout.setOrientation(VERTICAL);

        for(int i = 0 ; i < 4 ; i++) {

            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setOrientation(HORIZONTAL);

            for(int j = 0 ; j < 3 ; j++) {
                linearLayout.addView(textViews.get(textViewCount));
                textViewCount++;
            }

            mainLayout.addView(linearLayout);
        }
        return mainLayout;
    }

    private LinearLayout addContorlTextView() {

        LinearLayout.LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams textViewParams = new LayoutParams(autoTextViewWidth, autoTextViewWidth);
        textViewParams.weight = 1;
        String[] contorls = {"AC", "OK"};

        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(VERTICAL);

        for(int i = 0 ; i < contorls.length ; i++) {
            textViews.get(textViewCount).setLayoutParams(textViewParams);
            linearLayout.addView(textViews.get(textViewCount));
            textViewCount++;
        }
        return linearLayout;
    }

    private TextView getCustomTextView(LayoutParams layoutParams, String text, int id, int bgId, int textColor) {

        TextView textView = new TextView(getContext());
        textView.setLayoutParams(layoutParams);
        textView.setId(id);
        textView.setClickable(true);
        textView.setOnClickListener(onClickListener);
        textView.setGravity(Gravity.CENTER);
        textView.setText(text);
        textView.setTextSize(getPxToDp(autoTextViewWidth) / 2);
        textView.setTextColor(textColor);
        textView.setBackgroundResource(bgId);

        return textView;
    }

    private float getPxToDp(int px) {

        float density = getContext().getResources().getDisplayMetrics().density;

        return  px / density;
    }

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

        if(!answer.equals("")) {
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
            return answer + "";
        } else {
            if(!answer.equals("")) {
                return answer;
            } else {
                return number_1;
            }
        }

    }

    public void setOnItemCliclListener(OnItemCliclListener onItemCliclListener) {
        this.onItemCliclListener = onItemCliclListener;
    }

    public void setColors(int id, int color, int background) {
        if(id == NUMBER) {
            this.numberTextColor = color;
            this.numberBackground = background;
        } else {
            CalculatorUntil calculatorUntil = new CalculatorUntil();
            calculatorUntil.setId(id);
            calculatorUntil.setColor(color);
            calculatorUntil.setBackground(background);
            calculatorUntils.add(calculatorUntil);
        }
    }

    public void setColors(int id, int color) {
        if(id == NUMBER) {
            this.numberTextColor = color;
        } else {
            CalculatorUntil calculatorUntil = new CalculatorUntil();
            calculatorUntil.setId(id);
            calculatorUntil.setColor(color);
            calculatorUntils.add(calculatorUntil);
        }
    }

    public interface OnItemCliclListener {
        void onClick(String text);
    }

    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {

            if(onItemCliclListener != null) {
                switch (view.getId()) {
                    case 0:
                        textViews.get(textViews.size()-1).setText("=");
                        operator = textViews.get(view.getId()).getText().toString();
                        onNumberClick(answer);
                        onItemCliclListener.onClick(number_1 + operator + number_2);
                        break;
                    case 1:
                        textViews.get(textViews.size()-1).setText("=");
                        onNumberClick(answer);
                        operator = textViews.get(view.getId()).getText().toString();
                        onItemCliclListener.onClick(number_1 + operator + number_2);
                        break;
                    case 2:
                        textViews.get(textViews.size()-1).setText("=");
                        onNumberClick(answer);
                        operator = textViews.get(view.getId()).getText().toString();
                        onItemCliclListener.onClick(number_1 + operator + number_2);
                        break;
                    case 3:
                        textViews.get(textViews.size()-1).setText("=");
                        onNumberClick(answer);
                        operator = textViews.get(view.getId()).getText().toString();
                        onItemCliclListener.onClick(number_1 + operator + number_2);
                        break;
                    case 4:
                        computting(textViews.get(view.getId()).getText().toString());
                        onItemCliclListener.onClick(number_1 + operator + number_2);
                        break;
                    case 5:
                        computting(textViews.get(view.getId()).getText().toString());
                        onItemCliclListener.onClick(number_1 + operator + number_2);
                        break;
                    case 6:
                        computting(textViews.get(view.getId()).getText().toString());
                        onItemCliclListener.onClick(number_1 + operator + number_2);
                        break;
                    case 7:
                        computting(textViews.get(view.getId()).getText().toString());
                        onItemCliclListener.onClick(number_1 + operator + number_2);
                        break;
                    case 8:
                        computting(textViews.get(view.getId()).getText().toString());
                        onItemCliclListener.onClick(number_1 + operator + number_2);
                        break;
                    case 9:
                        computting(textViews.get(view.getId()).getText().toString());
                        onItemCliclListener.onClick(number_1 + operator + number_2);
                        break;
                    case 10:
                        computting(textViews.get(view.getId()).getText().toString());
                        onItemCliclListener.onClick(number_1 + operator + number_2);
                        break;
                    case 11:
                        computting(textViews.get(view.getId()).getText().toString());
                        onItemCliclListener.onClick(number_1 + operator + number_2);
                        break;
                    case 12:
                        computting(textViews.get(view.getId()).getText().toString());
                        onItemCliclListener.onClick(number_1 + operator + number_2);
                        break;
                    case 13:
                        computting(textViews.get(view.getId()).getText().toString());
                        onItemCliclListener.onClick(number_1 + operator + number_2);
                        break;
                    case 14:
                        computting(textViews.get(view.getId()).getText().toString());
                        onItemCliclListener.onClick(number_1 + operator + number_2);
                        break;
                    case 15:
                        break;
                    case 16:
                        allClearCalculate();
                        answer = "";
                        onItemCliclListener.onClick("0");
                        break;
                    case 17:
                        answer = onCalculate(number_1, number_2, operator);
                        onItemCliclListener.onClick(answer);
                        allClearCalculate();
                        break;
                }
            }

        }
    };
}

