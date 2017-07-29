package lteii.binarooms.utils.fileExplorer;

import java.util.List;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import lteii.binarooms.R;

public class FileArrayAdapter extends ArrayAdapter<FileExplorerItem>{

    private Context context;
    private int textViewResourceId;
    private List<FileExplorerItem> items;

    public FileArrayAdapter(Context context, int textViewResourceId, List<FileExplorerItem> items) {
        super(context, textViewResourceId, items);
        this.context = context;
        this.textViewResourceId = textViewResourceId;
        this.items = items;
    }
    public FileExplorerItem getItem(int i) {
        return items.get(i);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(textViewResourceId, null);
        }

        final FileExplorerItem o = items.get(position);
        if (o != null) {
            TextView t1 = (TextView) convertView.findViewById(R.id.TextView01);
            TextView t2 = (TextView) convertView.findViewById(R.id.TextView02);
            TextView t3 = (TextView) convertView.findViewById(R.id.TextViewDate);

            ImageView imageCity = (ImageView) convertView.findViewById(R.id.fd_Icon1);
            String uri = "drawable/" + o.getImage();
            int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
            Drawable image = context.getResources().getDrawable(imageResource);
            imageCity.setImageDrawable(image);

            if(t1!=null) t1.setText(o.getName());
            if(t2!=null) t2.setText(o.getData());
            if(t3!=null) t3.setText(o.getDate());
        }
        return convertView;
    }

}