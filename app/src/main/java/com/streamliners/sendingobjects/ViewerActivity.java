package com.streamliners.sendingobjects;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.streamliners.sendingobjects.databinding.ActivityViewerBinding;
import com.streamliners.sendingobjects.model.Student;

public class ViewerActivity extends AppCompatActivity {
    ActivityViewerBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityViewerBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        getnameintent();
    }

    /**
     * Receive and show object obtained from intent
     */
    private void getnameintent() {
//        Validating Object
        Student student = getIntent().getExtras().getParcelable(Constants.SEND_OBJ);

        // Showing data received from intent
       b.showNameTextField.setText(student.getmName());
       b.showGenderTextField.setText(student.getmGender());
       b.showRollNumberTextField.setText(student.getmRollNo());
       b.showMobileNumberTextField.setText(student.getmPhoneNo());

    }
}