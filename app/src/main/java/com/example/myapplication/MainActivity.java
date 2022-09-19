package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    boolean isSelected;
    int side=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creating Views for the two images
        ImageView Glass1=findViewById(R.id.glass1);
        ImageView Glass2=findViewById(R.id.glass2);

        // Creating Animations using ObjectAnimators
        ObjectAnimator AnimateGlass1 = ObjectAnimator.ofFloat(Glass1,"translationY",1300f);
        AnimateGlass1.setDuration(4000);

        ObjectAnimator AnimateGlass2 = ObjectAnimator.ofFloat(Glass2,"translationY",1300f);
        AnimateGlass2.setDuration(4000);

        // Generate a random number to choose a random side
        side= (int) (Math.random() * 2);
        isSelected=false;

        // Adding onClick Listeners to check the correct side
        Glass1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if(side==1)
                {
                    // Wrong Side
                    // Change the image to Broken Glass

                    // Stop the Animation
                    AnimateGlass1.pause();
                    AnimateGlass2.pause();
                    isSelected=false;
                    AnimateGlass1.removeAllListeners();
                    Toast.makeText(MainActivity.this, "Try Again!", Toast.LENGTH_SHORT).show();
                }
                else {
                    // Correct Side
                    // Change isSelected to True
                    isSelected=true;
                    Toast.makeText(MainActivity.this, "Lucky!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        Glass2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if(side==0)
                {
                    // Wrong Side
                    // Change the image to Broken Glass
                    // Stop the Animation
                    AnimateGlass1.pause();
                    AnimateGlass2.pause();
                    isSelected=false;
                    AnimateGlass1.removeAllListeners();
                    Toast.makeText(MainActivity.this, "Try Again!", Toast.LENGTH_SHORT).show();
                }
                else {
                    // Correct Side
                    // Change isSelected to True
                    isSelected=true;
                    Toast.makeText(MainActivity.this, "Lucky!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Adding Animation End Listener
        AnimateGlass1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                // Check any of the glasses are selected
                if(isSelected)
                {
                    // Continue the game
                    View glass1view=Glass1;
                    View glass2view=Glass2;
                    // Place the glasses again in the top
                    glass1view.setY(0f);
                    glass2view.setY(0f);
                    // new random side
                    side= (int) (Math.random() * 2);
                    // start the animations
                    AnimateGlass1.start();
                    AnimateGlass2.start();
                }
                else {
                    // No glasses are Selected

                    // Stop the Animation
                    AnimateGlass1.cancel();
                    AnimateGlass2.cancel();
                    Toast.makeText(MainActivity.this, "Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Starting the Animations for the First Time
        AnimateGlass1.start();
        AnimateGlass2.start();
    }
}