package com.example.kub.englishlearn;

import android.media.AudioManager;
import android.media.ToneGenerator;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainSimpleActivity extends AppCompatActivity implements View.OnClickListener {
    TextToSpeech toSpeech,toSpeechT;
    ImageButton bEspeak,bTspeak;
    ImageButton btnNext,btnBack;
    Button mbtnPre,mbtnNext;
    TextView tvE,tvT;
    ImageView im1;
    String text;
    int result;


    int count=0;
    int imageid[]={R.drawable.apple,
            R.drawable.baby,R.drawable.banana,
            R.drawable.book,R.drawable.car,R.drawable.carrot,
            R.drawable.cat,R.drawable.clock,R.drawable.computer,
            R.drawable.dog,R.drawable.elephant,R.drawable.guitar,R.drawable.icecream,
            R.drawable.lemon,R.drawable.monkey,R.drawable.tooth,R.drawable.tree};
    String word[]={"Apple","Baby","Banana","Book","Car","Carrot",
            "Cat","Clock","Computer","Dog","Elephant","Guitar","İce Cream","Lemon","Monkey","Tooth","Tree"};
String wordT[]={"Elma","Bebek","Muz","Kitap",
        "Araba","Havuç","Kedi","Saat","Bilgisayar","Köpek","Fil","Gitar","Dondurma","Limon","Maymun","Diş","Ağaç"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_simple);
        bEspeak=(ImageButton)findViewById(R.id.imageButtonEnglish);
        //bTspeak=(ImageButton)findViewById(R.id.imageBTurkish);
        im1=(ImageView)findViewById(R.id.imageVArray) ;
        tvE=(TextView) findViewById(R.id.textViewEnglish);
        tvT=(TextView)findViewById(R.id.textViewTurkish);
        mbtnNext=(Button)findViewById(R.id.btnNext);
        mbtnPre=(Button)findViewById(R.id.btnPrevious);

        mbtnPre.setOnClickListener(this);
        mbtnNext.setOnClickListener(this);
        toSpeech=new TextToSpeech(MainSimpleActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS){
                    result=toSpeech.setLanguage(Locale.UK);

                }
                else{
                    Toast.makeText(getApplicationContext(),"Feature not supported in your device ",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        try{
        switch (v.getId()){
            case R.id.btnPrevious:
                count=count-1;
                if(count<0){
                    Toast.makeText(getApplicationContext(), "Daha fazla geri gidemezsiniz!",
                            Toast.LENGTH_LONG).show();

                }
                im1.setImageResource(imageid[count]);
                tvE.setText(word[count]);
                tvT.setText(wordT[count]);

                break;
            case  R.id.btnNext:

                count=count+1;
                im1.setImageResource(imageid[count]);
                tvE.setText(word[count]);
                tvT.setText(wordT[count]);
                break;
        }
    }catch (Exception e) {
            // This will catch any exception, because they are all descended from Exception
            System.out.println("Error " + e.getMessage());
            Toast.makeText(getApplicationContext(), "Kelimeler Bitti! Diğer Bölüme Geçebilirsiniz.",
                    Toast.LENGTH_LONG).show();

        }
    }


    public  void  TTE(View view){
        if (result == TextToSpeech.LANG_MISSING_DATA|| result==TextToSpeech.LANG_NOT_SUPPORTED) {
            Toast.makeText(getApplicationContext(),"Feature not supported in your device ",Toast.LENGTH_SHORT).show();
        }
        else{
            text=tvE.getText().toString();
            toSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);

        }
    }
    protected void onDestroy(){
        super.onDestroy();
        if(toSpeech!=null){
            toSpeech.stop();
            toSpeech.shutdown();
        }
    }


}
