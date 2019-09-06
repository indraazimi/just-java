package org.d3ifcool.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int mQuantity = 2;
    private EditText mNameEditText;
    private CheckBox mCreamCheckBox;
    private CheckBox mChocoCheckBox;
    private TextView mQuantityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameEditText = findViewById(R.id.name_edittext);
        mCreamCheckBox = findViewById(R.id.whipped_cream_checkbox);
        mChocoCheckBox = findViewById(R.id.chocolate_checkbox);
        mQuantityTextView = findViewById(R.id.quantity_textview);
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
        String name = mNameEditText.getText().toString();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, getString(R.string.name_required), Toast.LENGTH_SHORT).show();
            return;
        }

        String subject = getString(R.string.email_subject, name);
        String message = getString(R.string.order_name, name) + "\n" +
                getString(R.string.order_cream, mCreamCheckBox.isChecked()) + "\n" +
                getString(R.string.order_choco, mChocoCheckBox.isChecked()) + "\n" +
                getString(R.string.order_quantity, mQuantity) + "\n" +
                getString(R.string.order_total, calculatePrice()) + "\n" +
                getString(R.string.thank_you);

        composeEmail(new String[] {"mobpro.d3if@gmail.com"}, subject, message);
    }

    private int calculatePrice() {
        int basePrice = 5;
        if (mCreamCheckBox.isChecked()) basePrice += 1;
        if (mChocoCheckBox.isChecked()) basePrice += 2;

        return mQuantity * basePrice;
    }

    private void composeEmail(String[] addresses, String subject, String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
