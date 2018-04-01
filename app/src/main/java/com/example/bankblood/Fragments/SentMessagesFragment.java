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
import com.example.bankblood.Adapters.SentMessagesAdapter;
import com.example.bankblood.Models.Messages.Messages;
import com.example.bankblood.R;
import com.example.bankblood.Utils.Callbacks;
import com.example.bankblood.Utils.MySharedPreferences;
import com.example.bankblood.Utils.NetworkState;
import com.example.bankblood.Utils.RestApiRequests.GetIncomingMessagesRequest;
import com.example.bankblood.Utils.RestApiRequests.GetOutgoingMessagesRequest;


public class SentMessagesFragment extends Fragment implements Callbacks, SentMessagesAdapter.RecyclerViewClickListener {

    Messages messages;
    SentMessagesAdapter sentMessagesAdapter;
    GetOutgoingMessagesRequest getOutgoingMessagesRequest;
    RecyclerView recyclerView;

    public SentMessagesFragment() {
        // Required empty public constructor
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MySharedPreferences.setUpMySharedPreferences(getContext());
        view=inflater.inflate(R.layout.fragment_sent_messages, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        if(NetworkState.ConnectionAvailable(getContext())){
            int id=Integer.valueOf(MySharedPreferences.getUserSetting("id"));
            getOutgoingMessagesRequest=new GetOutgoingMessagesRequest(id);
            getOutgoingMessagesRequest.setCallbacks(this);
            getOutgoingMessagesRequest.start();
        }
        return view;
    }

    private void displayData(Object obj) {
        messages=(Messages)obj;
        sentMessagesAdapter=new SentMessagesAdapter(messages,getContext());
        sentMessagesAdapter.setClickListener(this);
        recyclerView.setAdapter(sentMessagesAdapter);
        sentMessagesAdapter.notifyDataSetChanged();
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
        intent.setAction("to");
        intent.putExtra("message",messages.data.get(position));
        getActivity().startActivity(intent);
    }
}
