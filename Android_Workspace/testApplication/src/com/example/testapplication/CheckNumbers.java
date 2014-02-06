package com.example.testapplication;

import java.util.ArrayList;

import android.os.AsyncTask;

public class CheckNumbers extends
		AsyncTask<ArrayList<String>, Void, ArrayList<String>> {
	private static int numbersHit = 0;
	private static int specialBall = 0;
	private static ArrayList<Integer> checkedNumbers = new ArrayList<Integer>();

	public static void checkNumbers(ArrayList<String> inputNumbers,
			ArrayList<String> winningNumbers, int specialBallInput,
			int specialBallWin) {

		System.out.println(inputNumbers.size());

		if (specialBallInput == specialBallWin)
			specialBall++;

		for (int i = 0; i < inputNumbers.size(); i++) {

			for (int n = 0; n < winningNumbers.size(); n++) {

				if (inputNumbers.get(i) == winningNumbers.get(n))
					numbersHit++;

			}
		}
	}

	private static void resetCounters() {
		numbersHit = 0;
		specialBall = 0;
	}

	public static ArrayList<Integer> returnCheckedNumbers() {
		// System.out.println("In returnCheckedNumbers");
		return checkedNumbers;

	}

	@Override
	protected ArrayList<String> doInBackground(ArrayList<String>... params) {
		checkedNumbers.add(1);
		checkedNumbers.add(2);
		System.out.println("In new task");
		return null;
	}

}
