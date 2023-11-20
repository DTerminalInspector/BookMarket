package sec07.com.market.main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sec07.com.market.bookitem.Book;
import sec07.com.market.cart.Cart;
import sec07.com.market.member.Admin;
import sec07.com.market.member.User;
import sec07.com.market.exception.CartException;

//도서 정보 파일 저장 및 읽어오기
/* 
* 파일 입출력을 적용하여 온라인 서점의 도서 정보 목록을 파일에서 읽어오고, 관리자 인증을 통해 새로운 도서 정보를 파일에 추가로 저장해 보시오.
* 온라인 서점의 도서 정보 목록을 파일에 저장하고 파일로부터 도서 정보 목록을 읽어와 출력합니다.
* 관리자 인증을 통해 새로운 도서 정보를 입력받아 파일에 저장하고 파일에서 도서 정보 목록을 읽어와 출력하게 합니다.
*/

/*
<도서 정보 목록을 파일로 작성하기>
01. 배열로 저장했던 도서 정보 목록을 book.txt 파일로 만들고, 여기에 도서 정보 목록을 저장하시오.
	book.txt 파일 위치는 프로젝트 바로 하위 아래에 위치할 수 있도록 하시오 (src 파일과 동일한 선상에 저장)

<도서 정보 목록을 파일에서 읽어와 출력하기>
- 도서 정보 목록이 저장된 book.txt 파일에서 도서 정보 목록을 읽어와 도서의 개수와 도서 정보 목록을 출력하시오.
01. 도서의 개수 얻기
	Welcome.java 파일에서 book.txt 파일에서 도서의 개수를 얻는 totalFileToBookList() 메서드를 생성하시오.
02. 도서 정보 목록 읽고 저장하기
	Welcome.java 파일에서 book.txt 파일에서 도서 정보 목록을 읽어 배열에 저장하는 setFileToBookList() 메서드를 생성하시오.
03. 도서 정보 목록 저장 메서드 수정하기
	도서 정보를 배열에 저장하여 초기화하는 BookList() 메서드를 수정하시오.
04. 도서 정보 목록 출력하기
	[장바구니 항목 추가하기] 메뉴에서 도서 정보 목록을 출력하는 menuCartAddItem() 메서드를 수정하시오.

<파일에 새 도서 정보 저장하기>
- 관리자 인증을 통해 새로운 도서 정보를 키보드로 입력받아 book.txt 파일에 저장한 뒤 파일에서 도서 정보 목록을 읽어와 출력하시오.
01. 새 도서 정보 입력 받기
	관리자 인증을 거쳐 새로운 도서 정보를 키보드로 입력할 수 있도록 menuAdminLogin() 메서드를 수정하시오.
		: 도서 정보를 담고 있는 String[7] 배열을 하나 생성하시오.
		: 도서 정보를 추가할 것인지 물어보고 Y를 입력한 경우라면 String[7] 배열에 인덱스 [0]번부터 설정하시오.
			[0]번 ISBN의 경우 날짜 클래스 Date와 SimpleDateFormat을 이용하여 도서 ID를 "ISBN" + 날짜 시간(yyMMddhhmmss)로 자동 설정될 수 있도록 하시오.
			나머지 [1]번에서 [6]번의 값은 사용자에게 nextLine()으로 입력을 받아 저장하시오.
			nextLine() : 키보드로 한 행 입력 시 엔터키를 입력으로 처리하는 메서드입니다.
		: 도서 정보를 저장하지 않겠다고 하면 N 혹은 다른 문자열 입력 시, 관리자의 이름, 연락처, 아이디, 비밀번호가 출력될 수 있도록 하시오. (원래 사용하던 코드 사용)
* 	
*/

public class Welcome {

	static final int NUM_BOOK = 3; // 도서 개수에 대한 상수 NUM_BOOK 선언
	static final int NUM_ITEM = 7; // 도서 정보의 개수에 대한 상수 NUM_ITEM 선언

