package com.example.practive;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.example.practive.dao.ProductDao;
import com.example.practive.entity.Product;
import com.example.practive.utilss.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity {

    ProductDao productDao ;
    private AppDatabase appDatabase ;
    List<Product> productList;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        innitView();
        innitData();
    }

    private void innitView() {
        productList = new ArrayList<>();
        recyclerView = findViewById(R.id.listViewFunction);
        recyclerView.setAdapter(new ListProductAdapter(productList,this));
        appDatabase  = AppDatabase.buildDatabaseInstance( this, "android_exam" , false);
    }

    private void innitData() {
        productDao = appDatabase.productDao() ;
        List<Product> list = productDao.getlAll();
        productList.addAll(list);
        ((ListProductAdapter) recyclerView.getAdapter()).notifyDataSetChanged();
    }

}