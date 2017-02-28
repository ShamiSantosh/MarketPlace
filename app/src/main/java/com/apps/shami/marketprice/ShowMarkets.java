package com.apps.shami.marketprice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Santosh on 1/7/2015.
 */
public class ShowMarkets extends Activity {

    static final String[] elements = new String[] { "Karnataka", "Tamilnadu", "Maharashtra",
            "Andhra Pradesh", "Kerala","Gujarat","Rajasthan" };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list_view);

        ListView listView = (ListView) findViewById(R.id.marketlistView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, elements);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String text = ((TextView) (view.findViewById(android.R.id.text1))).getText().toString();
               // Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ShowMarkets.this, MainActivity.class);
                intent.putExtra("market_key", text);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onBackPressed() {

        Intent intent = new Intent(ShowMarkets.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
