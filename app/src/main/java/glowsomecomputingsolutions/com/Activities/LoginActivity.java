package glowsomecomputingsolutions.com.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import glowsomecomputingsolutions.com.R;
import glowsomecomputingsolutions.com.Utils.AppController;

public class LoginActivity extends AppCompatActivity {

    private String arrayUrl = "http://192.168.137.1/glowsomecomputingsolutions/v2/glowsomeapi.php";
    private String objectUrl = "http://192.168.137.1/glowsomecomputingsolutions/v1/glowsomeapi.php";
    private ProgressDialog progressDialog;
    SharedPreferences sharedpreferences;
    EditText nametext;
    EditText emailtext;
    EditText passwordtext;

    ArrayList<HashMap<String, String>> contactList;

    public static final String mypreference = "mypref";

    public static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextInputLayout nameWrapper = findViewById(R.id.input_layout_name);
        final TextInputLayout emailWrapper = findViewById(R.id.input_layout_email);
        final TextInputLayout passwordWrapper = findViewById(R.id.input_layout_password);

        nametext = findViewById(R.id.input_name);
        emailtext = findViewById(R.id.input_email);
        passwordtext = findViewById(R.id.input_password);

        contactList = new ArrayList<>();


        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);

        nameWrapper.setHint("Name");
        emailWrapper.setHint("Email");
        passwordWrapper.setHint("Password");

        Button signIn = findViewById(R.id.btn_signin);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //makeJsonArrayRequest();


                String name = nametext.getText().toString().trim();
                String email = emailtext.getText().toString().trim();
                String password = passwordtext.getText().toString().trim();


                if (validatelogin(name,email,password)){
                    makeJsonArrayRequest();
                    readSharedpreferences();
                    newActivity();
                }

            }
        });

    }



    private  void makeJsonArrayRequest(){
        showProgressDialog();
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET,arrayUrl,null,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG,response.toString());

                        //parsing json array
                        //looping through json array
                        try {
                            JSONArray jsonArray = new JSONArray(arrayUrl);
                            for (int i = 0;i<jsonArray.length();i++){

                                JSONObject jsonObject =(JSONObject) jsonArray.get(i);

                                //getSharedPreferences("PREFS_NAME",MODE_PRIVATE)

                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putString("jsonObjectString", jsonObject.toString());
                                editor.apply();



                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        hideProgressDialog();
                    }
                },new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG,"Error:"+ error.getMessage());
                    Toast.makeText(LoginActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    hideProgressDialog();
                }

        });
        AppController.getInstance().addToRequestQueue(arrayRequest);
    }

    private boolean validatelogin(String name,String email,String password){

        if (name == null || name.trim().length() == 0) {
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (email == null || email.trim().length() == 0) {
            Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password == null||password.trim().length() == 0) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private void showProgressDialog(){
        if (!progressDialog.isShowing()){
            progressDialog.show();
        }
    }
    private void hideProgressDialog(){
        if (progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    private void newActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private void readSharedpreferences(){
        SharedPreferences pref = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);

        String json_object = pref.getString("jsonObjectString", null);


        HashMap<String, String> teacher = new HashMap<>();


        // adding each child node to HashMap key => value
        teacher.put("id", id);
        teacher.put("name", name);
        teacher.put("email", email);
        teacher.put("sex", sex);
        teacher.put("password", password);
        teacher.put("schooltype", schooltype);
        teacher.put("level", level);
        teacher.put("subjects", subjects);
        teacher.put("paper", paper);

        // adding contact to contact list
        contactList.add(teacher);


        ///mUsers.add(json_object);
    }
}
