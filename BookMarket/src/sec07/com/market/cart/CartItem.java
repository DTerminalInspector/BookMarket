package sec07.com.market.cart;

import sec07.com.market.bookitem.Book;

public class CartItem {

	private Book itemBook;
	private String bookID;
	private int quantity;
	private int totalPrice;

	public CartItem() {
		super();
	}
	
	public CartItem(Book bookList) {
		this.itemBook = bookList;
		this.bookID = bookList.getBookId();
		this.quantity = 1;
		updateTotalPrice();
	}
	

	// 새로 추가된 setter, getter 메소드
	public Book getItemBook() {
		return itemBook;
	}


	public void setItemBook(Book itemBook) {
		this.itemBook = itemBook;
	}


	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}


	public String getBookID() {
		return bookID;
	}

	public void setBookID(String bookID) {
		this.bookID = bookID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
		this.updateTotalPrice();
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	// updateTotalPrice() 수정
	private void updateTotalPrice() {
		// totalPrice = Integer.parseInt(this.itemBook[2]) * this.quantity;
		totalPrice = this.itemBook.getUnitPrice() * this.quantity;
	}

}
