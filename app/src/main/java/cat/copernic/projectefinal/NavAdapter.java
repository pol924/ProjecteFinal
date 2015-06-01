package cat.copernic.projectefinal;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Pol on 23/05/2015.
 */
public class NavAdapter extends BaseAdapter{

    private Activity activity;
    ArrayList<Img> array;

    public NavAdapter(Activity activity, ArrayList<Img> array) {
        this.activity = activity;
        this.array = array;
    }


    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int position) {
        return array.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class Fila{
        TextView view;
        ImageView icono;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Fila view;
        LayoutInflater inflator = activity.getLayoutInflater();
        if(convertView==null){
            view = new Fila();
            Img img = array.get(position);
            convertView=inflator.inflate(R.layout.imgtx,null);

            view.view = (TextView) convertView.findViewById(R.id.text);
            view.icono = (ImageView) convertView.findViewById(R.id.icon);

            view.view.setText(img.getS());
            view.icono.setImageResource(img.getI());
            convertView.setTag(view);

        }else{
            view = (Fila) convertView.getTag();
        }

        return convertView;
    }
}
