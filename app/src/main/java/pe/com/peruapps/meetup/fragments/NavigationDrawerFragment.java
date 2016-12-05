package pe.com.peruapps.meetup.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import pe.com.peruapps.meetup.R;

public class NavigationDrawerFragment extends Fragment {
    private DrawerLayout drawerLayout;
    private Button btnHome;
    private Button btnStores;
    private Button btnFindThem;
    private Navigation mNavigation;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        btnHome = (Button) view.findViewById(R.id.btn_home);
        btnStores = (Button) view.findViewById(R.id.btn_stores);
        btnFindThem = (Button) view.findViewById(R.id.btn_find_them);

        setClicks();

        return view;
    }

    @Override public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Navigation) {
            mNavigation = (Navigation) context;
        }
    }

    private void setClicks() {
        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_home:
                        mNavigation.loadProducts();
                        break;
                    case R.id.btn_stores:
                        mNavigation.loadStores();
                        break;
                    case R.id.btn_find_them:
                        mNavigation.loadMap();
                        break;
                }

                drawerLayout.closeDrawer(GravityCompat.START);
            }
        };

        btnHome.setOnClickListener(click);
        btnStores.setOnClickListener(click);
        btnFindThem.setOnClickListener(click);
    }

    public void setDrawerLayout(DrawerLayout drawerLayout) {
        this.drawerLayout = drawerLayout;
    }
}