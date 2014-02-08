package com.example.testapplication;

import java.util.ArrayList;

import android.os.AsyncTask;
import android.os.PowerManager;

public class CheckNumbers extends AsyncTask<Object, Void, Object> {
	private static int numbersHit = 0;
	private static int specialBall = 0;
	private static ArrayList<Integer> checkedNumbers = new ArrayList<Integer>();

	// TODO have to alter this information so it pulls out powerball and
	// megaplier then only compare those to get winning numbers
	public static void checkNumbers(ArrayList<String> inputNumbers,
			ArrayList<String> winningNumbers) {

		// System.out.println("InputNumbers size: " + inputNumbers.size());
		// for (int a = 0; a < inputNumbers.size(); a++)
		// System.out.println("Input numbers @" + a + ": "
		// + inputNumbers.get(a));
		// System.out.println("WinningNumbers size: " + winningNumbers.size());
		// for (int a = 0; a < winningNumbers.size(); a++)
		// System.out.println("Winning numbers @" + a + ": "
		// + winningNumbers.get(a));

		for (int i = 0; i < inputNumbers.size(); i++) {

			for (int n = 0; n < winningNumbers.size(); n++) {

				if (winningNumbers.get(n).contains(inputNumbers.get(i))) {
					numbersHit++;

				} else {
					// System.out.println("Winning number: "
					// + winningNumbers.get(n));
					// System.out.println("Input number: " +
					// inputNumbers.get(i));
				}

			}
		}
		System.out.println("Numbers Hit in checkNumbers.java: " + numbersHit);
		checkedNumbers.add(numbersHit);
		checkedNumbers.add(specialBall);
	}

	private static void resetCounters() {
		numbersHit = 0;
		specialBall = 0;
	}

	public static ArrayList<Integer> returnCheckedNumbers() {
		return checkedNumbers;

	}

	@Override
	protected Object doInBackground(Object... params) {

		ArrayList<String> inputNumbers = (ArrayList<String>) params[0];
		ArrayList<String> winningNumbers = (ArrayList<String>) params[1];
		checkNumbers(inputNumbers, winningNumbers);
		return null;
	}

}
