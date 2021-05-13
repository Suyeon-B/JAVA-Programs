package proj_marble;
import java.util.Scanner;

class City {
	String city;
	int owner;
	int price;
	int toll;
	
	City(){ // 생성자 
		String city = "";
		String owner = "empty";
		int price = 300;
		int toll = 600;
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
	String[] ownCities = new String[9]; // 도시목록: (Start), Seoul, Tokyo, Sydney, LA, Cairo, Phuket, New delhi, Hanoi, Paris
	
	Player(){
		int player = 0;
		int budget = 5000;
		int diceNum = 0;
		int diceSum = 0;
		for (int i=0;i<9;i++) {
			ownCities[i] = null;
		}
	}
	
	Player(int player, int budget, int diceNum, int diceSum, String[] ownCities){
		this.player = player;
		this.budget = budget;
		this.diceNum = diceNum;
		this.diceSum = diceSum;
		this.ownCities = ownCities;
	}
	
}


class PlayGame {
	Scanner scanner = new Scanner(System.in);
	City[] cityList = new City[9];
	Player[] playerList = new Player[2];
	String[] Cities = {"Start", "Seoul", "Tokyo", "Sydney", "LA", "Cairo", "Phuket", "New delhi", "Hanoi", "Paris"};
	
	PlayGame() { 
		// 생성자 : 플레이어의 순번 지정과 초기화 
		for(int i=1;i<=9;i++) {
			cityList[i-1] = new City(); 
			cityList[i-1].city = Cities[i-1];
		}
		for(int i=1;i<=2;i++) {
			playerList[i-1] = new Player(); 
			playerList[i-1].player = i;
		}
		
	}
	
	void showResult(int i) {
		System.out.printf("\n%S(%d)\n", Cities[playerList[i-1].diceSum-1], cityList[playerList[i-1].diceSum-1].owner+1);
		if (Cities[playerList[i-1].diceSum] == "empty"){
			buyCity(i-1, playerList[i-1].diceSum);
		}
		else {
			payToll(i-1, playerList[i-1].diceSum);
		}
			
	}
	
	void randDiceNum(int i) { // 플레이어의 주사위 숫자 랜덤으로 뽑기 
		playerList[i-1].diceNum = (int) (Math.random() * 6) +1;
		playerList[i-1].diceSum += playerList[i-1].diceNum;
			
		System.out.printf("player %d: %d", i, playerList[i-1].diceNum);
	}
	
	void buyCity(int player, int location) {
		System.out.printf("%S(%d)\n", Cities[location], cityList[location].owner);
		if (playerList[player].budget < cityList[location].price) {
			System.out.println("자금이 부족합니다.");
		}
		else {
			cityList[location].owner = playerList[player].player;
			for(int i=1;i<=9;i++) {
				if (playerList[player].ownCities[i] == null) {
					playerList[player].ownCities[i] = Cities[location];
				}
			}
		}
	}
	
	void payToll(int player, int location){
		if (cityList[location].owner != player) {
			System.out.println("통행료를 지불하세요.");
			// 통행료를 지불할 돈이 부족하면 게임 종료 
			if (playerList[player].budget < cityList[location].toll) {
				System.out.println("자금이 부족합니다. 탈락입니다.");
				if (player == 1) {
					System.out.printf("player %d의 승리입니다.\n", 2);
				}
				else {
					System.out.printf("player %d의 승리입니다.\n", 1);
				}
			}
			// 통행료 지불 
			else {
				playerList[player].budget -= 600;
				playerList[1].budget += 600;
				System.out.printf("player %d (남은 자금): ", player, +  playerList[player].budget);
				if (player == 0) {
					System.out.printf("player %d (남은 자금): ", 2, +  playerList[2].budget);
				}
				else {
					System.out.printf("player %d (남은 자금): ", 1, +  playerList[1].budget);
				}
			}

		}
	}

}
public class OurMarble {
	public static void main(String[] args) {
		PlayGame game = new PlayGame();
		int turn=30;
		while (turn > 0) {
			// 주사위 뽑고 게임 시작 
			for (int i=1;i<=2;i++) {
				game.randDiceNum(i);
				game.showResult(i);
			}
			turn -=1;
		}
	}
}