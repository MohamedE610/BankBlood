package com.example.bankblood.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bankblood.Activities.MessageDetailsActivity;
import com.example.bankblood.Adapters.ReceivedMessagesAdapter;
import com.example.bankblood.Models.Messages.Messages;
import com.example.bankblood.R;
import com.example.bankblood.Utils.Callbacks;
import com.example.bankblood.Utils.MySharedPreferences;
import com.example.bankblood.Utils.NetworkState;
import com.example.bankblood.Utils.RestApiRequests.GetIncomingMessagesRequest;


public class ReceivedMessagesFragment extends Fragment implements Callbacks, ReceivedMessagesAdapter.RecyclerViewClickListener {

    Messages messages;
    ReceivedMessagesAdapter receivedMessagesAdapter;
    RecyclerView recyclerView;
    GetIncomingMessagesRequest getIncomingMessagesRequest;

    public ReceivedMessagesFragment() {
        // Required empty public constructor
    }


    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_received_messages, container, false);
        MySharedPreferences.setUpMySharedPreferences(getContext());
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        if(NetworkState.ConnectionAvailable(getActivity())){
            int id=Integer.valueOf(MySharedPreferences.getUserSetting("id"));
            getIncomingMessagesRequest=new GetIncomingMessagesRequest(id);
            getIncomingMessagesRequest.setCallbacks(this);
            getIncomingMessagesRequest.start();
        }

        return view;
    }

    private void displayData(Object obj) {

        messages=(Messages)obj;
        receivedMessagesAdapter=new ReceivedMessagesAdapter(messages,getContext());
        receivedMessagesAdapter.setClickListener(this);
        recyclerView.setAdapter(receivedMessagesAdapter);
        receivedMessagesAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnSuccess(Object obj) {

        displayData(obj);
    }

    @Override
    public void OnFailure(Object obj) {

    }

    @Override
    public void ItemClicked(View v, int position) {
        Intent intent=new Intent(getActivity(), MessageDetailsActivity.class);
        intent.setAction("from");
        intent.putExtra("message",messages.data.get(position));
        getActivity().startActivity(intent);
    }
}
