package sec07.com.market.cart;

import sec07.com.market.bookitem.Book;

public class Cart implements CartInterface {

	static final int NUM_BOOK = 30;
	public CartItem[] mCartItem = new CartItem[NUM_BOOK];
	public static int mCartCount = 0;

	public Cart() {
		super();
	}

	// 도서 목록 출력 
	@Override
	public void printBookList(Book[] bookList) {
		//System.out.println( bookList.length);
		for(int i = 0; i < bookList.length; i++) {
			System.out.print(bookList[i].getBookId() + " | ");
			System.out.print(bookList[i].getName() + " | ");
			System.out.print(bookList[i].getUnitPrice() + " | ");
			System.out.print(bookList[i].getAuthor() + " | ");
			System.out.print(bookList[i].getDescription() + " | ");
			System.out.print(bookList[i].getCategory() + " | ");
			System.out.print(bookList[i].getReleaseDate());
			System.out.println();
		}
	}
	
	// 장바구니에 담긴 도서 있는지 없는지 확인 후 수량, 가격 증가 
	@Override
	public boolean isCartInBook(String bookId) {
		boolean flag = false;
		for(int i = 0; i < mCartCount; i++) {
			if(bookId == mCartItem[i].getBookID()) {
				mCartItem[i].setQuantity(mCartItem[i].getQuantity()+1);
				flag = true;
			}
		}
		return flag;
	}

	// 도서 추가
	@Override
	public void insertBook(Book book) {
		mCartItem[mCartCount++] = new CartItem(book);
	}
	
	// 장바구니 비우기
	@Override
	public void deleteBook() {
		mCartItem = new CartItem[NUM_BOOK];
		mCartCount = 0;
	}
	
	// 장바구니 항목 삭제
	@Override
	public void removeCart(int numId) {
		CartItem[] cartItme = new CartItem[NUM_BOOK];
		int num = 0;
		
		for(int i = 0; i < mCartCount; i++) {
			if(numId != i) 
				cartItme[num++] = mCartItem[i];
		}
		
		mCartCount = num;
		mCartItem = cartItme;
	}
	
	// 장바구니 상품 목록 출력
	@Override
	public void printCart() {
		System.out.println("장바구니 상품 목록 보기 : ");
		System.out.println("==================================================");
		System.out.println("[도서 ID] \t | [수량] \t | 합계");
		for(int i = 0; i < mCartCount; i++) {
			System.out.print(mCartItem[i].getBookID() + " \t | ");
			System.out.print(mCartItem[i].getQuantity() + " \t | ");
			System.out.print(mCartItem[i].getTotalPrice());
			System.out.println();
		}
		System.out.println("==================================================");
	}

	

}
