package search.choseong;

import static org.junit.Assert.*;

import org.junit.Test;

public class ChoSeongSearchTest {
	@Test
	public void testMain() {
		SearchEngine engine = new SearchEngine();
		Product product = new Product("24692301", "쿠쿠 압력밥솥", 21000, 2);
		
		assertEquals("ㅋㅋㅇㄹㅂㅅ", product.getSearchIndex());
		assertEquals(true, engine.searchChoSeong("ㅂㅅ", product.getSearchIndex()));
	}
}
