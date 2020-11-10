package prime.tech.menu;


import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.chart.common.listener.Event;
import com.anychart.chart.common.listener.ListenersInterface;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;

import java.util.ArrayList;
import java.util.List;

public class Analysis extends AppCompatActivity {
   public void onCreate(Bundle saveInstanceState){
       super.onCreate(saveInstanceState);
       setContentView(R.layout.activity_analysis);

       AnyChartView anyChartView=findViewById(R.id.any_chart_view);
       anyChartView.setProgressBar(findViewById(R.id.progressBar));

       Pie pie= AnyChart.pie();
       pie.setOnClickListener(new ListenersInterface.OnClickListener() {
           @Override
           public void onClick(Event event) {
               Toast.makeText(Analysis.this,event.getData().get("x")+":"+event.getData().get("value"),Toast.LENGTH_LONG).show();
           }
       });
       List<DataEntry> data=new ArrayList<>();
       data.add(new ValueDataEntry("KBQ743A", 10540));
       data.add(new ValueDataEntry("KAC990F", 12500));
       data.add(new ValueDataEntry("KBG672S", 8000));
       data.add(new ValueDataEntry("KBA001W", 9873));
       data.add(new ValueDataEntry("KBH056D", 11050));

       pie.data(data);

       pie.title("Vehicals carrying From Town to Kasarani");

       pie.labels().position("outside");

       pie.legend().title().enabled(true);
       pie.legend().title()
               .text("Matatu")
               .padding(0d, 0d, 10d, 0d);

       pie.legend()
               .position("center-bottom")
               .itemsLayout(LegendLayout.VERTICAL)
               .align(Align.CENTER);

       anyChartView.setChart(pie);

   }
}
