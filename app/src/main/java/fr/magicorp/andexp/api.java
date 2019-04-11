package fr.magicorp.andexp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class api extends AppCompatActivity {
    ArrayList<HashMap<String, String>> serversList;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // theme
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }

        // load activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_activity);

        // init list
        serversList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);

        new getServers().execute();
    }

    // get server async task
    private class getServers extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(api.this,"Json Data is downloading",Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(api.this);
            String url ="http://api.magicorp.fr/batrenis/v1/servers";

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(final JSONObject response) {
                    try {
                        // navigate in json
                        JSONArray servers = response.getJSONArray("servers");
                        for (int i = 0; i < servers.length(); i++) {
                            JSONObject s = servers.getJSONObject(i);

                            HashMap<String, String> server = new HashMap<>();
                            server.put("id",  s.getString("id"));
                            server.put("name",  s.getString("name"));
                            server.put("description",  s.getString("description"));
                            server.put("status",  s.getString("status"));

                            serversList.add(server);
                        }

                        ListAdapter adapter = new SimpleAdapter(api.this, serversList,
                                R.layout.list_item, new String[]{ "id","name", "status", "description"},
                                new int[]{R.id.id, R.id.name, R.id.status, R.id.description});
                        lv.setAdapter(adapter);

                        ((TextView) findViewById(R.id.text)).setText("Response: " + response.toString());

                    } catch (final JSONException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),
                                        "Json parsing error: " + e.getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    throw new RuntimeException(error.toString());
                }
            });
            try {
                queue.add(jsonObjectRequest);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }
}
