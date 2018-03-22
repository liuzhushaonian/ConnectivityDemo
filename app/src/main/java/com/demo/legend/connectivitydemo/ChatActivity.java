package com.demo.legend.connectivitydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

public class ChatActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    Button button;
    EditText editText;
    MessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getComponent();
        sendEvent();
        initList();
    }


    private void getComponent(){

        button=findViewById(R.id.send_message);
        editText=findViewById(R.id.edit_body);
        recyclerView=findViewById(R.id.message_recyclerView);

    }

    private void initList(){
        linearLayoutManager=new LinearLayoutManager(this);
        adapter=new MessageAdapter();
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        SocketManager.getSocketManager().setAdapter(adapter);
    }



    private void sendEvent(){

        button.setOnClickListener(v -> {

            String s=editText.getText().toString();

            if (s.isEmpty()){
                return;
            }

            SocketManager.getSocketManager().sendMessage(s);

            Message message=new Message(s,2);

            adapter.addMsg(message);

            editText.setText("");

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SocketManager.getSocketManager().stopConnection();
    }
}
