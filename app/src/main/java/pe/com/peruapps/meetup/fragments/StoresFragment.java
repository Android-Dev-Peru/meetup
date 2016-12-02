package pe.com.peruapps.meetup.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pe.com.peruapps.meetup.R;
import pe.com.peruapps.meetup.adapters.StoreAdapter;

public class StoresFragment extends Fragment {
    private RecyclerView rcvStores;
    private StoreAdapter storeAdapter;

    public StoresFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stores, container, false);

        storeAdapter = new StoreAdapter(getActivity());
        rcvStores = (RecyclerView) view.findViewById(R.id.rcv_stores);
        rcvStores.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvStores.setAdapter(storeAdapter);

        return view;
    }
}