package com.example.appvk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Adapter.OnItemClickListener {
    private RecyclerView mRecyclerView;
    private Adapter Adapter;
    private ArrayList<AppItem> AppList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        AppList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }

    private void parseJSON() {
        String url = "https://publicstorage.hb.bizmrg.com/sirius/result.json";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject jsOBject = null;
                try {
                    jsOBject = response.getJSONObject("body");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    JSONArray jsonArray = jsOBject.getJSONArray("services");
                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject service = jsonArray.getJSONObject(i);

                        String name = service.getString("name");
                        String descr = service.getString("description");
                        String link = service.getString("link");
                        String ImageUrl = service.getString("icon_url");

                        AppList.add(new AppItem(ImageUrl,name, descr, link));
                    }

                    Adapter = new Adapter(MainActivity.this, AppList);
                    mRecyclerView.setAdapter(Adapter);
                    Adapter.setOnItemClickListener(MainActivity.this);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);

    }

    @Override
    public void onItemClick(int position) {
        Uri url = Uri.parse(AppList.get(position).getLink());
        Intent detailintent = new Intent(Intent.ACTION_VIEW, url);
        startActivity(detailintent);
    }
}