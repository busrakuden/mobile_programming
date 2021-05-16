package tr.edu.yildiz.busrakuden;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateExamActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<String> list = new ArrayList<String>();
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exam);
        SharedPreferences settings = getSharedPreferences("preference_name",MODE_PRIVATE);
        EditText eSure = findViewById(R.id.etSure2);
        EditText ePuan = findViewById(R.id.etPuan2);
        String sure = settings.getString("sure",null);
        eSure.setText(sure);
        String puan = settings.getString("puan",null);
        ePuan.setText(puan);

        System.out.println("List: "+list);

        String email = getIntent().getExtras().get("email").toString();
        System.out.println("Email: "+email);
        recyclerView = findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        String pathname=getFilesDir()+"/"+email+".txt";
        File fileEvents = new File(pathname);
        if (fileEvents.exists()){
            try {
                BufferedReader br = new BufferedReader(new FileReader(fileEvents));
                String line;

                while ((line = br.readLine()) != null) {
                    String[] splitString = line.split("\t");
                    String question = splitString[0]+"\nA) "+splitString[1]+"\nB) "+splitString[2]
                            +"\nC) "+splitString[3]+"\nD) "+splitString[4]+"\nE) "+splitString[5]+"\nDoğru Cevap: "+splitString[6];
                    System.out.println("Question: "+question);
                    list.add(question);
                    System.out.println("List: "+list);

                }
                br.close();
            } catch (IOException e) {
                System.out.println("HATAAAAAA");
            }
        }else{
            list.add("Eklenmiş Soru bulunmamaktadır.");
        }


        adapter = new RecyclerAdapter(list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
}