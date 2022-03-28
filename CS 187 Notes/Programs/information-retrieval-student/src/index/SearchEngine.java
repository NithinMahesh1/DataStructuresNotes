package index;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import comparators.TfIdfComparator;
import documents.DocumentId;

/**
 * A simplified document indexer and search engine.
 * 
 * Documents are added to the engine one-by-one, and uniquely identified by a DocumentId.
 *
 * Documents are internally represented as "terms", which are lowercased versions of each word 
 * in the document. 
 * 
 * Queries for terms are also made on the lowercased version of the term. Terms are 
 * therefore case-insensitive.
 * 
 * Lookups for documents can be done by term, and the most relevant document(s) to a specific term 
 * (as computed by tf-idf) can also be retrieved.
 *
 * See:
 * - <https://en.wikipedia.org/wiki/Inverted_index>
 * - <https://en.wikipedia.org/wiki/Search_engine_(computing)> 
 * - <https://en.wikipedia.org/wiki/Tf%E2%80%93idf>
 * 
 * @author Marc Liberatore
 *
 */
public class SearchEngine {
	
	/**
	 * Inserts a document into the search engine for later analysis and retrieval.
	 * 
	 * The document is uniquely identified by a documentId; attempts to re-insert the same 
	 * document are ignored.
	 * 
	 * The document is supplied as a Reader; this method stores the document contents for 
	 * later analysis and retrieval.
	 * 
	 * @param documentId
	 * @param reader
	 * @throws IOException iff the reader throws an exception 
	 */
//	Map<DocumentId, Map<String, Integer>> map;
//	Map<String, Set<DocumentId>> innerFrequency;
//	
//	public SearchEngine() {
//		this.map = new HashMap<DocumentId, Map<String, Integer>>();
//		this.innerFrequency =  new HashMap<String, Set<DocumentId>>();
//	}
	

	private Map<DocumentId, Map<String, Integer>> map;
	private Set<DocumentId> setForMap;
	private Map<String, Set<DocumentId>> contain;
	
	public SearchEngine() {
		this.map = new HashMap<DocumentId, Map<String, Integer>>();
		this.setForMap = new HashSet<>();
		this.contain = new HashMap<>();
	}
	

	public void addDocument(DocumentId documentId, Reader reader) throws IOException {
		Map<String, Integer> frequency = new HashMap<String, Integer>();
		BufferedReader br = new BufferedReader(reader);
		String line = null;
		while ((line = br.readLine()) != null) {
			String[] ar = line.toLowerCase().split("\\W+");
			
			for (int i = 0; i < ar.length; i++) {
				frequency.put(ar[i], frequency.getOrDefault(ar[i], 0) + 1);
				
				if (!contain.containsKey(ar[i])) {
					Set<DocumentId> set1 = new HashSet<DocumentId>();
					set1.add(documentId);
					contain.put(ar[i], set1);
				} else {
					contain.get(ar[i]).add(documentId);
				}
			}
			map.put(documentId, frequency);

		}
		
		
			
//		BufferedReader read = new BufferedReader(reader);
//		String reading = read.readLine();
//		String[] readArray = reading.toLowerCase().split("\\W+");
//		
//		Map<String, Integer> map2 = new HashMap<>();
//		
//		for (String s : readArray) {
//			if (!map.containsKey(s)) {
//				Set<DocumentId> add = new HashSet<DocumentId>();
//				add.add(documentId);
//				map2.put(s, map2.getOrDefault(s, 0) + 1);
//			} else {
//				Set<DocumentId> setId =  (Set<DocumentId>) map.get(s);
//				setId.add(documentId);
//				map2.put(s, setId);
//			}
//		}
//		map.put(documentId, map2);
		
		
		
//		int index = 0;
//		String read = "";
//		while (index != -1) {
//			index = reader.read();
//			read = read + (char) index;
//		}
//	
//		String[] traverse = read.toLowerCase().split("\\W+");	
//		
//		for (int i = 0; i < traverse.length; i++) {
//			if (!map.containsKey(traverse[i])) {
//				Set<DocumentId> add = new HashSet<DocumentId>();
//				add.add(documentId);
//				map.put(traverse[i], add);
//			}  else {
//				Set<DocumentId> setId =  map.get(traverse[i]);
//				setId.add(documentId);
//				map.put(traverse[i], setId);
//			}
//		}
	}
	
