package pe.com.peruapps.meetup;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import io.realm.Realm;
import io.realm.RealmList;
import pe.com.peruapps.meetup.fragments.MapFragment;
import pe.com.peruapps.meetup.fragments.NavigationDrawerFragment;
import pe.com.peruapps.meetup.fragments.ProductsFragment;
import pe.com.peruapps.meetup.fragments.StoresFragment;
import pe.com.peruapps.meetup.models.Product;
import pe.com.peruapps.meetup.models.Store;
import pe.com.peruapps.meetup.util.MarshMallowPermission;

public class MainActivity extends AppCompatActivity {
    private Realm realm;
    private DrawerLayout drawerLayout;
    private NavigationDrawerFragment navigationDrawerFragment;
    private MarshMallowPermission mallowPermission = new MarshMallowPermission(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        navigationDrawerFragment.setDrawerLayout(drawerLayout);

        setProducts();

        if (!mallowPermission.checkPermissionForAccessFineLocation()) {
            mallowPermission.requestPermissionForAccessFineLocation();
        } else {
            initialize();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case MarshMallowPermission.ACCESS_COARSE_LOCATION_REQUEST_CODE:
                initialize();
                break;
        }
    }

    private void initialize() {
        Fragment newFragment = new ProductsFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_layout, newFragment).commit();
    }

    public void openDrawer(View view) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void loadProducts() {
        Fragment newFragment = new ProductsFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_layout, newFragment);
        ft.addToBackStack("Products");
        ft.commit();
    }

    public void loadStores() {
        Fragment newFragment = new StoresFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_layout, newFragment).addToBackStack("Stores").commit();
    }

    public void loadMap() {
        Fragment newFragment = new MapFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_layout, newFragment).addToBackStack("Map").commit();
    }

    private void setProducts() {
        if (realm.where(Product.class).findAll().size() == 0) {
            String[] storeName = {"Ripley", "Saga Fallabella", "Gamarra Shopping Center"};
            double[] storeLatitude = {-12.086205, -12.118717, -12.065403};
            double[] storeLongitude = {-76.975343, -77.029364, -77.015018};

            String[][] productName = {
                    {"Polo negro", "Polo blanco", "Polo gris"},
                    {"Pantalón de cuero", "Pantalón jean", "Pantalón drill"},
                    {"Zapatillas", "Zapatos", "Chimpunes"}
            };
            String[][] productPhoto = {
                    {
                            "http://www.potencialhardcore.org/WebRoot/StoreES2/Shops/64798605/537B/282E/5C77/5FE2/C441/C0A8/2BB9/25FF/1213.jpg",
                            "http://www.camisetasyregalos.com/media/originales/E2908C0A-215E-95CD-868367D6A3E10FD5.JPG",
                            "http://www.camisetasyregalos.com/media/originales/E0C20087-215E-95CD-867180E5E01D15C9.JPG"
                    },
                    {
                            "https://cdn-images.farfetch-contents.com/11/54/13/81/11541381_7264243_300.jpg",
                            "http://2.bp.blogspot.com/-lr3w6LB51XA/TlxqrSjOsyI/AAAAAAAAEyk/iDZenB1d7Lk/s400/DSCF5359.JPG",
                            "https://images.ikrix.com/product_images/medium/prada-linea-rossa-straight-leg-jeans-online-bleached-cotton-denim-jeans-00000078127f00s012.jpg"
                    },
                    {
                            "http://e-buey.com/wp-content/uploads/2015/10/31-300x400.jpg",
                            "https://http2.mlstatic.com/zapatos-de-vestir-nro38-D_NQ_NP_197611-MLV20606851170_022016-F.jpg",
                            "https://images01.olx-st.com/ui/52/56/14/86/o_1477595921_8c72e68b77263a2bee18f0d9570a4ab9.jpg"
                    }
            };
            String[] productDescription = {
                    "Lorem ipsum dolor sit amet, consectetur adipisicing elit. A accusantium explicabo incidunt molestias rerum sequi similique?",
                    "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Architecto maxime omnis quidem tempore totam ut, voluptate voluptatum.",
                    "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad assumenda dolore enim hic maiores mollitia non, repellendus similique."
            };

            realm.beginTransaction();

            for (int i = 0; i < 3; i++) {
                Store store = new Store();
                RealmList<Product> products = new RealmList<>();

                store.setId(i + i);
                store.setName(storeName[i]);
                store.setLatitude(storeLatitude[i]);
                store.setLongitude(storeLongitude[i]);

                for (int j = 0; j < 3; j++) {
                    Product product = new Product();

                    product.setId((i * 3) + (1 + j));
                    product.setName(productName[i][j]);
                    product.setDescription(productDescription[j]);
                    product.setPhotoUrl(productPhoto[i][j]);

                    products.add(product);
                    realm.copyToRealmOrUpdate(product);
                }

                store.setProducts(products);

                realm.copyToRealmOrUpdate(store);
            }

            realm.commitTransaction();
        }
    }
}