package com.example.kkaminets.myapplication4.listOne;

import android.content.Context;
import android.database.Cursor;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kkaminets.myapplication4.R;

public class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.ServiceViewHolder> {
    private Context mContext;
    private Cursor mCursor;

    public MyAdapter1(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }
    // класс ViewHolder описывает представление элемента и метаданные о его месте в RecyclerView.
    public class ServiceViewHolder extends RecyclerView.ViewHolder {
        //Создаем переменные TextView
         TextView workText;
         TextView costText;
         TextView invisText;
         TextView fieldOneText;
         TextView fieldTwoText;
         TextView invisTwoText;

        public ServiceViewHolder(View itemView) {
            super(itemView);
            //Привязываем переменные TextView к id из list1_item.xml
            workText = itemView.findViewById(R.id.text_work_item);
            costText = itemView.findViewById(R.id.text_cost_item);
            invisText = itemView.findViewById(R.id.text_cost_invis);
            fieldOneText = itemView.findViewById(R.id.text_field_one);
            fieldTwoText = itemView.findViewById(R.id.text_field_two);
            invisTwoText = itemView.findViewById(R.id.text_field_four);
        }
    }

    @Override
    public ServiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list1_item, parent, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ServiceViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }

        //Присваиваем переменным значения
        String name = mCursor.getString(mCursor.getColumnIndex(ServiceBase.ServiceEntry.COLUMN_WORK));
        String cost = mCursor.getString(mCursor.getColumnIndex(ServiceBase.ServiceEntry.COLUMN_COST));
        String invis = mCursor.getString(mCursor.getColumnIndex(ServiceBase.ServiceEntry.COLUMN_FILED_TWO));
        String fileOne = mCursor.getString(mCursor.getColumnIndex(ServiceBase.ServiceEntry.COLUMN_FILED_ONE));
        String fileTwo = mCursor.getString(mCursor.getColumnIndex(ServiceBase.ServiceEntry.COLUMN_FILED_FOUR));
        String invisTwo = mCursor.getString(mCursor.getColumnIndex(ServiceBase.ServiceEntry.COLUMN_FILED_THREE));
        long id = mCursor.getLong(mCursor.getColumnIndex(ServiceBase.ServiceEntry._ID));

        holder.workText.setText(name);
        holder.costText.setText(cost);
        holder.invisText.setText(invis);
        holder.fieldOneText.setText(fileOne);
        holder.fieldTwoText.setText(fileTwo);
        holder.invisTwoText.setText(invisTwo);
        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) {
            mCursor.close();
        }

        mCursor = newCursor;

        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }
}
