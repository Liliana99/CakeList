package com.marialijideveloper.cakeslist_waracle;

import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class CakeDetail extends AppCompatActivity {

    public CakeDetail(Parcel in) {
    }


    public CakeDetail ()
    {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cake_detail);

        //Set toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_Cakedetail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Detail");
        getSupportActionBar().setSubtitle("marialijideveloper");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Getting extras from intent
        Bundle extras = getIntent().getExtras();
        String stitle = String.valueOf(extras.get(RecyclerViewAdapter.EXTRA_CAKE_ITEM_TITLE));
        String sdesc = String.valueOf(extras.get(RecyclerViewAdapter.EXTRA_CAKE_ITEM_DESC));
        ImageView imageView = (ImageView) findViewById(R.id.cake_detail_image_view);


        TextView title = (TextView) findViewById(R.id.cake_detail_title);
        title.setText(stitle);
        TextView desc = (TextView) findViewById(R.id.cake_detail_desc);
        desc.setText(sdesc);


        String imageUrl = String.valueOf(extras.get(RecyclerViewAdapter.EXTRA_CAKE_ITEM));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String imageTransitionName = extras.getString(RecyclerViewAdapter.EXTRA_CAKE_IMAGE_TRANSITION_NAME);
            imageView.setTransitionName(imageTransitionName);
        }


        Picasso.with(this)
                .load(imageUrl)
                .noFade()
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        supportStartPostponedEnterTransition();
                    }

                    @Override
                    public void onError() {
                        supportStartPostponedEnterTransition();
                    }
                });
    }


}
