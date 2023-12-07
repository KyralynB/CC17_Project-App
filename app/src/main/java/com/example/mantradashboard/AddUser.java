package com.example.mantradashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddUser extends AppCompatActivity {

    //dropdown items
    String[] role = {"admin", "pharmacist", "staff", "doctor"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;

    //Variables
    TextInputLayout regFirstName, regLastName, regEmail, regRole, regPass, regContNum, regAdd;
    Button regUser, callLogIn;

    //Firebase
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        //Hooks
        regFirstName = findViewById(R.id.reg_first_name);
        regLastName = findViewById(R.id.reg_last_name);
        regEmail = findViewById(R.id.reg_email);
        regRole = findViewById(R.id.reg_role);
        regPass = findViewById(R.id.reg_password);
        regContNum = findViewById(R.id.reg_contact_num);
        regAdd = findViewById(R.id.reg_address);

        //Buttons
        autoCompleteTextView = findViewById(R.id.auto_complete_txt);
        regUser = findViewById(R.id.reg_add_user);
        callLogIn = findViewById(R.id.btn_cancel);

        adapterItems = new ArrayAdapter<String>(this, R.layout.list_role, role);
        autoCompleteTextView.setAdapter(adapterItems);

        //Action; dropdown items for roles
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
            }
        });

        //Action; User Register/Sign up
        regUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Users");

                registerUser(v);
            }
        });

        //Action; Go back to Login
        callLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*                Intent intent = new Intent(AddUser.this, Login.class);
                startActivity(intent);*/
                finish();
            }
        });
    }

    private Boolean validateFirstName() {
        String val = regFirstName.getEditText().getText().toString();
        String noWhiteSpace = "(\\A\\w{4,20}\\z)";

        if (val.isEmpty()) {
            regFirstName.setError("Field cannot be empty.");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            regFirstName.setError("White Spaces are not allowed.");
            return false;
        } else {
            regFirstName.setError(null);
            regFirstName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateLastName() {
        String val = regLastName.getEditText().getText().toString();
        String noWhiteSpace = "(\\A\\w{4,20}\\z)";

        if (val.isEmpty()) {
            regLastName.setError("Field cannot be empty.");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            regLastName.setError("White Spaces are not allowed.");
            return false;
        } else {
            regLastName.setError(null);
            regLastName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = regEmail.getEditText().getText().toString();
        String emailPattern = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";

        if (val.isEmpty()) {
            regEmail.setError("Field cannot be empty.");
            return false;
        } else if (!val.matches(emailPattern)) {
            regEmail.setError("Invalid email address");
            return false;
        } else {
            regEmail.setError(null);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = regPass.getEditText().getText().toString();
        String passwordVal = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
              /*      "(?=.*[0-9])" +       // a digit must occur at least once
                    "(?=.*[a-z])" +      // a lower case letter must occur at least once
                    "(?=.*[A-Z])" +      // an upper case letter must occur at least once
                    "(?=.*[@#$%^&+=])" +  // a special character must occur at least once
                    "$" ;   */             // end-of-string

        if (val.isEmpty()) {
            regPass.setError("Field cannot be empty.");
            return false;
        } else if (!val.matches(passwordVal)) {
            regPass.setError("Password must have atleast a digit, lowercase, UPPERCASE, and special characters.");
            return false;
        } else {
            regPass.setError(null);
            return true;
        }
    }

    private Boolean validateRole() {
        String val = regRole.getEditText().getText().toString();

        if (val.isEmpty()) {
            regRole.setError("Choose a role.");
            return false;
        } else {
            regRole.setError(null);
            return true;
        }
    }

    private Boolean validateContactInfo() {
        String val = regContNum.getEditText().getText().toString();

        if (val.isEmpty()) {
            regContNum.setError("Field cannot be empty.");
            return false;
        } else {
            regContNum.setError(null);
            return true;
        }
    }

    private Boolean validateHomeAdd() {
        String val = regAdd.getEditText().getText().toString();

        if (val.isEmpty()) {
            regAdd.setError("Field cannot be empty.");
            return false;
        } else {
            regAdd.setError(null);
            return true;
        }
    }

    public void registerUser(View v) {

        //Call validation functions.
        if (!validateFirstName() | !validateLastName() | !validateEmail() | !validatePassword() | !validateRole() | !validateContactInfo() | !validateHomeAdd()) {
            return;
        }

        //Get all the values
        String fname = regFirstName.getEditText().getText().toString();
        String lname = regLastName.getEditText().getText().toString();
        String email = regEmail.getEditText().getText().toString();
        String role = regRole.getEditText().getText().toString();
        String password = regPass.getEditText().getText().toString();
        String contact_num = regContNum.getEditText().getText().toString();
        String address = regAdd.getEditText().getText().toString();

        //Registration excecution
        UserHelperClass helperClass = new UserHelperClass(fname, lname, email, role, password, contact_num, address);
        reference.child(contact_num).setValue(helperClass);

    }
}