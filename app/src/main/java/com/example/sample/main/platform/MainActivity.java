package com.example.sample.main.platform;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.sample.R;
import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		View containerView = findViewById(R.id.frag_container);
		
		getSupportFragmentManager().beginTransaction().replace(containerView.getId(), new MFragment()).commit();
		
	}
	
	
}