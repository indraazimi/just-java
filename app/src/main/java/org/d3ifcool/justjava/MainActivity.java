package org.d3ifcool.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int mQuantity = 2;
    private TextView mQuantityTextView;
    private TextView mPriceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuantityTextView = findViewById(R.id.quantity_textview);
        mPriceTextView = findViewById(R.id.price_textview);
    }

    public void decrement(View view) {
        if (mQuantity==0) return;

        mQuantity--;
        mQuantityTextView.setText(String.valueOf(mQuantity));
    }

    public void increment(View view) {
        mQuantity++;
        mQuantityTextView.setText(String.valueOf(mQuantity));
    }

    public void order(View view) {
        int price = mQuantity * 5;
        mPriceTextView.setText("$" + price);
    }
}
