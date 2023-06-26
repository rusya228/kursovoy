package com.example.sport;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
public class CustomGridAdapter extends ArrayAdapter<String[]> {

    private Context mContext;
    private String[][] mItems;

    public CustomGridAdapter(Context context, String[][] items) {
        super(context, 0, items);
        mContext = context;
        mItems = items;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridItem = convertView;
        if (gridItem == null) {
            gridItem = LayoutInflater.from(mContext).inflate(R.layout.grid_item_layout, parent, false);
        }

        String[] currentItem = mItems[position];

        ImageView imageView = gridItem.findViewById(R.id.imageView);
        String imageUrl = "https://smtpservers.ru/projects/Bardin/uploads/"+currentItem[2];
        Picasso.get()
                .load(imageUrl)
                .into(imageView);

        ImageView imageView2 = gridItem.findViewById(R.id.imageView2);
        String imageUrl2 = "https://smtpservers.ru/projects/Bardin/uploads/"+currentItem[3];
        Picasso.get()
                .load(imageUrl2)
                .into(imageView2);

        TextView textView1 = gridItem.findViewById(R.id.team1);
        textView1.setText(currentItem[0]);

        TextView textView2 = gridItem.findViewById(R.id.team2);
        textView2.setText(currentItem[1]);

        TextView textView3 = gridItem.findViewById(R.id.team1goal);
        textView3.setText(currentItem[4]);

        TextView textView4 = gridItem.findViewById(R.id.team2goal);
        textView4.setText(currentItem[5]);

        return gridItem;

    }
}
