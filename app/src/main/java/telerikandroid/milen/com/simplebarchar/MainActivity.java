package telerikandroid.milen.com.simplebarchar;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.animation.RotateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements ChartFragment.ChartMaker{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new ChartFragment();
        fragmentTransaction.replace(R.id.fragment_place, fragment);
        fragmentTransaction.commit();



        makeChart(new String[] {"cat_1:50","cat_2:30", "cat_3:80", "cat_4:20", "cat_5:100"});
    }

    @Override
    public void makeChart(String[] values) {
        String DIVIDER = ":";

        LinearLayout llChart = (LinearLayout) findViewById(R.id.llChart);
        int layoutWeight = values.length;
        llChart.setWeightSum(layoutWeight);
        llChart.setBackgroundResource(R.color.colorPrimaryDark);

        /*for(int i = 0; i < layoutWeight; i++){
            //if  I decide  to add textView.settId("category" + i);
        }*/

        for (String value : values) {
            String [] categoryHeightArr = value.split(DIVIDER);
            String category = categoryHeightArr[0];
            int height = Integer.parseInt(categoryHeightArr[1]);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                         LinearLayout.LayoutParams.MATCH_PARENT, makeDP(height) , 1.0f);
            int chart_element_margins = (int)getResources().getDimension(R.dimen.margin_chart_element);
            layoutParams.setMargins(0,0,chart_element_margins,0);
            TextView textView = new TextView(this);
            textView.setLayoutParams(layoutParams);
            textView.setText(category);
            textView.setGravity(Gravity.CENTER | Gravity.BOTTOM);
            textView.setBackgroundResource(R.drawable.chart_element_drawable);


            llChart.addView(textView);
        }

    }

    private int makeDP(int pixels){
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        return (int) (displayMetrics.density * pixels);
    }
}
