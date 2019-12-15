package com.example.kkaminets.myapplication4.Fire;


import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MenuItem;

import com.example.kkaminets.myapplication4.R;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Fire extends Activity {

    private RecyclerView recyclerView;
    private List<UserModel> result;
    private UserAdapter adapter;

    private FirebaseDatabase database;
    private DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firelist);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("uslugi");

        result = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.uslugi_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager lin = new LinearLayoutManager(this);
        lin.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(lin);


        adapter= new UserAdapter(result);
        recyclerView.setAdapter(adapter);

        updateList();


    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case 0:
                removeUser(item.getGroupId());
                break;
            case 1:
                break;
        }
        return super.onContextItemSelected(item);
    }


    private void updateList(){

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                result.add(dataSnapshot.getValue(UserModel.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                UserModel model = dataSnapshot.getValue(UserModel.class);
                int index = getitemIndex(model);

                result.set(index,model);
                adapter.notifyItemChanged(index);


            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                UserModel model = dataSnapshot.getValue(UserModel.class);
                int index = getitemIndex(model);

                result.remove(index);
                adapter.notifyItemRemoved(index);


            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private int getitemIndex(UserModel user){

        int index = -1;

        for (int i=0; i<result.size(); i++){

            if (result.get(1).key.equals(user.key)){
                index=1;
                break;
            }
        }
        return index;

    }

    private void  removeUser(int position){
        reference.child(result.get(position).key).removeValue();
    }
}
