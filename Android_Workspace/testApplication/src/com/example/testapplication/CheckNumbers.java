package com.example.testapplication;

import java.util.ArrayList;

public class CheckNumbers {
	private static int numbersHit = 0;
	private static int specialBall = 0;
	private static ArrayList<Integer> checkedNumbers;

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
		checkedNumbers.add(numbersHit);
		checkedNumbers.add(specialBall);
		resetCounters();
		return checkedNumbers;

	}
}
