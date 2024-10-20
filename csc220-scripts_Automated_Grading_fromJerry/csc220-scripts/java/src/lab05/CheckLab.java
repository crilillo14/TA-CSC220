package lab05;

import java.util.ArrayList;

public class CheckLab {

	public static void main(String[] args) {
		int[] gradePoint = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int[] givenPoint = { 5, 5, 5, 5, 5, 10, 10, 5, 8, 7 };
		String output = "";
		try {
			BinarySearchSet testObj = new BinarySearchSet();
			// BinarySearchSetRef refObj = new BinarySearchSetRef();
			try {
				if (testObj.storage.length == 6) {
					gradePoint[0] = 100;
				}  else {
					gradePoint[0] = 0;
					output += "## BinarySearchSet() does not initialize correctly\n"; 
				}
			} catch (Exception e) {
				gradePoint[0] = 0;
				output += "## BinarySearchSet() does not initialize correctly\n"; 
			}
			
			try {
				if (testObj.isEmpty() && testObj.size() == 0) {
					gradePoint[1] += 25;
					gradePoint[2] += 25;
				} else if (testObj.isEmpty()) {
					gradePoint[1] += 25;
					output += "## size() returned " + testObj.size() + " when list empty\n";
				} else if (testObj.size() == 0) {
					gradePoint[2] += 25;
					output += "## isEmpty() returned false when list empty\n";
				}
			} catch (Exception t) {
				output += "## size() or empty() threw " + t + "\n";
			}

			try {
				boolean in1 = testObj.binary_add(150);
				boolean in2 = testObj.binary_add(100);
				boolean in3 = testObj.binary_add(130);
				if (in1 && in2 && in3) {
					gradePoint[8] += 25;
				} else {
					output += "## binary_add() must return true when adding new values\n";
				}
			} catch (Exception t) {
				output += "## binary_add() threw " + t + "\n";
			}
			
			if (isNotSorted(testObj.storage, testObj.size())) {
				output += "## binary_add() does not keep data sorted\n";
			} else {
				gradePoint[8] += 10;
			}
			
			try {
				if (!testObj.isEmpty() && testObj.size() == 3) {
					gradePoint[1] += 50;
					gradePoint[2] += 25;
				} else if (!testObj.isEmpty()) {
					gradePoint[1] += 50;
					output += "## size() returned " + testObj.size() + "; should be 3\n";
				} else if (testObj.size() == 3) {
					gradePoint[2] += 25;
					output += "## isEmpty() returned true on non-empty list\n";
				}
			} catch (Exception t) {
				output += "## size() or isEmpty() threw " + t;
			}
			
			try {
				testObj.clear();
			} catch (Exception t) {
				output += "## clear() threw " + t;
			}

			if (testObj.isEmpty()) {
				gradePoint[1] += 25;
			} else {
				output += "## isEmpty() returned false after clear()\n";
			}

			if (testObj.size() == 0) {
				gradePoint[2] += 25;
			} else {
				output += "## size() returned " + testObj.size() + " after clear(); should be 0\n";
			}

			try {
				for (int i = 30; i > 10; i--) {
					testObj.binary_add(i);
				}
			} catch (Exception t) {
				output += "## add 20 numbers: binary_add() threw " + t + "\n";
			}

			if (testObj.size() == 20) {
				// points for clear, *_add(), and size() 
				gradePoint[2] += 25;
				gradePoint[8] += 50;
				gradePoint[3] += 100;
			} else if (testObj.size() == 23) {
				// no points for clear
				gradePoint[2] += 25;
				gradePoint[8] += 50;
				output += "## clear() did not make list empty\n";
			} else {
				output += "## add 20 numbers: incorrect size, yours " + testObj.size() + "; should be 20\n";
			}

			if (isNotSorted(testObj.storage, testObj.size())) {
				output += "## add 20 numbers: binary_add() does not keep the data sorted\n";
			} else {
				gradePoint[8] += 15;
			}

			try {
				testObj = new BinarySearchSet();
				testObj.storage[0] = 100;
				testObj.storage[1] = 120;
				testObj.storage[2] = 130;
				int old_size = testObj.storage.length;
				testObj.grow();
				int new_size = testObj.storage.length;
				if (old_size < new_size) {
					gradePoint[4] += 50;
				} else {
					output += "## grow() is incorrect\n";
				}
				if (testObj.storage[0] == 100 && testObj.storage[1] == 120 && testObj.storage[2] == 130) {
					gradePoint[4] += 50;
				} else {
					output += "## grow() must copy all elements from old list\n";
				}

			} catch (Exception t) {
				output += "## grow() threw " + t + "\n";
			}

			try {
				testObj = new BinarySearchSet();
				testObj.binary_add(100);
				testObj.binary_add(110);
				testObj.binary_add(120);
				testObj.binary_add(150);
				testObj.binary_add(180);
			} catch (Exception e) {
				output += "## binary_add() threw " + e + "\n";
			}
			

			try {
				if (testObj.contains(180) && testObj.contains(100)) {
					gradePoint[6] += 50;
				} else {
					output += "## contains() returned false on first or last element\n";
				}

				if (!testObj.contains(240) && !testObj.contains(20)) {
					gradePoint[6] += 50;
				} else {
					output += "## contains() returned true when value is not present\n";
				}

				if (testObj.containsAll(new double[] { 180, 100 })
						&& testObj.containsAll(new double[] { 120, 110, 150 })) {
					gradePoint[7] += 50;
				} else {
					output += "## containsAll() returned false on list of present data\n";
				}

				if (!testObj.containsAll(new double[] { 180, 10 })
						&& !testObj.containsAll(new double[] { 20, 10, 50 })) {
					gradePoint[7] += 50;
				} else {
					output += "## containsAll() returned true on list of data not present\n";
				}

			} catch (Exception t) {
				output += "## contain() or containsAll() threw " + t + "\n";
			}

			double check_size = testObj.size();
			try {
				if (testObj.remove(100) && (testObj.size() == (check_size - 1))) {
					gradePoint[5] += 40;
				} else {
					output += "## remove() incorrect; does not remove first value of list\n";
				}
				check_size = testObj.size();
				if (testObj.remove(180) && (testObj.size() == (check_size - 1))) {
					gradePoint[5] += 35;
				} else {
					output += "## remove() incorrect; does not remove last value of list\n";
				}

				if (!testObj.remove(280)) {
					gradePoint[5] += 25;
				} else {
					output += "## remove() returned true when value is not present\n";
				}
			} catch (Exception t) {
				output += "## remove() threw " + t + "\n";
			}
			try {
				testObj = new BinarySearchSet(new double[] { 8, 3, 2, 6, 4 });
				if (testObj.size() == 5 && testObj.storage[0] == 2 && testObj.storage[4] == 8) {
					gradePoint[9] += 50;
				} else {
					output += "## BinarySearchSet([]) does not initialize a valid list\n";
				}
				double[] testvalue = { 8, 3, 2, 6, 4, 100, 130, 180, 200, 230, 560, 340 };

				testObj = new BinarySearchSet(testvalue);
				if (testObj.size() == testvalue.length) {
					gradePoint[9] += 50;
				} else {
					output += "## BinarySearchSet([]) must grow when initialized with a large list\n";
				}
			} catch (Exception t) {
				output += "## BinarySearchSet([]) threw " + t + "\n";
			}

		} catch (Exception ex) {
			output += "## program threw " + ex + "\n";
			ex.printStackTrace();
		}
		arrayPrint(gradePoint, givenPoint);
		System.out.println(output);
	}

