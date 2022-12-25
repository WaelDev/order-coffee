package com.example.ordercoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    static final int PRICE_OF_ONE_COFFEE = 5;
    boolean hasWhippedCream = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(view -> {
            increment();
        });

        Button minButton = findViewById(R.id.min_button);
        minButton.setOnClickListener(view -> {
            decrement();
        });

        Button orderButton = findViewById(R.id.order_button);
        orderButton.setOnClickListener(v -> {
            Log.v("MainActivity", "Quantity : " + quantity);
            submitOrder();
        });



    }

    private void increment() {
        quantity++;
        displayQuantity();
    }

    private void decrement() {
        if (quantity > 0) {
            quantity--;
            displayQuantity();
        }
    }

    private void displayQuantity() {
        TextView quantityTextView = findViewById(R.id.quantity_num);
        quantityTextView.setText("" + quantity);
    }

    private void submitOrder() {
        TextView priceTextView = findViewById(R.id.price);
        CheckBox whippedCreamCheck = findViewById(R.id.checkbox_whipped_cream);
        hasWhippedCream = whippedCreamCheck.isChecked();
        Log.v("MainActivity", "Whipped cream : " + hasWhippedCream);
        priceTextView.setText(displayMessage());
    }

    private String displayMessage() {
        return "Name: Wael Eddine" +
                "\nAdd whipped cream ? " + hasWhippedCream +
                "\nQuantity: " + quantity +
                "\nTotal: " + NumberFormat.getCurrencyInstance().format(calculatePrice()) +
                "\nThank you!";
    }

    /**
     * Calculate the price of the order
     *
     * @return total price
     */
    private int calculatePrice() {
        return quantity * PRICE_OF_ONE_COFFEE;
    }
}