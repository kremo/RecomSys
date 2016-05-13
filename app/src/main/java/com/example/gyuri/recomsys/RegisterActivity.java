package com.example.gyuri.recomsys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gyuri.recomsys.R;

/**
 * Created by BB on 2016.05.02..
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
        Button bRegister;
        EditText etName, etUserName, etAge;
        Spinner spinner1;
        ArrayAdapter<CharSequence> adapter;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_register_user);

                //korválasztó legördülő listának

                spinner1 = (Spinner)findViewById(R.id.spinnerAge);

                adapter = ArrayAdapter.createFromResource(this, R.array.ages, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner1.setAdapter(adapter);
                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position)+" selected", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                });
                etName = (EditText) findViewById(R.id.etName);
                etUserName =  (EditText) findViewById(R.id.etUsername);
               // etAge
                bRegister = (Button) findViewById(R.id.bRegister);

                bRegister.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
                switch (v.getId()){
                        case R.id.bRegister:


                                break;
                }
        }
}
