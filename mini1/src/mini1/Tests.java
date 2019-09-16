package mini1;

import static mini1.FromLoopToNuts.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Random;
/**
 * 	
 * @author Braedon Giblin
 *
 */
public class Tests {
	@Test
	public void testCountMatches() {
		assertEquals("Identical strings should match every charactar", 5, countMatches("Hello", "Hello"));
		assertEquals("Should find 4 mathces", 4, countMatches("H3llo", "Hello"));
		assertEquals("Should find no matches with an empty string as param", 0, countMatches("", "Hello"));
		assertEquals("Should find no matches with 2 empty strings", 0, countMatches("", ""));
		assertEquals("Should find 1 match if second param is shorter string than first", 1, countMatches("abc", "a"));
		assertEquals("Should find 1 match if second param is longer than first", 1, countMatches("a", "abc"));
	}
	
	@Test
	public void testCountSubstrings() {
		assertEquals("Identical strings should return 1 match", 1, countSubstrings("Hello", "Hello"));
		assertEquals("2 discrete matches should return 2", 2, countSubstrings("aa", "aabbaa"));
		assertEquals("2 sequential matches should return 2", 2, countSubstrings("aa", "aaaa"));
		assertEquals("An empty target should return 0", 0, countSubstrings("", "aaaa"));
		assertEquals("An empty source should return 0", 0, countSubstrings("aa", ""));
		assertEquals("2 empty strings should return 0", 0, countSubstrings("", ""));		
		assertEquals("Strings with no matches should return 0", 0, countSubstrings("aa", "bbbb"));
	}
	
	@Test
	public void testCountSubstringsWithOverlap() {
		assertEquals("Identical strings should return 1 match", 1, countSubstringsWithOverlap("Hello", "Hello"));
		assertEquals("2 discrete matches should return 2", 2, countSubstringsWithOverlap("aa", "aabbaa"));
		assertEquals("2 sequential matches should return 3", 3, countSubstringsWithOverlap("aa", "aaaa"));
		assertEquals("4 sequential matches should return 7", 7, countSubstringsWithOverlap("aa", "aaaaaaaa"));
		assertEquals("An empty target should return 0", 0, countSubstringsWithOverlap("", "aaaa"));
		assertEquals("An empty source should return 0", 0, countSubstringsWithOverlap("aa", ""));
		assertEquals("2 empty strings should return 0", 0, countSubstringsWithOverlap("", ""));		
		assertEquals("Strings with no matches should return 0", 0, countSubstringsWithOverlap("aa", "bbbb"));
	}
	
	@Test
	public void testDifferByOneSwap() {
		assertFalse("Identical strings should not differ", differByOneSwap("aaaa", "aaaa"));
		assertTrue("Differs by one swap", differByOneSwap("ba", "ab"));
		assertTrue("Differs by one swap in the middle, should return true", differByOneSwap("aaba", "abaa"));
		assertTrue("Differs by one swap at the beginnning, should return true", differByOneSwap("baaa", "abaa"));
		assertTrue("Differs by one swap at the end, should return true", differByOneSwap("aaab", "aaba"));
		assertTrue("Differs by one swap at the end, should return true", differByOneSwap("aaab", "aaba"));
		assertFalse("Differs by multiple swaps should return false", differByOneSwap("aaaba", "ab/aab"));
		assertFalse("Strings are different lengths, should return false", differByOneSwap("aab", "aaba"));
		assertFalse("Differs by many charactars, should return false", differByOneSwap("aaabcda", "acddcba"));
		assertFalse("Differs by many charactars, should return false", differByOneSwap("abba", "baab"));
		assertFalse("Longer case, does not differ should return false", differByOneSwap("aaaaaaaaa", "aaaaaaaaa"));
		assertTrue("Longer case, difers by 1, should return true", differByOneSwap("aaabaaaaa", "aabaaaaaa"));
		assertFalse("Longer case, difers by several, should return false", differByOneSwap("aaabababa", "aaaababab"));
	}
	
	@Test
	public void testEliminateRuns() {
		assertEquals("Should eliminate runs", "abc", eliminateRuns("aaabbbbccccc"));
		assertEquals("Should eliminate runs", "abc", eliminateRuns("abbbbc"));
		assertEquals("Should eliminate runs", "abc", eliminateRuns("abccccc"));
		assertEquals("Shouldn't find any runs", "abc", eliminateRuns("abc"));
	}
	
	@Test
	public void testFindEscapeCount() {
		assertEquals(2, findEscapeCount(1, .5, 5));
		assertEquals("Should stop after max iterations", 1, findEscapeCount(1, .5, 1));
	}
	
	@Test
	public void testIsArithmetic() {
		assertTrue("Arithmetic Sequence w/ delta = 2", isArithmetic("2,4,6,8"));
		assertTrue("Arithmetic sequence w/ delta = 25 starting at 0", isArithmetic("0,25,50,75"));
		assertTrue("Arithmetic Sequence w/ delta = 12 starting at a negative", isArithmetic("-12,0,12,24"));
		assertFalse("Non-arithmetic sequence", isArithmetic("2,6,7,8"));
		assertTrue("Empty String", isArithmetic(""));
		assertTrue("1 number string", isArithmetic("2"));
		assertFalse("Invalid String", isArithmetic("2,4,hello"));
		assertFalse("Invalid String", isArithmetic("aa2,4,hello"));
	}
	
	@Test
	public void testThreeInARow() {
		assertEquals(7,threeInARow(new Random(42), 5));
		assertEquals(23,threeInARow(new Random(58), 3));
		assertEquals(3,threeInARow(new Random(78), 2));
		assertEquals(141,threeInARow(new Random(23), 10));
		
	}
}
