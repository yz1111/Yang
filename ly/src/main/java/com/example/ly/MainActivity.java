package com.example.ly;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inData();
             XListView lv= (XListView) findViewById(R.id.lv);

        MyAdapter adapter=new MyAdapter();
        lv.setAdapter(adapter);
    }

    private void inData() {
        list = new ArrayList<>();
        for (int i=0;i<10;i++)
        {
            list.add("亮了"+i);
        }

    }


    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            TextView tv=new TextView(MainActivity.this);
            tv.setText(list.get(position));
            return tv;
        }
    }
}
