package com.example.assignment2_b;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    SearchResponseViewModel sViewModel;
    ImageViewModel imageViewModel;
    ErrorViewModel errorViewModel;
    Button loadImage;
    ImageView picture;
    ProgressBar progressBar;
    EditText searchKey;
    RecyclerView imageRecyclerView; // Add RecyclerView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sViewModel = new ViewModelProvider(this).get(SearchResponseViewModel.class); // Simplify ViewModel instantiation
        imageViewModel = new ViewModelProvider(this).get(ImageViewModel.class);
        errorViewModel = new ViewModelProvider(this).get(ErrorViewModel.class);

        loadImage = findViewById(R.id.loadImage);
        picture = findViewById(R.id.picureId);
        progressBar = findViewById(R.id.progressBarId);
        searchKey = findViewById(R.id.inputSearch);
        imageRecyclerView = findViewById(R.id.imageRecyclerView); // Initialize RecyclerView

        picture.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);

        // Set up RecyclerView
        imageRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ImageAdapter imageAdapter = new ImageAdapter(); // Create an adapter for the RecyclerView
        imageRecyclerView.setAdapter(imageAdapter);

        loadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picture.setVisibility(View.INVISIBLE);
                String searchValues = searchKey.getText().toString();
                APISearchThread searchThread = new APISearchThread(searchValues, MainActivity.this, sViewModel, imageAdapter); // Pass the adapter
                progressBar.setVisibility(View.VISIBLE);
                searchThread.start();
            }
        });

        sViewModel.response.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(MainActivity.this, "Search Complete", Toast.LENGTH_LONG).show();
                // Image retrieval is now handled by the adapter
            }
        });

        errorViewModel.errorCode.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
}
