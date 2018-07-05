package com.example.kub.englishlearn;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Locale;

public class MainMediumActivity extends AppCompatActivity implements View.OnClickListener {
    TextToSpeech toSpeech,toSpeechT;
    ImageButton bEspeak;
    Button mbtnPre,mbtnNext;
    TextView tvE,tvT;
    ImageView im1;
    String text;
    int result;
    int count=0;
    int  imageid[]={R.drawable.bee,R.drawable.coup,R.drawable.doctor,
            R.drawable.dove,R.drawable.house,
    R.drawable.eyeglasses,R.drawable.fish,R.drawable.giraffe,R.drawable.snowman,
            R.drawable.goose,
            R.drawable.loin,R.drawable.mouse,
            R.drawable.penguin,R.drawable.rabbit,R.drawable.student,
            R.drawable.twin,R.drawable.tiger,R.drawable.sheep};
    String word[]={"Bee","Coup","Doctor","Dove","House","Eyeglasses",
            "Fish","Giraffe","Snowman","Goose","Lion","Mouse","Penguin","Rabbit","Student","Twin","Tiger","Sheep"};
    String wordT[]={"Arı","Kupa","Doktor","Güvercin",
            "Ev","Gözlük","Balık","Zürafa","Kardan Adam","Kaz","Aslan","Fare","Penguen","Tavşan","Öğrenci",
            "İkiz","Kaplan","Koyun"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_medium);
        bEspeak=(ImageButton)findViewById(R.id.imageButtonEnglish);
        im1=(ImageView)findViewById(R.id.imageVArray) ;
        tvE=(TextView) findViewById(R.id.textViewEnglish);
        tvT=(TextView)findViewById(R.id.textViewTurkish);

        mbtnNext=(Button)findViewById(R.id.btnNext);
        mbtnPre=(Button)findViewById(R.id.btnPrevious);

        mbtnPre.setOnClickListener(this);
        mbtnNext.setOnClickListener(this);
        toSpeech=new TextToSpeech(MainMediumActivity.this, new TextToSpeech.OnInitListener() {
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
                        Toast.makeText(getApplicationContext(), "Daha fazla geri gidemezsiniz!", Toast.LENGTH_LONG).show();

                    }
                    im1.setImageResource(imageid[count]);
                    tvE.setText(word[count]);
                    tvT.setText(wordT[count]);

                    break;
                case  R.id.btnNext:
                    //ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
                    //toneG.startTone(ToneGenerator.TONE_CDMA_ABBR_INTERCEPT, 200);
                    count=count+1;
                    im1.setImageResource(imageid[count]);
                    tvE.setText(word[count]);
                    tvT.setText(wordT[count]);

                    break;
            }
        }catch (Exception e) {
            // This will catch any exception, because they are all descended from Exception
            System.out.println("Error " + e.getMessage());
            Toast.makeText(getApplicationContext(), "Kelimeler Bitti! Diğer Bölüme Geçebilirsiniz",
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
