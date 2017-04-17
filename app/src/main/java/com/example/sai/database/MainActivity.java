package com.example.sai.database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

    TextView mName;
    TextView mPhoneNumber;
    TextView mId;

    Button mRead;
    Button mUpdate;
    Button mCreate;
    Button mDelete;

    EditText Name;
    EditText PhoneNumber;
    EditText Id;


    DatabaseHandler db = new DatabaseHandler(this);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText)findViewById(R.id.nameedit);
        PhoneNumber = (EditText)findViewById(R.id.phonenumberedit);
        Id = (EditText)findViewById(R.id.idedit);

        mName = (TextView)findViewById(R.id.name);
        mPhoneNumber = (TextView)findViewById(R.id.phonenumber);
        mId = (TextView)findViewById(R.id.id);

        mRead = (Button)findViewById(R.id.read);
        mCreate = (Button)findViewById(R.id.create);
        mDelete = (Button)findViewById(R.id.delete);
        mUpdate = (Button)findViewById(R.id.update);

        /**
         * CRUD Operations
         * */
        // Inserting Contacts


        mCreate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                db.addContact(new Contact(Integer.parseInt(Id.getText().toString()),Name.getText().toString(),PhoneNumber.getText().toString()));
                Toast.makeText(MainActivity.this, "Contact Created/Posted to database", Toast.LENGTH_SHORT).show();
            }
        });

//
//        Log.d("Insert: ", "Inserting ..");
//        db.addContact(new Contact("Ravi", "9100000000"));
//        db.addContact(new Contact("Srinivas", "9199999999"));
//        db.addContact(new Contact("Tommy", "9522222222"));
//        db.addContact(new Contact("Karthik", "9533333333"));
//        db.addContact(new Contact("Karthik", "9533333333"));
//        db.addContact(new Contact("Karthik", "9533333333"));

        // Reading all contacts
//        Log.d("Reading: ", "Reading all contacts..");
//        List<Contact> contacts = db.getAllContacts();

        mRead.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i("hey1","hey1");
                Contact cn = db.getContact(Integer.parseInt(Id.getText().toString()));
                Log.i("hey2","hey2");
                if(cn == null)
                    Toast.makeText(MainActivity.this, "Contact Not Found!", Toast.LENGTH_SHORT).show();
                else
                {
                    mId.setText(cn.getID() + "");
                    mName.setText(cn.getName());
                    mPhoneNumber.setText(cn.getPhoneNumber());

                }
            }
        });
//
//        for (Contact cn : contacts) {
//            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
//            // Writing Contacts to log
//            Log.d("Name: ", log);
//        }


        mDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Contact cn = new Contact(Integer.parseInt(Id.getText().toString()),Name.getText().toString(),PhoneNumber.getText().toString());
                db.deleteContact(cn);
                Toast.makeText(MainActivity.this, "Contact with ID : " + cn.getID() +"Deleted!", Toast.LENGTH_SHORT).show();
            }
        });

        mUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Contact cn = new Contact(Integer.parseInt(Id.getText().toString()),Name.getText().toString(),PhoneNumber.getText().toString());
                db.updateContact(cn);
            }
        });

    }
}
