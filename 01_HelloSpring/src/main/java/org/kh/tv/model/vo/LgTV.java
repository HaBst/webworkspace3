package org.kh.tv.model.vo;

public class LgTV implements TV{
	
	
	public void powerOn() {
		System.out.println("엘지티비 ---- 전원을 켠다");
	}
	public void powerOff() {
		System.out.println("엘지티비 ---- 전원을 끈다");
	}
	public void volumeUp() {
		System.out.println("엘지티비 ---- 소리를 올린다");
	}
	public void volumeDown() {
		System.out.println("엘지티비 ---- 소리를 내린다");
	}

}
