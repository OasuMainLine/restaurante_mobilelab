package com.example.restaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.restaurante.Model.Dish;
import com.example.restaurante.Model.RecordDishes;

import java.util.ArrayList;

public class act_Maintenance extends AppCompatActivity {
    private Button btnModify, btnDelete, btnList;
    private EditText txtDishId, txtDescription, txtPrice;

    private RecordDishes recordDishes = new RecordDishes();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_mantainance);
        Bundle bundle = this.getIntent().getExtras();
        ArrayList<Dish> dishList = bundle.getParcelableArrayList("dishList");
        this.recordDishes.setArray(dishList);
        Dish selectedDish = bundle.getParcelable("selectedDish");

        txtDishId = findViewById(R.id.txtDishId);
        txtDishId.setText(selectedDish.getId());

        txtDescription = findViewById(R.id.txtDescription);
        txtDescription.setText(selectedDish.getDescripction());

        txtPrice = findViewById(R.id.txtPrice);
        txtPrice.setText(selectedDish.getPrice() + "");

        txtDescription.addTextChangedListener(watcher);
        txtPrice.addTextChangedListener(watcher);
        btnModify = findViewById(R.id.btnModify);
        btnDelete = findViewById(R.id.btnDelete);
        btnList = findViewById(R.id.btnList);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordDishes.delete(selectedDish.getId());
                returnToMain();
            }
        });
        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String description = txtDescription.getText().toString().trim();
                Double price = Double.parseDouble(txtPrice.getText().toString().trim());

                selectedDish.setDescripction(description);
                selectedDish.setPrice(price);
                recordDishes.modify(selectedDish);
                makeToast("Platillo modificado con éxito");
            }
        });
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               returnToMain();
            }
        });
    }

    private void returnToMain(){
        Intent returnIntent = new Intent(getApplicationContext(), act_Main.class);

        returnIntent.putParcelableArrayListExtra("dishList", recordDishes.getArray());
        startActivity(returnIntent);
    }
    private void makeToast(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

    }

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String description = txtDescription.getText().toString().trim();
            String price = txtPrice.getText().toString().trim();

            if(description.isEmpty() || price.isEmpty()){
                btnModify.setEnabled(false);
                return;
            }
            btnModify.setEnabled(true);
        }
    };
}