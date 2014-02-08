package com.example.testapplication;

import java.util.ArrayList;

import android.os.AsyncTask;
import android.os.PowerManager;

public class CheckNumbers extends AsyncTask<Object, Void, Object> {
	private static int numbersHit = 0;
	private static int specialBall = 0;
	private static int multi = 0;
	private static ArrayList<Integer> checkedNumbers = new ArrayList<Integer>();

	// TODO have to alter this information so it pulls out powerball and
	// megaplier then only compare those to get winning numbers
	public static void checkNumbers(ArrayList<Integer> inputNumbers,
			ArrayList<Integer> winningNumbers) {

		for (int i = 0; i < inputNumbers.size() - 2; i++) {

			for (int n = 0; n < winningNumbers.size(); n++) {

				if (winningNumbers.get(n) == inputNumbers.get(i)) {
					numbersHit++;
				}
			}
			System.out.println("Numbers Hit in checkNumbers.java: "
					+ numbersHit);

		}
		// System.out.println(inputNumbers.get(5));
		// System.out.println(winningNumbers.get(5));

		if (inputNumbers.get(5) == winningNumbers.get(5)) {
			specialBall++;
		}
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

		ArrayList<Integer> inputNumbers = (ArrayList<Integer>) params[0];
		ArrayList<Integer> winningNumbers = (ArrayList<Integer>) params[1];
		checkNumbers(inputNumbers, winningNumbers);
		return null;
	}

}
