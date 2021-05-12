package proj_phoneDir2;
import java.util.Scanner;
import java.util.Arrays;

// 개인 정보를 저장하는 Phone 클래스 
class Phone { 
	int number=0; 
	String name; 
	String phone; 
	String email; 
	
	Phone() { 
		number = 0; 
		name = ""; 
		phone = ""; 
		email = "";
	}
	
	Phone(int number, String name, String phone, String email) {
		this.number = number; 
		this.name = name; 
		this.phone = phone; 
		this.email = email;
	}
	
	int getNumber() { 
		return number;
	}
	
	void setInfo(int number, String name, String phone, String email) {
		this.number = number; 
		this.name = name; 
		this.phone = phone; 
		this.email = email;
	}
	
	void show( ) { 
		System.out.print(number + ": ");
		System.out.println(name + " " + phone + " " + email);
	}
	
}

// 전체 정보 목록을 저장하는 Phonebook 클래스 
class Phonebook {
	Scanner scanner = new Scanner(System.in);
	Phone[] phonelist = new Phone[100];
	
	Phonebook() { // 생성자 
		for(int i=1;i<=100;i++) {
			phonelist[i-1] = new Phone(); 
		}
	}
	
	void display() { // 1: 전체 내역 보기
		System.out.println("전화번호 목록을 확인합니다.");
		System.out.println("(관리번호 | 성함 | 전화번호 | 이메일)");
		int i=0;
		while(true) {
			if (phonelist[0].name == null) { // 등록된 번호가 없을 때 
				System.out.println("!!! 아직 입력된 번호가 없습니다.");
				break;
			}
			if (phonelist[i].number != 0) { // 등록된 번호가 있다면 출력 
				System.out.print(phonelist[i].number + ": ");
				System.out.println(phonelist[i].name + " " + phonelist[i].phone + " " + phonelist[i].email);
				i+=1;
			}
			else {
				break;
			}
		}
	}	
	
	void addnew() { // 2: 신규 연락처 등록
		// 사용자로부터 정보를 입력받아
		// 새로운 곳에 넣는다.
		System.out.print("성함: ");
		String name = scanner.next();
		System.out.print("전화번호: ");
		String phone = scanner.next();
		System.out.print("이메일: ");
		String email = scanner.next();
		// 빈 곳 찾기 
		int i;
		for(i=0;i<100;i++)
			if (phonelist[i].getNumber() == 0) 
				break;
		if (i == 100) {
			System.out.println("비어있는 공간이 없습니다.");
		return;
		}
		
		// 빈 공간을 찾아 입력한다.
		phonelist[i].setInfo(i+1, name, phone, email);
				
		// 입력 확인 메세지 출력 
		System.out.println("\n아래 신규 연락처가 등록되었습니다.");
		System.out.println("관리번호: " + phonelist[i].number);
		System.out.println("성함: " + phonelist[i].name);
		System.out.println("연락처: " + phonelist[i].phone);
		System.out.println("이메일: " + phonelist[i].email);
	}
		

	
	void deleteone() { // 3: 전화번호 삭제
		if (phonelist[0].name == null) { // 등록된 번호가 없을 때 
			System.out.println("!!! 아직 입력된 번호가 없습니다.");
			System.out.print("다시 메뉴를 선택하세요: ");
		}
		// 관리번호 입력 메세지 출력 
		System.out.print("삭제할 관리번호를 입력하세요: ");
		int num = scanner.nextInt();
		
		for(int i=0;i<100;i++) {	
			// 관리번호에 해당하는 연락처 삭제 
			if (phonelist[i].number == num) {
				// 삭제 확인 메세지 출력 
				System.out.println("아래 연락처가 삭제되었습니다.");
				System.out.println("관리번호: " + phonelist[i].number);
				System.out.println("성함: " + phonelist[i].name);
				System.out.println("연락처: " + phonelist[i].phone);
				System.out.println("이메일: " + phonelist[i].email);
				
				// 삭제용 임시 배열 생성 
				Phone[] temp = new Phone[100];
				// 초기화 
				for(int x=0;x<100;x++) {
					temp[x] = new Phone(); 
				}
				// 삭제한 곳을 제외한 나머지 복사 
				System.arraycopy(phonelist, 0, temp, 0, i);
				System.arraycopy(phonelist, i+1, temp, i, phonelist.length-i-1);
				
				// 다시 원래 배열로 복사 및 관리번호 수정 
				System.arraycopy(temp, 0, phonelist, 0, 99);
				if(phonelist[i].number != 0) {
						phonelist[i].number -= 1;
				}
			}
		}
	} 
	
	void modifyone() { // 4: 전화번호 수정
		if (phonelist[0].name == null) { // 등록된 번호가 없을 때 
			System.out.println("!!! 아직 입력된 번호가 없습니다.");
			System.out.print("다시 메뉴를 선택하세요: ");
		}
		System.out.print("수정할 연락처의 관리번호를 입력하세요: ");
		int num = scanner.nextInt();
		for(int i=0;i<100;i++) {	
			// 관리번호에 해당하는 연락처 수정 
			if (phonelist[i].number == num) {
				// 수정할 번호 입력 메세지 출력 
				System.out.print("수정할 연락처를 입력하세요: ");
				String newPhone = scanner.next();
				
				// 수정한 결과 표시 
				System.out.print(phonelist[i].phone + " -> ");
				phonelist[i].phone = newPhone;
				System.out.print(phonelist[i].phone+"\n");
			}
			
		}	
	}
}

public class phoneDir2 { // 객체가 도입된 프로그램 
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Phonebook phoneBook = new Phonebook();
		
		int choice;

		// show menu 
		System.out.println("메뉴를 선택하세요. \n"
							+ "1: 전체 내역 보기 \n"
							+ "2: 신규 연락처 등록 \n"
							+ "3: 전화번호 삭제 \n"
							+ "4: 전화번호 수정 \n"
							+ "5: 프로그램 종료 \n");
		
		while (true) {
			
			choice = scanner.nextInt();
			// 메뉴 선택에 따른 기능 
			// 1: 전체 내역 보기
			if (choice == 1) {
				phoneBook.display();
			}
			
			// 2: 신규 연락처 등록
			else if (choice == 2) {
				phoneBook.addnew();		
				// 입력 완료 
			}
			
			// 3: 전화번호 삭제
			else if (choice == 3) {
				phoneBook.deleteone();
				// 삭제 완료 
			}		
			
			// 4: 전화번호 수정
			else if (choice == 4) {
				phoneBook.modifyone();
				// 수정 완료 
			}
			
			else if (choice == 5) { // 프로그램 종료 
				System.out.println("프로그램을 종료합니다. ");
				break;
			}
			
			else { // 1~5 이외의 번호를 입력했을 때 
				System.out.println("올바른 번호를 입력하세요.");
			}
			
			// 반복용 메뉴 표시 
			System.out.println("\n메뉴를 선택하세요. \n"
								+ "1: 전체 내역 보기 \n"
								+ "2: 신규 연락처 등록\n"
								+ "3: 전화번호 삭제 \n"
								+ "4: 전화번호 수정 \n"
								+ "5: 프로그램 종료 \n");
		}
		
	}
}
