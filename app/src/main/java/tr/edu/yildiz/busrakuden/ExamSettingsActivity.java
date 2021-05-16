package tr.edu.yildiz.busrakuden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ExamSettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public String zorluk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_settings);

        EditText eSure = findViewById(R.id.etSure);
        EditText ePuan = findViewById(R.id.etPuan);
        Button bAyarla = findViewById(R.id.bAyarla);

        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        zorluk = spinner.getSelectedItem().toString();
        System.out.println("ZORLUK: "+zorluk);
        bAyarla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences settings = getSharedPreferences("preference_name",MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("sure",eSure.getText().toString());
                editor.putString("puan",ePuan.getText().toString());
                zorluk = spinner.getSelectedItem().toString();
                System.out.println("ZORLUK: "+zorluk);
                editor.putString("zorluk",zorluk);
                System.out.println("ZORLUK: "+zorluk);
                editor.commit();
                System.out.println("ZORLUK: "+zorluk);
                Intent intent = new Intent(ExamSettingsActivity.this,HomePageActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text= parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}