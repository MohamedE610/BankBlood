package com.example.bankblood.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bankblood.Models.BloodTypes.BloodTypes;
import com.example.bankblood.Models.Donner.Donner;
import com.example.bankblood.Models.Region.Region;
import com.example.bankblood.Models.Regions.Regions;
import com.example.bankblood.R;
import com.example.bankblood.Utils.Callbacks;
import com.example.bankblood.Utils.RestApiRequests.FetchBloodTypesRequest;
import com.example.bankblood.Utils.RestApiRequests.FetchRegionsRequest;
import com.example.bankblood.Utils.RestApiRequests.GetDonnerByIDRequest;
import com.example.bankblood.Utils.RestApiRequests.GetRegionByIDRequest;


public class EditProfileFragment extends Fragment implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    String name,phone,email,gender,bloodTypeStr,areaStr,cityStr;;
    Spinner spinnerBloodType,spinnerArea,spinnerCity;
    RadioButton maleRadioBtn,femaleRadioBtn;
    private EditText inputEmail, inputPassword,inputUserName,inputPhoneNum,inputPasswordConfirm;
    private Button btnOk, btnCancel;
    private ProgressBar progressBar;

    Bundle bundle;

    public EditProfileFragment() {
        // Required empty public constructor
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_edit_profile, container, false);

        bundle=getArguments();

        btnOk = (Button) view.findViewById(R.id.sign_in_button);
        btnCancel = (Button) view.findViewById(R.id.btn_reset_password);

        btnCancel.setOnClickListener(this);
        btnOk.setOnClickListener(this);

        inputUserName = (EditText) view.findViewById(R.id.user_name);
        inputPhoneNum = (EditText) view.findViewById(R.id.phone_num);
        inputEmail = (EditText) view.findViewById(R.id.email);
        inputPassword = (EditText) view.findViewById(R.id.password);
        inputPasswordConfirm = (EditText) view.findViewById(R.id.password_confirm);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        maleRadioBtn = (RadioButton) view.findViewById(R.id.male_rb);
        femaleRadioBtn = (RadioButton) view.findViewById(R.id.female_rb);

        maleRadioBtn.setOnCheckedChangeListener(this);
        femaleRadioBtn.setOnCheckedChangeListener(this);

        spinnerBloodType=(Spinner)view.findViewById(R.id.spinner_blood_type);
        spinnerArea=(Spinner)view.findViewById(R.id.spinner_area);
        spinnerCity=(Spinner)view.findViewById(R.id.spinner_city);


        getBloodTypes();
        getRegions();


        getDonnerData();
        return view;
    }

    private void getDonnerData() {
        int id=bundle.getInt("person_id");
        GetDonnerByIDRequest getDonnerByIDRequest=new GetDonnerByIDRequest(id);
        getDonnerByIDRequest.setCallbacks(new Callbacks() {
            @Override
            public void OnSuccess(Object obj) {
                Donner donner=(Donner)obj;
                inputUserName.setText(donner.data.name);
                inputPhoneNum.setText(donner.data.phone);
                //ToDO email must be exist

            }

            @Override
            public void OnFailure(Object obj) {
                Toast.makeText(getContext(), "لقد حدث خطاء", Toast.LENGTH_SHORT).show();
            }
        });
        getDonnerByIDRequest.start();
    }

    private void getCities(int area_id) {

        GetRegionByIDRequest getRegionByIDRequest =new GetRegionByIDRequest(area_id);
        getRegionByIDRequest.setCallbacks(new Callbacks() {
            @Override
            public void OnSuccess(Object obj) {
                Region region=(Region) obj;
                spinnerCityData=new String[region.data.cities.data.size()+1];
                cityValues=new String[region.data.cities.data.size()+1];
                spinnerCityData[0]="";
                cityValues[0]="";
                for (int i = 1; i <region.data.cities.data.size()+1 ; i++) {
                    spinnerCityData[i]=region.data.cities.data.get(i-1).name;
                    cityValues[i]=region.data.cities.data.get(i-1).id+"";
                }
                createCitySpinner();
            }

            @Override
            public void OnFailure(Object obj) {

            }
        });
        getRegionByIDRequest.start();
    }

    private void getRegions() {
        FetchRegionsRequest fetchRegionsRequest=new FetchRegionsRequest();
        fetchRegionsRequest.setCallbacks(new Callbacks() {
            @Override
            public void OnSuccess(Object obj) {
                Regions regions=(Regions)obj;
                spinnerAreaData=new String[regions.data.size()+1];
                areaValues=new String[regions.data.size()+1];
                spinnerAreaData[0]="";
                areaValues[0]="";

                for (int i = 1; i < regions.data.size()+1; i++) {
                    spinnerAreaData[i]=regions.data.get(i-1).name;
                    areaValues[i]=regions.data.get(i-1).id+"";
                }

                createِAreaSpinner();
            }

            @Override
            public void OnFailure(Object obj) {

            }
        });
        fetchRegionsRequest.start();
    }

    private void getBloodTypes() {
        FetchBloodTypesRequest fetchBloodTypesRequest=new FetchBloodTypesRequest();
        fetchBloodTypesRequest.setCallbacks(new Callbacks() {
            @Override
            public void OnSuccess(Object obj) {
                BloodTypes bloodTypes=(BloodTypes)obj;
                spinnerBloodTypeData=new String[bloodTypes.data.size()];
                bloodTypeValues=new String[bloodTypes.data.size()];
                for (int i = 0; i < bloodTypes.data.size(); i++) {
                    if(i==0){
                        spinnerBloodTypeData[0]="";
                        bloodTypeValues[0]="";
                        continue;
                    }
                    spinnerBloodTypeData[i]=bloodTypes.data.get(i).name;
                    bloodTypeValues[i]=bloodTypes.data.get(i).id+"";
                }
                createBloodTypeSpinner();
            }

            @Override
            public void OnFailure(Object obj) {

            }
        });
        fetchBloodTypesRequest.start();
    }

    String[] spinnerBloodTypeData;
    String[] bloodTypeValues;
    private void createBloodTypeSpinner(){
        ArrayAdapter<String> SpinnerAdapter=new ArrayAdapter<String>(getContext(),R.layout.spinner_item,spinnerBloodTypeData);
        spinnerBloodType.setAdapter(SpinnerAdapter);
        spinnerBloodType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                bloodTypeStr=bloodTypeValues[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }



    String[] spinnerAreaData;
    String[] areaValues;
    private void createِAreaSpinner(){
        ArrayAdapter<String> SpinnerAdapter=new ArrayAdapter<String>(getContext(),R.layout.spinner_item,spinnerAreaData);
        spinnerArea.setAdapter(SpinnerAdapter);
        spinnerArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                areaStr=areaValues[position];
                int area_id=Integer.valueOf(areaStr);
                getCities(area_id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    String[] spinnerCityData;
    String[] cityValues;
    private void createCitySpinner(){
        ArrayAdapter<String> SpinnerAdapter=new ArrayAdapter<String>(getContext(),R.layout.spinner_item,spinnerCityData);
        spinnerCity.setAdapter(SpinnerAdapter);
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                cityStr=cityValues[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        int id = compoundButton.getId();

        switch (id) {
            case R.id.male_rb:
                if (b)
                    gender = "male";
                break;

            case R.id.female_rb:
                if (b)
                    gender = "female";
                break;
        }

    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id){
            case R.id.btn_ok:
                okMethod();
                break;
            case R.id.btn_cancel:
                cancelMethod();
                break;

        }
    }

    private void cancelMethod() {
        addProfileFragment(bundle);
    }

    private void okMethod() {

        addProfileFragment(bundle);
    }

    private void addProfileFragment(Bundle bundle) {
        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
        ProfileFragment profileFragment=new ProfileFragment();
        profileFragment.setArguments(bundle);
        fragmentManager.beginTransaction().add(R.id.container,profileFragment).commit();
    }
}
