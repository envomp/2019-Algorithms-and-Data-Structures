package ee.ttu.algoritmid.interestingstamps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class InterestingStamps {

	public static List<Integer> findStamps(int sum, List<Integer> stampOptions) throws IllegalArgumentException {

		stampOptions.sort(Collections.reverseOrder());
		List<Integer> dumb = new ArrayList<>();
		for (int el : stampOptions) {
			if (el % 10 == 0 || el == 1) {
				dumb.add(el);
			}
		}

		stampOptions.removeAll(dumb);
		stampOptions.addAll(dumb);

		int[] arr = new int[stampOptions.size()];

		// ArrayList to Array Conversion
		for (int i = 0; i < stampOptions.size(); i++)
			arr[i] = stampOptions.get(i);


		ProperClass properClass = new ProperClass(sum, arr);

		int n = arr.length;
		int r = 1000;
		properClass.CombinationRepetition(n, r);

		if (properClass.answer == null) {
			throw new IllegalArgumentException();
		}

		List<Integer> a = new ArrayList<>();
		for (int el : properClass.answer) {
			a.add(el);
		}

		return a;

	}
}

class ProperClass {

	private int sum;
	private Integer depth = 1000000000;
	private Integer bad = 1000000000;

	private final int[] arr;
	int[] answer;

	ProperClass(int sum, int[] arr) {
		this.sum = sum;
		this.arr = arr;
	}

//	void recc(int toGo, List<Integer> go, int depth, int dumb, List<Integer> answer) {
//		if (depth > best || depth == best && bes2 >= dumb) {
//			return;
//		}
//		List<Integer> toobig = new ArrayList<>();
//		for (int el : go) {
//			if (toGo - el < 0) {
//				toobig.add(el);
//				continue;
//			}
//
//			if (el == 1 || el % 10 == 0) {
//				dumb++;
//			}
//
//			if (el - toGo == 0) {
//				if (depth < best || depth == best && dumb < bes2) {
//					best = depth;
//					bes2 = dumb;
//					asnwer = answer;
//				}
//			}
//
//			List<Integer> list1 = new ArrayList<>(go);
//			list1.removeAll(toobig);
//			answer.add(el);
//			toGo -= el;
//			recc(toGo, list1, depth + 1, dumb, new ArrayList<>(answer));
//			break;
//
//		}
//		System.out.println(go);
//		List<Integer> list1 = new ArrayList<>(go);
//		list1.remove(0);
//		recc(toGo, list1, depth, dumb, answer);
//		return;
//	}

	private void CombinationRepetitionUtil(int[] chosen, int depth, int start, int end, int sum, int bad) {
		for (int i = start; i <= end; i++) {
			int newSum = sum + this.arr[i];

			if (newSum > this.sum || depth > this.depth || depth == this.depth && bad > this.bad) {
				continue;
			}

			chosen[depth] = this.arr[i];

			if (newSum == this.sum && (depth < this.depth || (depth == this.depth && bad < this.bad))) {
				this.answer = new int[depth + 1];
				System.arraycopy( chosen, 0, this.answer, 0, depth + 1 );
				this.depth = depth;
				this.bad = bad;

			} else if (sum < this.sum && depth < this.depth) {
				CombinationRepetitionUtil(chosen, depth + 1, i, end, sum + this.arr[i], this.arr[i] % 10 == 0 || this.arr[i] == 1 ? bad + 1 : bad);
			}
		}
	}

	void CombinationRepetition(int n, int r) {
		int[] chosen = new int[r + 1];

		CombinationRepetitionUtil(chosen, 0, 0, n - 1, 0, 0);
	}

	public static void main(String[] args) {
		List<Integer> stamps = new ArrayList<>(Arrays.asList(10, 24, 30, 33, 36));
		System.out.println(InterestingStamps.findStamps(2, stamps));
	}

}