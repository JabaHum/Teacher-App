package glowsomecomputingsolutions.com.Activities;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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
    private NfcAdapter nfcAdapter;
    private static  String TAG  = StudentsListActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);

        mlistView = findViewById(R.id.list_view);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        checkNfcAdapter();

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

                        Log.i(TAG,"Students" +students1);

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

    private void checkNfcAdapter(){
        if (nfcAdapter == null){
            Toast.makeText(StudentsListActivity.this,"NFC is not available for this Device",Toast.LENGTH_SHORT).show();
        }else {

            Toast.makeText(StudentsListActivity.this, "NFC is available for this Device", Toast.LENGTH_SHORT).show();
        }
    }

    private void readTag(){
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())){
            Tag detectedTag = getIntent().getParcelableExtra(NfcAdapter.EXTRA_TAG);
            //Log.d(TAG, "detectedTag "+intent.getAction());
            if(detectedTag != null){
                Ndef ndef = Ndef.get(detectedTag);

                readFromNFC(ndef);

            }

        }
    }

    private void readFromNFC(Ndef ndef) {

        try {
            ndef.connect();
            NdefMessage ndefMessage = ndef.getNdefMessage();
            String message = new String(ndefMessage.getRecords()[0].getPayload());
            Log.d(TAG, "readFromNFC: "+message);
            Toast.makeText(StudentsListActivity.this,"Message"+message,Toast.LENGTH_SHORT).show();
            //mTvMessage.setText(message);
            ndef.close();

        } catch (IOException | FormatException e) {
            e.printStackTrace();

        }
    }

    @Override
    protected void onResume() {
     super.onResume();
        IntentFilter ndefDetected = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        if(nfcAdapter!= null)
            nfcAdapter.enableForegroundDispatch(this, pendingIntent, new IntentFilter[]{ndefDetected}, null);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(nfcAdapter!= null)
            nfcAdapter.disableForegroundDispatch(this);
    }
}
