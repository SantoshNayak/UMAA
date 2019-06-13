package umaa.uu.mca.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import umaa.uu.mca.activities.R;
import umaa.uu.mca.model.UploadImage;

public class EventsViewImageAdapter extends RecyclerView.Adapter<EventsViewImageAdapter.EventsImageViewHolder> {
    private Context mContext;
    private List<UploadImage> mUploads;

    public EventsViewImageAdapter(Context mContext, List<UploadImage> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
    }

    public EventsViewImageAdapter() {
    }

    @Override
    public EventsImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.show_events_image, parent, false);
        return new EventsImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(EventsImageViewHolder holder, int position) {
        UploadImage uploadCurrent = mUploads.get(position);
        holder.txtView.setText(uploadCurrent.getmDescription());
        Picasso.with(mContext)
                .load(uploadCurrent.getmImageUrl())
                .fit()
                .centerCrop()
                .into(holder.imgView);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }
    public class EventsImageViewHolder extends RecyclerView.ViewHolder{
        public TextView txtView;
        public ImageView imgView;
        public EventsImageViewHolder(@NonNull View itemView) {
            super(itemView);
            txtView=itemView.findViewById(R.id.show_events_text_view_desc);
            imgView=itemView.findViewById(R.id.show_events_image_view_upload);
        }
    }
}
