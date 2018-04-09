package com.example.bankblood.Fragments;

import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.bankblood.Activities.ResetPasswordActivity;
import com.example.bankblood.Models.BloodTypes.BloodTypes;
import com.example.bankblood.Models.Donor.Donor;
import com.example.bankblood.Models.Region.Region;
import com.example.bankblood.Models.Regions.Regions;
import com.example.bankblood.R;
import com.example.bankblood.Utils.Callbacks;
import com.example.bankblood.Utils.RestApiRequests.FetchBloodTypesRequest;
import com.example.bankblood.Utils.RestApiRequests.FetchRegionsRequest;
import com.example.bankblood.Utils.RestApiRequests.GetDonorByIDRequest;
import com.example.bankblood.Utils.RestApiRequests.GetRegionByIDRequest;
import com.example.bankblood.Utils.RestApiRequests.UpdateDonorRequest;

import java.util.HashMap;


public class EditProfileFragment extends Fragment implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    String firstName,lastName,phone,email,gender,bloodTypeStr,areaStr,cityStr;;
    Spinner spinnerBloodType,spinnerArea,spinnerCity;
    RadioButton maleRadioBtn,femaleRadioBtn;
    private EditText inputEmail,inputFirstName,inputLastName,inputPhoneNum;
    private Button btnOk, btnCancel;
    private ProgressBar progressBar;
    TextView changePassword;

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

        btnOk = (Button) view.findViewById(R.id.btn_ok);
        btnCancel = (Button) view.findViewById(R.id.btn_cancel);

        btnCancel.setOnClickListener(this);
        btnOk.setOnClickListener(this);

        changePassword=(TextView)view.findViewById(R.id.change_password);
        changePassword.setOnClickListener(this);

        inputFirstName = (EditText) view.findViewById(R.id.first_name);
        inputLastName = (EditText) view.findViewById(R.id.last_name);
        inputPhoneNum = (EditText) view.findViewById(R.id.phone_num);
        inputEmail = (EditText) view.findViewById(R.id.email);

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
    Donor donor;
    private void getDonnerData() {
        int id=bundle.getInt("person_id");
        GetDonorByIDRequest getDonorByIDRequest =new GetDonorByIDRequest(id);
        getDonorByIDRequest.setCallbacks(new Callbacks() {
            @Override
            public void OnSuccess(Object obj) {
                try {
                    donor = (Donor) obj;
                    String[] strs = donor.data.name.split(" ");
                    inputFirstName.setText(strs[0]);
                    inputLastName.setText(strs[1]);
                    inputPhoneNum.setText(donor.data.phone);
                    bloodTypeStr= donor.data.bloodType;

                    for (int i = 0; i <spinnerBloodTypeData.length ; i++) {
                        if(bloodTypeStr.equals(spinnerBloodTypeData[i])){
                            spinnerBloodType.setSelection(i);
                            break;
                        }
                    }

                    areaStr= donor.data.region;
                    for (int i = 0; i <spinnerAreaData.length ; i++) {
                        if(cityStr.equals(spinnerAreaData[i])){
                            spinnerArea.setSelection(i);
                            break;
                        }
                    }



                }catch (Exception e){
                    e.getMessage();
                }

            }

            @Override
            public void OnFailure(Object obj) {
                Toast.makeText(getContext(), "لقد حدث خطاء", Toast.LENGTH_SHORT).show();
            }
        });
        getDonorByIDRequest.start();
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
                try {

                    int area_id = Integer.valueOf(areaStr);
                    getCities(area_id);

                }catch (Exception e){}

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

        cityStr= donor.data.city;
        for (int i = 0; i <spinnerCityData.length ; i++) {
            if(cityStr.equals(spinnerCityData[i])){
                spinnerCity.setSelection(i);
                break;
            }
        }
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
            case R.id.change_password:
                changePasswordMethod();
                break;

        }
    }

    private void changePasswordMethod() {
        getActivity().startActivity(new Intent(getActivity(), ResetPasswordActivity.class));
    }

    private void cancelMethod() {
        addProfileFragment(bundle);
    }

    private void okMethod() {

        int id=bundle.getInt("person_id");
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("first_name",firstName);
        hashMap.put("last_name",lastName);
        hashMap.put("phone",phone);
        hashMap.put("blood_type_id",bloodTypeStr);
        hashMap.put("city_id",cityStr);

        UpdateDonorRequest updateDonorRequest =new UpdateDonorRequest(hashMap,id);
        updateDonorRequest.setCallbacks(new Callbacks() {
            @Override
            public void OnSuccess(Object obj) {
                Donor donor =(Donor)obj;
                Toast.makeText(getActivity(), "تمت العملية بنجاح", Toast.LENGTH_SHORT).show();
                addProfileFragment(bundle);
            }

            @Override
            public void OnFailure(Object obj) {
                Toast.makeText(getActivity(), "لقد حدث خطاء", Toast.LENGTH_SHORT).show();
            }
        });
        updateDonorRequest.start();

    }

    private void addProfileFragment(Bundle bundle) {
        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
        ProfileFragment profileFragment=new ProfileFragment();
        profileFragment.setArguments(bundle);

        Fragment oldFragment=fragmentManager.findFragmentByTag("EditProfileFragment");
        if(oldFragment!=null)
            fragmentManager.beginTransaction().remove(oldFragment);

        fragmentManager.beginTransaction().replace(R.id.container,profileFragment,"ProfileFragment").commit();
    }
}
