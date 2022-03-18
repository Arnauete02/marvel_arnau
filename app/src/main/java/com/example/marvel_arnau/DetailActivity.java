package com.example.marvel_arnau;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    ImageView urlToImage2;
    TextView title2, publishedAt2, description2, content, authorname;
    Button web, author, contact;
    WebView urlvideo;
    ViewPager media;
    RecyclerView commentsRecycler;

    String titleS;
    String descriptionS;
    String publishedAtS;
    String urlToImageS;
    String contentS;
    String authornameS;
    String urlS;
    String authorlinkS;
    String contactS;
    String urlvideoS;
    String[] mediaS;
    String[] commentsS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        hook();

        getData();
        setData();

        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlS));
                startActivity(intent);
            }
        });

        author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(authorlinkS));
                startActivity(intent);
            }
        });

        authorname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(authorlinkS));
                startActivity(intent);
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                String[] TO = {contactS};

                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject of the mail");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "This is the text to send with the mail");

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(DetailActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();}
            }
        });
    }

    private void hook(){
        urlToImage2 = findViewById(R.id.urlToImage2);
        title2 = findViewById(R.id.title2);
        publishedAt2 = findViewById(R.id.publishedAt2);
        description2 = findViewById(R.id.description2);
        content = findViewById(R.id.content);
        authorname = findViewById(R.id.authorname);
        web = findViewById(R.id.web);
        author = findViewById(R.id.author);
        contact = findViewById(R.id.contact);
        urlvideo = findViewById(R.id.urlvideo);
        media = (ViewPager) findViewById(R.id.media);
        commentsRecycler = findViewById(R.id.commentsRecycler);
    }

    private void getData(){
        if (getIntent().hasExtra("title")){
            titleS = getIntent().getStringExtra("title");
            descriptionS = getIntent().getStringExtra("description");
            publishedAtS = getIntent().getStringExtra("publishedAt");
            urlToImageS = getIntent().getStringExtra("urlToImage2");
            contentS = getIntent().getStringExtra("content");
            authornameS = getIntent().getStringExtra("authorname");
            urlS = getIntent().getStringExtra("url");
            authorlinkS = getIntent().getStringExtra("authorlink");
            contactS = getIntent().getStringExtra("contact");
            urlvideoS = getIntent().getStringExtra("urlvideo");
            mediaS = getIntent().getStringArrayExtra("media");
            commentsS = getIntent().getStringArrayExtra("comments");
        } else {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData() {
        Picasso.get().load(urlToImageS)
                .fit()
                .centerCrop()
                .into(urlToImage2);
        title2.setText(titleS);
        description2.setText(descriptionS);
        publishedAt2.setText(publishedAtS);
        content.setText(contentS);
        authorname.setText(authornameS);

        int width = Resources.getSystem().getDisplayMetrics().widthPixels;
        int height = width * 9 / 16;
        String video = "<html> <body>Youtube video/. <br> <iframe class=\"youtube-player\"" +
                "type=\"text/html\" width=\"" + width + "\" height=\"" + height + "\" src=\"" + urlvideoS +  "\"" +
                "frameborder=\"0\">//body>//html>";
        urlvideo.loadData(video, "text/html", "utf-8");
        urlvideo.getSettings().setJavaScriptEnabled(true);
        urlvideo.setWebChromeClient(new WebChromeClient());
        urlvideo.getSettings().setLoadWithOverviewMode(true);
        urlvideo.getSettings().setUseWideViewPort(true);

        ImageAdapter imageAdapter = new ImageAdapter(this, mediaS);
        media.setAdapter(imageAdapter);
        //comment
    }
}