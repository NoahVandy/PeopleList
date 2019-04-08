package com.example.peoplelist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewPersonFrom extends AppCompatActivity {


    Button btn_ok, btn_cancel;
    EditText et_name, et_age, et_pictureNumber;
    int positionToEdit = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_person_from);


        btn_ok = findViewById(R.id.btn_ok);
        btn_cancel = findViewById(R.id.btn_cancel);

        et_age = findViewById(R.id.et_age);
        et_name = findViewById(R.id.et_name);
        et_pictureNumber = findViewById(R.id.et_pictureNumber);



        Bundle incomingIntent = getIntent().getExtras();

        Toast.makeText(this, "position: " + positionToEdit, Toast.LENGTH_SHORT).show();


        if(incomingIntent != null){
            String name = incomingIntent.getString("name");
            int age = incomingIntent.getInt("age");
            int pictureNumber = incomingIntent.getInt("pictureNumber");
            positionToEdit = incomingIntent.getInt("edit");

            Toast.makeText(this, "position: " + positionToEdit, Toast.LENGTH_SHORT).show();
            et_name.setText(name);
            et_age.setText(Integer.toString(age));
            et_pictureNumber.setText(Integer.toString(pictureNumber));
        }

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get strings from et's
                String newName = et_name.getText().toString();
                String newAge = et_age.getText().toString();
                String newPictureNumber = et_pictureNumber.getText().toString();

                //put the strings into a message


                //switch back to main activity


                Intent i = new Intent(v.getContext(), MainActivity.class);

                i.putExtra("edit",  positionToEdit);
                i.putExtra("name", newName);
                i.putExtra("age", newAge);
                i.putExtra("pictureNumber", newPictureNumber);


                startActivity(i);
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}
