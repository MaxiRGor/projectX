package com.example.kkaminets.myapplication4.listTwo;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kkaminets.myapplication4.R;

//Активити списка сотрудников
public class Spisok2 extends Activity implements OnItemTappedListener {

    private SQLiteDatabase mDatabase;
    private MyAdapter2 mAdapter;
    private EditText mEditTextName;
    private EditText mEditTextWorker;
    private EditText mEditTextCategory;
    private EditText mEditTextFildt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spisok2_activity);

        EmployeeDBHelper dbHelper = new EmployeeDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase(); //Открываем базу данных, которая будет использоваться для чтения и записи

        RecyclerView recyclerView = findViewById(R.id.recyclerView_employee);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyAdapter2(this, getAllItems());     // построение и заполнение recyclerView
        recyclerView.setAdapter(mAdapter);
        //ItemTouchHelper служебный класс для добавления свайпа для отклонения и поддержки перетаскивания в RecyclerView
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                removeItem((long) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(recyclerView);

        //присвоение id глобальным переменным из spisok1_activity.xml
        mEditTextName = findViewById(R.id.edit_text_name);
        mEditTextWorker = findViewById(R.id.edit_text_worker);
        mEditTextCategory = findViewById(R.id.edit_text_cost);
        mEditTextFildt = findViewById(R.id.edit_text_field);

        Button buttonAddEmployee = findViewById(R.id.button_add_employee);


        buttonAddEmployee.setOnClickListener(new View.OnClickListener() { // при нажатии на кнопку вызывает метот addItem();
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
    }

    private void addItem() {

        if (mEditTextName.getText().toString().trim().length() == 0 || mEditTextWorker.getText().toString().trim().length() == 0
                || mEditTextCategory.getText().toString().trim().length() == 0) {
            return;
        }// проверка на пустые поля  и возврат при их наличии


        //запись текста с EditText в переменные.
        String name = mEditTextName.getText().toString();
        String worker = mEditTextWorker.getText().toString();
        String categoryee = mEditTextCategory.getText().toString();
        String fildt = mEditTextFildt.getText().toString();
        ContentValues cv = new ContentValues();
        cv.put(EmployeeBase.EmployeeEntry.COLUMN_NAME, name);
        cv.put(EmployeeBase.EmployeeEntry.COLUMN_WORKER, worker);
        cv.put(EmployeeBase.EmployeeEntry.COLUMN_CATEGORY, categoryee);
        cv.put(EmployeeBase.EmployeeEntry.COLUMN_FILED, fildt);

        mDatabase.insert(EmployeeBase.EmployeeEntry.TABLE_NAME, null, cv);
        mAdapter.swapCursor(getAllItems());

        //очистка всех EditText
        mEditTextName.getText().clear();
        mEditTextWorker.getText().clear();
        mEditTextCategory.getText().clear();
        mEditTextFildt.getText().clear();

    }

    //метод удаления item  по id
    private void removeItem(long id) {
        mDatabase.delete(EmployeeBase.EmployeeEntry.TABLE_NAME,
                EmployeeBase.EmployeeEntry._ID + "=" + id, null);
        mAdapter.swapCursor(getAllItems());
    }

    //класс Cursor - это набор строк в табличном виде
    private Cursor getAllItems() {
        return mDatabase.query(     //возвращает запрос к бд
                EmployeeBase.EmployeeEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                EmployeeBase.EmployeeEntry.COLUMN_TIMESTAMP + " DESC"
        );
    }


    @Override
    public void itemTapped(String name, String worker, String category, String text) {
        mEditTextName.setText(name);
        mEditTextWorker.setText(worker);
        mEditTextCategory.setText(category);
        mEditTextFildt.setText(text);
    }
}