package sample.chap02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.ToString;

@Component
@ToString
@Getter
public class SampleHotel {
	private Chef chef ;
	
//	public SampleHotel() {
//		System.out.println("SampleHotel의 기본 생성자입니다.");
//	}
//	
//	@Autowired
//	public void setChef(Chef chef) {
//		this.chef = chef ;
//	}
	
	
	public SampleHotel(Chef chef) {
		this.chef = chef ;
		System.out.println("SampleHotel의 chef 초기화 생성자입니다.");
	}
}
