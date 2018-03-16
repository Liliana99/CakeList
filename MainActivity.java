package com.marialijideveloper.cakeslist_waracle;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static String JSON_URL = "https://gist.githubusercontent.com/hart88/198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json";
    private android.view.View mViewImage1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Set toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_Cake);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("List of cakes");
        getSupportActionBar().setSubtitle("marialijideveloper");
       

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



        @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

            PopupMenu popup = new PopupMenu(this, mViewImage1);
            popup.getMenuInflater().inflate(R.menu.menu_main, popup.getMenu());
            try {
                Method method = popup.getMenu().getClass().getDeclaredMethod("setOptionalIconsVisible", boolean.class);
                method.setAccessible(true);
                method.invoke(popup.getMenu(), true);
            } catch (Exception e) {
                e.printStackTrace();
            }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            RefreshView();
            return true;
        }

        //Exit function
        if (id == R.id.exit) {
            if (Build.VERSION.SDK_INT >= 21) {
                finishAndRemoveTask();
            }
            else
                finish();
            return true;
         }


            return super.onOptionsItemSelected(item);
    }

    //Refresh the  view
    private void RefreshView()
    {
        Intent intent = getIntent();
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        startActivity(intent);
    }



    /**
     * Fragment is responsible for loading in some JSON and
     * then displaying a list of cakes with images.
     * Fix any crashes
     * Improve any performance issues
     * Use good coding practices to make code more secure
     */
    public static class PlaceholderFragment extends ListFragment {


        private List<CakeAdapter> mList;
        private RecyclerView recyclerView;
        private String Image_image_JSON = "image";
        private String Image_desc_JSON = "desc";
        private String Image_title_JSON = "title";
        JsonArrayRequest RequestOfJSonArray ;

        RequestQueue requestQueue ;
        RecyclerView.LayoutManager layoutManagerOfrecyclerView;
        RecyclerView.Adapter recyclerViewadapter;
        private Object GhostViewImpl;


        public PlaceholderFragment() { /**/ }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            mList = new ArrayList<>();
            View rootView = inflater.inflate(R.layout.activity_main, container, false);
            recyclerView =  rootView.findViewById(R.id.list1);

            return rootView;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);


            recyclerView.setHasFixedSize(true);
            layoutManagerOfrecyclerView = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManagerOfrecyclerView);



           JSON_HTTP_CALL();
        }





        public void JSON_HTTP_CALL(){

            RequestOfJSonArray = new JsonArrayRequest(JSON_URL,

                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {

                            ParseJSonResponse(response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

            requestQueue = Volley.newRequestQueue(getContext());

            requestQueue.add(RequestOfJSonArray);
        }

        public void ParseJSonResponse(JSONArray array){

            for(int i = 0; i<array.length(); i++) {

                CakeAdapter getDataCakeAdapter2 = new CakeAdapter() {
                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel dest, int flags) {

                    }
                };

                JSONObject json = null;
                try {

                    json = array.getJSONObject(i);

                    getDataCakeAdapter2.setImageTitle(json.getString(Image_title_JSON));
                    getDataCakeAdapter2.setImageURL(json.getString(Image_image_JSON));
                    getDataCakeAdapter2.setImageDesc(json.getString(Image_desc_JSON));

                } catch (JSONException e) {

                    e.printStackTrace();
                }
                mList.add(getDataCakeAdapter2);
            }

            recyclerViewadapter = new RecyclerViewAdapter(mList,getContext());

            recyclerView.setAdapter(recyclerViewadapter);
        }
    }



}

