package com.example.guille.test;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guille.test.io.model.DealResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DealAdacter extends RecyclerView.Adapter<DealAdacter.DealViewHolder> {


    private List<DealResponse> deals;
    Context context;

    /**
     * Represent objects of activity_deal_card
     */
    protected  class DealViewHolder extends RecyclerView.ViewHolder{

        TextView description;
        TextView sourcePag;
        TextView discount;
        TextView price;
        ImageView image;
        ShareActionProvider shareProvider;
       // Context context;


        public DealViewHolder(View itemView) {
            super(itemView);
            description = (TextView ) itemView.findViewById(R.id.description );
            sourcePag= (TextView ) itemView.findViewById(R.id.source_pag);
            price= (TextView ) itemView.findViewById(R.id.price);
            discount= (TextView ) itemView.findViewById(R.id.discount);

            image= (ImageView) itemView.findViewById(R.id.image);
           // context = itemView.getContext();

//            shareProvider=(ShareActionProvider) itemView.findViewById(R.id.compartir);

        }

        public  void loadImage(String url){
            Picasso.with(context).load(url).placeholder(R.drawable.abc_btn_radio_to_on_mtrl_000).into(image);
        }
    }





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

        viewHolder.sourcePag.setText(d.getSource().getName());
        viewHolder.price.setText("RD $"+d.getDiscountedPrice());
        viewHolder.discount.setText("RD $" + d.getOriginalPrice());
        viewHolder.description.setText(d.getDescription());

        viewHolder.loadImage( d.getPicture());

        viewHolder.price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDeals(d.getSource().getUrl() + d.getUrl(),v);
            }
        });


        viewHolder.description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToDeals(d.getSource().getUrl() + d.getUrl(), v);
            }
        });

    }

    private void goToDeals(String _url, View v){
        Toast.makeText(v.getContext(), _url, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(_url));
        v.getContext().startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return deals.size();
    }
}
