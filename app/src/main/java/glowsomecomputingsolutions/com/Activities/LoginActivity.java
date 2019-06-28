package glowsomecomputingsolutions.com.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import glowsomecomputingsolutions.com.Model.Users;
import glowsomecomputingsolutions.com.Model.Users;
import glowsomecomputingsolutions.com.R;
import glowsomecomputingsolutions.com.Utils.AppController;

public class LoginActivity extends AppCompatActivity {

    //private String arrayUrl = "http://192.168.137.1/glowsomecomputingsolutions/v2/glowsomeapi.php";
    private String arrayUrl = "http://www.mocky.io/v2/5d148c3a2f00007405c4f1cc";

    private String objectUrl = "http://192.168.137.1/glowsomecomputingsolutions/v1/glowsomeapi.php";
    private ProgressDialog progressDialog;
    EditText nametext;
    EditText emailtext;
    EditText passwordtext;


    //public static final String mypreference = "mypref";

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


                String name = nametext.getText().toString().trim();
                String email = emailtext.getText().toString().trim();
                String password = passwordtext.getText().toString().trim();


                if (validatelogin(name,email,password)){
                    makeJsonArrayRequest(name,email,password);
                }

            }
        });

    }
    private  void makeJsonArrayRequest(final String name, final String email, final String password){
        showProgressDialog();
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET,arrayUrl,null,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.d(TAG, String.valueOf(response));


                        //parsing json array
                        //looping through json array
                        //-JSONArray jsonArray = response.getJSONArray(0);

                        Log.i(TAG,"Json Array :" +response);

                        //Toast.makeText(LoginActivity.this,"Users"+jsonArray,Toast.LENGTH_SHORT).show();


                        for (int i = 0;i<response.length();i++){

                            try {

                                JSONObject jsonObject = response.getJSONObject(i);

                                Log.i(TAG,"Json Object:" +jsonObject);

                                String json_string = jsonObject.toString();

                                Toast.makeText(LoginActivity.this,"Users:" +json_string,Toast.LENGTH_SHORT).show();


                                //Users users = new Users(jsonObject);
                                    Gson gson = new Gson();
                                    Users users = (Users) gson.fromJson(json_string,Users.class);

                                if(users.getName()!= null && users.getName().equals(name)){
                                    if (users.getEmail()!= null && users.getEmail().equals(email)){
                                        if (users.getPassword()!=null&& users.getPassword().equals(password)){
                                            newActivity();
                                        }else {
                                            Toast.makeText(LoginActivity.this,"Wrong Password",Toast.LENGTH_SHORT).show();
                                        }
                                    }else {
                                        Toast.makeText(LoginActivity.this,"Wrong Email",Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(LoginActivity.this,"Wrong Credentials",Toast.LENGTH_SHORT).show();

                                }

                               // passObject();
                                //Gson gson1 = new Gson();
                                //String json_string_2 = gson1.toJson(users);
                                //intent.putExtra(users_2,json_string_2);

                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                intent.putExtra("Users",users);
                                startActivity(intent);

                            }catch (JSONException e){
                                e.printStackTrace();
                            }

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

    private void passObject(){
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        intent.putExtra("Users",Users.class);
        startActivity(intent);
    }

}
