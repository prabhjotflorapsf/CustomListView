package com.app.customlistviewsample.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.app.customlistviewsample.R;
import com.app.customlistviewsample.model.Repository;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyListAdapter extends ArrayAdapter<Repository> {

    private List<Repository> mList;
    private Activity mActivity;
    private int resource;

    public MyListAdapter(List<Repository> mList, int resource,Activity mActivity){
        super(mActivity, resource, mList);
        this.mActivity = mActivity;
        this.mList  = mList;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mActivity);

        View view = layoutInflater.inflate(resource, null, false);

        //getting the view elements of the list from the view
        TextView tvRepositoryName = view.findViewById(R.id.tvRepositoryName);
        TextView tvOwnerName = view.findViewById(R.id.tvOwnerName);
        CircleImageView profile_photo = view.findViewById(R.id.profile_photo);

        Repository repository = mList.get(position);
        tvRepositoryName.setText("Repository Name : " + repository.getFullName());
        tvOwnerName.setText("Owner Name : " +repository.getOwner().getLogin());

        Glide.with(mActivity)
                .load(repository.getOwner().getAvatarUrl())
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .error(R.drawable.test)
                .priority(Priority.HIGH)
                .placeholder(R.drawable.test)
                .into(profile_photo);

        return view;
    }
}
