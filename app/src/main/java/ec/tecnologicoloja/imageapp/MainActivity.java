package ec.tecnologicoloja.imageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;


public class MainActivity extends AppCompatActivity {

    private Button btnCamara;
    private ImageView visor;
    private TextView NoImgSelec;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    boolean control = false;
    private  final String ruta_foto = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/ISTLImage";

    private File file = new File(ruta_foto);
    String imagenFileName = "imagenApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        file.mkdir();


        btnCamara = (Button) findViewById(R.id.btnCamara);
        visor = (ImageView) findViewById(R.id.iv_visor);
        NoImgSelec = (TextView) findViewById(R.id.textViewNoImageSelec);

        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camara();
                if (NoImgSelec.getVisibility() == View.VISIBLE){
                    NoImgSelec.setVisibility(View.GONE);
                    return;
                }
            }
        });
    }

    //Con este codigo activo la camara
    private void camara(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //La activamos mediante un intent
        if (intent.resolveActivity(getPackageManager())!=null){ //Validamos si esta disponible
            startActivityForResult(intent,1);
        }
    }
    //Con este metodo bloquesmos la captura de la imagen
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==1 && resultCode == RESULT_OK ){
            Bundle extras = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extras.get("data");
            visor.setImageBitmap(imgBitmap); //mostramos la imagen en el ImageView
        }
    }
}