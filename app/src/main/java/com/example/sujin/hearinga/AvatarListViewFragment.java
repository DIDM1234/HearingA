package com.example.sujin.hearinga;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AvatarListViewFragment extends Fragment {
    ArrayList<Avatars> avatarLists = new ArrayList();
    RecyclerView myRecyclerView;

    private DatabaseAccess databaseAccess;
    String[] avatar;
    int[] images;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        databaseAccess = DatabaseAccess.getInstance(getActivity().getApplicationContext());
        databaseAccess.open();
        List<Account> list = databaseAccess.getAccount();
        avatar = new String[list.size()];
        images = new int[list.size()];
        for(int i=0;i<list.size();i++)
        {
            Account account = list.get(i);
            int id = this.getResources().getIdentifier(account.iconName,"drawable",getActivity().getPackageName());
            images[i] = id;
            avatar[i] = account.name;
        }
        databaseAccess.close();

        avatarLists.clear();
        for(int i =0;i<list.size();i++){
            Avatars item = new Avatars();
            item.setAvatarName(avatar[i]);
            item.setavatarID(images[i]);
            avatarLists.add(item);
        }
    }

    @Override
    public void  onResume() {
        super.onResume();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_avatar_list, container, false);
        myRecyclerView = (RecyclerView) view.findViewById(R.id.avatarView);
        myRecyclerView.setHasFixedSize(true);

        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        if (avatarLists.size() > 0 & myRecyclerView != null) {
            myRecyclerView.setAdapter(new MyAdapter(avatarLists));
        }
        myRecyclerView.setLayoutManager(MyLayoutManager);

        return view;
    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private ArrayList<Avatars> list;

        public MyAdapter(ArrayList<Avatars> Data) {
            list = Data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
            // create a new view
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycle_avatar_list, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {

            holder.avatarTextView.setText(list.get(position).getAvatarName());
            holder.avatarImageView.setImageResource(list.get(position).getAvatarID());
            holder.avatarImageView.setTag(list.get(position).getAvatarID());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    private void  sendData(int id,String name)
    {
        MyListener myListener = (MyListener) getActivity();
        myListener.listener(id,name);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public CircleImageView avatarImageView;
        public TextView avatarTextView;

        public MyViewHolder(View v) {
            super(v);
            avatarImageView = (CircleImageView) v.findViewById(R.id.avatarImageView);
            avatarTextView = (TextView) v.findViewById(R.id.avatarTextView);
            avatarImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendData((int)avatarImageView.getTag(),(String) avatarTextView.getText());
                }


            });

        }
    }

}
