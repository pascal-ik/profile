package pamtech.com.zooapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button categories;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bind views
        categories = findViewById(R.id.btnCategories);

        //move to categories activity
        categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cat = new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(cat);
            }
        });


        makeAnimals();


    }


    public void makeAnimals(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(0);

                    Animal lion = new Animal("Mammal","Lion", (String) getText(R.string.lion_description),"res\\raw",R.drawable.lion);
                    Animal pigeon = new Animal("Bird","Rock Pigeon", (String) getText(R.string.pigeon_description),"res\\raw",R.drawable.pigeon);
                    Animal tilapia = new Animal("Fish","Tilapia", (String) getText(R.string.tilapia_description),"res\\raw",R.drawable.tilapia);
                    Animal cobra = new Animal("Reptile","Cobra", (String) getText(R.string.snake_description),"res\\raw",R.drawable.cobra);
                    Animal frog = new Animal("Amphibian","Frog", (String) getText(R.string.frog_description),"res\\raw",R.drawable.frog);

                    DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);

                    databaseHelper.addAnimal(lion);
                    databaseHelper.addAnimal(pigeon);
                    databaseHelper.addAnimal(tilapia);
                    databaseHelper.addAnimal(cobra);
                    databaseHelper.addAnimal(frog);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
