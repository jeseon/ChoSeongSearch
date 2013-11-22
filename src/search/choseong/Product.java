package search.choseong;

/**
 * 상품클래스
 * 
 * @author jeseon
 */
public class Product {
	private String sku;
	private String name;
	private int price;
	private int qty;
	private int saleAmt;
	private String searchIndex;
	
	public Product(String sku, String name, int price, int qty) {
		SearchEngine engine = new SearchEngine();
		
		this.sku = sku;
		this.name = name;
		this.price = price;
		this.qty = qty;
		this.saleAmt = price * qty;
		this.searchIndex = engine.getSearchIndex(name); // 한글 초성 변환기로 초성을 검색색인으로 생성
	}
	
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getSaleAmt() {
		return saleAmt;
	}

	public void setSaleAmt(int saleAmt) {
		this.saleAmt = saleAmt;
	}

	public String getSearchIndex() {
		return searchIndex;
	}
	
	public void setSearchIndex(String searchIndex) {
		this.searchIndex = searchIndex;
	}
}
