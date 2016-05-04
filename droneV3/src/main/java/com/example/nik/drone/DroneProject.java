package com.example.nik.drone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DroneProject extends Activity {
//private TextView tv;
   Button btn;
    EditText username,password;
    String UsernameResult;
    String PasswordResult;
    private ProgressDialog pDialog;
    private static String url;
    String json;
    JSONArray loginInfo = null;
    private static final String TAG_LOGIN = "login";
    private static final String TAG_USERNAME = "Username";
    private static final String TAG_PASSWORD = "Password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drone_project);

        TextView txt = (TextView) findViewById(R.id.txt2);

        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/redfive.TTF");
        txt.setTypeface(customFont);
//        tv = (TextView)findViewById(R.id.link_login);
        //      tv.setOnTouchListener((View.OnTouchListener) this);
    }
        public void login(View v) {


        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        url = "http://munro.humber.ca/~spnn0141/drone/login.php?username=" + username.getText();

            Log.v("testt", url);
        try {
            json = new GetLogin().execute().get();
            Log.v("testt", json);
            /* Parse */
            JSONObject jsonObj = new JSONObject(json);
            loginInfo = jsonObj.getJSONArray(TAG_LOGIN);

            JSONObject c = loginInfo.getJSONObject(0);


            UsernameResult = c.getString(TAG_USERNAME);
            PasswordResult = c.getString(TAG_PASSWORD);


        } catch (Exception e) {
            e.printStackTrace();
            Log.v("testt", "exeception" );

        }



        if (username.getText().toString().equals(UsernameResult) && password.getText().toString().equals(PasswordResult)) {
            Intent intent = new Intent(DroneProject.this, Choice.class);
            intent.putExtra("username", UsernameResult);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Wrong Username or Password. Try Again....",
                    Toast.LENGTH_LONG).show();
        }

    }

    public void register(View v){
        Button register = (Button) findViewById(R.id.btn_signup);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://munro.humber.ca/~spnn0141/drone/register.php");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }



    private class GetLogin extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(DroneProject.this);
            pDialog.setMessage("waiting");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(Void... arg0) {

            DownloadJSON jsonString = new DownloadJSON(url);
            return jsonString.getJSON();

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            json = result;
            if (pDialog.isShowing()) { pDialog.dismiss(); }

        }

    } //end of GetLogin
}