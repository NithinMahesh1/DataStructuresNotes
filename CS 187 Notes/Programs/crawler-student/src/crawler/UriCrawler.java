package crawler;

import java.awt.List;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.nodes.Document;

import document.RetrievedDocument;

/**
 * A simplified web crawler, specialized to crawl local URIs rather
 * than to retrieve remote documents.
 * 
 * @author liberato
 *
 */
public class UriCrawler {
	/**
	 * Instantiates a new UriCrawler. The maximum number of documents a crawler
	 * will attempt to visit, ever, is limited to visitQuota.
	 * 
	 * @param visitQuota
	 *            the maximum number of documents a crawler will attempt to
	 *            visit
	 * @throws IllegalArgumentException
	 *             if maximumRetrievalAttempts is less than one
	 */
	
	private int visitAttempts;
	private int visitQuota;
	private Set<URI> visitedUris = new HashSet<URI>();
	private Set<URI> unVisitedUris = new HashSet<URI>();
	private Set<RetrievedDocument> retrieved = new HashSet<RetrievedDocument>();
	
	
	public UriCrawler(int visitQuota) throws IllegalArgumentException {
		if (visitQuota < 1) {
			throw new IllegalArgumentException();
		}
		// TODO
		this.visitQuota = visitQuota;
	}

	/**
	 * Returns the set of URIs that this crawler has attempted to visit
	 * (successfully or not).
	 * 
	 * @return the set of URIs that this crawler has attempted to visit
	 */
	public Set<URI> getVistedUris() {
		// TODO
		return visitedUris;
	}
	
	/**
	 * Returns the set of RetrievedDocuments corresponding to the URIs
	 * this crawler has successfully visited.
	 * 
	 * @return the set of RetrievedDocuments corresponding to the URIs
	 * this crawler has successfully visited
	 */
	public Set<RetrievedDocument> getVisitedDocuments() {
		// TODO
		return retrieved;
	}

	/**
	 * Adds a URI to the collections of URIs that this crawler should attempt to
	 * visit.
	 * 
	 * @param uri
	 *            the URI to be visited
	 */
	public void addUri(URI uri) {
		// TODO
		unVisitedUris.add(uri);
	}

	/**
	 * Attempts to visit a single as-yet unattempted URI in this crawler's
	 * collection of to-be-visited URIs.
	 * 
	 * Visiting a document entails parsing the text and links from the URI.
	 * 
	 * If the parse succeeds:
	 * 
	 * - The "file:" links should be added to this crawler's collection of
	 * to-be-visited URIs.
	 * 
	 * - A new RetrievedDocument should be added to this crawler's collection of
	 * successfully visited documents.
	 * 
	 * If the parse fails, this method considers the visit attempted but
	 * unsuccessful.
	 * 
	 * @throws MaximumVisitsExceededException
	 *             if this crawler has already attempted to visit its quota of
	 *             visits
	 * @throws NoUnvisitedUrisException
	 *             if no more unattempted URI remain in this crawler's
	 *             collection of URIs to visit
	 */
	public void visitOne() throws MaximumVisitsExceededException, NoUnvisitedUrisException {
		// TODO
		if (unVisitedUris.isEmpty()) {
			throw new NoUnvisitedUrisException();
		}
		if (visitAttempts++ > visitQuota) {
			throw new MaximumVisitsExceededException();	
		}
		for (URI temp : unVisitedUris) {
			if (visitedUris.contains(temp) == false) {
				visitedUris.add(temp);
				visitAttempts++;
				if (CrawlerUtils.parse(temp) != null) {
					unVisitedUris.remove(temp);
					unVisitedUris.addAll(CrawlerUtils.getFileUriLinks(CrawlerUtils.parse(temp)));
					visitedUris.add(temp);
					retrieved.add(new RetrievedDocument(temp, CrawlerUtils.parse(temp).text(), CrawlerUtils.getFileUriLinks(CrawlerUtils.parse(temp))));
					return;
				}
			}
		}
		
	}

	/**
	 * Attempts to visit all URIs in this crawler (and any URIs they reference,
	 * and so on).
	 * 
	 * This method will not raise a MaximumVisitsExceededException if there are
	 * more URIs than can be visited. It will instead stop once the UriCrawler's
	 * quota has been reached.
	 */
	public void visitAll() throws MaximumVisitsExceededException, NoUnvisitedUrisException {
		// TODO
		while (visitAttempts <= visitQuota) {
			visitOne();
		}
	}
}
