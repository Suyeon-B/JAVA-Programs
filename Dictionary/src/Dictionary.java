import java.io.*;
import java.util.*;


public class Dictionary {

	 public static void main(String[] args) { 
		 try {
			 FileReader fr = new FileReader("randdict.TXT"); // 문자 기반의 파일 입력  
			 BufferedReader br = new BufferedReader(fr); // line단위로 읽어오기 위한 보조스트림 이용 
			 HashMap dic = new HashMap(); // 정렬 및 출력을 용이하게 하기 위한 HashMap 이용 
			 int count = 0; // 전체 몇 개의 단어를 입력받았는지 확인하기 위한 count 
			 			 
			 String line = ""; // 사전을 line단위로 읽어오기 위한 변수 line 초기화 
			 
			 while (true) { 
				 line = br.readLine(); // 사전의 한 줄을 읽어온다.
				 if (line == null) { // 더 이상 읽어올 line이 없으면 반복 종료 
					 break;
				 }				 
				 // " "(영단어 이후 첫 공백) 를 기준으로 문자열을 추출한다.
				 // 먼저 " "의 인덱스를 찾는다.
				 int idx = line.indexOf(" ");
				 
				 // " " 앞부분 - 영단어 추출
				 String key = line.substring(0, idx);
				 // " " 뒷부분 - 뜻 추출
				 String value = line.substring(idx+1);
				 
				 dic.put(key, value); // 값 추가 
				 count += 1; // 단어 개수 +1
			 }
			 // 입력된 단어의 총 개수 출력 
			 System.out.println(count + "개의 단어를 읽었습니다.");
			 
			// 단어를 알파벳순으로 정렬
			 Set<String> set = dic.keySet();
			 Object[] mapkey = set.toArray();
			 Arrays.sort(mapkey);
			 System.out.println("정렬을 진행했습니다.");
			 
			 // 정렬된 단어 10개 출력 
			 System.out.println();
			 System.out.println("정렬된 단어 10개를 표시합니다.");
			 for (int i = 0; i < 10; i++) { 
				 System.out.println(i+1+". " + mapkey[i]);
				 System.out.println(dic.get(mapkey[i]));	
			 }
			 
			 
			 while(true) {
				 System.out.println();
				 System.out.print("검색할 단어를 입력하세요(. 입력 시 종료합니다.) : ");
				 Scanner scanner = new Scanner(System.in);
				 String word = scanner.next();
				 if (dic.get(word) == null) {
					 if (word.equals(".")) {
						 System.out.print("프로그램을 종료합니다.");
						 break;
					 }
					 System.out.println("! 없는 단어입니다. 다시 입력하세요.");
				 }
				 else {
					 if (dic.get(word).equals(": ")) {
						 System.out.println("뜻이 등록되지 않은 단어입니다. 다시 입력하세요.");
						 continue;
					 }
					 System.out.println("뜻 "+dic.get(word));
				 }
				 
			 }
			 
		 } catch(IOException e) { 
			 System.out.println("ERROR!");
		 }
		 }

}
