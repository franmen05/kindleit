package com.example.guille.test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.guille.test.io.VolleySingleton;
import com.example.guille.test.io.model.DealResponse;

import java.util.List;

public class DealAdacter extends RecyclerView.Adapter<DealAdacter.DealViewHolder> {

    /**
     * Represent objects of activity_deal_card
     */
    protected  static class DealViewHolder extends RecyclerView.ViewHolder{

        TextView description;
        TextView sourcePag;
        ImageView image;
        ShareActionProvider shareProvider;
        Context context;


        public DealViewHolder(View itemView) {
            super(itemView);
            description = (TextView ) itemView.findViewById(R.id.description );
            sourcePag= (TextView ) itemView.findViewById(R.id.source_pag);
            image= (ImageView) itemView.findViewById(R.id.image);
            context = itemView.getContext();

//            shareProvider=(ShareActionProvider) itemView.findViewById(R.id.compartir);

        }
    }

    private List<DealResponse> deals;
    private Context context;
    private DealViewHolder _holder;
    private String _url;


    public DealAdacter(List<DealResponse> deals) {
        this.deals = deals;
    }

    @Override
    public DealViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        View v= LayoutInflater.from(context).inflate(R.layout.activity_deal_card,parent,false);
        return new DealViewHolder(v);
    }


    @Override
    public void onBindViewHolder( DealViewHolder viewHolder, int position) {


        final DealResponse d=deals.get(position);



        viewHolder.description.setText(d.getDescription());
        viewHolder.description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _url=d.getSource().getUrl()+d.getUrl();
                Toast.makeText(v.getContext(), _url, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(_url));
                v.getContext().startActivity(intent);
            }
        });
        viewHolder.sourcePag.setText(d.getSource().getName());

        loadAsyncImage(viewHolder, d.getPicture());


    }


    /**
     *
     * @param viewHolder
     * @param url
     */
    public  void loadAsyncImage(final DealViewHolder viewHolder,String url ){
//        _holder = viewHolder;

        final ImageRequest imgRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {

                viewHolder.image.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.FIT_XY, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        // Get a RequestQueue
        RequestQueue queue = VolleySingleton.getInstance(context.getApplicationContext()).getRequestQueue();


        // Add a request (in this example, called stringRequest) to your RequestQueue.
        VolleySingleton.getInstance(context).addToRequestQueue(imgRequest);
    }

    @Override
    public int getItemCount() {
        return deals.size();
    }
}
