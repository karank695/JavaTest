package prep.interview;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Test {
//function to find list of integer to find double target sum
	public static void subsetSum(ArrayList<Integer> al, int targetSum, Set<ArrayList<Integer>> set) {
		int i = 0, j = 0, sum = 0;
		ArrayList<Integer> temp = new ArrayList<>();
		while (j < al.size()) {
			sum += al.get(j);
			temp.add(al.get(j));
			if (sum < targetSum) {
				j++;
			} else if (sum == targetSum) {
				set.add(temp);
				j++;
			} else {
				while (sum > targetSum) {
					int x = temp.remove(0);
					sum -= x;
					i++;
				}
				j++;
			}
		}
	}

//function to get list of integer corresponding to target sum
	public static void solve(int a[], int sum) {
		Set<ArrayList<Integer>> set = new HashSet<>();
		for (int i = 0; i < a.length; i++) {
			int j = i + 1;
			HashSet<Integer> hs = new HashSet<>();
			ArrayList<Integer> al = new ArrayList<>();
			while (j < a.length) {
				if (a[i] + a[j] == sum) {
					al.add(a[i]);
					al.add(a[j]);
					Collections.sort(al);
					set.add(al);
				}
				j++;
			}

		}
		System.out.println("target sum is: " + sum);
		System.out.println("Integer list for target sum:");
		System.out.println(set);
		ArrayList<Integer> newList = new ArrayList<>();
		for (ArrayList<Integer> ele : set) {
			newList.add(ele.get(0));
			newList.add(ele.get(1));
		}
		System.out.println("After merging all list");
		System.out.println(newList);
		int targetSum = 2 * sum;
		System.out.println("Now the target sum is: " + targetSum);
		subsetSum(newList, targetSum, set);
		System.out.println(set);

	}

	public static void main(String args[]) {
		int a[] = { 1, 3, 2, 2, -4, -6, -2, 8 };
		solve(a, 4);

	}
}
