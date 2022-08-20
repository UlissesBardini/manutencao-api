package br.com.farol.manutencaoapi.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Util {

	public String getDataString(LocalDateTime dt) {
		String data = "";
		
		if (dt.getDayOfMonth() < 10) {
			data += "0";
		}
		data += dt.getDayOfMonth() + "/";
		if (dt.getMonthValue() < 10) {
			data += "0";
		}
		data += dt.getMonthValue() + "/" + dt.getYear() + " ";
		if (dt.getHour() < 10) {
			data += "0";
		}
		data += dt.getHour() + ":";
		if (dt.getMinute() < 10) {
			data += "0";
		}
		data += dt.getMinute() + ":";
		if (dt.getSecond() < 10) {
			data += "0";
		}
		data += dt.getSecond();
		
		return data;
	}
	
	public String getDataString(LocalDate dt) {
		String data = "";
		
		if (dt.getDayOfMonth() < 10) {
			data += "0";
		}
		data += dt.getDayOfMonth() + "/";
		if (dt.getMonthValue() < 10) {
			data += "0";
		}
		data += dt.getMonthValue() + "/" + dt.getYear();
		
		return data;
	}
	
}
