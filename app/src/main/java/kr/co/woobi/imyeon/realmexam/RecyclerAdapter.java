package kr.co.woobi.imyeon.realmexam;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;
import kr.co.woobi.imyeon.realmexam.databinding.ItemTwoTextBinding;

public class RecyclerAdapter extends RealmRecyclerViewAdapter<Person, RecyclerAdapter.ViewHolder> {

    public RecyclerAdapter(@Nullable OrderedRealmCollection<Person> data) {
        super(data, true);
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_two_text, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.binding.setPerson(getItem(i));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemTwoTextBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
