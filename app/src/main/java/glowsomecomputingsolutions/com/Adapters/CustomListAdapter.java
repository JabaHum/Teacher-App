package glowsomecomputingsolutions.com.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import glowsomecomputingsolutions.com.Model.Students;
import glowsomecomputingsolutions.com.R;
import glowsomecomputingsolutions.com.Utils.AppController;


public class CustomListAdapter extends BaseAdapter {
    private List<Students> mStudents;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();


    @Override
    public int getCount() {
        return mStudents.size();
    }
//order  of the argument matters
    public CustomListAdapter(List<Students> mStudents, Context mContext) {
        this.mStudents = mStudents;
        this.mContext = mContext;
    }

    @Override
    public Object getItem(int position) {
        return mStudents.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (mLayoutInflater == null)
            mLayoutInflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = mLayoutInflater.inflate(R.layout.list_view_item, null);
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();


        NetworkImageView thumbNail = convertView.findViewById(R.id.img_list_view_item);
        TextView names = convertView.findViewById(R.id.txt_names);


        // getting movie data for the row
        Students students = mStudents.get(position);

        thumbNail.setImageUrl(students.getThumbnailUrl(),imageLoader);
        names.setText(students.getStudent_name());


        return convertView;
    }
}
