package com.example.weixin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Map;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private List<Map<String, Object>> data;

    public MyAdapter(Context context, List<Map<String, Object>> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context)
                .load(data.get(position).get("key1").toString())
                .into(holder.imageView1);
        holder.textView5.setText(data.get(position).get("key2").toString());
        holder.textView6.setText(data.get(position).get("key3").toString());
        holder.textView7.setText(data.get(position).get("key4").toString());
//        holder.detail_text.setText(data.get(position).get("key4").toString());
//        holder.Clicked.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            LinearLayout detail = view.findViewById(R.id.detail);
            TextView cctMsg = view.findViewById(R.id.cctMsg);
            if (detail.getVisibility() == View.VISIBLE) {
                detail.setVisibility(View.GONE);
            } else {
                detail.setVisibility(View.VISIBLE);
            }

            if (cctMsg.getVisibility() == View.VISIBLE) {
                cctMsg.setVisibility(View.INVISIBLE);
            } else {
                cctMsg.setVisibility(View.VISIBLE);
            }
        }
    };


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView1;
        TextView textView5;
        TextView textView6;
        TextView textView7;

        //        LinearLayout Clicked;
//        TextView detail_text;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView1 = itemView.findViewById(R.id.photo);
            textView5 = itemView.findViewById(R.id.cctName);
            textView6 = itemView.findViewById(R.id.time);
            textView7 = itemView.findViewById(R.id.cctMsg);
//            Clicked = itemView.findViewById(R.id.Clicked);
//            detail_text = itemView.findViewById(R.id.detail_text);
        }
    }


}
