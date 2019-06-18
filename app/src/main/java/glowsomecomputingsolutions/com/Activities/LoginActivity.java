package glowsomecomputingsolutions.com.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.lang.reflect.Method;

import glowsomecomputingsolutions.com.R;
import glowsomecomputingsolutions.com.Utils.AppController;

public class LoginActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    public static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextInputLayout nameWrapper = findViewById(R.id.input_layout_name);
        final TextInputLayout emailWrapper = findViewById(R.id.input_layout_email);
        final TextInputLayout passwordWrapper = findViewById(R.id.input_layout_password);

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
                makeJsonObjectRequest();
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private  void makeJsonObjectRequest(){
        showProgressDialog();

        JsonObjectRequest objectRequest = new JsonObjectRequest(Method.GET, stringurl,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG,response.toString());

                        hideProgressDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG,"Error:" + error.getMessage());
                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                hideProgressDialog();
            }
        });

        //adding request to requestQueue
        AppController.getInstance().addToRequestQueue(objectRequest);
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
}
