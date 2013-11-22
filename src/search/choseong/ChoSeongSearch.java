package search.choseong;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * 초성 검색
 * 
 * @author jeseon
 */
public class ChoSeongSearch {
	public static void main(String[] args) throws UnsupportedEncodingException {
		List<Product> products = getProducts();
		SearchEngine engine = new SearchEngine();
		boolean boolSearch = false;
		
		System.out.print("검색할 상품의 초성을 입력하세요: ");
		
		Scanner scanner = new Scanner(System.in);
		String choStr = scanner.nextLine();
		scanner.close();
		
		if (choStr.length() < 2) {
			System.out.println("유효하지 않은 입력으로 종료합니다.");
			return;
		}
		
		Collections.sort(products, new SaleAmtDescCompare()); // 판매매출 내림차순 정렬
		for (Product product : products) {
			boolSearch = engine.searchChoSeong(choStr, product.getSearchIndex());
			if (boolSearch) {
				System.out.println(product.getName());
			}
		}
	}
	
	/**
	 * 판매매출 비교
	 */
	public static class SaleAmtDescCompare implements Comparator<Product> {
		@Override
		public int compare(Product p1, Product p2) {
			return p1.getSaleAmt() > p2.getSaleAmt() ? -1 : p1.getSaleAmt() < p2.getSaleAmt() ? 1:0;
		}
	}
	
	/**
	 * 데이터 입력
	 */
	private static List<Product> getProducts() {
		List<Product> products = new ArrayList<Product>();
		
		products.add(new Product("24771911", "KFC 치킨불고기 콤보", 2600, 27));
		products.add(new Product("24756487", "소다/카시오 패션스포츠시계", 49800, 2));
		products.add(new Product("24750911", "[목동] 육감숯불구이", 21600, 4));
		products.add(new Product("24718904", "[정자] 박종일 프로헤어", 29000, 1));
		products.add(new Product("24703223", "[구로디지털] LA식당", 30000, 29));
		products.add(new Product("24702630", "고려홍삼명가 명기홍삼정", 65300, 1));
		products.add(new Product("24692301", "쿠쿠 압력밥속", 21000, 2));
		products.add(new Product("24671273", "나이키 BEST 런닝화 22종", 45000, 20));
		products.add(new Product("24670532", "투세븐 자체제작 F/W신상슈즈", 34900, 2));
		products.add(new Product("24669991", "가을맞이 모던큐트 로퍼/플랫", 17900, 9));
		products.add(new Product("24669702", "트루릴리젼 프리미엄 스니커즈", 37500, 1));
		products.add(new Product("24667948", "나이키 남녀 팬츠 모음전", 49000, 1));
		products.add(new Product("24664912", "축구/농구/배구 공모음전", 14000, 6));
		products.add(new Product("24661322", "쿨제이 가을신상 트랜디셔츠", 19800, 2));
		products.add(new Product("24660422", "도드람포크 돼지갈비", 5400, 1));
		products.add(new Product("24659696", "[후쿠오카]자유여행 3일 189000", 189000, 1));
		products.add(new Product("24659141", "HK CLA다이어트", 29700, 1));
		products.add(new Product("24654726", "[청담] 민한복", 350000, 15));
		products.add(new Product("24654255", "[동대문] 애니피부", 15000, 4));
		products.add(new Product("24651025", "영진 표고버섯 슬라이스 1kg", 18500, 2));
		
		return products;
	}
}
