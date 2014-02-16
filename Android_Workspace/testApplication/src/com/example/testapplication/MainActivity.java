package com.example.testapplication;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.format.Formatter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	private Button submitNumbers;
	private EditText firstNumber;
	private EditText secondNumber;
	private EditText thirdNumber;
	private EditText fourthNumber;
	private EditText fifthNumber;
	private EditText specialNumber;
	private EditText resultField;
	private ToggleButton multiplierButton;
	private int multiplier;
	private DatePicker datePicker;
	private static String date;
	private static String dayString;
	private Integer day;
	private Integer month;
	private Integer year;
	private StringBuilder sb = new StringBuilder();
	private NumberFormat formatter = new DecimalFormat("00");
	final Context context = this;
	private String results = "";

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
					new GetLotteryResults().execute(15, returnYear(),
							returnDate()).get(10000, TimeUnit.MILLISECONDS);
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

				results = CheckPrizes.returnResult();

				// THIS IS NEW
				final Dialog dialog = new Dialog(context);
				dialog.setContentView(R.layout.dialog);
				dialog.setTitle("Results");

				TextView text = (TextView) dialog.findViewById(R.id.text);
				text.setText(results);
				ImageView image = (ImageView) dialog.findViewById(R.id.image);
				image.setImageResource(R.drawable.ic_launcher);

				Button dialogButton = (Button) dialog
						.findViewById(R.id.dialogButtonOK);
				dialogButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});

				dialog.show();

				GetLotteryResults.reset();
				CheckNumbers.reset();
				CheckPrizes.reset();
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
		resultField = (EditText) findViewById(R.id.resultField);
		multiplierButton = (ToggleButton) findViewById(R.id.multiplier);

		inputNumbers.add(Integer.parseInt(firstNumber.getText().toString()));
		inputNumbers.add(Integer.parseInt(secondNumber.getText().toString()));
		inputNumbers.add(Integer.parseInt(thirdNumber.getText().toString()));
		inputNumbers.add(Integer.parseInt(fourthNumber.getText().toString()));
		inputNumbers.add(Integer.parseInt(fifthNumber.getText().toString()));
		inputNumbers.add(Integer.parseInt(specialNumber.getText().toString()));

		datePicker = (DatePicker) findViewById(R.id.datePicker1);

		if (datePicker.getDayOfMonth() < 10) {
			day = datePicker.getDayOfMonth();
			sb.append("0");
			sb.append(day.toString());

			dayString = sb.toString();

			month = datePicker.getMonth() + 1; // need to add one for website to
												// return correct results
			year = datePicker.getYear();

			date = month.toString() + "/" + dayString + "/" + year.toString();

			System.out.println("Date: " + month.toString() + "/" + dayString
					+ "/" + year.toString());

		} else {

			day = datePicker.getDayOfMonth();
			month = datePicker.getMonth() + 1; // need to add one for
			// website to return correct results
			year = datePicker.getYear();
			date = month.toString() + "/" + day.toString() + "/"
					+ year.toString();
			System.out.println("Date: " + month.toString() + "/"
					+ day.toString() + "/" + year.toString());

		}

	}

	private String returnDate() {
		return date;
	}

	private int returnYear() {
		return year;
	}

	private void reset() {

		firstNumber.setText("");
		secondNumber.setText("");
		thirdNumber.setText("");
		fourthNumber.setText("");
		fifthNumber.setText("");
		specialNumber.setText("");
		inputNumbers.clear();
	}
}
