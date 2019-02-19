package dk.tangsolutions.currencyconverter;

import android.app.DownloadManager;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;


public class CurrencyService extends AsyncTask<String,Void, ArrayList<Rates>> {

    public CurrencyService() {
    }

    @Override
    protected ArrayList<Rates> doInBackground(String... strings) {

        ArrayList<Rates> rates = new ArrayList<>();
        final String BASE = strings[0];
        final String url = "https://api.exchangeratesapi.io/latest?base="+BASE;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
//        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        try {
            JSONObject obj  = (new JSONObject(responseEntity.getBody()).getJSONObject("rates"));
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                Iterator<String> keys = obj.keys();
                while(keys.hasNext()){
                    String key = keys.next();
                    rates.add(new Rates(key,obj.getDouble(key)));
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        MainActivity.rates = rates;
        return rates;
    }



}