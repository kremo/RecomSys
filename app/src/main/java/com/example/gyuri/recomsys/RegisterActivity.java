package com.example.gyuri.recomsys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gyuri.recomsys.model.DataSource;
import com.example.gyuri.recomsys.model.User;

/**
 * Created by BB on 2016.05.02..
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
        Button bRegister;
        EditText etName, etUserName, etAge;
        Spinner spinner1;
        Spinner spinner2;
        ArrayAdapter<CharSequence> adapter1;
        ArrayAdapter<CharSequence> adapter2;
        boolean isMan = true;
        Integer age = 25;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_register_user);

                //korválasztó legördülő listának

                spinner1 = (Spinner)findViewById(R.id.spinnerAge);

                adapter1 = ArrayAdapter.createFromResource(this, R.array.ages, android.R.layout.simple_spinner_item);
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner1.setAdapter(adapter1);
                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                String selected = (String) parent.getItemAtPosition(position);
                                Toast.makeText(getBaseContext(), selected + " kiválasztva", Toast.LENGTH_LONG).show();
                                String[] parts = selected.split("-");
                                age = Integer.parseInt(parts[0])+5;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                });

                //Nemválasztó legördülő listaa
                spinner2 = (Spinner)findViewById(R.id.spinnerGender);

                adapter2 = ArrayAdapter.createFromResource(this, R.array.genders, android.R.layout.simple_spinner_item);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(adapter2);
                spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position)+" kiválasztva", Toast.LENGTH_LONG).show();
                                if (parent.getItemAtPosition(position) == "Férfi"){
                                        isMan = true;
                                }
                                else {
                                        isMan = false;
                                }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                });


                etName = (EditText) findViewById(R.id.etName);
                etUserName =  (EditText) findViewById(R.id.etUsername);
                // etAge

        }

        @Override
        protected void onResume() {
                super.onResume();
                bRegister = (Button) findViewById(R.id.bRegister);

                bRegister.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
                switch (v.getId()){
                        case R.id.bRegister:
                                EditText etName = (EditText) findViewById(R.id.etName);
                                EditText etUserName = (EditText) findViewById(R.id.etUsername);
                                Spinner spinner = (Spinner) findViewById(R.id.spinnerAge) ;
                                String text = spinner.getSelectedItem().toString();

                                User nUser = new User(etName.getText().toString(), DataSource.getUsers(this).size()+1,etUserName.getText().toString(),age);
                                nUser.isMan = this.isMan;
                                DataSource.addUserToSavedUsers(nUser, this);

                                CharSequence outText = "Regisztrálás sikeres!";
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(this, outText, duration);
                                toast.show();

                                onBackPressed();

                                break;
                }
        }
}
