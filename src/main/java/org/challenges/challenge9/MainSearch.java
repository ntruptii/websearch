package org.challenges.challenge9;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainSearch {
	Reuseables reuse = new Reuseables();

	public Map<SearchEngines, List<SearchResult>> search(String searchTerm) throws InterruptedException {
		Map<SearchEngines, List<SearchResult>> mapResult = new HashMap<SearchEngines, List<SearchResult>>();

                // yahoo Search
				reuse.openBrowser("https://www.yahoo.com");
				mapResult.put(SearchEngines.Yahoo, reuse.enterText(searchTerm, SearchEngines.Yahoo));
				reuse.CloseBrowser();
				
				// Google Search
				 reuse.openBrowser("https://www.google.com");
				 mapResult.put(SearchEngines.Google,reuse.enterText(searchTerm, SearchEngines.Google)); 
				 reuse.CloseBrowser();
				  
				// Bing Search
				 reuse.openBrowser("https://www.bing.com");
			     mapResult.put(SearchEngines.Bing,reuse.enterText(searchTerm, SearchEngines.Bing));
			     reuse.CloseBrowser();
				 
				return mapResult;
	}

}
