package glowsomecomputingsolutions.com.Activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import glowsomecomputingsolutions.com.Adapters.CustomListAdapter;
import glowsomecomputingsolutions.com.Model.Students;
import glowsomecomputingsolutions.com.Model.Users;
import glowsomecomputingsolutions.com.R;
import glowsomecomputingsolutions.com.Utils.AppController;

public class StudentsListActivity extends AppCompatActivity {

    private static final String url = "http://www.mocky.io/v2/5d1a24922f0000117dfd74ef";
    //private static  final String url ="http://localhost/glowsomecomputingsolutions/v1/students/glowsomeapi.php";
    private List<Students> studentsList = new ArrayList<>();
    private ListView mlistView;
    private CustomListAdapter adapter;
    private ProgressDialog pDialog;
    private static  String TAG  = StudentsListActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);

        mlistView = findViewById(R.id.list_view);
        //order of the arguments matters
        //settin list view adapter
        adapter = new CustomListAdapter(studentsList, getApplicationContext());
        mlistView.setAdapter(adapter);
        progBar();
        fetchData();

    }

    private void progBar(){
        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    private void fetchData(){
        // create a json request object
        JsonArrayRequest studentListRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                hidePDialog();

                //Parsing Json Now
                for (int i = 0;i<response.length();i++){

                    Log.d(TAG, String.valueOf(response));

                    try {
                        //looping through the  json objects
                        JSONObject jsonObject = response.getJSONObject(i);

                        Log.d(TAG, String.valueOf(jsonObject));

                        String json_string = jsonObject.toString();

                        Log.i(TAG,json_string);

                        //studentsList.add(j);

                        Gson gson = new Gson();

                        Students students1 = gson.fromJson(json_string,Students.class);
                        studentsList.add(students1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getInstance().addToRequestQueue(studentListRequest);
    }
}
