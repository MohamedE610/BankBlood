package com.example.bankblood.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.bankblood.Models.BloodTypes.BloodTypes;
import com.example.bankblood.Models.Cities.Cities;
import com.example.bankblood.Models.Region.Region;
import com.example.bankblood.Models.Regions.Regions;
import com.example.bankblood.R;
import com.example.bankblood.Utils.Callbacks;
import com.example.bankblood.Utils.RestApiRequests.DonnerSearchRequest;
import com.example.bankblood.Utils.RestApiRequests.FetchBloodTypesRequest;
import com.example.bankblood.Utils.RestApiRequests.FetchCitiesRequest;
import com.example.bankblood.Utils.RestApiRequests.FetchRegionsRequest;
import com.example.bankblood.Utils.RestApiRequests.FilterDonnersByBloodTypeRequest;
import com.example.bankblood.Utils.RestApiRequests.GetRegionByIDRequest;

import java.util.HashMap;


/**
 * Created by abdallah on 12/14/2017.
 */
public class SearchDialog extends Dialog implements View.OnClickListener {

    public Activity c;
    public Dialog d;
    String bloodTypeStr,areaStr,cityStr;;

    Button okBtn;
    Button cancelBtn;

    Spinner spinnerBloodType,spinnerArea,spinnerCity;

    Callbacks callbacks;

    public void setCallbacks(Callbacks callbacks){
        this.callbacks=callbacks;
    }

    public SearchDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.search_dialog);


        okBtn=(Button)findViewById(R.id.btn_ok);
        cancelBtn=(Button)findViewById(R.id.btn_cancel);
        okBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);

        spinnerBloodType=(Spinner)findViewById(R.id.spinner_blood_type);
        spinnerArea=(Spinner)findViewById(R.id.spinner_area);
        spinnerCity=(Spinner)findViewById(R.id.spinner_city);

        getBloodTypes();
        getRegions();

        /*createBloodTypeSpinner();
        createCitySpinner();
        createِAreaSpinner();*/


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
                        spinnerBloodTypeData[i]="";
                        bloodTypeValues[i]="";
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

    @Override
    public void onClick(View view) {
        int id=view.getId();

        switch (id){
            case R.id.btn_ok:
                okBtnWork();
                break;
            case R.id.btn_cancel:
                cancelBtnWork();
                break;
        }
    }

    private void cancelBtnWork() {
        this.cancel();
    }

    private void okBtnWork() {

        if(TextUtils.isEmpty(cityStr)&&TextUtils.isEmpty(bloodTypeStr))
            return;

        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("blood_type_id",bloodTypeStr);
        hashMap.put("city_id",cityStr);
        DonnerSearchRequest donnerSearchRequest=new DonnerSearchRequest(hashMap);
        donnerSearchRequest.setCallbacks(new Callbacks() {
            @Override
            public void OnSuccess(Object obj) {
                callbacks.OnSuccess(obj);
                cancel();
            }

            @Override
            public void OnFailure(Object obj) {
                callbacks.OnFailure(obj);
                cancel();
            }
        });
        donnerSearchRequest.start();

    }

    String[] spinnerBloodTypeData;
    String[] bloodTypeValues;
    private void createBloodTypeSpinner(){
        ArrayAdapter<String> SpinnerAdapter=new ArrayAdapter<String>(c,R.layout.spinner_item,spinnerBloodTypeData);
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
        ArrayAdapter<String> SpinnerAdapter=new ArrayAdapter<String>(c,R.layout.spinner_item,spinnerAreaData);
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
        ArrayAdapter<String> SpinnerAdapter=new ArrayAdapter<String>(c,R.layout.spinner_item,spinnerCityData);
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


}
