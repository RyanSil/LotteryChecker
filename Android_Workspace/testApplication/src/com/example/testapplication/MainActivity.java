package com.example.testapplication;

import java.util.ArrayList;
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
	private EditText powerBallInput;
	private Editable[] numbers;
	private String[] lotteryNumbers;
	private String powerBall = "07";
	private int numbersHit = 0;
	private int powerBallHit = 0;

	private ArrayList<String> testArray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		submitNumbers = (Button) findViewById(R.id.submitButton);
		submitNumbers.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					new GetLotteryResults()
							.execute(
									"http://www.palottery.state.pa.us/Games/Past-Winning-Numbers.aspx?id=15&year=2014#pwn_results")
							.get(5000, TimeUnit.MILLISECONDS);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TimeoutException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				getNumbers();
				checkNumbers();
				checkPowerball();
				checkPrizes();
				reset();
				for (int i = 0; i < GetLotteryResults.returnNumbers().size(); i++) {

					System.out.println("The string at " + i + " is: "
							+ GetLotteryResults.returnNumbers().get(i));
				}

				for (int i = 0; i < CheckNumbers.returnCheckedNumbers().size(); i++) {

					System.out.println("The string at " + i + " is: "
							+ GetLotteryResults.returnNumbers().get(i));
				}
			}
		});

		CheckNumbers.returnCheckedNumbers();
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
		numbers = new Editable[5];

		numbers[0] = firstNumber.getText();
		numbers[1] = secondNumber.getText();
		numbers[2] = thirdNumber.getText();
		numbers[3] = fourthNumber.getText();
		numbers[4] = fifthNumber.getText();

		for (int n = 0; n < numbers.length; n++) {
			System.out.println("The number at array[" + n + "] is: "
					+ numbers[n].toString());

		}

	}

	private void checkNumbers() {
		lotteryNumbers = new String[5];
		lotteryNumbers[0] = "08";
		lotteryNumbers[1] = "14";
		lotteryNumbers[2] = "17";
		lotteryNumbers[3] = "20";
		lotteryNumbers[4] = "39";

		for (int n = 0; n < numbers.length; n++) {
			for (int i = 0; i < lotteryNumbers.length; i++) {
				if (lotteryNumbers[i].toString().contains(numbers[n])) {
					System.out.println("This number hit: "
							+ numbers[n].toString());
					numbersHit++;

				} else {
					System.out.println("");
				}
			}
		}
		System.out.println(numbersHit);
	}

	private void checkPrizes() {

		if (powerBallHit == 1) {
			if (numbersHit == 5)
				System.out.println("You hit the Jackpot!");
			if (numbersHit == 4)
				System.out.println("You won $5,000.00");
			if (numbersHit == 3)
				System.out.println("You won $50.00");
			if (numbersHit == 2)
				System.out.println("You won $5.00");
			if (numbersHit == 1)
				System.out.println("You won $2.00");
			if (numbersHit == 0)
				System.out.println("You won $1.00");
		} else {

			if (numbersHit == 3) {
				System.out.println("You've won $7.00!");
			} else {
				if (numbersHit == 4) {
					System.out.println("You've won $100.00!");
				} else {
					if (numbersHit == 5) {
						System.out.println("You've won $1,000,000.00!");
					} else {
						System.out.println("You did not win, sorry.");
					}
				}
			}
		}
	}

	private void checkPowerball() {

		powerBallInput = (EditText) findViewById(R.id.powerBall);

		if (powerBall.contains(powerBallInput.getText())) {
			powerBallHit++;
			System.out.println("Did I hit the powerball?: " + powerBallHit);
		} else {
			System.out.println("You did not hit the powerball!");
		}

	}

	private void reset() {
		numbersHit = 0;
		powerBallHit = 0;
		firstNumber.setText("");
		secondNumber.setText("");
		thirdNumber.setText("");
		fourthNumber.setText("");
		fifthNumber.setText("");
		powerBallInput.setText("");
	}
}
