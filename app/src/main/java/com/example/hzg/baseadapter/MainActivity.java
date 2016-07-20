package com.example.hzg.baseadapter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ListView listView = (ListView) findViewById(R.id.listView);
        List<ItemBean> dataList = new ArrayList<>();
        // 创建假数据
        for (int i = 0; i < 20; i++) {
            dataList.add(new ItemBean(
                    R.mipmap.ic_launcher,
                    "我是标题" + i,
                    "我是内容" + i));
        }
        // 设置适配器
        listView.setAdapter(new MyAdapter(this, dataList));
    }
}
