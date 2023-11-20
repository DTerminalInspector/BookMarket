package sec07.com.market.cart;

import sec07.com.market.bookitem.Book;

public interface CartInterface {
	void printBookList(Book[] p);
	boolean isCartInBook(String id);
	void insertBook(Book p);
	void removeCart(int numId);
	void deleteBook();
	void printCart();
}
