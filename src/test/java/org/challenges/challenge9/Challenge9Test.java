package org.challenges.challenge9;

import java.util.List;
import java.util.Map;

import org.challenges.challenge9.MainSearch;
import org.challenges.challenge9.SearchEngines;
import org.challenges.challenge9.SearchResult;
import org.junit.Test;

public class Challenge9Test {

	@Test
	public void test() throws InterruptedException {

		// chrome driver file path needs to be specified via system property.
		// for this simple example we just do this here directly in the test.
		System.setProperty("webdriver.chrome.driver", "c:/chromedriver");

		String searchTerm = "abcd";
		
		// Call the search function and get results by given input
		MainSearch mainSearch = new MainSearch();
		Map<SearchEngines, List<SearchResult>> map = mainSearch.search(searchTerm);

		// Display the results of Yahoo
		List<SearchResult> yahoo = map.get(SearchEngines.Yahoo);
		for (SearchResult itr : yahoo) {
			System.out.println(" Yahoo Search result:" + itr.getDescription());
		}
		
		// Display the results of Google
		List<SearchResult> google = map.get(SearchEngines.Google);
		for (SearchResult itr2 : google) {
			System.out.println(" Google Search result:" + itr2.getDescription());
		}

		// Display the results of Bing
		List<SearchResult> bing = map.get(SearchEngines.Bing);
		for (SearchResult itr3 : bing) {
			System.out.println(" Bing Search result:" + itr3.getDescription());

		}
	}
}
