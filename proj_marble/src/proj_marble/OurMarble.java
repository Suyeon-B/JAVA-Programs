package proj_marble;
import java.util.Scanner;

class City {
	String city;
	int owner;
	int price;
	int toll;
	
	City(){ // 생성자 
		this(null, 0, 300, 600); // 기초 값으로 세팅 
	}
	
	City(String city, int owner, int price, int toll){ // 매개변수가 있는 생성자 
		this.city = city;
		this.owner = owner;
		this.price = price;
		this.toll = toll;
	}
}

class Player {
	int player;
	int budget;
	int diceNum;
	int diceSum;
	String[] ownCities = new String[10]; // 도시목록: (Start), Seoul, Tokyo, Sydney, LA, Cairo, Phuket, New delhi, Hanoi, Paris
	
	Player(){
		this(0, 5000, 0, 0);
	}
	
	Player(int player, int budget, int diceNum, int diceSum){
		this.player = player;
		this.budget = budget;
		this.diceNum = diceNum;
		this.diceSum = diceSum;
		
		for (int i=0;i<10;i++) {
			this.ownCities[i] = null;
		}
	}
}

// 실제 게임을 실행하는 Manager 클래스 
class PlayGame {
	Scanner scanner = new Scanner(System.in);
	City[] cityList = new City[360];
	Player[] playerList = new Player[2];
	String[] Cities = {"Start", "Seoul", "Tokyo", "Sydney", "LA", "Cairo", "Phuket", "New delhi", "Hanoi", "Paris"};
	
	PlayGame() { 
		// 생성자 : 플레이어의 순번 지정과 초기화 
		int count=0;
		for(int i=0;i<360;i++) {
			if (count == 9) {
				count = 0;
			}
			cityList[i] = new City();
			cityList[i].city = Cities[count];
			count+=1;
		}
		for(int i=0;i<2;i++) {
			playerList[i] = new Player();
			playerList[i].player = i;
		}
	}
	
	String showResult(int i) {
		
		System.out.printf("\n%s(%d)\n", cityList[playerList[i].diceSum].city, i+1);
		if (cityList[playerList[i].diceSum].owner == 0){ // 아직 소유자가 없는 땅일 때
			buyCity(i, playerList[i].diceSum);
		}
		else if (cityList[playerList[i].diceSum].owner != i+1){ // 소유자가 내가 아닐 때 
			if (payToll(i, playerList[i].diceSum) == "game over") { // 통행료가 부족하면 상대방 승리 후 게임 종료 
				String gameover = "game over";
				return gameover;
			}
		}
		else {
			// 소유자가 나일 때 아무 일 없음 
		}
		System.out.println("\n");
		return null;
	}
	
	void randDiceNum(int i) { // 플레이어의 주사위 숫자 랜덤으로 뽑기 
		gameTable();
		playerList[i].diceNum = (int) (Math.random() * 6) +1;
		playerList[i].diceSum += playerList[i].diceNum;
			
		System.out.printf("player %d: %d", i+1, playerList[i].diceNum);
	}
	
	void buyCity(int player, int location) {
		if (playerList[player].budget < cityList[location].price) {
			System.out.println("자금이 부족하여 도시를 구매할 수 없습니다.");
		}
		else if(cityList[location].city == "Start") {
			// 시작 지점에서는 아무 일 없음 
		}
		else { // 충분한 자금이 있다면
			// 구매 후 소유주 표기 & budget에서 차감 
			for(int i=0;i<360;i++) {
				if (cityList[i].city == cityList[location].city) {
					cityList[i].owner = playerList[player].player+1;
				}
			}
			playerList[player].budget -= 300;
			System.out.printf("player %d이 %s(을)를 구매했습니다. (남은 자금): %d", player+1, cityList[location].city, playerList[player].budget);
			for(int i=0;i<10;i++) {
				if (playerList[player].ownCities[i] == null) {
					playerList[player].ownCities[i] = cityList[location].city;
					break;
				}
			}
		}
	}
	
	String payToll(int player, int location){
		System.out.println("상대 player의 사유지입니다. 통행료를 지불하세요.");
		// 통행료를 지불할 돈이 부족하면 게임 종료 
		if (playerList[player].budget < cityList[location].toll) {
			System.out.println("자금이 부족합니다. 탈락입니다.");
			if (player == 1) {
				System.out.printf("player %d의 승리입니다.\n", 1);
			}
			else {
				System.out.printf("player %d의 승리입니다.\n", 2);
			}
			String result = "game over";
			return result;
		}
		// 통행료 지불 
		else {
			playerList[player].budget -= 600;
			playerList[1].budget += 600;
			if (player == 0) {
				System.out.printf("player %d (남은 자금): %d", 1, playerList[0].budget);
			}
			else {
				System.out.printf("player %d (남은 자금): %d", 2, playerList[1].budget);
			}
		}
		return null;
	}
	
	void gameTable() {
		// 게임판 그리는 메서드 
		// ownCities와 각 플레이어의 위치(playerList[i].diceSum) 정보를 가지고 구현할 수 있다.
		for (int i=0;i<2;i++) {
			System.out.println("---------------------------------------------------------------------------");
			System.out.printf("player %d (소유 도시): ", i+1);
			for (int j=0; j<10; j++) {
				if (playerList[i].ownCities[j] != null) {
					System.out.printf(playerList[i].ownCities[j]);
					System.out.print(", ");
				}
			}
			System.out.println("\n");
			System.out.println("---------------------------------------------------------------------------");
		}
	}
	void winner() {
		int result_1=0;
		int result_2=0;
		System.out.println("\n<< 게임 결과 >>");
		for (int i=0;i<2;i++) {
			// 소유지 개수 세기 
			for (int j=0; j<10; j++) {
				if (playerList[i].ownCities[j] != null) {
					if (i == 0) {
						result_1+=300;
					}
					else {
						result_2+=300;
					}
				}
			}
			// 전체 합산 결과 도출 
			if (i == 0) {
				result_1 += playerList[i].budget;
			}
			else {
				result_2 += playerList[i].budget;
			}
		}
		// '자산 + 소유 도시의 개수*300' 이 큰 player가 승리 
		if (result_1>result_2) {
			System.out.printf("player %d 이 승리했습니다. 축하합니다.", 1);
		}
		else if(result_1<result_2){
			System.out.printf("player %d 가 승리했습니다. 축하합니다.", 2);
		}
		else {
			System.out.printf("비겼습니다.");
		}
	}
}

public class OurMarble {
	public static void main(String[] args) {
		PlayGame game = new PlayGame();
		int turn=0;
		outerLoop:
		while (turn < 30) {
			// 주사위 뽑고 게임 시작 
			for (int i=0;i<2;i++) {
				System.out.printf("<< Turn : %d >>\n", turn+1);
				game.randDiceNum(i);
				//game.showResult(i);
				if (game.showResult(i) == "game over") {
					break outerLoop;
				}
				turn +=1;
			}
		}
		game.winner();
	}
}