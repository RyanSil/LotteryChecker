package com.example.testapplication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private Button submitNumbers;
	private EditText firstNumber;
	private EditText secondNumber;
	private EditText thirdNumber;
	private EditText fourthNumber;
	private EditText fifthNumber;
	private EditText specialNumber;
	private EditText multiplier;

	private ArrayList<Integer> inputNumbers = new ArrayList<Integer>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		submitNumbers = (Button) findViewById(R.id.submitButton);
		submitNumbers.setOnClickListener(new View.OnClickListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void onClick(View v) {

				getNumbers();

				try {
					new GetLotteryResults().execute(15, 2012, "12/18/2012")
							.get(10000, TimeUnit.MILLISECONDS);
					new CheckNumbers().execute(inputNumbers,
							GetLotteryResults.returnNumbers()).get(10000,
							TimeUnit.MILLISECONDS);
					new CheckPrizes().execute(
							CheckNumbers.returnCheckedNumbers(),
							"Mega Millions").get(10000, TimeUnit.MILLISECONDS);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				} catch (TimeoutException e) {
					e.printStackTrace();
				}

				reset();

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;

	}

	private void getNumbers() {

		firstNumber = (EditText) findViewById(R.id.firstNumber);
		secondNumber = (EditText) findViewById(R.id.secondNumber);
		thirdNumber = (EditText) findViewById(R.id.thirdNumber);
		fourthNumber = (EditText) findViewById(R.id.fourthNumber);
		fifthNumber = (EditText) findViewById(R.id.fifthNumber);
		specialNumber = (EditText) findViewById(R.id.powerBall);
		multiplier = (EditText) findViewById(R.id.multiplier);

		inputNumbers.add(Integer.parseInt(firstNumber.getText().toString()));
		inputNumbers.add(Integer.parseInt(secondNumber.getText().toString()));
		inputNumbers.add(Integer.parseInt(thirdNumber.getText().toString()));
		inputNumbers.add(Integer.parseInt(fourthNumber.getText().toString()));
		inputNumbers.add(Integer.parseInt(fifthNumber.getText().toString()));
		inputNumbers.add(Integer.parseInt(specialNumber.getText().toString()));

	}

	private void reset() {
		// numbersHit = 0;
		// powerBallHit = 0;
		firstNumber.setText("");
		secondNumber.setText("");
		thirdNumber.setText("");
		fourthNumber.setText("");
		fifthNumber.setText("");
		specialNumber.setText("");
	}
}