	public static boolean isNotSorted(double[] data, double size) {
		for (int i = 0; i < size - 1; i++) {
			if (data[i] >= data[i + 1]) {
				return true;
			}
		}
		return false;
	}

	public static void arrayPrint(int[] arr, int[] givenpoint) {
		System.out.print("$$");
		int i = 0;
		for (int r : arr) {
			// System.out.println(givenpoint[i]);
			System.out.print((r * givenpoint[i]) / 100.0);
			System.out.print("$$");
			i++;
		}
	}

}

class BinarySearchSetB {
	private int capacity;
	private int numItems;
	public double[] storage;

	public BinarySearchSetB() {
		capacity = 6;
		storage = new double[6];
		numItems = 0;
	}

	public BinarySearchSetB(double[] input) {
		this();

		for (int i = 0; i < input.length; i++)
			add(input[i]);
	}

	public boolean binary_add(double val) {
		return add(val);
	}

	public boolean sequential_add(double val) {
		return add(val);
	}

	public boolean MoveAndInsert(double newVal, int index) {
		if (storage[index] == newVal)
			return false;

		for (int i = numItems - 1; i >= index; i--)
			storage[i + 1] = storage[i];

		storage[index] = newVal;
		numItems++;
		return true;
	}

	public boolean BinaryInsertIndex(double target, int start, int end) {
		if (start == end)
			return (target > storage[start]) ? MoveAndInsert(target, start + 1) : MoveAndInsert(target, start);

		if (start > end)
			return (target > storage[start]) ? MoveAndInsert(target, end) : MoveAndInsert(target, start);

		if (target > storage[end])
			return MoveAndInsert(target, end + 1);

		if (target <= storage[start])
			return MoveAndInsert(target, start);

		int mid = (start + end) / 2;

		if (target == storage[mid])
			return MoveAndInsert(target, mid);
		else if (target < storage[mid])
			return BinaryInsertIndex(target, start, mid - 1);
		else // target > storage[mid
			return BinaryInsertIndex(target, mid + 1, end);
	}

