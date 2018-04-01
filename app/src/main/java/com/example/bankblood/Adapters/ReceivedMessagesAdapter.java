package com.example.bankblood.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.bankblood.Models.Messages.Messages;
import com.example.bankblood.R;

/**
 * Created by abdallah on 2/21/2018.
 */

public class ReceivedMessagesAdapter extends RecyclerView.Adapter<ReceivedMessagesAdapter.MyViewHolder> {

    Messages messages;
    Context context;
    int LastPosition = -1;
    RecyclerViewClickListener ClickListener;


    public ReceivedMessagesAdapter() {
    }

    public ReceivedMessagesAdapter(Messages messages, Context context) {
        this.messages = messages;
        this.context = context;
    }


    public void setClickListener(RecyclerViewClickListener clickListener) {
        this.ClickListener = clickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_message, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        String name=messages.data.get(position).from.name;
        holder.nameTextView.setText(name);

        String title=messages.data.get(position).title;
        holder.titleTextView.setText(title);

        if(messages.data.get(position).read)
            holder.readView.setVisibility(View.GONE);
        else
            holder.readView.setVisibility(View.VISIBLE);

        setAnimation(holder.cardView, position);
    }

    @Override
    public void onViewRecycled(MyViewHolder holder) {

        super.onViewRecycled(holder);
    }


    @Override
    public int getItemCount() {
        if (messages == null || messages.data==null)
            return 0;
        return messages.data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nameTextView;
        TextView titleTextView;
        CardView cardView;
        View readView;


        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            nameTextView = (TextView) itemView.findViewById(R.id.text_name);
            titleTextView = (TextView) itemView.findViewById(R.id.text_msg);
            cardView = (CardView) itemView.findViewById(R.id.card);
            readView = (View) itemView.findViewById(R.id.read_view);


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

