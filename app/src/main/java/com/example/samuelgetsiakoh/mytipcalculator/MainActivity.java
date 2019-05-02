package com.example.samuelgetsiakoh.mytipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//Importation of widgets packages
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

//EditText event handling and listener
import android.text.Editable;
import android.text.TextWatcher;

//For value formatting
import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {
    //Variables to handle the various widgets
    private TextView amountTextView;
    private TextView percentTextView;
    private TextView tipTextView;
    private TextView totalTextView;

    //Creating the objects for value formatting
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();

    //input value variable
    private double billAmount = 0.0;
    private double percent = 0.15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Getting references tto the various widgets
        amountTextView = (TextView)findViewById(R.id.amountTextView);
        percentTextView = (TextView) findViewById(R.id.percentTextView);
        tipTextView = (TextView) findViewById(R.id.TipTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);

        //Setting tip and total to 0
        tipTextView.setText(currencyFormat.format(0));
        totalTextView.setText(currencyFormat.format(0));

        //Create and get references to the EditText and Seekbar
        EditText amountEditText = (EditText) findViewById(R.id.amountEditText);

        SeekBar percentseekBar = (SeekBar) findViewById(R.id.percentseekBar);

        //Setting up the
        amountEditText.addTextChangedListener(amountTextWatcher);
        percentseekBar.setOnSeekBarChangeListener(seekBarListener);
    }

    //A method to compute the bill and tip
    private void calculate ()
    {
        percentTextView.setText(percentFormat.format(percent));
        double tip = billAmount * percent;
        double total = billAmount + tip;
        tipTextView.setText(currencyFormat.format(tip));
        totalTextView.setText(currencyFormat.format(total));
    }

    private final SeekBar.OnSeekBarChangeListener seekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
            percent = progress / 100.0;
            calculate();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private final TextWatcher amountTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            try {
                billAmount = Double.parseDouble(charSequence.toString()) / 100.0;
                amountTextView.setText(currencyFormat.format(billAmount));
            }
            catch (NumberFormatException e){
                amountTextView.setText("");
                billAmount = 0.0;
            }

            calculate();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}
