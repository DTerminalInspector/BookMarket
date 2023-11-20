package sec07.com.market.member;


public class User extends Person {

	public User(String name, int phone) {
		super(name, phone);
	}
	
	public User(String name, int phone, String address) {
		super(name, phone);
	}
	
	@Override
	public String toString() {
		return "이름 [" + super.getName() + "] 연락처 [" + super.getPhone() + "]"; 
	}

}
