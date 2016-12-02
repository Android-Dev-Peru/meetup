package pe.com.peruapps.meetup.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import io.realm.Realm;
import io.realm.RealmResults;
import pe.com.peruapps.meetup.R;
import pe.com.peruapps.meetup.models.Product;
import pe.com.peruapps.meetup.util.VolleySingleton;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Realm realm;
    private Context context;
    private LayoutInflater layoutInflater;
    private RealmResults<Product> products;
    private ImageLoader imageLoader = VolleySingleton.getsInstance().getImageLoader();

    public ProductAdapter(Context context) {
        this.realm = Realm.getDefaultInstance();
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.products = realm.where(Product.class).findAll();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = products.get(holder.getAdapterPosition());

        holder.txtName.setText(product.getName());
        holder.txtDescription.setText(product.getDescription());
        holder.imgProduct.setImageUrl(product.getPhotoUrl(), imageLoader);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public NetworkImageView imgProduct;
        public TextView txtName;
        public TextView txtDescription;

        public ViewHolder(View itemView) {
            super(itemView);

            imgProduct = (NetworkImageView) itemView.findViewById(R.id.img_product);
            txtName = (TextView) itemView.findViewById(R.id.txt_name);
            txtDescription = (TextView) itemView.findViewById(R.id.txt_description);
        }
    }
}