	// 장바구니 항목 저장하기 위한 Cart 클래스 객체 생성
	static Cart mCart;
	// 사용자 정보 저장하기 위해 User 클래스의 객체를 생성한다.
	static User mUser;
	static Book[] mBookList;

	public static void main(String[] args) {

		mBookList = new Book[NUM_BOOK];
		mCart=new Cart();
		Scanner input = new Scanner(System.in);

		System.out.println("당신의 이름을 입력하세요 : ");
		String userName = input.next();

		System.out.println("연락처를 입력하세요 : ");
		int userMobile = input.nextInt();

		// 사용자에게 입력을 받은 값을 User 객체를 이용해 생성
		mUser = new User(userName, userMobile);

		String greeting = "Welcome to Shopping Mall";
		String tagline = "Welcome to Book Market";

		boolean quit = false; // 종료 여부 설정 변수
		while (!quit) {

			System.out.println("==================================================");
			System.out.println("\t" + greeting);
			System.out.println("\t" + tagline);

			menuIntroduction();

			try {
				System.out.println("메뉴 번호를 선택해 주세요.");
				int n = input.nextInt();

				switch (n) {
				case 1:
					menuGusetInfo(userName, userMobile);
					break;
				case 2:
					menuCartItemList();
					break;
				case 3:
					menuCartClear();
					break;
				case 4:
					//System.out.printf("mBookList의 갯수 %d",mBookList.length);
					menuCartAddItem(mBookList);
					break;
				case 5:
					menuCartRemoveItemCount();
					break;
				case 6:
					menuCartRemoveItem();
					break;
				case 7:
					menuCartBill();
					break;
				case 8:
					menuExit();
					quit = true;
					break;
				case 9:
					menuAdminLogin();
					break;

				default:
					throw new Exception();
				}
			} catch (CartException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println("올바르지 않은 메뉴 선택으로 종료합니다.");
				quit = true;
			}
		}
	}

	// 도서 정보 저장 메서드 - book 배열에 세 가지 도서의 정보를 초기화
	public static Book[] BookList(Book[] booklist) {

		int k = totalFileToBookList();
		//System.out.println(k);
		setFileToBookList(k);
		/*
		 * for (Book book : mBookList) { System.out.println(book.getBookId());
		 * System.out.println(book.getName()); System.out.println(book.getUnitPrice());
		 * System.out.println(book.getAuthor());
		 * System.out.println(book.getDescription());
		 * System.out.println(book.getCategory());
		 * System.out.println(book.getReleaseDate()); }
		 */
		return mBookList;
		 		

	}

	// 메뉴 목록 출력
	private static void menuIntroduction() {
		System.out.println("==================================================");
		System.out.println(" 1. 고객 정보 확인하기 \t4. 장바구니에 항목 추가하기");
		System.out.println(" 2. 장바구니 상품 목록 보기 \t5. 장바구니의 항목 수량 줄이기");
		System.out.println(" 3. 장바구니 비우기 \t6. 장바구니의 항목 삭제하기");
		System.out.println(" 7. 영수증 표시하기 \t8. 종료");
		System.out.println(" 9. 관리자 로그인");
		System.out.println("==================================================");
	}

	// 1. 고객 정보 확인하기
	private static void menuGusetInfo(String userName, int userMobile) {
		System.out.println("현재 고객 정보 : ");
//		System.out.printf("이름 [%s] 연락처 [%d]%n", userName, userMobile);
		System.out.println(mUser);
	}

	// 2. 장바구니 상품 목록 보기
	private static void menuCartItemList() throws CartException {
		if (mCart.mCartCount == 0) {
			throw new CartException("장바구니에 항목이 없습니다.");
		} else {
			mCart.printCart();
		}
	}

	// 3. 장바구니 비우기
	private static void menuCartClear() throws CartException {
		if (mCart.mCartCount == 0)
			throw new CartException("장바구니에 항목이 없습니다.");
		else {
			System.out.println("장바구니의 모든 항목을 삭제하시겠습니까? Y | N");
			Scanner input = new Scanner(System.in);
			String str = input.nextLine();

			if (str.toUpperCase().equals("Y")) {
				System.out.println("장바구니의 모든 항목을 삭제헀습니다.");
				mCart.deleteBook();
			} else
				System.out.println("장바구니 비우기를 취소하셨습니다.");
		}
	}

