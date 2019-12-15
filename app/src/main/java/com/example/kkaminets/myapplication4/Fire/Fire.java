package com.example.kkaminets.myapplication4.Fire;


import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kkaminets.myapplication4.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Fire extends Activity {

    private RecyclerView recyclerView;

    private UserAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firelist);


        recyclerView = (RecyclerView) findViewById(R.id.uslugi_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager lin = new LinearLayoutManager(this);
        lin.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(lin);

        adapter = new UserAdapter(new ArrayList<UserModel>());
        recyclerView.setAdapter(adapter);

        updateList();
    }


    private void updateList() {
        FirebaseFirestore.getInstance()
                .collection("uslugi")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                    UserModel model = snapshot.toObject(UserModel.class);

                    //for easy access from adapter
                    model.setDocumentID(snapshot.getId());
                    adapter.addResult(model);
                }
            }
        });

    }
}
