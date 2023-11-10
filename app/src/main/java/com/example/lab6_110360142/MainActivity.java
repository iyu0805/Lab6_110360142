package com.example.lab6_110360142;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    class Data {
        int photo; //圖片id
        String name; //名稱
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {  //整個程式最先執行的地方！！！
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //使用 Spinner，透過客製化 layout 與 myAdapter 顯示 transData(自製的資料)
        //Java 物件導向的特性，每個陣列物件都必須要產生實體
        String[] transNameArray = new String[]{"腳踏車","機車","汽車","巴士","飛機","輪船"};
        int[] transPhotoIdArray = new int[]{R.drawable.trans1, R.drawable.trans2,
                R.drawable.trans3, R.drawable.trans4, R.drawable.trans5, R.drawable.trans6};

        //Step1 建立資料來源，用陣列方式宣告自行設計的類別，並為陣列的每個內容填入要顯示的資料
        Data[] transData = new Data[transNameArray.length];
        for (int i = 0; i < transData.length; i++) {
            transData[i] = new Data();
            transData[i].name = transNameArray[i];
            transData[i].photo = transPhotoIdArray[i];
        }

        //Step2 建立 myAdapter 物件，並放入 transData 與 trans_list.xml
        MyAdapter transAdapter = new MyAdapter(transData, R.layout.trans_list);
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(transAdapter);

        //---------------------------------------------------
        //使用 GridView，透過客製化 layout 與 myAdapter 顯示 cubeeData (自製的資料)
        String[] cubeeNameArray = new String[]{"哭哭","發抖","再見","生氣","昏倒","竊笑","很棒","你好","驚嚇","大笑"};
        int[] cubeePhotoIdArray = new int[]{R.drawable.cubee1, R.drawable.cubee2,
                R.drawable.cubee3, R.drawable.cubee4,
                R.drawable.cubee5, R.drawable.cubee6,
                R.drawable.cubee7, R.drawable.cubee8,
                R.drawable.cubee9,R.drawable.cubee10};
        //Step1：建立資料來源，用陣列方式宣告自行設計的類別，將陣列的每個內容填入要顯示的資料
        Data[] cubeeData = new Data[cubeeNameArray.length];
        for (int i = 0; i < cubeeData.length; i++) {
            cubeeData[i] = new Data();
            cubeeData[i].name = cubeeNameArray[i];
            cubeeData[i].photo = cubeePhotoIdArray[i];
        }

        //Step2：建立 myAdapter 物件，並放入 cubeeData 與
        MyAdapter cubeeAdapter = new MyAdapter(cubeeData, R.layout.cubee_list);
        GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(cubeeAdapter);
        gridView.setNumColumns(3); //Step3：連接 GridView 元件，並連結 myAdapter


        //---------------------------------------------------
        //使用 ListView，透過 Android SDK 提供的現成 layout 與 ArrayAdapter 顯示messageArray(字串陣列)
        //Step1：建立資料來源
        String[] messageArray = new String[]{"訊息1", "訊息2", "訊息3", "訊息4", "訊息5", "訊息6"};
        //Step2：建立 Adapter 物件，並放入字串與 simple_list_item_1.xml
        ArrayAdapter<String> messageAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, messageArray);
        //Step3：連接 ListView 元件，並連結 Adapter
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(messageAdapter);
    }


    //---------------------------------------------------
    //建立 myAdapter 來顯示 Spinner 及 GridView 的客製化畫面。
    public class MyAdapter extends BaseAdapter { //繼承BaseAdapter
        private MainActivity.Data[] data; //保存在myAdapter之中的資料夾
        private int view; //保存在myAdapter之中的畫面

        //透過建構子儲存資料來源與畫面到myAdapater之中
        public MyAdapter (MainActivity.Data[] data, int view) {
            this.data = data;
            this.view = view;
        }

        //回傳資料來源筆數
        @Override
        public int getCount() { return data.length; }
        //回傳資料項目內容
        @Override
        public Object getItem(int position) { return data[position]; }
        //回傳某筆項目id
        @Override
        public long getItemId(int position) { return 0; }

        //取得畫面元件-------------------------------------
        @Override               //需要顯示的項目編號
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(view, parent, false); //取得XML畫面
            TextView name = convertView.findViewById(R.id.name);            //連接 TextView 元件
            name.setText(data[position].name);                              //根據 position 把字串顯示到 TextView
            ImageView imageView = convertView.findViewById(R.id.imageView); //連接 ImageView 元件
            imageView.setImageResource(data[position].photo);               //根據 position 把圖片顯示到 ImageView

            return  convertView;
        }
    }


}



