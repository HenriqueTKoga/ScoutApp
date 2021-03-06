package scoutapp.com.br.scoutapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
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
import scoutapp.com.br.scoutapp.model.GameOpponent;
import scoutapp.com.br.scoutapp.model.GameUser;
import scoutapp.com.br.scoutapp.model.User;

public class MissChartActivity extends ChartBase implements SeekBar.OnSeekBarChangeListener,
        OnChartValueSelectedListener {

    private PieChart mChartAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miss_chart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupActionBar();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // -----Miss Chart-----
        mChartAction = (PieChart) findViewById(R.id.miss_chart);
        mChartAction.setUsePercentValues(true);
        mChartAction.setDescription("");
        mChartAction.setExtraOffsets(5, 10, 5, 5);

        mChartAction.setDragDecelerationFrictionCoef(0.95f);

        mChartAction.setCenterTextTypeface(mTfLight);
        mChartAction.setCenterText(generateCenterSpannableText());

        mChartAction.setDrawHoleEnabled(false);
        mChartAction.setHoleColor(Color.WHITE);

        mChartAction.setTransparentCircleColor(Color.WHITE);
        mChartAction.setTransparentCircleAlpha(110);

        mChartAction.setHoleRadius(58f);
        mChartAction.setTransparentCircleRadius(61f);

        mChartAction.setDrawCenterText(false);

        mChartAction.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChartAction.setRotationEnabled(true);
        mChartAction.setHighlightPerTapEnabled(true);


        // add a selection listener
        mChartAction.setOnChartValueSelectedListener(this);

        setData();

        mChartAction.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChartAthlete.spin(2000, 0, 360);

        // entry label styling
        mChartAction.setEntryLabelColor(Color.BLACK);
        mChartAction.setEntryLabelTypeface(mTfRegular);
        mChartAction.setEntryLabelTextSize(12f);
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
//            action = action.toLowerCase();
//            action = Character.toString(action.charAt(0)).toUpperCase()+action.substring(1);
            setTitle("Misses Chart");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chart_misses, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = getIntent();
        GameUser gameUser = (GameUser) intent.getSerializableExtra("game_user");
        GameOpponent gameOpponent = (GameOpponent) intent.getSerializableExtra("game_opponent");
        Athlete athleteOpponent = (Athlete) intent.getSerializableExtra("athlete_opponent");
        User athleteUser = (User) intent.getSerializableExtra("user");
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intentChart = new Intent(MissChartActivity.this, ChartActivity.class);
                intentChart.putExtra("game_user", gameUser);
                intentChart.putExtra("game_opponent", gameOpponent);
                intentChart.putExtra("athlete_opponent", athleteOpponent);
                intentChart.putExtra("user", athleteUser);
                startActivity(intentChart);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

//        tvX.setText("" + (mSeekBarX.getProgress()));
//        tvY.setText("" + (mSeekBarY.getProgress()));
//
//        setData(mSeekBarX.getProgress(), mSeekBarY.getProgress());
    }

    private void setData() {

        ArrayList<PieEntry> entries = new ArrayList<>();

        Intent intent = getIntent();
        GameOpponent gameOpponent = (GameOpponent) intent.getSerializableExtra("game_opponent");

        ArrayList<Integer>listTechniques = new ArrayList<>();
        listTechniques.add(gameOpponent.getService());
        listTechniques.add(gameOpponent.getReception());
        listTechniques.add(gameOpponent.getForehand());
        listTechniques.add(gameOpponent.getBackhand());
        listTechniques.add(gameOpponent.getSmash());
        listTechniques.add(gameOpponent.getSlice());
        listTechniques.add(gameOpponent.getBlock());
        listTechniques.add(gameOpponent.getFlick());
        listTechniques.add(gameOpponent.getLob());

        for (int i = 0; i < listTechniques.size() ; i++) {
            if(listTechniques.get(i) > 0){
                entries.add(new PieEntry((float)  listTechniques.get(i)/gameOpponent.getTotal() * 100, mTechniques[i]));
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
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.BLACK);
        data.setValueTypeface(mTfLight);
        mChartAction.setData(data);

        // undo all highlights
        mChartAction.highlightValues(null);

        mChartAction.invalidate();
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
