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

public class GetLotteryResults extends AsyncTask<String, Void, String> {
	private static int year;
	private static int id;
	private static ArrayList<String> lotteryNumbers = new ArrayList<String>();

	public static void getResults(int id, int year, String[] website) {
		String[] site = website;
		try {
			URL url = new URL(site[0]);
			URLConnection connection = url.openConnection();
			connection.connect();
			Scanner in = new Scanner(connection.getInputStream());
			// System.out.println("Before While");

			while (in.hasNextLine())
				if (in.nextLine().contains("01/31/2014")) {
					// System.out.println("In if");
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
					lotteryNumbers.add(in.nextLine());
					in.nextLine();
					in.nextLine();
				}

		} catch (Exception e) {
			System.out.println("In Exception");
			System.out.println(e);
		}

		// for (int i = 0; i < lotteryNumbers.size(); i++)
		// System.out.println("The string at " + i + "is: "
		// + lotteryNumbers.get(i));
	}

	@Override
	protected String doInBackground(String... params) {
		getResults(15, 2014, params);
		return null;
	}

	public static ArrayList<String> returnNumbers() {
		return lotteryNumbers;

	}
}
