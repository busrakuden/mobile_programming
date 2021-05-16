package tr.edu.yildiz.busrakuden;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddQuestionActivity extends AppCompatActivity {
    private EditText eSoru;
    private EditText eAsikki;
    private EditText eBsikki;
    private EditText eCsikki;
    private EditText eDsikki;
    private EditText eEsikki;
    private EditText eCevap;
    private Button bEkle;

    public String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MainActivity mainActivity = new MainActivity();
        String email = getIntent().getExtras().get("email").toString();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        eSoru = findViewById(R.id.etSoru);
        eAsikki = findViewById(R.id.etAsikki);
        eBsikki = findViewById(R.id.etBsikki);
        eCsikki = findViewById(R.id.etCsikki);
        eDsikki = findViewById(R.id.etDsikki);
        eEsikki = findViewById(R.id.etEsikki);
        eCevap = findViewById(R.id.etCevap);
        bEkle = findViewById(R.id.bEkle);

        bEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newSoru = eSoru.getText().toString();
                String newAsikki = eAsikki.getText().toString();
                String newBsikki = eBsikki.getText().toString();
                String newCsikki = eCsikki.getText().toString();
                String newDsikki = eDsikki.getText().toString();
                String newEsikki = eEsikki.getText().toString();
                String newCevap = eCevap.getText().toString();
                if ((newSoru.equals("")) || (newAsikki.equals(""))|| (newBsikki.equals(""))|| (newCsikki.equals(""))|| (newDsikki.equals(""))|| (newEsikki.equals(""))|| (newCevap.equals(""))){
                    Toast.makeText(AddQuestionActivity.this,"Fill in all fields!",Toast.LENGTH_SHORT).show();

                }else{
                    String pathname=getFilesDir()+"/"+email+".txt";
                    File file = new File(pathname);
                    boolean result;
                    try{
                        result = file.createNewFile();  //creates a new file
                        if(result){
                            System.out.println("file created "+file.getCanonicalPath()); //returns the path string
                            FileOutputStream fos=new FileOutputStream(pathname, true);  // true for append mode

                            String str=newSoru+"\t"+newAsikki+"\t"+newBsikki+"\t"+newCsikki+"\t"+newDsikki+"\t"+newEsikki+"\t"+newCevap;      //str stores the string which we have entered
                            byte[] b= str.getBytes();       //converts string into bytes
                            fos.write(b);           //writes bytes into file
                            fos.close();
                        }
                        else{
                            System.out.println("File already exist at location: "+file.getCanonicalPath());
                            FileOutputStream fos=new FileOutputStream(pathname, true);  // true for append mode

                            String str="\n"+newSoru+"\t"+newAsikki+"\t"+newBsikki+"\t"+newCsikki+"\t"+newDsikki+"\t"+newEsikki+"\t"+newCevap;      //str stores the string which we have entered
                            byte[] b= str.getBytes();       //converts string into bytes
                            fos.write(b);           //writes bytes into file
                            fos.close();
                        }

                    }  catch (IOException e){
                        e.printStackTrace();    //prints exception if any
                    }


                    Intent intent = new Intent(AddQuestionActivity.this,HomePageActivity.class);
                    startActivity(intent);
                }

            }

        });


    }
}