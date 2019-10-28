package edith.gomez.cajadepandora.ui.extras;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

import edith.gomez.cajadepandora.R;

public class VistaWeb extends AppCompatActivity {
    public static final String SITIO_WEB = "sitio_web";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_web);
        //Vincular componentes
        Toolbar toolbar = findViewById(R.id.toolbarVistaWeb);
        WebView vistaWeb = findViewById(R.id.vistaWeb);
        //Toolbar
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //WebView
        String url = getIntent().getStringExtra(SITIO_WEB);
        if (url == null || url.isEmpty()) finish();

        WebSettings configWeb = vistaWeb.getSettings();
        configWeb.setJavaScriptEnabled(true);

        vistaWeb.loadUrl(url);
        vistaWeb.setHorizontalScrollBarEnabled(false);
        vistaWeb.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        vistaWeb.setBackgroundColor(128);
        vistaWeb.setWebViewClient(new ClienteWeb());
        vistaWeb.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }

    public void cargando(){
        WebView vistaWeb = findViewById(R.id.vistaWeb);
        ProgressBar loadWeb = findViewById(R.id.loadWeb);
        TextView txtWeb = findViewById(R.id.txtWeb);

        vistaWeb.setVisibility(View.INVISIBLE);
        loadWeb.setVisibility(View.VISIBLE);
        txtWeb.setVisibility(View.VISIBLE);
    }

    public void cargaTerminada(){
        WebView vistaWeb = findViewById(R.id.vistaWeb);
        ProgressBar loadWeb = findViewById(R.id.loadWeb);
        TextView txtWeb = findViewById(R.id.txtWeb);

        vistaWeb.setVisibility(View.VISIBLE);
        loadWeb.setVisibility(View.INVISIBLE);
        txtWeb.setVisibility(View.INVISIBLE);
    }

    private class ClienteWeb extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            String url = request.getUrl().toString();
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            cargando();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            cargaTerminada();
        }
    }

}
