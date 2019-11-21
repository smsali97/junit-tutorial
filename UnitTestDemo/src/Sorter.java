import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Sorter {

	public static void main(String[] args) {
		// ARGS: -n for name, -s for size, -t for timestamp, -r for reverse(i.e.
		// descending)
		// if none specified then -n is default
		String dirPath = args[0];
		ArrayList<File> files = makeDir(dirPath);
		String param1, param2;
		try {
			param1 = args[1];
		} catch (ArrayIndexOutOfBoundsException e) {
			param1 = "-n"; // if no arg specified it will sort by name
		}
		try {
			param2 = args[2];
		} catch (ArrayIndexOutOfBoundsException e) {
			param2 = null; // if no 2nd arg
		}
		ArrayList<File> sorted = new ArrayList<>();
		long b;
		b = sortUtil(files, sorted, param1, param2); // time before insertion sort
		long c = System.nanoTime(); // time after insertion sort
		System.out.println("InsertionSort:" + files + " Time: " + ((double) (c - b)) / 1000000000 + " secs");
	}

	public static ArrayList<File> makeDir(String dirPath) {
		File folder = new File(dirPath);
		if (!folder.isDirectory()) { // directory check
			System.out.println("Directory not found!");
			System.exit(0);
		}
		ArrayList<File> files = new ArrayList<>();
		Collections.addAll(files, folder.listFiles()); // copy files from array to arraylist for convenience
		return files;
	}

	public static long sortUtil(ArrayList<File> files, ArrayList<File> sorted, String param1, String param2) {
		// just a helper function to reduce code in main method
		long a = System.nanoTime(), b = 0; // time before quickSort
		if (param2 == null) { // if ascending
			if (param1.equals("-n")) { // name sort
				sorted = quickSort(files, new NameAsc());
				b = System.nanoTime();
				insertionSort(files, new NameAsc());
			} else if (param1.equals("-s")) { // size sort
				sorted = quickSort(files, new SizeAsc());
				b = System.nanoTime();
				insertionSort(files, new SizeAsc());
			} else if (param1.equals("-t")) { // time sort
				sorted = quickSort(files, new TimeAsc());
				b = System.nanoTime();
				insertionSort(files, new TimeAsc());
			} else {
				System.out.println("Invalid Option");
				System.exit(0);
			}
		} else if (param2.equals("-r")) { // if descending
			if (param1.equals("-n")) {
				sorted = quickSort(files, new NameDes());
				b = System.nanoTime();
				insertionSort(files, new NameDes());
			} else if (param1.equals("-s")) {
				sorted = quickSort(files, new SizeDes());
				b = System.nanoTime();
				insertionSort(files, new SizeDes());
			} else if (param1.equals("-t")) {
				sorted = quickSort(files, new TimeDes());
				b = System.nanoTime();
				insertionSort(files, new TimeDes());
			} else {
				System.out.println("Invalid Option");
				System.exit(0);
			}
		} else {
			System.out.println("Invalid Option");
			System.exit(0);
		}
		System.out.println("QuickSort: " + sorted + ", Time: " + ((double) (b - a)) / 1000000000 + " secs");
		return b;
	}

	// Comparators for different compare types and order(asc/des)

	public static class NameAsc implements Comparator<File> {
		public int compare(File a, File b) {
			return (a.getName().toLowerCase()).compareTo(b.getName().toLowerCase());
		}
	}

	public static class NameDes implements Comparator<File> {
		public int compare(File a, File b) {
			return (b.getName().toLowerCase()).compareTo(a.getName().toLowerCase());
		}
	}

	public static class SizeAsc implements Comparator<File> {
		public int compare(File a, File b) {
			return (int) (a.length() - b.length());
		}
	}

	public static class SizeDes implements Comparator<File> {
		public int compare(File a, File b) {
			return (int) (b.length() - a.length());
		}
	}

	public static class TimeAsc implements Comparator<File> {
		public int compare(File a, File b) {
			return ((Long) b.lastModified()).compareTo(a.lastModified());
		}
	}

	public static class TimeDes implements Comparator<File> {
		public int compare(File a, File b) {
			return ((Long) a.lastModified()).compareTo(b.lastModified());
		}
	}

	public static ArrayList<File> quickSort(ArrayList<File> fileList, Comparator<File> c) { // the reqd comparator will
																							// be passed
		if (fileList.isEmpty() || fileList.size() == 1) {
			return fileList;
		}
		ArrayList<File> greater = new ArrayList<>(); // Files greater than pivot
		File pivot = fileList.get(0); // first File in list, used as pivot
		ArrayList<File> smaller = new ArrayList<>(); // Files smaller than pivot

		File file;

		for (int i = 1; i < fileList.size(); i++) {
			file = fileList.get(i);
			if (c.compare(file, pivot) < 0) { // if file is less than pivot
				smaller.add(file);
			} else {// if greater or equal
				greater.add(file);
			}
		}

		greater = quickSort(greater, c);
		smaller = quickSort(smaller, c); // sort smaller recursively
		smaller.add(pivot); // add pivot to end of smaller
		smaller.addAll(greater); // add the greater Files to the smallers
		return smaller;
	}

	public static void insertionSort(ArrayList<File> fileList, Comparator<File> c) {
		for (int i = 1; i < fileList.size(); ++i) {
			File key = fileList.get(i);
			int j = i - 1;
			while (j >= 0 && (c.compare(fileList.get(j), key) > 0)) // if prev > next
			{
				fileList.set(j + 1, fileList.get(j)); // then move ahead
				j = j - 1;
			}
			fileList.set(j + 1, key);
		}
	}
}
