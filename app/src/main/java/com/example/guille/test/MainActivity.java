package com.example.guille.test;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.guille.test.io.EdealsApiAdacter;
import com.example.guille.test.io.model.DealResponse;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends AppCompatActivity implements Callback<List<DealResponse>> {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ImageButton bSearch;
    private EditText etSearch;
    private Dialog d;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        d= new Dialog(this);
        d.setTitle("Cargando..");

        etSearch = (EditText)findViewById(R.id.et_search);
        bSearch = (ImageButton) findViewById(R.id.b_search);
        bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String desc = etSearch.getText().toString();
                if (!"".equalsIgnoreCase(desc)) {
                    EdealsApiAdacter.getApiService().getEdealsByDesc(desc, MainActivity.this);
                    d.show();
                }
            }
        });

        recycler =(RecyclerView) findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);

        d.show();

        EdealsApiAdacter.getApiService().getEdeals(this);
    }
    public void ejecutar(){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void success(List<DealResponse> deals, Response response) {

        adapter = new DealAdacter(deals);
        recycler.setAdapter(adapter);
        d.dismiss();
    }

    @Override
    public void failure(RetrofitError error) {
        error.printStackTrace();

    }
}
