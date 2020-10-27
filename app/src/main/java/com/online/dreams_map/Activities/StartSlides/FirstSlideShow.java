package com.online.dreams_map.Activities.StartSlides;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.online.dreams_map.R;





public class FirstSlideShow extends AppCompatActivity {

    private Button nextSlide;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.slide1_activity);

        nextSlide=findViewById(R.id.nextButton);
        nextSlide.setOnClickListener(goToNextSlide);
    }


    // Переход к следующему слайду
    OnClickListener goToNextSlide = new OnClickListener() {
        @Override
        public void onClick(View v){
            Intent intent=new Intent(getApplicationContext(), SecondSlideShow.class);
            startActivity(intent);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }
    };

}