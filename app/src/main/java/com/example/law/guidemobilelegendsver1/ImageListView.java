package com.example.law.guidemobilelegendsver1;

/**
 * Created by law on 1/29/16.
 */

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class ImageListView extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView listView;
    public String JSON_STRING;
public  String link;
   public static String aw;
    public static final String GET_IMAGE_URL = "http://gopacitan.co.id/guideMobileLegends/get_alldata.php";
    public GetAlImages getAlImages;

    public static String name_hero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //listView = (ListView) findViewById(R.id.listView);
        //  listView.setOnItemClickListener(this);



        getURLs();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        listView = (ListView) findViewById(R.id.listView);
        //registerForContextMenu(listView);
        //listView.setOnCreateContextMenuListener(this);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){


            @Override
            public void onItemClick(AdapterView<?>parent, View view, int position, long id) {

                // listView.getItemAtPosition(position);
                name_hero = listView.getItemAtPosition(position).toString();
                ///  name_hero.equals(listView.getItemAtPosition(position));

                // config.hero_name = name_hero.toString();
                // Toast.makeText(ImageListView.this,name_hero, Toast.LENGTH_SHORT).show();
                //  Intent intent;
                //    intent = new Intent(ImageListView.this,Main_web.class);
                //   intent.putExtra(config.TAG_link,link);

                Intent intent;
                intent = new Intent(ImageListView.this,Main_web.class);
                intent.putExtra(config.id_hero,name_hero);
                gethero();
                intent.putExtra(aw,link);

                //  intent.putExtra(config.hero_name,name_hero);


                //     startActivity(intent);

                //based on item add info to intent


                //   Toast.makeText(ImageListView.this, config.hero_name, Toast.LENGTH_SHORT).show();

            }

        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_youtube) {

            try {


                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + "2MSMSuPn-is"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            } catch (ActivityNotFoundException e) {

                // youtube is not installed.Will be opened in other available apps

                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/watch?v=2MSMSuPn-is" + id));
                startActivity(i);
            }
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void getImages() {
        class GetImages extends AsyncTask<Void, Void, Void> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ImageListView.this, "Downloading images...", "Please wait...", false, false);
            }

            @Override
            protected void onPostExecute(Void v) {
                super.onPostExecute(v);
                loading.dismiss();
                //   Toast.makeText(ImageListView.this,GetAlImages.bitmaps.toString().trim(),Toast.LENGTH_LONG).show();
                CustomList customList = new CustomList(ImageListView.this,GetAlImages.id_hero ,GetAlImages.nama, GetAlImages.bitmaps);

                //     ListAdapter adapter = new SimpleAdapter(
                //             ImageListView.this,GetAlImages.list, R.layout.list_item,
                //                new Bitmap[] new String[](config.TAG_Id_nama_konveksi,config.TAG_Nama_konveksi,GetAlImages.bitmaps},
                //                 new int[]{R.id.status, R.id.name,R.id.imageView});
                listView.setAdapter(customList);
                //   listView.setAdapter();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    getAlImages.getAllImages();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        GetImages getImages = new GetImages();
        getImages.execute();
    }

    private void getURLs() {
        class GetURLs extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ImageListView.this, "Loading...", "Please Wait...", true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                getAlImages = new GetAlImages(s);
                getImages();
            }

            @Override
            protected String doInBackground(String... strings) {
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }

                    return sb.toString().trim();

                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetURLs gu = new GetURLs();
        gu.execute(GET_IMAGE_URL);
    }


    /**
     * @Override public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
     * String ap = "a";
     * Intent intent = new Intent(this, Edit_Konveksi.class);
     * intent.putExtra(config.idget,i);
     * intent.putExtra(config.konv_ID,getAlImages.idgambar[i]);
     * intent.putExtra(config.ambilgambar,ap);
     * startActivity(intent);
     * finish();
     * }
     **/

   private void gethero() {
        class Gethero extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ImageListView.this, "Fetching...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);

            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(config.GET_data,name_hero);
                return s;
            }
        }
        Gethero ge = new Gethero();
        ge.execute();

    }

    private void showEmployee(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);

           link = c.getString(config.TAG_link);
         //   Toast.makeText(ImageListView.this, link, Toast.LENGTH_SHORT).show();
        //    name_hero  = c.getString(config.hero_name);

            Intent intent;
            intent = new Intent(ImageListView.this,Main_web.class);
            //intent.putExtra(config.id_hero,name_hero);

            intent.putExtra("aw",link);
       //     Toast.makeText(ImageListView.this, link, Toast.LENGTH_SHORT).show();
            startActivity(intent);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}