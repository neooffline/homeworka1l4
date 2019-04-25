package ru.neooffline.homeworka1l4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class SecondScreen extends AppCompatActivity {

    static final String TOKEN = "weatherObj";
    static final String isCheckedParam = "isCheckedParam";
    static final int STRINGS = 3;
    private TextView[][] tTopics, tValues, tDims;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);
        Intent i = this.getIntent();
        Weather weather;
        try {
            weather = i.getParcelableExtra(TOKEN);
        } catch (Throwable t) {
            Log.e("Error", t.getMessage());
            weather = new Weather(true);
        }
        settTopics();
        for (int j = 0; j < STRINGS; j++) {
            if (i.getExtras().getBoolean(isCheckedParam + j))
                tTopics[j][1].setText(String.format(Locale.ENGLISH,"%d",weather.getAllparams()[j]));
            else {
                for (int k = 0; k < STRINGS; k++) {
                    tTopics[j][k].setVisibility(View.INVISIBLE);
                }
            }
        }
        /*hideTextViewies(findViewById(R.id.temp_value),
                (TextView) findViewById(R.id.ss_temp_value),
                findViewById(R.id.temp_dim),
                i.getBooleanExtra(isCheckedParam + 0, false),
                weather.getTemperature());
        hideTextViewies(findViewById(R.id.hum_value),
                (TextView) findViewById(R.id.ss_hum_value),
                findViewById(R.id.hum_dim),
                i.getBooleanExtra(isCheckedParam + 1, false),
                weather.getHumidity());
        hideTextViewies(findViewById(R.id.press_value),
                (TextView) findViewById(R.id.ss_press_value),
                findViewById(R.id.press_dim),
                i.getBooleanExtra(isCheckedParam + 2, false),
                weather.getPressure());*/
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    void hideTextViewies(View tV, TextView tvVal, View dimV, boolean isCheked, int text) {
        if (isCheked) {
            tvVal.setText(String.format(Locale.ENGLISH, "%d", text));
        } else {
            tV.setVisibility(View.INVISIBLE);
            tvVal.setVisibility(View.INVISIBLE);
            dimV.setVisibility(View.INVISIBLE);
        }
    }

    void settTopics() {
        tTopics = new TextView[STRINGS][STRINGS];
        tTopics[0][0] = findViewById(R.id.temp_value);
        tTopics[1][0] = findViewById(R.id.hum_value);
        tTopics[2][0] = findViewById(R.id.press_value);
        tTopics[0][1] = findViewById(R.id.ss_temp_value);
        tTopics[1][1] = findViewById(R.id.ss_hum_value);
        tTopics[2][1] = findViewById(R.id.ss_press_value);
        tTopics[0][2] = findViewById(R.id.temp_dim);
        tTopics[1][2] = findViewById(R.id.hum_dim);
        tTopics[2][2] = findViewById(R.id.press_dim);
    }
}
