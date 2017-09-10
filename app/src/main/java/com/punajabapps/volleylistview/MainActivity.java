package com.punajabapps.volleylistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Todo:  Step 1
    private static  String url = "http://android.apnitaleem.com/water_dis/trackemp.php?trackemp=123";
    GetSetData getsetdata ;

    //// TODO: Step 2  Make Lisr
    private List<GetSetData> getsetdatelist = new ArrayList<GetSetData>();

    ListView listView ;

    CustomBaseAdapter customBaseAdapter ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = (ListView) findViewById(R.id.list_item);







    }

    public void load_a() {


        final JsonArrayRequest get = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        Toast.makeText(MainActivity.this, ""+response, Toast.LENGTH_SHORT).show();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                getsetdata =new GetSetData();
                                JSONObject obj = response.getJSONObject(i);


                                String c = obj.getString("name");

                                getsetdata.setName(c);
                                getsetdata.setId(obj.getString("id"));
                                getsetdata.setImg(obj.getString("img"));
                                getsetdata.setPhone("Contact: "+obj.getString("phone"));
                                getsetdata.setEmp_cnic(obj.getString("emp_cnic"));
                                getsetdata.setAddress("Address: "+obj.getString("address"));
                                getsetdata.setLat(obj.getString("lat"));
                                getsetdata.setLng(obj.getString("lng"));

                                //convert string to double
                                String lat = obj.getString("lat");
                                String lng = obj.getString("lng");

                            //Todo add value to list

                                getsetdatelist.add(getsetdata);

                             //Todo value of constructor here
                                customBaseAdapter = new CustomBaseAdapter(MainActivity.this,getsetdatelist );

                                //Todo set adapter
                                listView.setAdapter(customBaseAdapter);





                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(MainActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                            }

                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
///                      adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(get);
    }

    public void load(View view) {

        load_a();

    }
}
