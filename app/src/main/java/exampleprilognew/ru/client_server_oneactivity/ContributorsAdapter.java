package exampleprilognew.ru.client_server_oneactivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;

import java.util.List;



public class ContributorsAdapter extends RecyclerView.Adapter<ContributorsAdapter.ViewHolder> {

    private final List<Contributor> contributors;

    private final RequestManager requestManager;

    public ContributorsAdapter(List<Contributor> contributors,  RequestManager requestManager) {
        this.contributors = contributors;

        this.requestManager=requestManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater= LayoutInflater.from(parent.getContext());

        View view=inflater.inflate(R.layout.contributors_item,parent,false);

        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Contributor contributor= contributors.get(position);

        holder.bindTo(contributor);

    }

    @Override
    public int getItemCount() {
        return contributors.size();
    }

       class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imageView;
        private final TextView tvContribut;

        public ViewHolder(View itemView){
            super(itemView);

            imageView=(ImageView)itemView.findViewById(R.id.image);
            tvContribut =(TextView)itemView.findViewById(R.id.login);

        }

        public void bindTo(Contributor contributor) {

            tvContribut.setText(contributor.getLogin());

            requestManager.load(contributor.getAvatarUrl()).into(imageView);
        }
    }

}
