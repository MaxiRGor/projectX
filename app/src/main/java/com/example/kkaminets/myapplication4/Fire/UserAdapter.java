package com.example.kkaminets.myapplication4.Fire;



import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import com.example.kkaminets.myapplication4.R;


import java.util.List;

public class UserAdapter  extends  RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<UserModel> list;

    public UserAdapter(List<UserModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fire_element,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder holder, int position) {

        UserModel user = list.get(position);

        holder.textName.setText(user.firstName);
        holder.textJob.setText(user.cost);
        holder.textAge.setText(user.time + "");
        holder.textUnit.setText(user.unit);
        holder.textCount.setText(user.count);

        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                menu.add(holder.getAdapterPosition(),0,0, "Удалить");
               }
        });
        {

        };

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class  UserViewHolder extends RecyclerView.ViewHolder{

        TextView textName, textAge, textJob, textUnit, textCount;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);


            textName= (TextView) itemView.findViewById(R.id.text_firstName);
            textJob= (TextView) itemView.findViewById(R.id.text_stoimost);
            textAge= (TextView) itemView.findViewById(R.id.text_vremya);
            textCount= (TextView) itemView.findViewById(R.id.text_count);
            textUnit= (TextView) itemView.findViewById(R.id.text_unit);

        }
    }

}

