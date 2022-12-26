package com.example.ordercoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity = 1;
    static final int PRICE_OF_ONE_COFFEE = 5;
    boolean hasWhippedCream = false;
    boolean hasChocolate = false;
    String name;

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
        if (quantity > 1) {
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
        CheckBox whippedCreamCheckBox = findViewById(R.id.whipped_cream_checkbox);
        hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = findViewById(R.id.chocolate_checkbox);
        hasChocolate =  chocolateCheckBox.isChecked();

        EditText nameEditText = findViewById(R.id.name_editText);
        name =  nameEditText.getText().toString();

        Log.v("MainActivity", "Whipped cream : " + hasWhippedCream);
        Log.v("MainActivity", "Chocolate : " + hasChocolate);
        Log.v("MainActivity", "name : " + name);
        priceTextView.setText(displayMessage());
    }

    private String displayMessage() {
        return "Name: " + name +
                "\nAdd whipped cream ? " + hasWhippedCream +
                "\nAdd Chocolate ? " + hasChocolate +
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
        if(hasChocolate){
            if(hasWhippedCream){
                return (PRICE_OF_ONE_COFFEE + 2 + 1) * quantity;
            }
            return  (PRICE_OF_ONE_COFFEE + 2) * quantity;
        } else if (hasWhippedCream) {
            return  (PRICE_OF_ONE_COFFEE + 1) * quantity;
        } else {
           return PRICE_OF_ONE_COFFEE  * quantity;
        }
    }
}