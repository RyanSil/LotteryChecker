package com.example.testapplication;

import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GetLotteryResults extends AsyncTask<Object, Void, Object> {

	private static ArrayList<String> lotteryNumbers = new ArrayList<String>();
	private static ArrayList<Integer> intLotterNumbers = new ArrayList<Integer>();

	public static void getResults(int id, int year, String date) {
		String site = "http://www.palottery.state.pa.us/Games/Past-Winning-Numbers.aspx?id="
				+ id + "&year=" + year + "#pwn_results";

		try {
			URL url = new URL(site);
			URLConnection connection = url.openConnection();
			connection.connect();
			Scanner in = new Scanner(connection.getInputStream());

			while (in.hasNextLine())
				if (in.nextLine().contains(date)) {

					lotteryNumbers.add(in.nextLine());
					in.nextLine();
					lotteryNumbers.add(in.nextLine());
					in.nextLine();
					lotteryNumbers.add(in.nextLine());
					in.nextLine();
					lotteryNumbers.add(in.nextLine());
					in.nextLine();
					lotteryNumbers.add(in.nextLine());
					in.nextLine();
					in.nextLine();
					in.nextLine();
					lotteryNumbers.add(in.nextLine()); // powerball
					in.nextLine();
					in.nextLine();
					in.nextLine();
					lotteryNumbers.add(in.nextLine()); // multiplier
				}
			for (int i = 0; i < lotteryNumbers.size(); i++) {
				lotteryNumbers.set(i, lotteryNumbers.get(i).trim());
				// System.out.println("lotteryNumbers.get(" + i + "): "
				// + lotteryNumbers.get(i));
			}

			try {
				for (int n = 0; n < lotteryNumbers.size(); n++) {

					intLotterNumbers
							.add(Integer.parseInt(lotteryNumbers.get(n)));
					// System.out.println("intLotteryNumbers.get(" + n + "): "
					// + intLotterNumbers.get(n));
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			System.out.println("In Exception");
			System.out.println(e);
		}

	}

	@Override
	protected String doInBackground(Object... params) {
		// params[0] id of game IE: 15 = megaball
		// params[1] year
		// params[2] date ie: 01/31/2014

		int id = (Integer) params[0];
		int year = (Integer) params[1];
		String date = (String) params[2];
		getResults(id, year, date);
		return null;
	}

	public static ArrayList<Integer> returnNumbers() {
		return intLotterNumbers;

	}
}
