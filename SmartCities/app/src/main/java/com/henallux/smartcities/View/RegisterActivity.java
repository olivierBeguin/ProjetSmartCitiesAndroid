package com.henallux.smartcities.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.henallux.smartcities.R;
import com.henallux.smartcities.exception.FormException;
import com.henallux.smartcities.model.User;

import java.util.Date;

public class RegisterActivity extends AppCompatActivity
{

    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerButton = (Button) findViewById(R.id.registerButtonRegister);
        registerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {registerUser();}
        });
    }

    private void registerUser()
    {
        try
        {
            String firstname, lastname, password, confirmPassword, mailAdress, phoneNumber, street, city, country, postalCode, houseNumber;
            lastname = String.valueOf(((EditText) findViewById(R.id.lastnameEditTextRegister)).getText());
            firstname = String.valueOf(((EditText) findViewById(R.id.firstnameTextEditTextRegister)).getText());
            password = String.valueOf(((EditText) findViewById(R.id.passwordEditTextRegister)).getText());
            confirmPassword = String.valueOf(((EditText) findViewById(R.id.confirmPasswordEditTextRegister)).getText());
            mailAdress = String.valueOf(((EditText) findViewById(R.id.mailEditTextRegister)).getText());
            phoneNumber = String.valueOf(((EditText) findViewById(R.id.phoneEditTextRegister)).getText());
            street = String.valueOf(((EditText) findViewById(R.id.streetEditTextRegister)).getText());
            city = String.valueOf(((EditText) findViewById(R.id.cityEditTextRegister)).getText());
            country = String.valueOf(((EditText) findViewById(R.id.countryTextEditRegister)).getText());
            postalCode = String.valueOf(((EditText) findViewById(R.id.postalCodeEditTextRegister)).getText());
            houseNumber = String.valueOf(((EditText) findViewById(R.id.numEditTextRegister)).getText());
            //Log.i("Test", "lastname : " + lastname + "\n firstname :  " + firstname + "\n password :  " + password + "\n confirmPassword : " + confirmPassword + "\n mailAdress : " + mailAdress + "\n phoneNumber : " + phoneNumber + "\n street :  " + street + "\n city : " + city + "\n country : " + country + "\n postalCode : " + postalCode + "\n houseNumber :  " + houseNumber);

            if (lastname.isEmpty() || firstname.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || mailAdress.isEmpty() || phoneNumber.isEmpty() || street.isEmpty() || city.isEmpty() || country.isEmpty() || postalCode.isEmpty() || houseNumber.isEmpty())
            {
                throw new FormException(getString(R.string.not_filled));
            }

            if (!confirmPassword.equals(password))
            {
                throw new FormException(getString(R.string.dont_match));
            }

            if (!mailAdress.matches("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"))
            {
                throw new FormException(getString(R.string.error_adressMail));
            }

            User user = new User(firstname, lastname, password, mailAdress, phoneNumber, street, city, country, new Date(), postalCode, houseNumber);
            //BD.addUser() :p
            goToServicesActivity();
        }
        catch(FormException e)
        {
            Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void goToServicesActivity()
    {
        Intent intent = new Intent(RegisterActivity.this, ServicesActivity.class);
        startActivity(intent);
    }

}
