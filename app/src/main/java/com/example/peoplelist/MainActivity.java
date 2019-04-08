package com.example.peoplelist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    Button btn_sortABC, btn_sort123, btn_add;

    ListView lv_friendsList;

    PersonAdapter adapter;

    MyFriends myFriends;

    int positionToEdit = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.btn_add);
        btn_sortABC = findViewById(R.id.btn_sortABC);
        btn_sort123 = findViewById(R.id.btn_sort123);
        lv_friendsList = findViewById(R.id.lv_friendsList);

        myFriends = ((MyApplication) this.getApplication()).getMyFriends();

        adapter = new PersonAdapter(MainActivity.this, myFriends);

        lv_friendsList.setAdapter(adapter);


        //listen for new intents
        Bundle incomingMessages = getIntent().getExtras();

        if(incomingMessages != null) {
            //capture incoming data
            String name = incomingMessages.getString("name");
            int age = Integer.parseInt(incomingMessages.getString("age"));
            int pictureNumber = Integer.parseInt(incomingMessages.getString("pictureNumber"));
            int positionToEdit = incomingMessages.getInt("edit");


            //create new person object

            Person p = new Person(name, age, pictureNumber);

            if(positionToEdit > -1){
                myFriends.getMyFriendsList().remove(positionToEdit);
            }

            myFriends.getMyFriendsList().add(p);

            adapter.notifyDataSetChanged();
        }
        //add him to list and update adapter


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), NewPersonFrom.class);
                startActivity(i);
            }
        });


        btn_sortABC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(myFriends.getMyFriendsList());
                adapter.notifyDataSetChanged();
            }
        });

        btn_sort123.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(myFriends.getMyFriendsList(), new Comparator<Person>() {
                    @Override
                    public int compare(Person p1, Person p2) {
                        return p1.getAge() - p2.getAge();
                    }
                });
                adapter.notifyDataSetChanged();
            }
        });


        lv_friendsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editPerson(position);
            }
        });


    }
    public void editPerson(int position){
        Intent i = new Intent(getApplicationContext(), NewPersonFrom.class);

        // get contents of person
        Person p = myFriends.getMyFriendsList().get(position);

        i.putExtra("edit", position);
        i.putExtra("name", p.getName());
        i.putExtra("age", p.getAge());
        i.putExtra("pictureNumber", p.getPictureNumber());

        startActivity(i);
    }
}
