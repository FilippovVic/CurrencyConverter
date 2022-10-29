package com.example.currencyconverter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private double dollar;
    private double euro;
    private double manat;

    private double dollarSetValue;
    private double euroSetValue;
    private double roubleSetValue;
    private double manatSetValue;

    private ArrayList<String> list;

    private Values values;

    private EditText editDollar;
    private EditText editEuro;
    private EditText editRouble;
    private EditText editManat;

    private double scale;

    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        androidx.core.splashscreen.SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @SuppressLint("ResourceAsColor")
    public void init() {
        editDollar = findViewById(R.id.editTextDollar);
        editEuro = findViewById(R.id.editTextEuro);
        editRouble = findViewById(R.id.editTextRouble);
        editManat = findViewById(R.id.editTextManat);

        scale = Math.pow(10, 4);

        editDollar.setOnKeyListener((view, i, keyEvent) -> {
            if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                editDollar.clearFocus();
                calculateValues("dollar");
            }
            return false;
        });

        editEuro.setOnKeyListener((view, i, keyEvent) -> {
            if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                editEuro.clearFocus();
                calculateValues("euro");
            }
            return false;
        });

        editRouble.setOnKeyListener((view, i, keyEvent) -> {
            if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                editRouble.clearFocus();
                calculateValues("rouble");
            }
            return false;
        });

        editManat.setOnKeyListener((view, i, keyEvent) -> {
            if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                editManat.clearFocus();
                calculateValues("manat");
            }
            return false;
        });

        editDollar.setOnFocusChangeListener((view, b) -> {
            if (!view.isFocused()) {
                hideKeyBoard(view);
                calculateValues("dollar");
            }
        });

        editEuro.setOnFocusChangeListener((view, b) -> {
            if (!view.isFocused()) {
                hideKeyBoard(view);
                calculateValues("euro");
            }
        });

        editRouble.setOnFocusChangeListener((view, b) -> {
            if (!view.isFocused()) {
                hideKeyBoard(view);
                calculateValues("rouble");
            }
        });

        editManat.setOnFocusChangeListener((view, b) -> {
            if (!view.isFocused()) {
                hideKeyBoard(view);
                calculateValues("manat");
            }
        });
    }

    public void calculateValues(String currency) {

        if (dollar == 0 && euro == 0 && manat == 0) {

            if (editDollar.getText().toString().equals("") && editEuro.getText().toString().equals("") && editRouble.getText().toString().equals("") && editManat.getText().toString().equals("")) {

            } else {
                if (toast != null) {
                    toast.cancel();
                }
                toast = Toast.makeText(this, "Need currency update!", Toast.LENGTH_SHORT);
                toast.show();
            }
        } else {
            if (!editDollar.getText().toString().equals("") || !editEuro.getText().toString().equals("") || !editRouble.getText().toString().equals("") || !editManat.getText().toString().equals("")) {

                switch (currency) {

                    case "dollar":

                        if (!editDollar.getText().toString().equals("")) {

                            dollarSetValue = Double.parseDouble(editDollar.getText().toString());
                            editDollar.setText(String.valueOf(dollarSetValue));

                            euroSetValue = (dollarSetValue * dollar) / euro;
                            editEuro.setText(String.valueOf(Math.ceil(euroSetValue * scale)/scale));

                            editRouble.setText(String.valueOf(Math.ceil(dollarSetValue * dollar * scale)/scale));

                            manatSetValue =  (dollarSetValue * dollar) / manat;
                            editManat.setText(String.valueOf(Math.ceil(manatSetValue * scale)/scale));
                        } else {

                            editDollar.setText("");
                            editEuro.setText("");
                            editRouble.setText("");
                            editManat.setText("");
                        }
                        break;

                    case "euro":

                        if (!editEuro.getText().toString().equals("")) {

                            euroSetValue = Double.parseDouble(editEuro.getText().toString());
                            editEuro.setText(String.valueOf(euroSetValue));

                            dollarSetValue = (euroSetValue * euro) / dollar;
                            editDollar.setText(String.valueOf(Math.ceil(dollarSetValue * scale)/scale));

                            editRouble.setText(String.valueOf(Math.ceil(euroSetValue * euro * scale)/scale));

                            manatSetValue = (euroSetValue * euro) / manat;
                            editManat.setText(String.valueOf(Math.ceil(manatSetValue * scale)/scale));
                        } else {

                            editDollar.setText("");
                            editEuro.setText("");
                            editRouble.setText("");
                            editManat.setText("");
                        }
                        break;

                    case "rouble":

                        if (!editRouble.getText().toString().equals("")) {

                            roubleSetValue = Double.parseDouble(editRouble.getText().toString());
                            editRouble.setText(String.valueOf(roubleSetValue));

                            dollarSetValue = roubleSetValue / dollar;
                            editDollar.setText(String.valueOf(Math.ceil(dollarSetValue * scale)/scale));

                            euroSetValue = roubleSetValue / euro;
                            editEuro.setText(String.valueOf(Math.ceil(euroSetValue * scale)/scale));

                            manatSetValue = roubleSetValue / manat;
                            editManat.setText(String.valueOf(Math.ceil(manatSetValue * scale)/scale));
                        } else {

                            editDollar.setText("");
                            editEuro.setText("");
                            editRouble.setText("");
                            editManat.setText("");
                        }
                        break;

                    case "manat":

                        if (!editManat.getText().toString().equals("")) {

                            manatSetValue = Double.parseDouble(editManat.getText().toString());
                            editManat.setText(String.valueOf(manatSetValue));

                            euroSetValue = (manatSetValue * manat) / euro;
                            editEuro.setText((String.valueOf(Math.ceil(euroSetValue * scale)/scale)));

                            editRouble.setText(String.valueOf(Math.ceil(manatSetValue * manat * scale)/scale));

                            dollarSetValue = (manatSetValue * manat) / dollar;
                            editDollar.setText(String.valueOf(Math.ceil(dollarSetValue * scale)/scale));
                        } else {

                            editDollar.setText("");
                            editEuro.setText("");
                            editRouble.setText("");
                            editManat.setText("");
                        }
                }
            } else {

                System.out.println("Вот-вот!");

            }
        }
    }

    public void hideKeyBoard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    public void updateCurrencyData(View view) {

        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() && conMgr.getActiveNetworkInfo().isConnected()) {

            values = new Values();

            Thread thread = new Thread(values);
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException ignored) {

            }
            list = values.getValues();

            dollar = Double.parseDouble(list.get(0));
            euro = Double.parseDouble(list.get(1));
            manat = Double.parseDouble(list.get(2));

        } else {

            if (toast != null) {
                toast.cancel();
            }
            toast = Toast.makeText(this, "Please check Internet connection!", Toast.LENGTH_SHORT);
            toast.show();

        }
    }
}