package com.sample.tabbarcontroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.tab.bar.controller.TabBarBuilder;
import com.tab.bar.controller.TabBarController;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabBarBuilder.withDataSourceType(Data.class)
                .withTabsCount(4)
                .withIndicatorsColor(android.R.color.holo_blue_dark)
                .withLabelsColor(android.R.color.holo_blue_dark)
                .withTab(0)
                .withItem(new Data("tab-1"))
                .withIcon(R.drawable.ic_dashboard_menu)
                .withLabel(R.string.data_one)
                .withOnClick(this::displayToast)
                .add()
                .withTab(1)
                .withItem(new Data("tab-2"))
                .withIcon(R.drawable.ic_dashboard_menu)
                .withLabel(R.string.data_two)
                .withOnClick(this::displayToast)
                .add()
                .withTab(2)
                .withItem(new Data("tab-3"))
                .withIcon(R.drawable.ic_dashboard_menu)
                .withLabel(R.string.data_three)
                .withOnClick(this::displayToast)
                .add()
                .withTab(3)
                .withItem(new Data("tab-4"))
                .withIcon(R.drawable.ic_dashboard_menu)
                .withLabel(R.string.data_four)
                .withOnClick(this::displayToast)
                .add()
                .draw(tabBarController());


    }

    private TabBarController tabBarController() {
        return findViewById(R.id.bar);
    }

    private void displayToast(int index, Data data) {
        Toast.makeText(this,"["+index+"] "+data.value,Toast.LENGTH_LONG).show();
    }
}


class Data{

    final String value;

    Data(String value) {
        this.value = value;
    }
}
