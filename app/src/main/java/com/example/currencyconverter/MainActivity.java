package com.example.currencyconverter;

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

    private float dollar;
    private float euro;
    private float manat;

    private float dollarSetValue;
    private float euroSetValue;
    private float roubleSetValue;
    private float manatSetValue;

    private ArrayList<String> list;

    private Values values;

    private EditText editDollar;
    private EditText editEuro;
    private EditText editRouble;
    private EditText editManat;

    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        androidx.core.splashscreen.SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {

        editDollar = findViewById(R.id.editTextDollar);
        editEuro = findViewById(R.id.editTextEuro);
        editRouble = findViewById(R.id.editTextRouble);
        editManat = findViewById(R.id.editTextManat);

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

                            dollarSetValue = Float.parseFloat(editDollar.getText().toString());
                            editDollar.setText(String.valueOf(dollarSetValue));

                            euroSetValue = dollarSetValue * dollar / euro;
                            editEuro.setText(String.valueOf(euroSetValue));

                            editRouble.setText(String.valueOf(dollarSetValue * dollar));

                            manatSetValue = dollarSetValue * dollar / manat;
                            editManat.setText(String.valueOf(manatSetValue));
                        } else {

                            editDollar.setText("");
                            editEuro.setText("");
                            editRouble.setText("");
                            editManat.setText("");
                        }
                        break;

                    case "euro":

                        if (!editEuro.getText().toString().equals("")) {

                            euroSetValue = Float.parseFloat(editEuro.getText().toString());
                            editEuro.setText(String.valueOf(euroSetValue));

                            dollarSetValue = (euroSetValue * euro) / dollar;
                            editDollar.setText(String.valueOf(dollarSetValue));

                            editRouble.setText(String.valueOf(euroSetValue * euro));

                            manatSetValue = (euroSetValue * euro) / manat;
                            editManat.setText(String.valueOf(manatSetValue));
                        } else {

                            editDollar.setText("");
                            editEuro.setText("");
                            editRouble.setText("");
                            editManat.setText("");
                        }
                        break;

                    case "rouble":

                        if (!editRouble.getText().toString().equals("")) {

                            roubleSetValue = Float.parseFloat(editRouble.getText().toString());
                            editRouble.setText(String.valueOf(roubleSetValue));

                            dollarSetValue = roubleSetValue / dollar;
                            editDollar.setText(String.valueOf(dollarSetValue));

                            euroSetValue = roubleSetValue / euro;
                            editEuro.setText(String.valueOf(euroSetValue));

                            manatSetValue = roubleSetValue / manat;
                            editManat.setText(String.valueOf(manatSetValue));
                        } else {

                            editDollar.setText("");
                            editEuro.setText("");
                            editRouble.setText("");
                            editManat.setText("");
                        }
                        break;

                    case "manat":

                        if (!editManat.getText().toString().equals("")) {

                            manatSetValue = Float.parseFloat(editManat.getText().toString());
                            editManat.setText(String.valueOf(manatSetValue));

                            euroSetValue = manatSetValue * manat / euro;
                            editEuro.setText(String.valueOf(euroSetValue));

                            editRouble.setText(String.valueOf(manatSetValue * manat));

                            dollarSetValue = manatSetValue * manat / dollar;
                            editDollar.setText(String.valueOf(dollarSetValue));
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

            dollar = Float.parseFloat(list.get(0));
            euro = Float.parseFloat(list.get(1));
            manat = Float.parseFloat(list.get(2));

        } else {

            if (toast != null) {
                toast.cancel();
            }
            toast = Toast.makeText(this, "Please check Internet connection!", Toast.LENGTH_SHORT);
            toast.show();

        }
    }
}