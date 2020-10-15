package co.com.aruma.utils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class Logger {
	
	public static void log(String data) {
		log.debug(data);
	}

}
