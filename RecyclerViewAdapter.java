package com.marialijideveloper.cakeslist_waracle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * Created by marialijideveloper on 15/3/18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {



    Context context;
    List<CakeAdapter> dataCakeAdapters;
    ImageLoader imageLoader;


    public static final String EXTRA_CAKE_ITEM = "image_url";
    public static final String EXTRA_CAKE_ITEM_TITLE = "title";
    public static final String EXTRA_CAKE_ITEM_DESC = "desc";
    public static final String EXTRA_CAKE_IMAGE_TRANSITION_NAME = "image_transition_name";


    public RecyclerViewAdapter() {
        // Required empty public constructor
    }



    public RecyclerViewAdapter(List<CakeAdapter> getCakeAdapter, Context context) {

        super();

        this.dataCakeAdapters = getCakeAdapter;
        this.context = context;

    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }




    @Override
    public void onBindViewHolder(final ViewHolder ViewHolder, int position) {
        final CakeAdapter dataCakeAdapterOBJ =  dataCakeAdapters.get(position);



        imageLoader = ImageAdapter.getInstance(context).getImageLoader();
        imageLoader.get(dataCakeAdapterOBJ.getImageURL(),
                ImageLoader.getImageListener(ViewHolder.VollyImageView
                        ,//Server Image
                        R.mipmap.ic_launcher,//Before loading server image the default showing image.
                        android.R.drawable.ic_dialog_alert //Error image if requested image dose not found on server.
                )
        );

        ViewHolder.VollyImageView.setImageUrl(dataCakeAdapterOBJ.getImageURL(),imageLoader);
        ViewHolder.ImageTitleTextView.setText(dataCakeAdapterOBJ.getImageTitle());
        ViewHolder.mdesc.setText(dataCakeAdapterOBJ.getImageDesc());
        ViewCompat.setTransitionName(ViewHolder.VollyImageView,dataCakeAdapterOBJ.ImageTitle);

        //Listener image
        ViewHolder.VollyImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, CakeDetail.class);
                intent.putExtra(EXTRA_CAKE_ITEM, dataCakeAdapterOBJ.ImageURL);
                intent.putExtra(EXTRA_CAKE_ITEM_TITLE, dataCakeAdapterOBJ.getImageDesc());
                intent.putExtra(EXTRA_CAKE_ITEM_DESC, dataCakeAdapterOBJ.getImageDesc());
                //Send transition
                intent.putExtra(EXTRA_CAKE_IMAGE_TRANSITION_NAME, ViewCompat.getTransitionName( ViewHolder.VollyImageView));

                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        (Activity) context,
                        ViewHolder.VollyImageView,
                        ViewCompat.getTransitionName(ViewHolder.VollyImageView));

                context.startActivity(intent,options.toBundle());

            }
        });
    }




    @Override
    public int getItemCount() {
        return dataCakeAdapters.size();
    }




    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView ImageTitleTextView, mdesc;
        public NetworkImageView VollyImageView ;

        public ViewHolder(View itemView) {

            super(itemView);

            ImageTitleTextView = (TextView) itemView.findViewById(R.id.ImageNameTextView) ;
            mdesc=(TextView)itemView.findViewById(R.id.ImageDescTextView);

            VollyImageView = (NetworkImageView) itemView.findViewById(R.id.VolleyImageView) ;




        }
    }





}
