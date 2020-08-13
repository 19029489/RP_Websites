package sg.edu.rp.c346.id19029489.rpwebsites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Spinner spn1, spn2;
    Button btnGo;
    ArrayList<String> alCategory;
    ArrayAdapter<String> aaCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spn1 = findViewById(R.id.spinner1);
        spn2 = findViewById(R.id.spinner2);
        btnGo = findViewById(R.id.buttonGo);

        alCategory = new ArrayList<>();

        //Create an ArrayAdapter using the default Spinner layout and the ArrayList
        aaCategory = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, alCategory);

        //Bind the ArrayAdapter to the Spinner
        spn2.setAdapter(aaCategory);

        spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                alCategory.clear();
                if (position == 0){
                    String[] strNumbers = getResources().getStringArray(R.array.rp_sub_category);
                    alCategory.addAll(Arrays.asList(strNumbers));
                }
                else{
                    String[] strNumbers = getResources().getStringArray(R.array.soi_sub_category);
                    alCategory.addAll(Arrays.asList(strNumbers));
                }
                aaCategory.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);

                String[][] sites = {
                        {
                                "https://www.rp.edu.sg/",
                                "https://www.rp.edu.sg/student-life"
                        },
                        {
                                "https://www.rp.edu.sg/soi/full-time-diplomas/details/r47",
                                "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12"
                        }
                };

                String url = sites[spn1.getSelectedItemPosition()][spn2.getSelectedItemPosition()];

                intent.putExtra("url", url);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        int catPos = spn1.getSelectedItemPosition();
        int subPos = spn2.getSelectedItemPosition();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();

        prefEdit.putInt("spn1", catPos);
        prefEdit.putInt("spn2", subPos);

        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        Integer category = prefs.getInt("spn1", 0);
        Integer subCategory = prefs.getInt("spn2", 0);

        spn1.setSelection(category);

        alCategory.clear();

        if (category == 0){
            String[] strNumbers = getResources().getStringArray(R.array.rp_sub_category);
            alCategory.addAll(Arrays.asList(strNumbers));
        }
        else if (category == 1){
            String[] strNumbers = getResources().getStringArray(R.array.soi_sub_category);
            alCategory.addAll(Arrays.asList(strNumbers));
        }
        spn2.setSelection(subCategory);

        aaCategory.notifyDataSetChanged();
    }
}