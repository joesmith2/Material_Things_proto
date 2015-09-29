package com.example.josephsmith.materialthings;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;


public class FindMaterial extends ActionBarActivity {

    private Scene timberScene, metalScene, glassScene, ceramicScene, polymersScene;
    private Transition transition;
    private Boolean listinflated;
    private ImageView image;
    private ScrollView scrollView;
    private View textView1, textView2;
    public final static String SEARCH_TERM = "com.mycompany.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_material);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        image = (ImageView) findViewById(R.id.image1);
        scrollView = (ScrollView) findViewById(R.id.scrollview1);
        textView1 = (View) findViewById(R.id.textBox1);
        textView2 = (View) findViewById(R.id.textBox2);

        listinflated = false;

        final ViewTreeObserver.OnScrollChangedListener onScrollChangedListener = new
                ViewTreeObserver.OnScrollChangedListener() {

                    @Override
                    public void onScrollChanged() {
                        if (scrollView.getScrollY() > 30){
                            if (textView1.getVisibility()== View.VISIBLE)
                            //make low TextView invisible if it isn't already visible
                            textView1.setVisibility((View.INVISIBLE));
                            textView2.setVisibility(View.INVISIBLE);
                        }else{
                            if (textView1.getVisibility() == View.INVISIBLE)
                            textView1.setVisibility(View.VISIBLE);
                            textView2.setVisibility(View.VISIBLE);
                        }
                    }
                };

            scrollView.setOnTouchListener(new View.OnTouchListener() {
            private ViewTreeObserver observer;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (observer == null) {
                    observer = scrollView.getViewTreeObserver();
                    observer.addOnScrollChangedListener(onScrollChangedListener);
                }
                else if (!observer.isAlive()) {
                    observer.removeOnScrollChangedListener(onScrollChangedListener);
                    observer = scrollView.getViewTreeObserver();
                    observer.addOnScrollChangedListener(onScrollChangedListener);
                }

                return false;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_find_material, menu);
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

    @Override
    public void onBackPressed () {


        if (listinflated) {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        } else {
            super.onBackPressed();
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
        }
    }

    public void inflateTimber(View v) {
//        Log.d("I'm talking to you, Joe", "y = " +y );

        RelativeLayout baseLayout = (RelativeLayout) findViewById(R.id.base);
        ViewGroup timberViews = (ViewGroup) getLayoutInflater().inflate(R.layout.material_list, baseLayout, false);
        timberScene = new Scene(baseLayout, timberViews);

        transition = new AutoTransition();
        transition.setDuration(300);
        transition.setInterpolator(new AccelerateDecelerateInterpolator());

        image = (ImageView) findViewById(R.id.image1);
        image.setImageResource(R.drawable.timber);
        image.setTop(230 - scrollView.getScrollY());
        image.setVisibility(image.VISIBLE);
        TransitionManager.go(timberScene, transition);

        listinflated = true;
    }

    public void inflateMetal (View v) {
        RelativeLayout baseLayout = (RelativeLayout) findViewById(R.id.base);
        ViewGroup metalViews = (ViewGroup) getLayoutInflater().inflate(R.layout.metal_list, baseLayout, false);
        metalScene = new Scene(baseLayout, metalViews);

        transition = new AutoTransition();
        transition.setDuration(300);
        transition.setInterpolator(new AccelerateDecelerateInterpolator());

        image = (ImageView) findViewById(R.id.image1);
        image.setImageResource(R.drawable.metal);
        image.setTop(550 - scrollView.getScrollY());
        image.setVisibility(image.VISIBLE);
        TransitionManager.go(metalScene, transition);

        listinflated = true;

        ParseQueryAdapter<ParseObject> adapter = new ParseQueryAdapter<>(this, new ParseQueryAdapter.QueryFactory<ParseObject>(){
            public ParseQuery<ParseObject> create(){
                //create parse query here
                ParseQuery query = new ParseQuery("Arup_Material_Samples");
                query.whereEqualTo("materialType", "Metal");
                return query;
            }
        });
        adapter.setTextKey("materialName");

        ListView listView = (ListView) findViewById(R.id.metalListView);
        listView.setAdapter(adapter);
    }


    public void inflateGlass (View v) {
        RelativeLayout baseLayout = (RelativeLayout) findViewById(R.id.base);
        ViewGroup glassViews = (ViewGroup) getLayoutInflater().inflate(R.layout.glass_list, baseLayout, false);
        glassScene = new Scene(baseLayout, glassViews);

        transition = new AutoTransition();
        transition.setDuration(300);
        transition.setInterpolator(new AccelerateDecelerateInterpolator());

        image = (ImageView) findViewById(R.id.image1);
        image.setImageResource(R.drawable.glass);
        image.setTop(870 - scrollView.getScrollY());
        image.setVisibility(image.VISIBLE);
        TransitionManager.go(glassScene, transition);

        listinflated = true;
    }

    public void inflateCeramic (View v) {
        RelativeLayout baseLayout = (RelativeLayout) findViewById(R.id.base);
        ViewGroup ceramicViews = (ViewGroup) getLayoutInflater().inflate(R.layout.ceramic_list, baseLayout, false);
        ceramicScene = new Scene(baseLayout, ceramicViews);

        transition = new AutoTransition();
        transition.setDuration(300);
        transition.setInterpolator(new AccelerateDecelerateInterpolator());

        image = (ImageView) findViewById(R.id.image1);
        image.setImageResource(R.drawable.ceramic);
        image.setTop(1190 - scrollView.getScrollY());
        image.setVisibility(image.VISIBLE);
        TransitionManager.go(ceramicScene, transition);

        listinflated = true;
    }

    public void inflatePolymers (View v) {
        RelativeLayout baseLayout = (RelativeLayout) findViewById(R.id.base);
        ViewGroup polymersViews = (ViewGroup) getLayoutInflater().inflate(R.layout.polymers_list, baseLayout, false);
        polymersScene = new Scene(baseLayout, polymersViews);

        transition = new AutoTransition();
        transition.setDuration(300);
        transition.setInterpolator(new AccelerateDecelerateInterpolator());

        image = (ImageView) findViewById(R.id.image1);
        image.setImageResource(R.drawable.polymers);
        image.setTop(1510 - scrollView.getScrollY());
        image.setVisibility(image.VISIBLE);
        TransitionManager.go(polymersScene, transition);

        listinflated = true;
    }

    public void searchName (View view){
            Intent intent = new Intent(this, MaterialSearch.class);
            EditText searchBox = (EditText) findViewById(R.id.nameText);
            String searchTerm = searchBox.getText().toString();

            intent.putExtra(SEARCH_TERM, searchTerm);
            startActivity(intent);
    }
    public void searchNumber (View view) {
        //do nothing
    }

}