	// 4. 바구니에 항목 추가하기 - 사용자로부터 도서 ID를 입력받고, 입력된 ID가 유효하면 해당 도서를 카트에 추가
	private static void menuCartAddItem(Book[] books) {

		books=BookList(books); // 책을 파일에서 읽어서 배열에 저장하는 메소드
		//System.out.printf("mBookList의 갯수 %d %n",mBookList.length);
		//mBookList 는 스테틱 멤버함수
		mCart.printBookList(books);
		
		
		boolean quit = false;

		while (!quit) {
			System.out.println("장바구니에 추가할 도서의 ID를 입력하세요.");

			Scanner input = new Scanner(System.in);
			String str = input.nextLine();

			boolean flag = false;
			int numId = -1;

			for (int i = 0; i < NUM_BOOK; i++) {
				if (str.equalsIgnoreCase(books[i].getBookId())) {
					numId = i;
					flag = true;
					break;
				}
			}

			if (flag) {
				System.out.println("장바구니에 추가하시겠습니까? Y | N");
				str = input.nextLine(); // 장바구니에 도서 추가 여부를 위한 입력값(Y 또는 N)을 받음

				if (str.toUpperCase().equals("Y")) {
					System.out.println(books[numId].getBookId() + "도서가 장바구니에 추가되었습니다.");
					// 장바구니에 넣기
					if (!isCartInBook(books[numId].getBookId()))
						mCart.insertBook(books[numId]);
				} else {
					System.out.println("장바구니 추가를 취소하셨습니다.");
				}

				quit = true;

			} else {
				System.out.println("다시 입력해 주세요.");
			}

		}
	}

	private static boolean isCartInBook(String bookId) {
		return mCart.isCartInBook(bookId);
	}

	// 5. 장바구니의 항목 수량 줄이기
	private static void menuCartRemoveItemCount() throws CartException {
		System.out.println("5. 장바구니의 항목 수량 줄이기 : ");
		if (mCart.mCartCount == 0)
			throw new CartException("장바구니에 항목이 없습니다.");
	}

	// 6. 장바구니의 항목 삭제하기
	private static void menuCartRemoveItem() throws CartException {
		// System.out.println("6. 장바구니의 항목 삭제하기 : ");
		if (mCart.mCartCount == 0)
			throw new CartException("장바구니에 항목이 없습니다.");
		else {
			menuCartItemList();
			boolean quit = false;
			while (!quit) {
				System.out.print("장바구니에서 삭제할 도서의 ID를 입력하세요. : ");
				Scanner input = new Scanner(System.in);
				String str = input.nextLine();
				boolean flag = false;
				int numId = -1;

				for (int i = 0; i < mCart.mCartCount; i++) {
					if (str.equalsIgnoreCase(mCart.mCartItem[i].getBookID())) {
						numId = i;
						flag = true;
						break;
					}
				}

				if (flag) {
					System.out.println("장바구니의 항목을 삭제하시겠습니까? Y | N");
					str = input.nextLine();
					if (str.toUpperCase().equals("Y")) {
						System.out.println(mCart.mCartItem[numId].getBookID() + " 장바구니에서 도서가 삭제되었습니다.");
						mCart.removeCart(numId);
					} else {
						System.out.println("항목 삭제를 취소하였습니다.");
					}
					quit = true;
				} else
					System.out.println("다시 입력해 주세요.");

			}
		}
	}

