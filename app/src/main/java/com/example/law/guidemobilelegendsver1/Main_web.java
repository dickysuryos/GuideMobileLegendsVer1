package com.example.law.guidemobilelegendsver1;

/**
 * Created by law on 5/18/2017.
 */


        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.support.design.widget.AppBarLayout;
        import android.support.design.widget.CollapsingToolbarLayout;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.text.TextUtils;
        import android.view.MotionEvent;
        import android.view.View;
        import android.webkit.WebView;
        import android.webkit.WebViewClient;
        import android.widget.ImageView;
        import android.widget.ProgressBar;
        import android.widget.Toast;

        import com.bumptech.glide.Glide;
        import com.bumptech.glide.load.engine.DiskCacheStrategy;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

public class Main_web extends AppCompatActivity {

    public String postUrl;
    private String Url;
    private WebView webView;
    private ProgressBar progressBar;
    private ImageView imgHeader;
    public String link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_web);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();
        postUrl = bundle.getString("aw");

        Toast.makeText(Main_web.this,postUrl, Toast.LENGTH_SHORT).show();

       // postUrl = "http://mobilelegendsbangbang.com/initiate-tigreal-guide";
      //  Toast.makeText(Main_web.this, postUrl, Toast.LENGTH_SHORT).show();

        webView = (WebView) findViewById(R.id.webView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        imgHeader = (ImageView) findViewById(R.id.backdrop);





       // Toast.makeText(Main_web.this,postUrl, Toast.LENGTH_SHORT).show();
        webView.loadUrl(postUrl);
        webView.setHorizontalScrollBarEnabled(false);
        //

        initCollapsingToolbar();

      //  getheros();
     //   webView.getSettings().setJavaScriptEnabled(true);


    }



    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the txtPostTitle when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle("Web View");
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });

        // loading toolbar header image
        Glide.with(getApplicationContext()).load("http://169.254.187.195/guideMobileLegend/hero_image/header.png")
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgHeader);
    }
    /**
    private void getheros() {
        class Gethero extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Main_web.this, "Fetching...", "Wait...", false, false);
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
                String s = rh.sendGetRequestParam(config.GET_data,2);
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


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
**/

}