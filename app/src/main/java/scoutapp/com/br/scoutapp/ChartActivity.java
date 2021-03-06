package scoutapp.com.br.scoutapp;

import android.content.Intent;
import android.os.Bundle;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;


import java.util.ArrayList;

import scoutapp.com.br.scoutapp.model.Athlete;
import scoutapp.com.br.scoutapp.model.Championship;
import scoutapp.com.br.scoutapp.model.GameOpponent;
import scoutapp.com.br.scoutapp.model.GameUser;
import scoutapp.com.br.scoutapp.model.User;


public class ChartActivity extends ChartBase implements OnSeekBarChangeListener,
        OnChartValueSelectedListener {

    private PieChart mChartAthlete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupActionBar();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // -----Athlete Chart-----
        mChartAthlete = (PieChart) findViewById(R.id.chartAthlete);
        mChartAthlete.setUsePercentValues(true);
        mChartAthlete.setDescription("");
        mChartAthlete.setExtraOffsets(5, 10, 5, 5);

        mChartAthlete.setDragDecelerationFrictionCoef(0.95f);

        mChartAthlete.setCenterTextTypeface(mTfLight);
        mChartAthlete.setCenterText(generateCenterSpannableText());

        mChartAthlete.setDrawHoleEnabled(false);
        mChartAthlete.setHoleColor(Color.WHITE);

        mChartAthlete.setTransparentCircleColor(Color.WHITE);
        mChartAthlete.setTransparentCircleAlpha(110);

        mChartAthlete.setHoleRadius(58f);
        mChartAthlete.setTransparentCircleRadius(61f);

        mChartAthlete.setDrawCenterText(false);

        mChartAthlete.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChartAthlete.setRotationEnabled(true);
        mChartAthlete.setHighlightPerTapEnabled(true);


        // add a selection listener
        mChartAthlete.setOnChartValueSelectedListener(this);

        setData();

        mChartAthlete.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChartAthlete.spin(2000, 0, 360);

        // entry label styling
        mChartAthlete.setEntryLabelColor(Color.BLACK);
        mChartAthlete.setEntryLabelTypeface(mTfRegular);
        mChartAthlete.setEntryLabelTextSize(15f);
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            setTitle("Hit Chart");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_chart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String action;
        Intent intent = getIntent();
        Championship championship= (Championship) intent.getSerializableExtra("championship");
        GameUser gameUser = (GameUser) intent.getSerializableExtra("game_user");
        GameOpponent gameOpponent = (GameOpponent) intent.getSerializableExtra("game_opponent");
        Athlete athleteOpponent = (Athlete) intent.getSerializableExtra("athlete_opponent");
        User athleteUser = (User) intent.getSerializableExtra("user");
        Intent intentSpecChart = new Intent(ChartActivity.this, SpecChartActivity.class);
        Intent intentMissesChart = new Intent(ChartActivity.this, MissChartActivity.class);
        intentSpecChart.putExtra("game_user", gameUser);
        intentSpecChart.putExtra("game_opponent", gameOpponent);
        intentSpecChart.putExtra("athlete_opponent", athleteOpponent);
        intentSpecChart.putExtra("user", athleteUser);

        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intentRegister = new Intent(ChartActivity.this, ScoutActivity.class);
                intentRegister.putExtra("championship", championship);
                intentRegister.putExtra("game_user", gameUser);
                intentRegister.putExtra("game_opponent", gameOpponent);
                intentRegister.putExtra("athlete_opponent", athleteOpponent);
                intentRegister.putExtra("user", athleteUser);
                startActivity(intentRegister);
                finish();
                break;

            case R.id.menu_service_chart:
                action = "service";
                intentSpecChart.putExtra("action", action);
                intentSpecChart.putExtra("championship", championship);
                intentSpecChart.putExtra("game_user", gameUser);
                intentSpecChart.putExtra("game_opponent", gameOpponent);
                intentSpecChart.putExtra("athlete_opponent", athleteOpponent);
                intentSpecChart.putExtra("user", athleteUser);
                startActivity(intentSpecChart);
                finish();
                break;

            case R.id.menu_reception_chart:
                action = "reception";
                intentSpecChart.putExtra("action", action);
                intentSpecChart.putExtra("championship", championship);
                intentSpecChart.putExtra("game_user", gameUser);
                intentSpecChart.putExtra("game_opponent", gameOpponent);
                intentSpecChart.putExtra("athlete_opponent", athleteOpponent);
                intentSpecChart.putExtra("user", athleteUser);
                intentSpecChart.putExtra("action", action);
                startActivity(intentSpecChart);
                finish();
                break;

            case R.id.menu_forehand_chart:
                action = "forehand";
                intentSpecChart.putExtra("action", action);
                intentSpecChart.putExtra("championship", championship);
                intentSpecChart.putExtra("game_user", gameUser);
                intentSpecChart.putExtra("game_opponent", gameOpponent);
                intentSpecChart.putExtra("athlete_opponent", athleteOpponent);
                intentSpecChart.putExtra("user", athleteUser);
                startActivity(intentSpecChart);
                finish();
                break;

            case R.id.menu_backhand_chart:
                action = "backhand";
                intentSpecChart.putExtra("action", action);
                intentSpecChart.putExtra("championship", championship);
                intentSpecChart.putExtra("game_user", gameUser);
                intentSpecChart.putExtra("game_opponent", gameOpponent);
                intentSpecChart.putExtra("athlete_opponent", athleteOpponent);
                intentSpecChart.putExtra("user", athleteUser);
                startActivity(intentSpecChart);
                finish();
                break;

            case R.id.menu_smash_chart:
                action = "smash";
                intentSpecChart.putExtra("action", action);
                intentSpecChart.putExtra("championship", championship);
                intentSpecChart.putExtra("game_user", gameUser);
                intentSpecChart.putExtra("game_opponent", gameOpponent);
                intentSpecChart.putExtra("athlete_opponent", athleteOpponent);
                intentSpecChart.putExtra("user", athleteUser);
                startActivity(intentSpecChart);
                finish();
                break;

            case R.id.menu_slice_chart:
                action = "slice";
                intentSpecChart.putExtra("action", action);
                intentSpecChart.putExtra("championship", championship);
                intentSpecChart.putExtra("game_user", gameUser);
                intentSpecChart.putExtra("game_opponent", gameOpponent);
                intentSpecChart.putExtra("athlete_opponent", athleteOpponent);
                intentSpecChart.putExtra("user", athleteUser);
                startActivity(intentSpecChart);
                finish();
                break;

            case R.id.menu_block_chart:
                action = "block";
                intentSpecChart.putExtra("action", action);
                intentSpecChart.putExtra("championship", championship);
                intentSpecChart.putExtra("game_user", gameUser);
                intentSpecChart.putExtra("game_opponent", gameOpponent);
                intentSpecChart.putExtra("athlete_opponent", athleteOpponent);
                intentSpecChart.putExtra("user", athleteUser);
                startActivity(intentSpecChart);
                finish();
                break;

            case R.id.menu_flick_chart:
                action = "flick";
                intentSpecChart.putExtra("action", action);
                intentSpecChart.putExtra("championship", championship);
                intentSpecChart.putExtra("game_user", gameUser);
                intentSpecChart.putExtra("game_opponent", gameOpponent);
                intentSpecChart.putExtra("athlete_opponent", athleteOpponent);
                intentSpecChart.putExtra("user", athleteUser);
                startActivity(intentSpecChart);
                finish();
                break;

            case R.id.menu_lob_chart:
                action = "lob";
                intentSpecChart.putExtra("action", action);
                intentSpecChart.putExtra("championship", championship);
                intentSpecChart.putExtra("game_user", gameUser);
                intentSpecChart.putExtra("game_opponent", gameOpponent);
                intentSpecChart.putExtra("athlete_opponent", athleteOpponent);
                intentSpecChart.putExtra("user", athleteUser);
                startActivity(intentSpecChart);
                finish();
                break;

            case R.id.menu_misses_chart:
                intentSpecChart.putExtra("championship", championship);
                intentMissesChart.putExtra("game_user", gameUser);
                intentMissesChart.putExtra("game_opponent", gameOpponent);
                intentMissesChart.putExtra("athlete_opponent", athleteOpponent);
                intentMissesChart.putExtra("user", athleteUser);
                startActivity(intentMissesChart);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    private void setData() {

        ArrayList<PieEntry> entries = new ArrayList<>();

        Intent intent = getIntent();
        GameUser gameUser = (GameUser) intent.getSerializableExtra("game_user");

        ArrayList<Integer>listTechniques = new ArrayList<>();
        listTechniques.add(gameUser.getService());
        listTechniques.add(gameUser.getReception());
        listTechniques.add(gameUser.getForehand());
        listTechniques.add(gameUser.getBackhand());
        listTechniques.add(gameUser.getSmash());
        listTechniques.add(gameUser.getSlice());
        listTechniques.add(gameUser.getBlock());
        listTechniques.add(gameUser.getFlick());
        listTechniques.add(gameUser.getLob());

        for (int i = 0; i < listTechniques.size() ; i++) {
            if(listTechniques.get(i) > 0){
                entries.add(new PieEntry((float)  listTechniques.get(i)/gameUser.getTotal() * 100, mTechniques[i]));
            }
        }

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(15f);
        data.setValueTextColor(Color.BLACK);
        data.setValueTypeface(mTfLight);
        mChartAthlete.setData(data);

        // undo all highlights
        mChartAthlete.highlightValues(null);

        mChartAthlete.invalidate();
    }

    private SpannableString generateCenterSpannableText() {
        SpannableString s = new SpannableString("SCOUTChart\n Athlete performance");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 12, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 20, s.length(), 0);
        return s;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getY() + ", index: " + h.getX()
                        + ", DataSet index: " + h.getDataSetIndex());
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }
}
