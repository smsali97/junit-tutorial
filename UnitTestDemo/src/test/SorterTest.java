package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.unittesting.Sorter;


class SorterTest {

	ArrayList<File> myFiles;
	ArrayList<File> myFilesSorted;
	
	
	@DisplayName("This validates reverse order")
	@Test
	public void validateQuickSortSortingByReverse() {
	
		myFiles = new ArrayList<File>(Arrays.asList(
				new File("C:\\Users\\smsal\\Drive\\Books\\CS Books\\Programming Books\\Programming Books")
				.listFiles()));
	
		myFilesSorted = new ArrayList<File>(myFiles.size());
	
		myFilesSorted.addAll(myFiles);
		
		Sorter.quickSort(myFiles, new Sorter.NameAsc());
		Collections.sort(myFilesSorted, Collections.reverseOrder());
		
		
		assertTrue(myFilesSorted.equals(myFiles));
		
	}
	
	@DisplayName("This validates normal order")
	@Test
	public void validateQuickSortSorting() {
	
		myFiles = new ArrayList<File>(Arrays.asList(
				new File("C:\\Users\\smsal\\Drive\\Books\\CS Books\\Programming Books\\Programming Books")
				.listFiles()));
	
		myFilesSorted = new ArrayList<File>(myFiles.size());
	
		myFilesSorted.addAll(myFiles);
		
		Sorter.quickSort(myFiles, new Sorter.NameAsc());
		Collections.sort(myFilesSorted);
		
		
		assertTrue(myFilesSorted.equals(myFiles));
		
	}
	

}
