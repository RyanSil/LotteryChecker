package com.example.testapplication;

import java.util.ArrayList;

import android.os.AsyncTask;

public class CheckPrizes extends AsyncTask<Object, Void, Object> {
	private static String result = new String();

	private static void checkPrizes(ArrayList<Integer> checkedNumbers,
			String game) {

		if (game == "Mega Millions") {

			if (checkedNumbers.get(1) == 1) {
				if (checkedNumbers.get(0) == 5)
					result = "You hit the Jackpot!!!!";
				if (checkedNumbers.get(0) == 4)
					result = "You won $5,000.00";
				if (checkedNumbers.get(0) == 3)
					result = "You won $50.00";
				if (checkedNumbers.get(0) == 2)
					result = "You won $5.00";
				if (checkedNumbers.get(0) == 1)
					result = "You won $2.00";
				if (checkedNumbers.get(0) == 0)
					result = "You won $1.00";
			} else {

				if (checkedNumbers.get(0) == 3) {
					result = "You've won $7.00!";
				} else {
					if (checkedNumbers.get(0) == 4) {
						result = "You've won $100.00!";
					} else {
						if (checkedNumbers.get(0) == 5) {
							result = "You've won $1,000,000.00!";
						} else {
							result = "You did not win, sorry.";
						}
					}
				}
			}
		} else {
			result = "Something didn't work";
		}

	}

	public static String returnResult() {
		return result;
	}

	public static void reset() {
		result = "";
	}

	@Override
	protected Object doInBackground(Object... params) {
		ArrayList<Integer> checkedNumbers = (ArrayList<Integer>) params[0];
		String game = (String) params[1];

		// for (int i = 0; i < checkedNumbers.size(); i++)
		// System.out.println("checkedNumbers@" + i + ": "
		// + checkedNumbers.get(i));

		checkPrizes(checkedNumbers, game);

		return null;
	}
}
