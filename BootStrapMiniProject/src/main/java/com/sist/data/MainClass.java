package com.sist.data;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MainClass {
	private WebDriver driver;
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\webDev\\chromedriver.exe";

	public static void main(String[] args) {
		MainClass cgv = new MainClass();
		WebElement movieDiv = null;
		int rank= 0;
		
		//해당 브라우저에 다양한 옵션을 주기위해 ChromeOptions 객체화
		ChromeOptions options = new ChromeOptions();
		//옵션 설정
		//headless : 브라우저 실행이 내부로 전환된다, 눈에 안보인다.
		options.addArguments("headless");
		
		//운영체제에 드라이버 설정
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		
		//설정한 옵션 객체를 ChromeDriver 생성자를 통해 전달한다.
		cgv.driver = new ChromeDriver(options);
		
		options.setBinary("/path/to/other/chrome/binary");
		
		
		
		String url = "http://www.cgv.co.kr/movies/";

		//요청할 URL을 get()에 전달하면 응답된 페이지를 브라우저를 통해 확인할 수 있다.
		cgv.driver.get(url);
		try {
			//브라우저가 실행되는 시간을 기다려준다.
			Thread.sleep(1000);
		} catch (InterruptedException e) { // 자바가 셀레니움보다 빨라서 1초씩은 기다려줍니다. 브라우저 열리기도 전에 태그를 가져올수 있기떄문에
			e.printStackTrace();
		}

		// btn-more-fontbold : 더 보기 버튼 클래스 명
		//해당 URL에서 더 보기 버튼을 객체로 가져온 후 click()을 통해 클릭해준다.
		cgv.driver.findElement(By.className("btn-more-fontbold")).click(); 

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// sect-movie-chart : 영화 목록을 담고 있는 div 태그 클래스 명
		//div태그를 가져온 뒤 movieDiv에 넣어준다.
		movieDiv = cgv.driver.findElement(By.className("sect-movie-chart"));
		
		//div태그 안에 title클래스 태그를 전부 가져온다.(findElements())      
		for(WebElement el:movieDiv.findElements(By.className("title"))){
			//각각의 <strong class="title">태그를 el에 순서대로 담아준다.
			//가져온 태그안에 있는 내용(요소 , 컨텐츠, 텍스트)를 getText()로 가져올 수 있다.
			System.out.println(++rank+". "+el.getText());
		}
        try
        {
          if(cgv.driver!=null)
          {
		    cgv.driver.close();
		    cgv.driver.quit();
          }
        }catch(Exception ex) {}

	}
}