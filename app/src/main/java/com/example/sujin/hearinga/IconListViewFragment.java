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

import java.lang.reflect.Field;
import java.text.Format;
import java.util.ArrayList;

import javax.xml.datatype.DatatypeConstants;

import de.hdodenhof.circleimageview.CircleImageView;

public class IconListViewFragment extends Fragment {
    ArrayList<Avatars> avatarLists = new ArrayList();
    RecyclerView myRecyclerView;

    int images[]= {R.drawable.icon1,R.drawable.icon2,R.drawable.icon3,R.drawable.icon4,R.drawable.icon5,R.drawable.icon6,R.drawable.icon7,R.drawable.icon8,R.drawable.icon9,R.drawable.icon10,R.drawable.icon11};
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        avatarLists.clear();

        images = getImages();

        for (int i = 0; i < images.length; i++) {
            Avatars item = new Avatars();
            item.setavatarID(images[i]);
            avatarLists.add(item);
        }
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

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private ArrayList<Avatars> list;

        public MyAdapter(ArrayList<Avatars> Data) {
            list = Data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycle_icon_list, parent, false);
                    MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {

            holder.avatarImageView.setImageResource(list.get(position).getAvatarID());
            holder.avatarImageView.setTag(list.get(position).getAvatarID());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }


    private void sendData(int id, String name) {
        MyListener myListener = (MyListener) getActivity();
        myListener.listener(id, name);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public CircleImageView avatarImageView;

        public MyViewHolder(View v) {
            super(v);
            avatarImageView = (CircleImageView) v.findViewById(R.id.avatarImageView);
            avatarImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendData((int) avatarImageView.getTag(),"none");
                }


            });

        }
    }
    private int[] getImages()
    {
        String[] iConName = getResources().getStringArray(R.array.icon_list);
        int[] image = new int[iConName.length];
        for(int i=0;i<iConName.length;i++)
        {
            image[i] = getResources().getIdentifier(iConName[i],"drawable",this.getContext().getPackageName());
        }
        return image;
    }
}
