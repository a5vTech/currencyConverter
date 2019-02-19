package dk.tangsolutions.currencyconverter;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Mainactivity";
    Spinner spinnerFrom, spinnerTo;
    static List<Rates> rates;
    private ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setup();
    }

    private void init() {
        this.spinnerFrom = findViewById(R.id.spinnerFrom);
        this.spinnerTo = findViewById(R.id.spinnerTo);
        this.layout = findViewById(R.id.layout);
        layout.setBackgroundColor(Color.rgb(178,178,178));
    }


    public void setup(){
        try {
            new CurrencyService().execute("DKK").get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> ratesList = new ArrayList<>();
        for (int i = 0; i < rates.size(); i++) {
            ratesList.add(rates.get(i).getId());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ratesList);
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);

    }


    public void convert(View view){

        Log.d(TAG, spinnerTo.getSelectedItem().toString());


    }


}
