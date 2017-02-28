package com.apps.shami.marketprice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Santosh on 1/7/2015.
 */
public class ShowProducts extends Activity {

    static final String[] elements1 = new String[] { "Apple", "Avocado", "Banana",
            "Blueberry", "Coconut", "Durian", "Guava", "Kiwifruit",
            "Jackfruit", "Mango", "Olive", "Pear", "Sugar-apple" };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list_view);

        ListView listView = (ListView) findViewById(R.id.productlistView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, elements1);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String text = ((TextView)(view.findViewById(android.R.id.text1))).getText().toString();
               // Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ShowProducts.this, MainActivity.class);
                intent.putExtra("product_key", text);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(ShowProducts.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
