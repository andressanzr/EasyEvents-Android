package es.easyevents.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import es.easyevents.Fragments.EventoFragment;
import es.easyevents.Models.Usuario;
import es.easyevents.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class PrincipalActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    //private RecyclerView recycler;
    private FloatingActionButton fab;
    boolean isDark = false;
    EditText searchInput ;

    //private RecyclerView.LayoutManager Manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        setToolbar();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navview);


        updateNavHeader();
        fab = (FloatingActionButton) findViewById(R.id.fabAddBoard);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PrincipalActivity.this, EventoCreando.class);
                startActivity(intent);
            }
        });
        setFragmentByDefault();

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().hide();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                getSupportActionBar().show();
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                boolean fragmentTransaction = false;
                Fragment fragment = null;


                switch (item.getItemId()) {

                    case R.id.eventos:
                      //  fragment = new EmailFragment();
                      //  fragmentTransaction = true;
                        break;
                    case R.id.invitados:

                        break;
                    case R.id.compartir:
                        Intent intent = new Intent (Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        String shareBody= "Your body here";
                        String shareSubject= "Your Subject here";

                        intent.putExtra(Intent.EXTRA_TEXT, shareBody);
                        intent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);


                        startActivity(Intent.createChooser(intent, "Compartir"));
                        break;
                    case R.id.cerrar_sesion:
                        Intent loginActivity = new Intent(getApplicationContext(),Login.class);
                        startActivity(loginActivity);
                        finish();
                        break;
                }


                if (fragmentTransaction) {
                    changeFragment(fragment, item);
                    drawerLayout.closeDrawers();
                }
                return true;
            }
        });



    }

    public void updateNavHeader() {

        NavigationView navigationView = (NavigationView) findViewById(R.id.navview);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.nav_username);
        TextView navUserMail = headerView.findViewById(R.id.nav_gmail);
        ImageView navUserPhot = headerView.findViewById(R.id.nav_user_photo);

        navUserMail.setText(Usuario.getEmail());
        navUsername.setText(Usuario.getName());

        // now we will use Glide to load user image
        // first we need to import the library

        // Glide.with(this).load(currentUser.getPhotoUrl()).into(navUserPhot);




    }
    private void setToolbar() {
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setFragmentByDefault() {
        changeFragment(new EventoFragment(), navigationView.getMenu().getItem(0));
    }

    private void changeFragment(Fragment fragment, MenuItem item) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
        item.setChecked(true);
        getSupportActionBar().setTitle(item.getTitle());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                // abrir el menu lateral
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
