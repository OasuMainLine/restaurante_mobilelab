package com.example.restaurante;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.restaurante.Model.Dish;
import com.example.restaurante.Model.DishAdapter;
import com.example.restaurante.Model.RecordDishes;

import java.util.ArrayList;

public class act_Main extends AppCompatActivity {
    private RecordDishes recordDishes = new RecordDishes();
    private EditText txtDishId, txtDescription, txtPrice;
    private Button btnAddDish;
    private ImageView imgDish;
    private ListView listView;
    private Uri tempPhoto = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_main);
        ArrayList<Dish> dishList = this.getIntent().getParcelableArrayListExtra("dishList");
        System.out.println(dishList);


        txtDishId = findViewById(R.id.txtDishId);
        txtDescription = findViewById(R.id.txtDescription);
        txtPrice = findViewById(R.id.txtPrice);

        imgDish = findViewById(R.id.imgDish);
        listView = findViewById(R.id.lvDishes);

        if(dishList != null){
            this.recordDishes.setArray(dishList);
            this.updateList();
        }
        txtDishId.addTextChangedListener(watcher);
        txtDescription.addTextChangedListener(watcher);
        txtPrice.addTextChangedListener(watcher);
        btnAddDish = findViewById(R.id.btnAddDish);
        btnAddDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Hola");
                String dishId = getText(txtDishId);
                String description = getText(txtDescription);
                String price = getText(txtPrice);

                Dish dish = new Dish(dishId, description, Double.parseDouble(price), tempPhoto);

                int code = recordDishes.add(dish);

                if(code == RecordDishes.CODE_OK){
                    makeToast("Se a??adi?? el platillo con ??xito");
                    updateList();
                    clear();

                } else {
                    makeToast("El platillo ya se ha a??adido");
                }
            }
        });

        imgDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Selecciona una imagen"), 1);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("dishList", recordDishes.getArray());
                bundle.putParcelable("selectedDish", recordDishes.get(i));
                Intent maintenanceIntent = new Intent(getApplicationContext(), act_Maintenance.class);
                maintenanceIntent.putExtras(bundle);

                startActivity(maintenanceIntent);

            }
        });
    }

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String dishId = getText(txtDishId);
            String description = getText(txtDescription);
            String price = getText(txtPrice);

            if(dishId.isEmpty() || description.isEmpty() || price.isEmpty()){
                btnAddDish.setEnabled(false);
                return;
            }
            btnAddDish.setEnabled(true);
        }
    };

    private String getText(EditText editText){ return editText.getText().toString(); }

    private void makeToast(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

    }

    public void updateList(){
        DishAdapter adapter = new DishAdapter(getApplicationContext(), recordDishes.getArray());
        listView.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        tempPhoto = data.getData();
        imgDish.setImageURI(tempPhoto);
        makeToast("Imagen cargada correctamente");
    }

    public void clear(){
        txtPrice.setText("");
        txtDescription.setText("");
        txtDishId.setText("");
        tempPhoto = null;
    }

}