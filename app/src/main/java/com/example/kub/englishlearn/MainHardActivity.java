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

import java.util.Locale;

public class MainHardActivity extends AppCompatActivity implements View.OnClickListener{
    TextToSpeech toSpeech;
    ImageButton bEspeak;
    Button mbtnPre,mbtnNext;
    TextView tvE,tvT;
    ImageView im1;
    String text;
    int result;
    int count=0;
    int  imageid[]={R.drawable.bowl,R.drawable.hamburger,R.drawable.family,R.drawable.play,
            R.drawable.read,R.drawable.horse,R.drawable.cry,R.drawable.clean,
            R.drawable.sit,R.drawable.sad,R.drawable.run,R.drawable.truck,
            R.drawable.listen,
            R.drawable.win,R.drawable.star,R.drawable.love,
            R.drawable.dinosaur,R.drawable.do_homework,R.drawable.cook,
            R.drawable.angry,R.drawable.buy,R.drawable.sleep,R.drawable.turtle,R.drawable.repair};
    String word[]={"Bowl","Hamburger","Family","Play","Read","Horse","Cry","Clean","Sit","Sad",
            "Run","Truck","Listen","Win","Star","Love","Dinasaur","Homework","Cook","Angry","Buy","Sleep","Turtle","Repair"};
    String wordT[]={"Kase","Hamburger","Aile","Oyun Oynamak",
            "Kitap Okumak","At","Ağlamak","Temizlemek","Oturmak","Üzgün","Koşmak","Kamyon","Dinlemek","Kazanmak",
            "Yıldız","Sevmek","Dinazor","Ev Ödevi","Yemek Yapmak","Kızgın","Satın Almak","Uyumak","Kaplumbağa","Tamir Etmek"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hard);
        bEspeak=(ImageButton)findViewById(R.id.imageButtonEnglish);
        im1=(ImageView)findViewById(R.id.imageVArray) ;
        tvE=(TextView) findViewById(R.id.textViewEnglish);
        tvT=(TextView)findViewById(R.id.textViewTurkish);
        mbtnNext=(Button)findViewById(R.id.btnNext);
        mbtnPre=(Button)findViewById(R.id.btnPrevious);

        mbtnPre.setOnClickListener(this);
        mbtnNext.setOnClickListener(this);
        toSpeech=new TextToSpeech(MainHardActivity.this, new TextToSpeech.OnInitListener() {
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
            Toast.makeText(getApplicationContext(), "Kelimeler Bitti!",
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
