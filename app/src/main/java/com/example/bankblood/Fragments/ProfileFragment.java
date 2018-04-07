package com.example.bankblood.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bankblood.Dialogs.SendMessageDialog;
import com.example.bankblood.Models.Donor.Donor;
import com.example.bankblood.R;
import com.example.bankblood.Utils.Callbacks;
import com.example.bankblood.Utils.NetworkState;
import com.example.bankblood.Utils.RestApiRequests.DonationApprovedRequest;
import com.example.bankblood.Utils.RestApiRequests.GetDonorByIDRequest;


public class ProfileFragment extends Fragment implements Callbacks, View.OnClickListener {

    Donor donor;
    GetDonorByIDRequest getDonorByIDRequest;

    TextView name,bloodType,city,status;
    String nameStr,bloodTypeStr,cityStr,statusStr;
    CardView callCard,msgCard;
    Button btnDonated;

    public ProfileFragment() {
        // Required empty public constructor
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_profile, container, false);

        name=(TextView)view.findViewById(R.id.person_name);
        bloodType=(TextView)view.findViewById(R.id.person_blood_type);
        city=(TextView)view.findViewById(R.id.person_city);
        status=(TextView)view.findViewById(R.id.person_status);

        callCard=(CardView)view.findViewById(R.id.call_card);
        msgCard=(CardView)view.findViewById(R.id.msg_card);

        btnDonated=(Button)view.findViewById(R.id.btn_donated);

        callCard.setOnClickListener(this);
        msgCard.setOnClickListener(this);
        btnDonated.setOnClickListener(this);

        Bundle bundle=getArguments();
        int id=bundle.getInt("person_id");

        if(NetworkState.ConnectionAvailable(getContext())){
            getDonorByIDRequest =new GetDonorByIDRequest(id);
            getDonorByIDRequest.setCallbacks(this);
            getDonorByIDRequest.start();
        }

        return view;
    }

    private void displayData(Object obj) {
        donor =(Donor)obj;
        name.setText(donor.data.name);
        bloodType.setText(donor.data.bloodType);
        city.setText(donor.data.city);
        statusStr= donor.data.status;
        if(!statusStr.equals("Available")) {
            statusStr="متبرع سابقا";
            status.setText(statusStr);
        }else if(statusStr.equals("Available")){
            statusStr="غير متبرع";
            status.setText(statusStr);
        }
    }

    @Override
    public void OnSuccess(Object obj) {
        displayData(obj);
    }

    @Override
    public void OnFailure(Object obj) {
        Toast.makeText(getActivity(), "لقد حدث خطاء ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id){
            case R.id.call_card:
                makeCall(donor.data.phone);
                break;
            case R.id.msg_card:
                sendMsg(donor.data.id);
                break;
            case R.id.btn_donated:
                donatedMethod(donor.data.id);
                break;
        }
    }

    private void makeCall(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNum));
        getContext().startActivity(intent);
    }

    private void sendMsg(int donnerID) {
        SendMessageDialog sendMessageDialog=new SendMessageDialog(getContext(),donnerID);
        sendMessageDialog.show();
    }
    private void donatedMethod(int id) {
        DonationApprovedRequest donationApprovedRequest=new DonationApprovedRequest(id);
        donationApprovedRequest.setCallbacks(new Callbacks() {
            @Override
            public void OnSuccess(Object obj) {
                Toast.makeText(getActivity(), "تمت العملية بنجاح", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnFailure(Object obj) {
                Toast.makeText(getActivity(), "لقد حدث خطاء الرجاء اعد المحاولة", Toast.LENGTH_SHORT).show();
            }
        });

        if(NetworkState.ConnectionAvailable(getContext())) {
            donationApprovedRequest.start();
        }else{
            Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }
}
