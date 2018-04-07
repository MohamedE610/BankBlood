package com.example.bankblood.Fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.bankblood.Activities.ProfileActivity;
import com.example.bankblood.Adapters.DonnersAdapter;
import com.example.bankblood.Dialogs.DonateDialog;
import com.example.bankblood.Dialogs.SearchDialog;
import com.example.bankblood.Models.Donors.DonorData;
import com.example.bankblood.Models.Donors.Donors;
import com.example.bankblood.R;
import com.example.bankblood.Utils.Callbacks;
import com.example.bankblood.Utils.NetworkState;
import com.example.bankblood.Utils.RestApiRequests.FetchDonorsRequest;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends Fragment implements Callbacks, DonnersAdapter.RecyclerViewClickListener, View.OnClickListener {

    Donors donors;
    DonnersAdapter donnersAdapter;
    RecyclerView recyclerView;
    FetchDonorsRequest fetchDonorsRequest;

    Button btnDonate,btnSearch;

    public HomeFragment() {
    }


    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));

        if(NetworkState.ConnectionAvailable(getContext())){
            fetchDonorsRequest =new FetchDonorsRequest();
            fetchDonorsRequest.setCallbacks(this);
            fetchDonorsRequest.start();
        }


        btnDonate=(Button)view.findViewById(R.id.btn_donate);
        btnSearch=(Button)view.findViewById(R.id.btn_search);

        btnDonate.setOnClickListener(this);
        btnSearch.setOnClickListener(this);


        return view;
    }


    @Override
    public void OnSuccess(Object obj) {
        displayData(obj);

    }

    private void displayData(Object obj) {
        donors =(Donors)obj;
        donnersAdapter=new DonnersAdapter(donors,getContext());
        donnersAdapter.setClickListener(this);
        recyclerView.setAdapter(donnersAdapter);
        donnersAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnFailure(Object obj) {

    }

    @Override
    public void ItemClicked(View v, int position) {
        Intent intent=new Intent(getActivity(),ProfileActivity.class);
        DonorData donorData = donors.data.get(position);
        intent.putExtra("person_id", donorData.id);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        int id =view.getId();

        switch (id){
            case R.id.btn_donate:
                donateMethod();
                break;
            case R.id.btn_search:
                searchDonnersMethod();
                break;
        }
    }

    private void searchDonnersMethod() {
        SearchDialog searchDialog=new SearchDialog(getActivity());
        searchDialog.setCallbacks(new Callbacks() {
            @Override
            public void OnSuccess(Object obj) {

                Toast.makeText(getActivity(), "جارى البحث", Toast.LENGTH_SHORT).show();
                displayData(obj);
            }

            @Override
            public void OnFailure(Object obj) {
                Toast.makeText(getActivity(), "لقد حدث خطاء", Toast.LENGTH_SHORT).show();
            }
        });
        searchDialog.show();
    }

    private void donateMethod() {
        DonateDialog donateDialog=new DonateDialog(getActivity());
        donateDialog.show();
    }

}
