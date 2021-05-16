package tr.edu.yildiz.busrakuden;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText eMail;
    private EditText ePassword;
    private Button eLogin;
    private Button eSignup;
    private TextView eCount;
    List<String> UserNames=new ArrayList<String>();
    List<String> UserSurnames=new ArrayList<String>();
    List<String> UserEmails=new ArrayList<String>();
    List<String> UserPhones=new ArrayList<String>();
    List<String> UserBdates=new ArrayList<String>();
    public List<String> getUserNames() { return UserNames; }
    public List<String> setUserNames(List<String> newUsername) { return this.UserNames=newUsername; }
    List<String> Passwords=new ArrayList<String>();
    public List<String> getPasswords() { return Passwords; }
    public List<String> setPasswords(List<String> newPassword) { return this.UserNames=newPassword; }


    String email;
    String getEmail() { return email; }
    String setEmail(String newEmail) { return this.email=newEmail; }
    boolean isValid = false;
    private int falseCounter = 3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String pathname=getFilesDir()+"/userinfos.txt";
        File file = new File(pathname); //initialize File object and passing path as argument

        boolean result;
        try
        {
            result = file.createNewFile();  //creates a new file
            if(result)      // test if successfully created a new file
            {
                System.out.println("file created "+file.getCanonicalPath()); //returns the path string
                 FileOutputStream fos=new FileOutputStream(pathname, true);  // true for append mode

                String str="busrakuden@gmail.com\tBüşra\tKüden\t12345\t5459054335\t11/08/1998\n" +
                        "deneme@gmail.com\tDeneme\tDeneme\t3214\t5384630466\t21/12/2000\n" +
                        "admin@gmail.com\tAdmin\tAdmin\t98765\t5442316587\t5/02/1987";      //str stores the string which we have entered
                byte[] b= str.getBytes();       //converts string into bytes
                fos.write(b);           //writes bytes into file
                fos.close();
            }
            else
            {
                System.out.println("File already exist at location: "+file.getCanonicalPath());

            }

        }
        catch (IOException e)
        {
            e.printStackTrace();    //prints exception if any
        }
        File fileEvents = new File(pathname);
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileEvents));
            String line;

            while ((line = br.readLine()) != null) {
                String[] splitString = line.split("\t");
                System.out.println("LİNE: "+splitString[5]);

                UserNames.add(splitString[1]);
                UserSurnames.add(splitString[2]);
                UserEmails.add(splitString[0]);
                UserPhones.add(splitString[4]);
                UserBdates.add(splitString[5]);
                Passwords.add(splitString[3]);

            }
            br.close();
        } catch (IOException e) {
            System.out.println("HATAAAAAA");
        }
        System.out.println("Emails: "+UserEmails);

        /*UserNames.add("Admin");
        Passwords.add("123456");
        UserNames.add("Busra");
        Passwords.add("123");*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eMail = findViewById(R.id.etEmail);
        ePassword = findViewById(R.id.etPassword);
        eLogin = findViewById(R.id.btnSignin);
        eSignup = findViewById(R.id.btnSignup);

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputEmail = eMail.getText().toString();
                String inputPassword = ePassword.getText().toString();

                if (inputEmail.isEmpty() || inputPassword.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter all the details correctly!",Toast.LENGTH_SHORT).show();
                }else{
                    isValid = validate(inputEmail,inputPassword);
                    if (!isValid){
                        falseCounter --;
                        Toast.makeText(MainActivity.this,"Incorrect credentials entered!"+falseCounter+" left.",Toast.LENGTH_SHORT).show();
                        if(falseCounter == 0){
                            eLogin.setEnabled(false);
                        }
                    }else{
                        setEmail(inputEmail.substring( 0, inputEmail.indexOf("@")));
                        System.out.println("Email: "+email);
                        Toast.makeText(MainActivity.this,"Login successful!",Toast.LENGTH_SHORT).show();
                        //Add new activity.
                        Intent intent = new Intent(MainActivity.this,HomePageActivity.class);
                        intent.putExtra("email",email);
                        startActivity(intent);
                    }
                }
            }
        });

        eSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean validate(String name, String password){
        int i=0;
        System.out.println(UserEmails);
        System.out.println(Passwords);
        while (i<UserNames.size()){
            if( name.equals(UserEmails.get(i)) && password.equals(Passwords.get(i))){
                return true;
            }
            i++;
        }

        return false;
    }
}