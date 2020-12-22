package com.smv.implicitintent_camera;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private Button buttonSlikaj;
    private ImageView imageViewSlika;

    private static final int REQUEST_CAMERA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewSlika = findViewById(R.id.imageViewSlika);
        buttonSlikaj = findViewById(R.id.buttonSlikaj);

        buttonSlikaj.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if(intent.resolveActivity(getPackageManager()) != null)
                    //rabimo requet code, priporočena praksa je, da ustvarimo konstanto (final)
                    //deklariramo tako, da je dostopna v celotnem razredu
                    startActivityForResult(intent, REQUEST_CAMERA);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK)
        {
            Bitmap slika = (Bitmap) data.getExtras().get("data");
            imageViewSlika.setImageBitmap(slika);
        }
        else
        {
            Toast.makeText(this, "Ne morem naložiti slike!", Toast.LENGTH_SHORT).show();
        }
    }
}