	public boolean add(double newVal) {
		if ((capacity - numItems) < 1)
			grow();

		if (numItems == 0) {
			storage[0] = newVal;
			numItems++;
			return true;
		}

		return BinaryInsertIndex(newVal, 0, numItems - 1);
	}

	public String toString() {
		String toReturn = ("capacity: " + capacity + " numItems: " + numItems + "\n");
		for (int i = 0; i < numItems; i++) {
			toReturn += (Double.toString(storage[i]) + ", ");
		}
		return toReturn;
	}

	public boolean remove(double element) {
		int index = FindIndex(element);
		if (index == -1)
			return false;

		for (int i = index; i < numItems - 1; i++)
			storage[i] = storage[i + 1];

		storage[(numItems--) - 1] = 0.0;
		return true;
	}

	public boolean containsAll(double[] elements) {
		if (elements.length == 0)
			return false;

		if (numItems == 0 || numItems < elements.length)
			return false;

		for (int i = 0; i < elements.length; i++) {
			if (FindIndex(elements[i]) < 0)
				return false;
		}
		return true;
	}

	public boolean contains(double element) {
		if (numItems == 0) {
			return false;
		} else {
			return FindIndex(element) > 0;
		}
	}

	public int FindIndex(double target) {
		// simple binary search!
		if (numItems == 0)
			return -1;

		int min = 0;
		int max = numItems - 1;
		while (min <= max) {
			int mid = (max + min) / 2;
			if (storage[mid] == target) {
				return mid;
			} else if (storage[mid] < target) {
				min = mid + 1;
			} else {
				max = mid - 1;
			}
		}
		return -1;
	}

	public void grow() {
		double[] temp = new double[2 * capacity];

		for (int i = 0; i < capacity; i++)
			temp[i] = storage[i];

		storage = temp;

		capacity *= 2;
	}

	public boolean isEmpty() {
		if (numItems == 0)
			return true;
		else
			return false;
	}

	public int size() {
		return numItems;
	}

	public void clear() {
		if (numItems == 0)
			return;

		for (int i = 0; i < numItems; i++)
			storage[i] = 0.0;

		numItems = 0;
	}
}