	/**
	 * Returns the set of DocumentIds contained within the search engine that contain a given term.
	 * 
	 * @param term
	 * @return the set of DocumentIds that contain a given term
	 */
	public Set<DocumentId> indexLookup(String term) {
		if (!(contain.containsKey(term.toLowerCase()))){
			return setForMap;
		}
		return contain.get(term.toLowerCase());
		
		
		
//		Set<DocumentId> returnMatch = new HashSet<>();
//		
//		for (int i = 0; i < map.size() - 1; i++) {
//			if (map.get(i).equals(term)) {
//				returnMatch.add((DocumentId) map.get(i));
//			}
//		}
//		
//		return returnMatch;
	}
	
	/**
	 * Returns the term frequency of a term in a particular document.
	 * 
	 * The term frequency is number of times the term appears in a document.
	 * 
	 * See 
	 * @param documentId
	 * @param term
	 * @return the term frequency of a term in a particular document
	 * @throws IllegalArgumentException if the documentId has not been added to the engine
	 */
	public int termFrequency(DocumentId documentId, String term) throws IllegalArgumentException {
		
		
		if (!(map.keySet().contains(documentId))) {
			throw new IllegalArgumentException("No document is contained");
		}
		for (DocumentId doc : map.keySet()) {
			if (doc.equals(documentId)) {
				for (String z : map.get(doc).keySet()) {
					if (z.equals(term.toLowerCase())) {
						return map.get(doc).get(z);
					}
				}
			}
		}
		return 0;
		
		
		
		
//		int count = 0;
//		
//		for (int i = 0; i < map.size() - 1; i++) {
//			if (map.get(i).equals(term)) {
//				count++;
//			}
//		}
//		
//		return count;
	}
	
	/**
	 * Returns the inverse document frequency of a term across all documents in the index.
	 * 
	 * For our purposes, IDF is defined as log ((1 + N) / (1 + M)) where 
	 * N is the number of documents in total, and M
	 * is the number of documents where the term appears.
	 * 
	 * @param term
	 * @return the inverse document frequency of term 
	 */
	public double inverseDocumentFrequency(String term) {
		double answer = 0.0;
		int count = 0;
		for (DocumentId d : map.keySet()) {
			if (termFrequency(d, term) > 0) {
				count++;
			}
			answer = Math.log((double) (1 + map.size()) / (double)(1 + count));
		}
		return answer;
	}
	
	/**
	 * Returns the tfidf score of a particular term for a particular document.
	 * 
	 * tfidf is the product of term frequency and inverse document frequency for the given term and document.
	 * 
	 * @param documentId
	 * @param term
	 * @return the tfidf of the the term/document
	 * @throws IllegalArgumentException if the documentId has not been added to the engine
	 */
	public double tfIdf(DocumentId documentId, String term) throws IllegalArgumentException {
		if (!(map.containsKey(documentId))) {
			throw new IllegalArgumentException("Document Not Found");
		}
		double frequency = 0.0;
		frequency = (inverseDocumentFrequency(term) * (termFrequency(documentId, term)));
		return frequency;
	}
	
	/**
	 * Returns a sorted list of documents, most relevant to least relevant, for the given term.
	 * 
	 * A document with a larger tfidf score is more relevant than a document with a lower tfidf score.
	 * 
	 * Each document in the returned list must contain the term.
	 * 
	 * @param term
	 * @return a list of documents sorted in descending order by tfidf
	 */
	public List<DocumentId> relevanceLookup(String term) {
		if (!(contain.containsKey(term))) {
			return new ArrayList<>();
		}
		List<DocumentId> listIDs = new ArrayList<>(contain.get(term));
		TfIdfComparator comparator = new TfIdfComparator(this, term);
		listIDs.sort(comparator);
		Collections.reverse(listIDs);
		return listIDs;
	}
}
