package com.example.kkaminets.myapplication4.listOne;
//Активити списка услуг
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

public class Spisok1 extends Activity {

    private SQLiteDatabase mDatabase;
    private MyAdapter1 mAdapter;
    private EditText mEditTextWork;
    private EditText mEditTextCost;
    private EditText mEditTextFieldOne;
    private EditText mEditTextFieldTwo;
    private EditText mEditTextFieldThree;
    private EditText mEditTextFieldFour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spisok1_activity);

        ServiceDBHelper dbHelper = new ServiceDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // так как список линейный используем LinearLayoutManager
        mAdapter = new MyAdapter1(this, getAllItems()); // присвоение mAdapter
        recyclerView.setAdapter(mAdapter);

        //ItemTouchHelper служебный класс для добавления свайпа для отклонения и поддержки перетаскивания в RecyclerView
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {   //действие при свайпе влево или право
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
        mEditTextWork = findViewById(R.id.edit_text_name_services);
        mEditTextCost = findViewById(R.id.edit_text_cost);
        mEditTextFieldOne = findViewById(R.id.edit_text_field_one);
        mEditTextFieldTwo = findViewById(R.id.edit_text_field_two);
        mEditTextFieldThree = findViewById(R.id.edit_text_field_three);
        mEditTextFieldFour = findViewById(R.id.edit_text_field_four);

        Button buttonAdd = findViewById(R.id.button_add);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });  // при нажатии на кнопку вызывает метот addItem();
    }

    private void addItem() {
        if (mEditTextWork.getText().toString().trim().length() == 0 || mEditTextCost.getText().toString().trim().length() == 0||
                mEditTextFieldOne.getText().toString().trim().length() == 0 || mEditTextFieldTwo.getText().toString().trim().length() == 0) {
            return;
        }    // проверка на пустые поля  и возврат при их наличии

        //запись текста с EditText в переменные.
        String work = mEditTextWork.getText().toString();
        String cost = mEditTextCost.getText().toString();
        String fieldeone = mEditTextFieldOne.getText().toString();
        String fieldetwo = mEditTextFieldTwo.getText().toString();
        String fieldethree = mEditTextFieldThree.getText().toString();
        String fieldefour = mEditTextFieldFour.getText().toString();

        ContentValues cv = new ContentValues(); // класс используется для хранения набора значений
        cv.put(ServiceBase.ServiceEntry.COLUMN_WORK, work);
        cv.put(ServiceBase.ServiceEntry.COLUMN_COST, cost);
        cv.put(ServiceBase.ServiceEntry.COLUMN_FILED_ONE, fieldeone);
        cv.put(ServiceBase.ServiceEntry.COLUMN_FILED_TWO, fieldetwo);
        cv.put(ServiceBase.ServiceEntry.COLUMN_FILED_THREE, fieldethree);
        cv.put(ServiceBase.ServiceEntry.COLUMN_FILED_FOUR, fieldefour);// добавляем значение в набор put(String key, String value)

        mDatabase.insert(ServiceBase.ServiceEntry.TABLE_NAME, null, cv); //добавляем в таблицу значения из cv
        mAdapter.swapCursor(getAllItems()); //

        //очистка всех EditText
        mEditTextWork.getText().clear();
        mEditTextCost.getText().clear();
        mEditTextFieldOne.getText().clear();
        mEditTextFieldTwo.getText().clear();
        mEditTextFieldThree.getText().clear();
        mEditTextFieldFour.getText().clear();
    }
    //метод удаления item  по id
    private void removeItem(long id) {
        mDatabase.delete(ServiceBase.ServiceEntry.TABLE_NAME,
                ServiceBase.ServiceEntry._ID + "=" + id, null);
        mAdapter.swapCursor(getAllItems());
    }
    //метод возвращает
    private Cursor getAllItems() {
        return mDatabase.query(
                ServiceBase.ServiceEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                ServiceBase.ServiceEntry.COLUMN_TIMESTAMP + " DESC"
        );
    }
}
