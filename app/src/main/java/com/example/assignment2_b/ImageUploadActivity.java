package com.example.assignment2_b;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ImageUploadActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);

        Button selectImageButton = findViewById(R.id.selectImageButton);
        ImageView imagePreview = findViewById(R.id.imagePreview);
        Button uploadImageButton = findViewById(R.id.uploadImageButton);

        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();
            }
        });

        uploadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    private void uploadImage() {
        if (imageUri != null) {
            // Implement image upload logic to Firebase Storage or your chosen storage service here
        } else {
            // Handle the case when no image is selected
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            ImageView imagePreview = findViewById(R.id.imagePreview);
            imagePreview.setImageURI(imageUri);
            imagePreview.setVisibility(View.VISIBLE);

            // Show the "Upload Image" button
            Button uploadImageButton = findViewById(R.id.uploadImageButton);
            uploadImageButton.setVisibility(View.VISIBLE);
        }
    }
}
