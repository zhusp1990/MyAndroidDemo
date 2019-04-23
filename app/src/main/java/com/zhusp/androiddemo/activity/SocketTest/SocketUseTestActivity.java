package com.zhusp.androiddemo.activity.SocketTest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zhusp.androiddemo.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketUseTestActivity extends AppCompatActivity {

    private EditText mEditText;
    private TextView mTextView;
    private static final String TAG = "TAG";
    private static final String HOST = "10.111.2.255";
    private static final int PORT = 7788;
    private PrintWriter printWriter;
    private BufferedReader in;
    private ExecutorService mExecutorService = null;
    private String receiveMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_use_test);
        mEditText = (EditText) findViewById(R.id.editText);
        mTextView = (TextView) findViewById(R.id.textView);
        mExecutorService = Executors.newCachedThreadPool();
    }

    public void connect() {
        mExecutorService.execute(new ConnectService());
    }

    public void send() {
        String sendMsg = mEditText.getText().toString();
        mExecutorService.execute(new sendService(sendMsg));
    }

    public void disconnect() {
        mExecutorService.execute(new sendService("0"));
    }

    private class sendService implements Runnable {
        private String msg;

        sendService(String msg) {
            this.msg = msg;
        }
        @Override
        public void run() {
            printWriter.println(this.msg);
        }
    }

    private class ConnectService implements Runnable {
        @Override
        public void run() {
            try {
                Socket socket = new Socket(HOST, PORT);
                socket.setSoTimeout(60000);
                printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                        socket.getOutputStream(), "UTF-8")), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                receiveMsg();
            } catch (Exception e) {
                Log.e(TAG, ("ConnectService:" + e.getMessage()));
            }
        }
    }



    private void receiveMsg() {
        try {
            while (true) {
                if ((receiveMsg = in.readLine()) != null) {
                    Log.d(TAG, "receiveMsg:" + receiveMsg);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTextView.setText(receiveMsg + "\n\n" + mTextView.getText());
                        }
                    });
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "receiveMsg: ");
            e.printStackTrace();
        }
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_connect:
                connect();
                break;
            case R.id.btn_sendMsg:
                send();
                break;
            case R.id.btn_disconnect:
                disconnect();
                break;
        }
    }
}