	// 7. 영수증 표시하기
	private static void menuCartBill() throws CartException {
		// System.out.println("7. 영수증 표시하기 : ");
		if (mCart.mCartCount == 0)
			throw new CartException("장바구니에 항목이 없습니다.");
		else {
			System.out.println("배송받을 분은 고객 정보와 같습니까? Y | N");
			Scanner input = new Scanner(System.in);
			String str = input.nextLine();

			if (str.toUpperCase().equals("Y")) { // 고객 정보와 동일한 경우
				System.out.println("배송지를 입력해 주세요 ");
				String address = input.nextLine();

				printBill(mUser.getName(), String.valueOf(mUser.getPhone()), address);

			} else { // 고객 정보와 동일하지 않은 경우
				System.out.print("배송받을 고객명을 입력하세요 : ");
				String name = input.nextLine();
				System.out.print("배송받을 고객의 연락처를 입력하세요 : ");
				String phone = input.nextLine();
				System.out.print("배송받을 고객의 배송지를 입력해 주세요 : ");
				String address = input.nextLine();

				printBill(name, phone, address);
			}
		}
	}

	// 7-1. 영수증 출력
	private static void printBill(String name, String phone, String address) {

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String strDate = formatter.format(date);

		System.out.println();
		System.out.println("----------배송받을 고객 정보----------");
		System.out.println("고객명 : " + name);
		System.out.println("연락처 : " + phone);
		System.out.println("배송지 : " + address);
		System.out.println("발송일 : " + strDate);
		// 장바구니에 담긴 항목의 총 금액 산출
		int sum = 0;
		for (int i = 0; i < mCart.mCartCount; i++) {
			sum += mCart.mCartItem[i].getTotalPrice();
		}
		System.out.printf("주문 총 금액 : %d원\n", sum);

		mCart.printCart(); // 장바구니에 담긴 항목 출력

		System.out.println("------------------------------");
	}

	// 8. 종료
	private static void menuExit() {
		System.out.println("8. 종료");
	}

	// 9. 관리자 로그인
	private static void menuAdminLogin() {
		System.out.println("관리자 정보를 입력하세요.");

		Scanner input = new Scanner(System.in);
		System.out.println("아이디 : ");
		String adminId = input.next();

		System.out.println("비밀번호 : ");
		String adminPwd = input.next();

		Admin admin = new Admin(mUser.getName(), mUser.getPhone());
		if (adminId.equals(admin.getId()) && adminPwd.equals(admin.getPassword())) {
			System.out.printf("이름 [%s] 연락처 [%d]\n관리자 아이디 [%s] 관리자 비밀번호 [%s]\n", admin.getName(), admin.getPhone(),
					admin.getId(), admin.getPassword());
		} else {
			System.out.println("관리자 정보가 일치하지 않습니다.");
		}

	}

	// 파일 읽어서 북 배열에 넣기
	private static int totalFileToBookList() {
		int numOfBooks = 0;
		String path = System.getProperty("user.dir") + "\\src\\book.txt";
//		String filename="book.txt";

		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			String line;
			String pattern = "^ISBN[0-9]*$";
			// Matcher matcher;

			while ((line = reader.readLine()) != null) {
				// matcher = pattern.matcher(line);
				if (Pattern.matches(pattern, line))
					numOfBooks++;
			}
		} catch (IOException e) {
			System.err.println("파일 읽기 오류: " + e.getMessage());
		}

		return numOfBooks;
	}

	private static void setFileToBookList(int num) {
		mBookList = new Book[num];
		int numOfBooks = -1;
		String path = System.getProperty("user.dir") + "\\src\\book.txt";

		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			String line;
			String pattern = "^ISBN[0-9]*$";
			while ((line = reader.readLine()) != null) {
				if (Pattern.matches(pattern, line))
					numOfBooks++;

				String isbn = line.trim();
				String title = reader.readLine().trim();
				int price = Integer.parseInt(reader.readLine().trim());
				String author = reader.readLine().trim();
				String description = reader.readLine().trim();
				String category = reader.readLine().trim();
				String publicationDate = reader.readLine().trim();

				mBookList[numOfBooks] = new Book(isbn, title, price, author, description, category, publicationDate);

			}
		} catch (IOException e) {
			System.err.println("파일 읽기 오류: " + e.getMessage());
		}

	}

	// https://hianna.tistory.com/587

}