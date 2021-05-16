package tr.edu.yildiz.busrakuden;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class SignupActivity extends AppCompatActivity {
    MainActivity mainActivity = new MainActivity();
    private EditText name;
    private EditText surname;
    private EditText sEmail;
    private EditText phone;
    private EditText mBDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private EditText password;
    private EditText repassword;
    private Button eSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = findViewById(R.id.etName);
        surname = findViewById(R.id.etSurname);
        sEmail = findViewById(R.id.etSignupEmail);
        phone = findViewById(R.id.etPhone);
        mBDate = (EditText) findViewById(R.id.etBDate);
        eSignup = findViewById(R.id.btn_Signup);
        password = findViewById(R.id.etSignupPassword);
        repassword = findViewById(R.id.etRe_enterPassword);


        mBDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        SignupActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d("SignupActivity", "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                mBDate.setText(date);
            }
        };

        eSignup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String newName = name.getText().toString();
                String newSurname = surname.getText().toString();
                String newEmail = sEmail.getText().toString();
                String newPhone = phone.getText().toString();
                String newBdate = mBDate.getText().toString();
                String newPassword = password.getText().toString();
                String newRepassword = repassword.getText().toString();
                if((newName.equals("")) || (newSurname.equals("")) || (newEmail.equals("")) || (newPhone.equals("")) || (newBdate.equals("")) || (newPassword.equals("")) || (newRepassword.equals(""))){
                    Toast.makeText(SignupActivity.this,"Fill in all blank!",Toast.LENGTH_SHORT).show();
                }else{
                    if (newPassword.equals(newRepassword)){
                        try{
                            String pathname=getFilesDir()+"/userinfos.txt";
                            FileOutputStream fos=new FileOutputStream(pathname, true);  // true for append mode

                            String str="\n"+newEmail+"\t"+newName+"\t"+newSurname+"\t"+newPassword+"\t"+newPhone+"\t"+newBdate;      //str stores the string which we have entered
                            byte[] b= str.getBytes();       //converts string into bytes
                            fos.write(b);           //writes bytes into file
                            fos.close();
                        }catch (IOException e){
                            System.out.println(e);
                        }
                        String tmp = mainActivity.getEmail();
                        System.out.println("Email: "+tmp);
                        Intent intent = new Intent(SignupActivity.this,MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(SignupActivity.this,"Password and Re-Enter Password must be same!",Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }
}