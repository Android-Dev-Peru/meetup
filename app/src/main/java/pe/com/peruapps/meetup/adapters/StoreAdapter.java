package pe.com.peruapps.meetup.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;
import pe.com.peruapps.meetup.R;
import pe.com.peruapps.meetup.models.Store;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {
    private Realm realm;
    private Context context;
    private LayoutInflater layoutInflater;
    private RealmResults<Store> stores;

    public StoreAdapter(Context context) {
        this.realm = Realm.getDefaultInstance();
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.stores = realm.where(Store.class).findAll();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_store, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Store store = stores.get(holder.getAdapterPosition());

        holder.txtName.setText(store.getName());
        holder.txtProducts.setText("Esta tienda tiene " + store.getProducts().size() + " productos");
    }

    @Override
    public int getItemCount() {
        return stores.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public TextView txtProducts;

        public ViewHolder(View itemView) {
            super(itemView);

            txtName = (TextView) itemView.findViewById(R.id.txt_name);
            txtProducts = (TextView) itemView.findViewById(R.id.txt_products);
        }
    }
}
