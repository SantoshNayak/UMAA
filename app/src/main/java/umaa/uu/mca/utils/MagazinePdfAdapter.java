package umaa.uu.mca.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import umaa.uu.mca.activities.R;

public class MagazinePdfAdapter extends RecyclerView.Adapter<MagazinePdfAdapter.ViewHolder> {

    RecyclerView recyclerView;
    Context context;
    ArrayList<String> items=new ArrayList<>();
    ArrayList<String> urls=new ArrayList<>();

    public MagazinePdfAdapter(RecyclerView recyclerView, Context context, ArrayList<String> items,ArrayList<String> urls) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.items = items;
        this.urls=urls;
    }

    public MagazinePdfAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.magazine_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.nameOfFile.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void update(String fileName, String url) {
        items.add(fileName);
        items.add(url);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
            TextView nameOfFile;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameOfFile=itemView.findViewById(R.id.magazineItemTextView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int p=recyclerView.getChildLayoutPosition(view);
                    Intent intent=new Intent();
                    intent.setType(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(urls.get(p)));
                    context.startActivity(intent);
                }
            });
        }
    }
}
