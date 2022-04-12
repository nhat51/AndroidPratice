package com.example.practive;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.practive.dao.ProductDao;
import com.example.practive.entity.Product;
import com.example.practive.utilss.AppDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button addButton,viewBtn ;
    EditText productName ;
    EditText quantity ;
    ProductDao productDao ;
    private AppDatabase appDatabase ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appDatabase  = AppDatabase.buildDatabaseInstance( this, "android_exam" , false);
        innitView();
        innitData();
    }

    public void innitView(){
        addButton = findViewById(R.id.btnAddProduct);
        viewBtn = findViewById(R.id.btnList);
        productName = findViewById(R.id.productName);
        quantity = findViewById(R.id.productQuantity);
    }

    public void innitData(){
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name  = String.valueOf(productName.getText());
                int quantityValue =  Integer.parseInt(String.valueOf(quantity.getText()));
                addProduct(new Product(name , quantityValue));
                Intent intent = new Intent(MainActivity.this , ViewActivity.class);
                startActivity(intent);
            }
        });
        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , ViewActivity.class);
                startActivity(intent);
            }
        });
    }
    public void addProduct(Product p ){
        productDao = appDatabase.productDao() ;
        productDao.add(p);
        Toast.makeText(this, "inserted", Toast.LENGTH_SHORT).show();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getAllProduct(){
        productDao = appDatabase.productDao() ;
        List<Product> songs = productDao.getlAll();
        songs.stream().forEach(s -> {
                    Toast.makeText(this, s.getName(), Toast.LENGTH_SHORT).show();
                }
        );
    }
}