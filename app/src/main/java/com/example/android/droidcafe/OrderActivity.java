/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.droidcafe;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This activity is blank for now. Subsequent versions of the app
 * provide input controls and a delivery method for an order.
 */
public class OrderActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.labels_array,
                android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        Spinner spinner = findViewById(R.id.label_spinner);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                displayToast(parent.getItemAtPosition(position).toString());
            }

            private void displayToast(String toString) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        EditText editText = findViewById(R.id.editText_main);
        if (editText != null) {
            editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                private static void
                final Object Tag = 0;

                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    boolean handled = false;
                    if (actionId == EditorInfo.IME_ACTION_SEND) {
                        dialNunmber();
                        handled = true;
                    }
                    return handled;
                }

                private void dialNunmber() {

                    // Find the editText_main view.
                    EditText editText = findViewById(R.id.editText_main);
                    String phoneNum = null;
                    // If the editText field is not null,
                    // concatenate "tel: " with the phone number string.
                    if (editText != null) phoneNum = "tel:" +
                            editText.getText().toString();
                    // Optional: Log the concatenated phone number for dialing.
                    Log.d((String) Tag, "dialNumber: " + phoneNum);
                    // Specify the intent.
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    // Set the data for the intent as the phone number.
                    intent.setData(Uri.parse(phoneNum));
                    // If the intent resolves to a package (app),
                    // start the activity with the intent.
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        Log.d("ImplicitIntents", "Can't handle this!");
                    }
                }
            


                private void displayToast(String message) {
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                }

                public void onRadioButtonClicked(View view) {
                    // when radioButton is tapped


                    RadioButton selected = (RadioButton) view;
                    // check if the button is checked;
                    boolean checked = selected.isChecked();

                    if (selected.getId() == R.id.sameday) {
                        if (checked) {
                            displayToast("Same day service selected");
                        }

                    } else if (selected.getId() == R.id.nextday) {
                        if (checked) {
                            displayToast("Next day service selected");
                        }

                    } else if (selected.getId() == R.id.pickup) {
                        displayToast("Pick up service selected");

                    }
                }
            }
        }
    }
}

