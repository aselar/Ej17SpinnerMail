package com.android.asel.ej17spinnermail;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNombre, editTextEmail, editTextFeedback;
    private Spinner spinner;
    private CheckBox checkBox;
    private Button button;

    private void inicializar() {

        editTextNombre = (EditText)findViewById(R.id.editTextNombre);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextFeedback = (EditText)findViewById(R.id.editTextFeedback);

        spinner = (Spinner)findViewById(R.id.spinner);

        checkBox = (CheckBox)findViewById(R.id.checkBox);

        button = (Button)findViewById(R.id.button);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializar();

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.arraySpinner, android.R.layout.simple_list_item_1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setPrompt("Introducir tipo de feedback");
        spinner.setAdapter(adapter);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked)
                    checkBox.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorGreen));
                else
                    checkBox.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBackground));

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentMensaje = new Intent(android.content.Intent.ACTION_SEND);
                intentMensaje.setType("plain/text");

                String email = editTextEmail.getText().toString();
                String asunto = spinner.getSelectedItem().toString();
                String mensaje = editTextNombre.getText().toString();
                mensaje = mensaje + "\n" + "\n" + editTextFeedback.getText().toString();

                if (checkBox.isChecked())
                    mensaje = mensaje + "\n" + "\n" + "\n" + "Este mensaje requiere respuesta";
                else
                    mensaje = mensaje + "\n" + "\n" + "\n" + "Este mensaje no requiere respuesta";

                intentMensaje.putExtra(android.content.Intent.EXTRA_EMAIL, email);
                intentMensaje.putExtra(android.content.Intent.EXTRA_SUBJECT, asunto);
                intentMensaje.putExtra(android.content.Intent.EXTRA_TEXT, mensaje);
                startActivity(intentMensaje);

            }
        });

    }

}
