package com.example.cruiseapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ChatActivity extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference dbRef;
    RecyclerView chatListRV;
    EditText et_msg;
    Button btnSend;
    ChatAdapter chatAdapter;
    String currentDateTimeString,date;
    final String TAG=">>>>>>>";
    public static final String MY_PREFS_NAME = "Myshareduserdata";
    String fname,lname,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        btnSend=findViewById(R.id.button_send);
        et_msg=findViewById(R.id.editText_message);
        chatListRV=findViewById(R.id.chatList_rv);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        fname = prefs.getString("fname", "anonymous");
        lname = prefs.getString("lname", "");
        email = prefs.getString("email", "");


        db=FirebaseDatabase.getInstance();
        currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        date = new SimpleDateFormat("ddMMyyyy").format(new Date());
        //HashMap<String,String> message=new HashMap<>();

//        message.put("userName","csj");
//        message.put("userEmail","csj@648gmail.com");
//        message.put("time",currentDateTimeString);
//        message.put("message","Hello EveryOne!!");

        dbRef=db.getReference("groupChat").child(date).child("users");
        //dbRef.setValue("hello add");
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message=new Message();
                message.setMessage(et_msg.getText().toString());
                message.setTime(currentDateTimeString);
                message.setUserEmail(fname+" "+lname);
                message.setUserName(email);
                dbRef.push().setValue(message);
            }
        });

        Log.d(">>>>>>>>>>>>>", "onCreate: "+dbRef);

        readData();
    }

    public void readData(){
        // Read from the database
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<Message> mlist=new ArrayList<>();
                for(DataSnapshot ds:dataSnapshot.getChildren()) {
                      Message m = ds.getValue(Message.class);
                      mlist.add(m);


                      Toast.makeText(ChatActivity.this, "" + m.getMessage(), Toast.LENGTH_SHORT).show();
                     }
                chatAdapter=new ChatAdapter(mlist);
                chatListRV.setAdapter(chatAdapter);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(ChatActivity.this);
                chatListRV.setLayoutManager(linearLayoutManager);
                Log.d(TAG, "Value is: " + dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public void open_drawer(View view) {
    }
}
