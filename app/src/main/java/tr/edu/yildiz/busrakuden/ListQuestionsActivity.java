package tr.edu.yildiz.busrakuden;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListQuestionsActivity extends AppCompatActivity {

    private Button back;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<String> list = new ArrayList<String>();
    private RecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_questions);
        back = findViewById(R.id.back);
        System.out.println("List: "+list);

        String email = getIntent().getExtras().get("email").toString();

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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListQuestionsActivity.this,HomePageActivity.class);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
    }
}