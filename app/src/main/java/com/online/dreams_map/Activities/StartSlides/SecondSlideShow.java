package com.online.dreams_map.Activities.StartSlides;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.online.dreams_map.R;





public class SecondSlideShow extends AppCompatActivity {

    private Button prevSlide;
    private Button nextSlide;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.slide2_activity);


        prevSlide=findViewById(R.id.backButton);
        nextSlide=findViewById(R.id.nextButton);

        prevSlide.setOnClickListener(goToBackSlide);
        nextSlide.setOnClickListener(goToNextSlide);

    }


    // Переход к следующему слайду
    OnClickListener goToBackSlide = new OnClickListener() {
        @Override
        public void onClick(View v){
            Intent intent=new Intent(getApplicationContext(), FirstSlideShow.class);
            startActivity(intent);
            overridePendingTransition(R.anim.left_in, R.anim.right_out);
        }
    };


    // Переход к следующему слайду
    OnClickListener goToNextSlide = new OnClickListener() {
        @Override
        public void onClick(View v){
            Intent intent=new Intent(getApplicationContext(), ThridSlideShow.class);
            startActivity(intent);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }
    };

}