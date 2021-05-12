package proj_phoneDir;
import java.util.Scanner;
import java.util.ArrayList;


class Phone { 
	int number; 
	String name; 
	String phone; 
	String email;
}

public class phoneDir1 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Phone[] phonelist = new Phone[100];
		
		int choice;

		// 초기화 
		for(int i=0;i<100;i++) {
			phonelist[i] = new Phone(); 
		}
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
				System.out.println("전화번호 목록을 확인합니다.");
				System.out.println("(관리번호 | 성함 | 전화번호 | 이메일)");
				for(int i=0;i<100;i++) {
					//if (phonelist[0].name == null) {
					//	System.out.println("!!! 아직 입력된 번호가 없습니다.");
					//	break;
					//}
					if (phonelist[i].name != null) {
						System.out.print(phonelist[i].number + ": ");
						System.out.println(phonelist[i].name + " " + phonelist[i].phone + " " + phonelist[i].email);
					}
					
				}
			}
			
			// 2: 신규 연락처 등록
			else if (choice == 2) {
				System.out.print("성함: ");
				String name = scanner.next();
				System.out.print("전화번호: ");
				String phone = scanner.next();
				System.out.print("이메일: ");
				String email = scanner.next();
				
				for(int i=0;i<100;i++) {	
					// 빈 공간을 찾아 차례로 입력한다.
					if (phonelist[i].name == null) {
						phonelist[i].number = i+1;
						phonelist[i].name = name;
						phonelist[i].phone = phone;
						phonelist[i].email = email;
						
						// 입력 확인 메세지 출력 
						System.out.println("\n아래 신규 연락처가 등록되었습니다.");
						System.out.println("관리번호: " + phonelist[i].number);
						System.out.println("성함: " + phonelist[i].name);
						System.out.println("연락처: " + phonelist[i].phone);
						System.out.println("이메일: " + phonelist[i].email);
					
					
						// 등록 완료 
						break;
					}
				}			
			}
			
			// 3: 전화번호 삭제
			else if (choice == 3) {
				System.out.println(phonelist[0].name);
				if (phonelist[0].name == null) {
					System.out.println("!!! 아직 입력된 번호가 없습니다.");
					System.out.print("다시 메뉴를 선택하세요: ");
					continue;
				}
				// 관리번호 입력 메세지 출력 
				System.out.print("삭제할 관리번호를 입력하세요: ");
				int num = scanner.nextInt();
outerLoop:		for(int i=0;i<100;i++) {	
					// 관리번호에 해당하는 연락처 삭제 
					if (phonelist[i].number == num) {
						// 삭제 확인 메세지 출력 
						System.out.println("아래 연락처가 삭제되었습니다.");
						System.out.println("관리번호: " + phonelist[i].number);
						System.out.println("성함: " + phonelist[i].name);
						System.out.println("연락처: " + phonelist[i].phone);
						System.out.println("이메일: " + phonelist[i].email);
						
						// 삭제 후 뒤 연락처를 앞으로 끌고 온다. 
						for(int j=i+1;j<100;j++) {
							if (phonelist[j].name != null) {
								//System.out.println(phonelist[0].name);
								phonelist[i].name = phonelist[j].name;
								//System.out.println(phonelist[0].name);
								phonelist[i].phone = phonelist[j].phone;
								phonelist[i].email = phonelist[j].email;
								phonelist[i].number = phonelist[j].number-1;
								//System.out.println(phonelist[0].number);
							}
							else {
								phonelist[i].number = phonelist[j].number-1;
								phonelist[i].name = null;
								break outerLoop;
							}
						}
						
						// 삭제 완료 
						continue;
					}
					
				}		
				
			}
			
			// 4: 전화번호 수정
			else if (choice == 4) {
				if (phonelist[0].name == null) {
					System.out.println("!!! 아직 입력된 번호가 없습니다.");
					System.out.print("다시 메뉴를 선택하세요: ");
					continue;
				}
				System.out.print("수정할 연락처의 관리번호를 입력하세요: ");
				int num = scanner.nextInt();
				for(int i=0;i<100;i++) {	
					// 관리번호에 해당하는 연락처 수정 
					if (phonelist[i].number == num) {
						// 수정할 번호 입력 메세지 출력 
						System.out.print("수정할 연락처를 입력하세요: ");
						String newPhone = scanner.next();
						
						System.out.print(phonelist[i].phone + " -> ");
						phonelist[i].phone = newPhone;
						System.out.print(phonelist[i].phone+"\n");
						
						// 수정 완료 
						break;
					}
					
				}		
			}
			else if (choice == 5) {
				System.out.println("프로그램을 종료합니다. ");
				break;
			}
			else {
				System.out.println("올바른 번호를 입력하세요.");
			}
			// show menu 
			System.out.println("\n메뉴를 선택하세요. \n"
								+ "1: 전체 내역 보기 \n"
								+ "2: 신규 연락처 등록\n"
								+ "3: 전화번호 삭제 \n"
								+ "4: 전화번호 수정 \n"
								+ "5: 프로그램 종료 \n");
		}
		
	}
}
