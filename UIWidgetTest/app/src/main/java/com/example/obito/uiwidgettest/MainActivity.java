package com.example.obito.uiwidgettest;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private ImageView imageView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar=(ProgressBar)findViewById(R.id.progress_bar);
        imageView=(ImageView)findViewById(R.id.image_view);
        editText=(EditText)findViewById(R.id.edit_text);
        Button button=(Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView=(TextView)findViewById(R.id.text_view);String inputText= editText.getText().toString();
                Toast.makeText(MainActivity.this,inputText, Toast.LENGTH_SHORT).show();
                imageView.setImageResource(R.drawable.img_2);
                int progress=progressBar.getProgress();
                progress=progress+10;
                progressBar.setProgress(progress);
//                if(progressBar.getVisibility()== View.GONE){
//                    progressBar.setVisibility(View.VISIBLE);
//                }
//                else{
//                    progressBar.setVisibility(View.GONE);
//                }
//                AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
//                dialog.setTitle("This is Dialog");
//                dialog.setMessage("Something important.");
//                dialog.setCancelable(false);
//
//                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                dialog.show();
                ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("This is ProgressDialog");
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(true);
                progressDialog.show();
            }
        });
    }
}
