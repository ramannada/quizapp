package com.blogspot.ramannada.quizapp.user;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blogspot.ramannada.quizapp.R;
import com.blogspot.ramannada.quizapp.model.User;

import java.util.List;

/**
 * Created by ramannada on 11/17/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {
    private Context context;
    private List<User> users;

    public UserAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_user, parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {
        User user = users.get(position);
        holder.bind(user);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        private TextView tvSkor;

        public UserHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvSkor = itemView.findViewById(R.id.tv_score);
        }

        private void bind(User user) {
            tvName.setText(user.getName());
            tvSkor.setText(String.valueOf(user.getScore()));
        }
    }


}
