package com.jay.leejay;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private ArrayList<ListViewItem> arrayList1 = new ArrayList<ListViewItem>();

    @Override
    public int getCount() {
        return arrayList1.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList1.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        Context context = viewGroup.getContext();
        //뷰가 여기서 convertView라고 한다.
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //첫번째 인수는 어떤 xml 파일인지 지정한다. 두번째 인수는 어디서 붙일 것이냐 지정
            convertView = inflater.inflate(R.layout.listview_item, viewGroup, false);

            TextView titleTextView = convertView.findViewById(R.id.textViewEmail);
            TextView descTextView = convertView.findViewById(R.id.textViewPwd);

            //ListViewItem에서 포지션에 위치한 데이터 참조
            ListViewItem listViewItem = arrayList1.get(i);

            //각 item내에서 각 위젯에 데이터 반영
            titleTextView.setText(listViewItem.getEmail());
            descTextView.setText(listViewItem.getPwd());

        }
        return convertView;
    }
}
