package com.example.bankblood.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bankblood.Dialogs.SendMessageDialog;
import com.example.bankblood.Models.Donors.Donors;
import com.example.bankblood.R;


public class DonnersAdapter extends RecyclerView.Adapter<DonnersAdapter.MyViewHolder> {

     Donors donors;
    Context context;
    int LastPosition = -1;
    RecyclerViewClickListener ClickListener;


    public DonnersAdapter() {
    }

    public DonnersAdapter(Donors donors, Context context) {
        this.donors = donors;
        this.context = context;
    }


    public void setClickListener(RecyclerViewClickListener clickListener) {
        this.ClickListener = clickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_donner, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        String name= donors.data.get(position).name;
        holder.nameTextView.setText(name);

        String bloodType= donors.data.get(position).bloodType;
        holder.bloodTypeTextView.setText(bloodType);

        String city= donors.data.get(position).city;
        holder.cityTextView.setText(city);

        final String phoneNum= donors.data.get(position).phone;

        final int donnerID= donors.data.get(position).id;

        holder.msgImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMsg(donnerID);
            }
        });

        holder.callImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeCall(phoneNum);
            }
        });


        setAnimation(holder.cardView, position);
    }

    private void makeCall(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNum));
        context.startActivity(intent);
    }

    private void sendMsg(int donnerID) {
        SendMessageDialog sendMessageDialog=new SendMessageDialog(context,donnerID);
        sendMessageDialog.show();
    }

    @Override
    public void onViewRecycled(MyViewHolder holder) {

        super.onViewRecycled(holder);
    }


    @Override
    public int getItemCount() {
        if (donors == null || donors.data==null)
            return 0;
        return donors.data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nameTextView;
        TextView bloodTypeTextView;
        TextView cityTextView;
        ImageView callImg;
        ImageView msgImg;
        CardView cardView;


        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            callImg = (ImageView) itemView.findViewById(R.id.img_call);
            msgImg = (ImageView) itemView.findViewById(R.id.img_msg);
            nameTextView = (TextView) itemView.findViewById(R.id.text_name);
            bloodTypeTextView = (TextView) itemView.findViewById(R.id.text_blood_type);
            cityTextView = (TextView) itemView.findViewById(R.id.text_city);
            cardView = (CardView) itemView.findViewById(R.id.card);

        }

        @Override
        public void onClick(View view) {
            if (ClickListener != null)
                ClickListener.ItemClicked(view, getAdapterPosition());
        }

        public void clearAnimation() {
            cardView.clearAnimation();
        }
    }

    public interface RecyclerViewClickListener {

        public void ItemClicked(View v, int position);
    }

    private void setAnimation(View viewToAnimate, int position) {

        if (position > LastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            LastPosition = position;
        }
    }

    @Override
    public void onViewDetachedFromWindow(MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.clearAnimation();
    }

    /*
                            */

}

