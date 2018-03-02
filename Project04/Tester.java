/*
* @Author: Chris Kim, Thinh Luu
* @Usernames: ckim65, tpluu
* @Date:   2018-03-01 19:26:02
* @Last Modified by:   Chris Kim
* @Last Modified time: 2018-03-01 19:38:46
*/

import java.util.stream.*;

public class Tester {

	public static void main(String[] args) {
		
		int[] set1 = {100, 50, 25, 10, 5, 1 };
		int[] set2 = {100, 50, 20, 15, 10, 5, 3, 2, 1};
		int[] set3 = {64, 32, 16, 8, 4, 2, 1};
		int[] set4 = {100, 50, 25, 10, 1};
		int[] set5 = {66, 35, 27, 18, 10, 1};

		System.out.println("Testing change_DP and change_greedy algortihms");

		int count = 0; 
		for (int i = 1;  i <= 200;  i++) {
			
			int greedy = IntStream.of(ChangeMaker.change_greedy(i, set1)).sum();
			int dp = IntStream.of(ChangeMaker.change_DP(i, set1)).sum();
			if (greedy == dp) {
				count++;
			}
		}
		System.out.println("Testing set1: " + count +" matches in 200 tests ");

		count = 0; 
		for (int i = 1;  i <= 200;  i++) {
			
			int greedy = IntStream.of(ChangeMaker.change_greedy(i, set2)).sum();
			int dp = IntStream.of(ChangeMaker.change_DP(i, set2)).sum();
			if (greedy == dp) {
				count++;
			}
		}
		System.out.println("Testing set2: " + count +" matches in 200 tests ");

		count = 0; 
		for (int i = 1;  i <= 200;  i++) {
			
			int greedy = IntStream.of(ChangeMaker.change_greedy(i, set3)).sum();
			int dp = IntStream.of(ChangeMaker.change_DP(i, set3)).sum();
			if (greedy == dp) {
				count++;
			}
		}
		System.out.println("Testing set3: " + count +" matches in 200 tests ");

		count = 0; 
		for (int i = 1;  i <= 200;  i++) {
			
			int greedy = IntStream.of(ChangeMaker.change_greedy(i, set4)).sum();
			int dp = IntStream.of(ChangeMaker.change_DP(i, set4)).sum();
			if (greedy == dp) {
				count++;
			}
		}
		System.out.println("Testing set4: " + count +" matches in 200 tests ");

		count = 0; 
		for (int i = 1;  i <= 200;  i++) {
			
			int greedy = IntStream.of(ChangeMaker.change_greedy(i, set5)).sum();
			int dp = IntStream.of(ChangeMaker.change_DP(i, set5)).sum();
			if (greedy == dp) {
				count++;
			}
		}
		System.out.println("Testing set5: " + count +" matches in 200 tests ");

	}

}