package similarity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import sets.SetUtilities;

public class SimilarityUtilities extends SetUtilities{
	/**
	 * Returns the set of non-empty lines contained in a text, trimmed of
	 * leading and trailing whitespace.
	 * 
	 * @param text
	 * @return the trimmed set of lines
	 */
	public static Set<String> trimmedLines(String text) {	
		Set<String> set = new HashSet<String>();
		if(text.isEmpty()) {
			return set; 
		}
		String[] textCopy = text.split("\\n");	
		
		for(int i=0; i<textCopy.length; i++) {	
			textCopy[i] = textCopy[i].trim();
		}
		for(int j = 0; j < textCopy.length; j++){
			if(!(textCopy[j].isEmpty())){
				set.add(textCopy[j]);
			}
		}
		return set;
	}

	/**
	 * Returns a list of words in the text, converted to lowercase.
	 * 
	 * Words are defined as a contiguous sequence of letters and numbers.
	 *
	 * @param text
	 * @return a list of lowercase words
	 */
	public static List<String> asLowercaseWords(String text) { 
		List<String> returnListString = new ArrayList<String>();
		String[] copy = text.split("\\W+");
		if(text.isEmpty()) {
			return returnListString;
		}
		for(int i=0; i<copy.length; i++) {
			copy[i] = copy[i].trim();
			copy[i] = copy[i].toLowerCase();
		}
		for(int j = 0; j < copy.length; j++){
			if(!(copy[j].isEmpty())){
				returnListString.add(copy[j]);
			}	
		}
		
		return returnListString;
	}

	/**
	 * Returns the line-based similarity of two texts.
	 * 
	 * The line-based similarity is the Jaccard index between each text's line
	 * set.
	 * 
	 * A text's line set is the set of trimmed lines in that text, as defined by
	 * trimmedLines.
	 * 
	 * @param text1
	 *            a text
	 * @param text2
	 *            another text
	 * @return
	 */
	public static double lineSimilarity(String text1, String text2) {
		Set<String> set1 = trimmedLines(text1);
		Set<String> set2 = trimmedLines(text2);
		double jaccard = jaccardIndex(set1,set2);
		
		return Math.abs(jaccard);	
	}

	/**
	 * Returns the line-based similarity of two texts.
	 * 
	 * The line-based similarity is the Jaccard index between each text's line
	 * set.
	 * 
	 * A text's line set is the set of trimmed lines in that text, as defined by
	 * trimmedLines, less the set of trimmed lines from the templateText.
	 * 
	 * @param text1
	 *            a text
	 * @param text2
	 *            another text
	 * @param templateText
	 *            a template, representing things the two texts have in common
	 * @return
	 */
	public static double lineSimilarity(String text1, String text2, String templateText) {
		Set<String> set1 = trimmedLines(text1);
		Set<String> set2 = trimmedLines(text2);
		Set<String> set3 = trimmedLines(templateText);
		
		Set<String> set4 = setDifference(set1,set3);
		Set<String> set5 = setDifference(set2,set3);
		double jaccardIndex = jaccardIndex(set4,set5);
		return Math.abs(jaccardIndex);
	}

	/**
	 * Returns a set of strings representing the shingling of the given length
	 * of a list of words.
	 * 
	 * A shingling of length k of a list of words is the set of all k-shingles
	 * of that list.
	 * 
	 * A k-shingle is the concatenation of k adjacent words.
	 * 
	 * For example, a 3-shingle of the list: ["a" "very" "fine" "young" "man"
	 * "I" "know"] is the set: {"averyfine" "veryfineyoung" "fineyoungman"
	 * "youngmanI" "manIknow"}.
	 * 
	 * @param words
	 * @param shingleLength
	 * @return 
	 */
	public static Set<String> shingle(List<String> words, int shingleLength) {
		Set<String> set = new HashSet<>();
		int minusOne = shingleLength - 1;
		int minusShingleLength = words.size() - minusOne;
		String[] array = new String[words.size()];
		if(words.isEmpty()){
			return set;
		} 
		for(int i = 0; i < array.length; i++){
			array[i] = words.get(i);
		}
		String s; 
		for(int j = 0; j < minusShingleLength; j++ ){
			s = array[j];
			for(int k =j+1; k < j+shingleLength; k++ ){
				s = s.concat(array[k]);
			}
			set.add(s);
		}
		return set;
	}

	/**
	 * Returns the shingled word similarity of two texts.
	 * 
	 * The shingled word similarity is the Jaccard index between each text's
	 * shingle set.
	 * 
	 * A text's shingle set is the set of shingles (of the given length) in that
	 * text, as defined by shingle and asLowercaseWords, 
	 * less the shingle set of the templateText.
	 * 
	 * @param text1
	 * @param text2
	 * @param templateText
	 * @param shingleLength
	 * @return
	 */
	public static double shingleSimilarity(String text1, String text2, String templateText, int shingleLength) {
		List<String> list1 = asLowercaseWords(text1);
		List<String> list2 = asLowercaseWords(text2);
		List<String> list3 = asLowercaseWords(templateText);
		
		Set<String> shingle1 = shingle(list1,shingleLength);
		Set<String> shingle2 = shingle(list2,shingleLength);
		Set<String> shingle3 = shingle(list3,shingleLength);
		
		Set<String> difference1 = setDifference(shingle1,shingle3);
		Set<String> difference2 = setDifference(shingle2,shingle3);
	
		
	
		double d = jaccardIndex(difference1,difference2);
		return d;

	}
}
