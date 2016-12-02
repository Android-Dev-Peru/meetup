package pe.com.peruapps.meetup.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pe.com.peruapps.meetup.R;
import pe.com.peruapps.meetup.adapters.ProductAdapter;

public class ProductsFragment extends Fragment {
    private RecyclerView rcvProducts;
    private ProductAdapter productAdapter;

    public ProductsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);

        productAdapter = new ProductAdapter(getActivity());
        rcvProducts = (RecyclerView) view.findViewById(R.id.rcv_products);
        rcvProducts.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvProducts.setAdapter(productAdapter);

        return view;
    }
}