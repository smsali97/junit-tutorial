package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.unittesting.HeapSort;


@RunWith(Parameterized.class)
class ParametriziedSortingTest {

	private Integer[] numbers;
	private Integer[] sortedNumbers;
	
	
	
	public ParametriziedSortingTest(Integer[] numbers, Integer[] sortedNumbers) {
		super();
		this.numbers = numbers;
		this.sortedNumbers = sortedNumbers;
	}

	@Parameterized.Parameters
	   public static Collection primeNumbers() {
	      return Arrays.asList(new Integer[][][] {
	         {new Integer[]{ 1,2,3 }, new Integer[]{1,2,3} },
	         {	  new Integer[]{ 0 }, new Integer[]{0} },
	         {  new Integer[]{ 3,2,1} , new Integer[]{1,2,3} }  }
	      );
	   }


	
	@ParameterizedTest
	public void checkIfListIsSorted() {
		Integer[] arr = Arrays.copyOf(numbers, numbers.length);
		
		HeapSort<Integer> heapSort = new HeapSort<Integer>(arr);
		heapSort.sort();
		
		assertEquals(arr, sortedNumbers );
		
	}

}
