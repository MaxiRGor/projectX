package com.example.kkaminets.myapplication4.listTwo;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kkaminets.myapplication4.R;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.EmployeeViewHolder> {
    private Context mContext;
    private Cursor mCursor;

    public MyAdapter2(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }
    // класс ViewHolder описывает представление элемента и метаданные о его месте в RecyclerView.
    public class EmployeeViewHolder extends RecyclerView.ViewHolder {
        //Создаем переменные TextView
        public TextView nameText;
        public TextView workerText;
        public TextView categoryText;
        public TextView fieldText;

        public EmployeeViewHolder(View itemView) {
            super(itemView);
            //Привязываем переменные TextView к id из list1_item.xml
            nameText = itemView.findViewById(R.id.text_name_item);
            workerText = itemView.findViewById(R.id.text_worker_item);
            categoryText = itemView.findViewById(R.id.text_category_item);
            fieldText = itemView.findViewById(R.id.text_field_three);
        }
    }

    @Override
    public MyAdapter2.EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list2_item, parent, false);
        return new MyAdapter2.EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter2.EmployeeViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }
        //Присваиваем переменным значения
        String name = mCursor.getString(mCursor.getColumnIndex(EmployeeBase.EmployeeEntry.COLUMN_NAME));
        String worker = mCursor.getString(mCursor.getColumnIndex(EmployeeBase.EmployeeEntry.COLUMN_WORKER));
        String category = mCursor.getString(mCursor.getColumnIndex(EmployeeBase.EmployeeEntry.COLUMN_CATEGORY));
        String field = mCursor.getString(mCursor.getColumnIndex(EmployeeBase.EmployeeEntry.COLUMN_FILED));

        long id = mCursor.getLong(mCursor.getColumnIndex(EmployeeBase.EmployeeEntry._ID));

        holder.nameText.setText(name);
        holder.workerText.setText(worker);
        holder.categoryText.setText(category);
        holder.fieldText.setText(field);
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
