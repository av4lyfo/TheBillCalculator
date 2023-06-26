package sg.edu.rp.c346.id22013834.thebillcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;



import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

        EditText amountInput;
        EditText paxInput;
        ToggleButton SVS;
        ToggleButton GST;
        EditText discountInput;
        RadioGroup paymentType;
        RadioButton cash;
        RadioButton paynow;
        Button split;
        Button reset;
        TextView display1;
        TextView display2;





        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            amountInput =findViewById(R.id.amount_input);
            paxInput = findViewById(R.id.pax_input);
            SVS = findViewById(R.id.SVS_btn);
            GST = findViewById(R.id.GST_btn);
            discountInput = findViewById(R.id.discount_Input);
            paymentType = findViewById(R.id.type);
            cash = findViewById(R.id.cash_radio);
            paynow = findViewById(R.id.paynow_radio);
            split = findViewById(R.id.split_btn);
            reset = findViewById(R.id.reset_btn);
            display1 = findViewById(R.id.display);
            display2 = findViewById(R.id.display2);

            reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    amountInput.setText("");
                    paxInput.setText("");
                    SVS.setChecked(false);
                    GST.setChecked(false);
                    discountInput.setText("");
                }
            });




            split.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    double updatedAmount = 0.0;
                    if (!SVS.isChecked() && !GST.isChecked()) {
                        updatedAmount = Double.parseDouble(amountInput.getText().toString());
                    } else if (SVS.isChecked() && !GST.isChecked()){
                        updatedAmount = Double.parseDouble(amountInput.getText().toString()) * 1.1;
                    } else if (!SVS.isChecked() && GST.isChecked()) {
                        updatedAmount = Double.parseDouble(amountInput.getText().toString()) * 1.07;
                    } else {
                        updatedAmount = Double.parseDouble(amountInput.getText().toString()) * 1.17;
                    }

                    if (discountInput.getText().toString().length() != 0) {
                        updatedAmount = (1 - (Double.parseDouble(discountInput.getText().toString())) / 100) * updatedAmount;
                    }

                    display1.setText("Total Bill: $" + String.format("%.2f", updatedAmount));
                    int numPax = Integer.parseInt(paxInput.getText().toString());
                    int checkedRadioId = paymentType.getCheckedRadioButtonId();
                    if (checkedRadioId == R.id.cash_radio) {
                        if (numPax > 1) {
                            display2.setText("Each Pays: $" + String.format("%.2f", updatedAmount / numPax) + " in cash.");
                        } else {
                            display2.setText("Each Pays: $" + updatedAmount + " in cash.");
                        }


                    } else {
                        if (numPax > 1) {
                            display2.setText("Each Pays: $" + String.format("%.2f", updatedAmount / numPax) + "via Paynow");
                        } else {
                            display2.setText("Each Pays: $" + updatedAmount + "via Paynow");
                        }

                    }
                }



            });










        }
    }
