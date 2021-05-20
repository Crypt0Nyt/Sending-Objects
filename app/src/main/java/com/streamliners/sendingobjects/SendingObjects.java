package com.streamliners.sendingobjects;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.streamliners.sendingobjects.databinding.ActivitySendingObjectsBinding;
import com.streamliners.sendingobjects.model.Student;

import java.util.Objects;


public class SendingObjects extends AppCompatActivity {

    ActivitySendingObjectsBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivitySendingObjectsBinding.inflate(getLayoutInflater());

        setContentView(b.getRoot());
        setupHideErrorsForEditText();

        setupActionListner();
        
//        Restoring Data
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);

        b.nameTextField.getEditText().setText(prefs.getString(Constants.NAME, ""));
        b.radioGroup.check(prefs.getInt(Constants.GENDER_TYPE, -1));
        b.rollNoTextField.getEditText().setText(prefs.getString(Constants.ROLL_NO, ""));
        b.phoneNoTextField.getEditText().setText(prefs.getString(Constants.PHONE_NO, ""));

    }

//    Applying IME Action Send to the editor text
    private void setupActionListner() {
        b.phoneNoTextField.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEND){
                    sendObject(v);
                }
                return false;
            }
        });

    }


//    Methods for hiding errors
    private void setupHideErrorsForEditText() {

        //      Hide Errors for name text field
        b.nameTextField.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                b.nameTextField.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//      For Hiding errors for roll number text field
        b.rollNoTextField.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                b.rollNoTextField.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//      Hiding errors for Phone number text field
        b.phoneNoTextField.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                b.phoneNoTextField.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * Setup Layout
     */
    public void sendObject(View view) {

        //      Taking data input of Student
        String nameInput = Objects.requireNonNull(b.nameTextField.getEditText()).getText().toString().trim();
        String rollNo = b.rollNoTextField.getEditText().getText().toString().trim();
        String phoneNo = b.phoneNoTextField.getEditText().getText().toString().trim();

//      Show Errors
        RadioGroup grp = findViewById(R.id.radioGroup);
        if(grp.getCheckedRadioButtonId() == -1 || nameInput.isEmpty() || rollNo.isEmpty() || phoneNo.isEmpty()) {
            Toast.makeText(this, "Please fill all the required details!", Toast.LENGTH_LONG).show();
        }

        //          Validating Name, Roll no and phone
            if (nameInput.isEmpty() ) {
                b.nameTextField.setError("Please Enter your Name!");
                return;
            }
            else if(!nameInput.matches("^[A-Za-z\\s]+[.]?[A-Za-z\\s]*$")){
                b.nameTextField.setError("Invalid Format!");
                return;
            }

            if (rollNo.isEmpty()) {
                b.rollNoTextField.setError("Please Enter your Roll Number!");
                return;
            }

            else if(!rollNo.matches("^\\d{2}(?i)[a-z]{3,}(?-i)\\d{3}+$")){
                b.rollNoTextField.setError("Invalid Roll No!");
                return;
            }

            if (phoneNo.isEmpty()) {
                b.phoneNoTextField.setError("Please Enter your Phone Number!");
                return;
            }
            else if (!phoneNo.matches("^\\d{10}")){
                b.phoneNoTextField.setError("Invalid Phone No!");
                return;
            }

//            Select gender
            String gender;
            int type = b.radioGroup.getCheckedRadioButtonId();
            if(type == R.id.femaleRadioBtn){
                gender = "Female";
            }
            else{
                gender = "Male";
            }

        //Create student object
        Student student = new Student(nameInput,gender,rollNo,phoneNo);

        //Send student object as explicit intent
        Intent intent = new Intent(this,ViewerActivity.class);
        intent.putExtra(Constants.SEND_OBJ, student);
        startActivity(intent);
    }


    @Override
    protected void onPause() {
        super.onPause();
        //create preferences reference i.e create object of preferences
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);

        //save and commit data
        prefs.edit()
         .putString(Constants.NAME, b.nameTextField.getEditText().getText().toString().trim())
                .putInt(Constants.GENDER_TYPE, b.radioGroup.getCheckedRadioButtonId())
                .putString(Constants.ROLL_NO, Objects.requireNonNull(b.rollNoTextField.getEditText()).getText().toString().trim())
                .putString(Constants.PHONE_NO, b.phoneNoTextField.getEditText().getText().toString().trim())
                .apply();
    }
}