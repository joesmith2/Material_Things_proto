package com.example.josephsmith.materialthings;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;


public class MaterialSearch extends ActionBarActivity {

    TextView textView1, searchTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_search);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        Intent intent = getIntent();
        String searchTerm = intent.getStringExtra(FindMaterial.SEARCH_TERM);
        textView1 = (TextView) findViewById(R.id.nameText);
        searchTitle = (TextView) findViewById(R.id.searchTitle);

        textView1.setText("" + searchTerm);
        searchMaterials(searchTerm);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_material_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void searchMaterials(final String searchTerm) {

        //search material database for searchTerm and set it to the listView
        ParseQueryAdapter<ParseObject> adapter = new ParseQueryAdapter<>(this, new ParseQueryAdapter.QueryFactory<ParseObject>(){
            public ParseQuery<ParseObject> create(){
                //create parse query here
                ParseQuery query = new ParseQuery("Arup_Material_Samples");
                query.whereEqualTo("materialName", searchTerm);
                return query;
            }
        });
        adapter.setTextKey("materialName");

        ListView listView = (ListView) findViewById(R.id.listView1);
        listView.setAdapter(adapter);
    }
}
