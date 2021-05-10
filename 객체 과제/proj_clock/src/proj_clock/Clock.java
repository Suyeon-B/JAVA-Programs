package proj_clock;

import java.util.Scanner;

class Make_clock{
	int time=0;
	int hour=0, minute=0, sec=0;
	
	// 시간이 없는 생성자
	Make_clock(){
		this(0); // 생성자에서 다른 생성자 호출로 코드 재사용성을 높임 
	}
	// 시간을 지정한 생성자
	Make_clock(int time){
		this.time = time;
		if (this.time < 60){ // 초 단위만 있을 때 
			sec = this.time;
		}
		else if (this.time < 3600){ // 1분 이상 60분 미만일 때 
			sec = this.time%60;
			minute = this.time/60;
			hour = 0;
		}
		else{ // 1시간 이상일 때 
			sec = this.time%60;
			minute = this.time/3600%60;
			hour = this.time/3600;
			if (hour > 24) {
				hour = 0;
				minute = 0;
				sec = 0;
			}
		}
	}
	
	// 시간 출력 메서드 
	void print_time(){ 
		System.out.printf("%d시 %d분 %d초", this.hour, this.minute, this.sec);
	}
	
	// go 메서드
	void go() {
		sec += 1;
		if (sec >= 60) { // 초 단위가 60초 이상이 되면 
			sec -= 60; // 분으로 올려준다.
			minute += 1;
		}
		if (minute >= 60) { // 분 단위가 60분 이상이 되면 
			minute -= 60; // 시로 올려준다.
			hour += 1;
		}
		if (hour >= 24) { // 시 단위가 24시 이상이 되면 초기화 
			hour = 0;
			minute = 0;
			sec = 0;
		}
	}
	// goNsec 메서드
	void goNsec(int s){
		sec += s;
		if (sec >= 60) { // 초 단위가 60초 이상이 되면 
			sec = s%60; // 분으로 올려준다.
			int plus_m = s/60;
			minute += plus_m;
		}
		if (minute >= 60) { // 분 단위가 60분 이상이 되면 
			int plus_h = s/(60*60); // 시로 올려준다.
			minute %= 60; 
			hour += plus_h;
		}
		if (hour >= 24) { // 시 단위가 24시 이상이 되면 초기화 
			hour = 0;
			minute = 0;
			sec = 0;
		}
	}
	// back 메서드
	void back(){
		if (sec > 0) { // 00:00:nn 형태일 때 
			sec -= 1;
		}
		else if (minute > 0) { // nn:nn:00 형태일 때 
			minute -= 1;
			sec += 60;
			sec -= 1;
		}
		else { // nn:00:00 형태일 때 
			hour -= 1;
			minute += 59;
			sec += 60;
			sec -= 1;
		}
		
	}
	void backNsec(int s){
		// 초 단위로 바꾸어 빼줌 
		int origin_time = hour*60*60 + minute*60 + sec;
		if (origin_time >= s) {
			origin_time -= s;
		}
		else {
			origin_time = 0;
		}
		
		// 다시 시:분:초 형태로 바꿔줌 
		if (origin_time < 60){ // 초 단위만 있을 때 
			sec = origin_time;
		}
		else if (origin_time < 3600){ // 1분 이상 60분 미만일 때 
			sec = origin_time%60;
			minute = origin_time/60;
			hour = 0;
		}
		else{ // 1시간 이상일 때 
			sec = origin_time%60;
			minute = origin_time/3600%60;
			hour = origin_time/3600;
			if (hour > 24) {
				hour = 0;
				minute = 0;
				sec = 0;
			}
		}
	}
}

public class Clock {

	public static void main(String[] args) {
		System.out.print("시간을 입력해주세요(초 단위 / 미정 시 0 입력): ");
		Scanner sc = new Scanner(System.in); // 사용자로 부터 시간을 입력받아 
		int t = sc.nextInt(); // 시간 정보 저장
		
		// 시계 생성 문구 출력 
		System.out.print("시계가 생성되었습니다(");
		
		// Make_clock 인스턴스 생성 & 시간 초기값 출력 
		Make_clock my_clock = new Make_clock(t);
		my_clock.print_time();
		System.out.print(")");
		
		// 프로그램 실행 
		while(true) {
			System.out.print("\n");
			System.out.print("다음을 선택하세요 (1:go, 2:goNsec, 3:back, 4:backNsec, 5: 종료) ");
			int choice = sc.nextInt();
			if (choice == 1) {
				my_clock.go();
			}
			else if (choice == 2) {
				System.out.print("몇 초를 이동할까요? ");
				int go_sec = sc.nextInt();
				my_clock.goNsec(go_sec);
			}
			else if (choice == 3) {
				my_clock.back();
			}
			else if (choice == 4) {
				System.out.print("몇 초를 이동할까요? ");
				int back_sec = sc.nextInt();
				my_clock.backNsec(back_sec);
			}
			else if (choice == 5){
				System.out.print("프로그램을 종료합니다.");
				break;
			}
			System.out.print("현재 시간은 ");
			my_clock.print_time();
		}
		
	}

}
