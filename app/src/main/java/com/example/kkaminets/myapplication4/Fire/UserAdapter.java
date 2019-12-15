package com.example.kkaminets.myapplication4.Fire;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;


import com.example.kkaminets.myapplication4.R;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<UserModel> list;

    UserAdapter(List<UserModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fire_element, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder holder, int position) {

        UserModel user = list.get(position);
        holder.bind(user);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    void addResult(UserModel result) {
        list.add(result);
        notifyDataSetChanged();
    }


    class UserViewHolder extends RecyclerView.ViewHolder {

        EditText textName, textAge, textJob, textUnit, textCount;


        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.text_firstName);
            textJob = itemView.findViewById(R.id.text_stoimost);
            textAge = itemView.findViewById(R.id.text_vremya);
            textCount = itemView.findViewById(R.id.text_count);
            textUnit = itemView.findViewById(R.id.text_unit);
        }

        void bind(UserModel user) {
            textName.setText(user.firstName);
            textJob.setText(user.cost);
            textAge.setText(user.time);
            textUnit.setText(user.unit);
            textCount.setText(user.count);

            textName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if ((keyEvent != null && (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (i == EditorInfo.IME_ACTION_DONE)) {
                        updateFirestoreInfo("firstName", textName.getText().toString(), list.get(getAdapterPosition()).getDocumentID());
                    }
                    return false;
                }
            });
            textJob.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if ((keyEvent != null && (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (i == EditorInfo.IME_ACTION_DONE)) {
                        updateFirestoreInfo("cost", textJob.getText().toString(), list.get(getAdapterPosition()).getDocumentID());
                    }
                    return false;
                }
            });
            textAge.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if ((keyEvent != null && (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (i == EditorInfo.IME_ACTION_DONE)) {
                        updateFirestoreInfo("time", textAge.getText().toString(), list.get(getAdapterPosition()).getDocumentID());
                    }
                    return false;
                }
            });
            textUnit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if ((keyEvent != null && (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (i == EditorInfo.IME_ACTION_DONE)) {
                        updateFirestoreInfo("unit", textUnit.getText().toString(), list.get(getAdapterPosition()).getDocumentID());
                    }
                    return false;
                }
            });
            textCount.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if ((keyEvent != null && (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (i == EditorInfo.IME_ACTION_DONE)) {
                        updateFirestoreInfo("count", textCount.getText().toString(), list.get(getAdapterPosition()).getDocumentID());
                    }
                    return false;
                }
            });
        }
    }

    private void updateFirestoreInfo(String firestoreFieldToUpdate, String infoToUpdate, String documentId) {

        Log.d("logs", firestoreFieldToUpdate + infoToUpdate + documentId);

        FirebaseFirestore.getInstance().collection("uslugi").document(documentId).update(firestoreFieldToUpdate, infoToUpdate);

/*        switch (textViewId){
            case R.id.text_firstName:
                firestoreFieldToUpdate = "firstName";
                infoToUpdate =
        }*/
    }

}

