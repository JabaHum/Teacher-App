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

    private class ViewHolder{
        NetworkImageView thumbNail;
        TextView mTextView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (mLayoutInflater == null)
            mLayoutInflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_view_item, null);
            holder = new ViewHolder();

            holder.thumbNail = convertView.findViewById(R.id.img_list_view_item);
            holder.mTextView = convertView.findViewById(R.id.txt_names);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();




        // getting students data for the row
        Students students = mStudents.get(position);

        holder.thumbNail.setImageUrl(students.getThumbnailUrl(),imageLoader);
        holder.mTextView.setText(students.getStudent_name());


        return convertView;
    }
}
