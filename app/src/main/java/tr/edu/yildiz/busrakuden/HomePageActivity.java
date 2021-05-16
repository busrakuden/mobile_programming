package tr.edu.yildiz.busrakuden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePageActivity extends AppCompatActivity {

    private Button bEkle;
    private Button bListele;
    private Button bOlustur;
    private Button bAyarlar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        String email = getIntent().getExtras().get("email").toString();
        bEkle = findViewById(R.id.bEkle);
        bListele = findViewById(R.id.bListele);
        bOlustur = findViewById(R.id.bOlustur);
        bAyarlar = findViewById(R.id.bAyarlar);

        bEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this,AddQuestionActivity.class);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });

        bListele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, ListQuestionsActivity.class);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });

        bAyarlar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, ExamSettingsActivity.class);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });

        bOlustur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, CreateExamActivity.class);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });


    }
}