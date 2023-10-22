package com.example.assignment2_b;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_SINGLE_COLUMN = 1;
    private static final int VIEW_TYPE_DOUBLE_COLUMN = 2;

    private List<SearchItem> searchItems = new ArrayList<>();
    private Context context;

    public ImageAdapter(Context context) {
        this.context = context;
    }

    // Add a method to update the adapter's dataset with search items
    public void updateData(List<SearchItem> searchItems) {
        this.searchItems = searchItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == VIEW_TYPE_SINGLE_COLUMN) {
            View view = inflater.inflate(R.layout.item_image_single_column, parent, false);
            return new SingleColumnViewHolder(view);
        } else if (viewType == VIEW_TYPE_DOUBLE_COLUMN) {
            View view = inflater.inflate(R.layout.item_image_double_column, parent, false);
            return new DoubleColumnViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SingleColumnViewHolder) {
            SingleColumnViewHolder singleColumnHolder = (SingleColumnViewHolder) holder;
            singleColumnHolder.bind(position);
        } else if (holder instanceof DoubleColumnViewHolder) {
            DoubleColumnViewHolder doubleColumnHolder = (DoubleColumnViewHolder) holder;
            doubleColumnHolder.bind(position);
        }
    }

    @Override
    public int getItemCount() {
        return searchItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return searchItems.get(position).getViewType();
    }

    // ViewHolder for single-column view
    private class SingleColumnViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ProgressBar progressBar;

        SingleColumnViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }

        void bind(int position) {
            SearchItem searchItem = searchItems.get(position);

            // Load and display image using searchItem.getImageUrl()
            // You can use an image loading library like Glide or Picasso here

            // For demonstration, we simulate loading by delaying execution
            simulateImageLoading(progressBar, imageView);
        }
    }

    // ViewHolder for double-column view
    private class DoubleColumnViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView1, imageView2;
        ProgressBar progressBar1, progressBar2;

        DoubleColumnViewHolder(View itemView) {
            super(itemView);
            imageView1 = itemView.findViewById(R.id.imageView1);
            imageView2 = itemView.findViewById(R.id.imageView2);
            progressBar1 = itemView.findViewById(R.id.progressBar1);
            progressBar2 = itemView.findViewById(R.id.progressBar2);
        }

        void bind(int position) {
            SearchItem searchItem = searchItems.get(position);

            // Load and display images using searchItem.getImageUrl()
            // You can use an image loading library like Glide or Picasso here

            // For demonstration, we simulate loading by delaying execution
            simulateImageLoading(progressBar1, imageView1);
            simulateImageLoading(progressBar2, imageView2);
        }
    }

    // Simulate image loading with a delay
    private void simulateImageLoading(final ProgressBar progressBar, final ImageView imageView) {
        progressBar.setVisibility(View.VISIBLE);

        // Simulate a delay for loading images (you should use an image loading library)
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                // Set the loaded image to the ImageView
                imageView.setImageResource(R.drawable.placeholder); // Placeholder image resource
            }
        }, 3000); // 3 seconds delay (adjust as needed)
    }
}
