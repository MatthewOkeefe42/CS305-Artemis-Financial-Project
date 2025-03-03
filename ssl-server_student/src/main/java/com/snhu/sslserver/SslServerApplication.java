package com.snhu.sslserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}

}
//FIXME: Add route to enable check sum return of static data example:  String data = "Hello World Check Sum!";
@RestController
class ServerController{

	@RequestMapping("/hash")
    public String myHash() throws NoSuchAlgorithmException{

    	MessageDigest md = MessageDigest.getInstance("SHA-256");
    		try {
    			String data = "Hello Matthew O'Keefe!!";
        	
    			byte[] hashedBytes = md.digest(data.getBytes());
        	
    			String hashHex = bytesToHex(hashedBytes);
                 
    			return "<p>data: "+ data + "</p><p>SHA-256 Hash: " + hashHex;
    			
    		}catch (Exception e) {
    		     return "couldn't make digest of partial content";
    		 }
    }

    public static String bytesToHex(byte[] hashedBytes) {
    	 StringBuilder result = new StringBuilder();
         for (byte aByte : hashedBytes) {
             result.append(String.format("%02x", aByte));
         }
         return result.toString();
    }
}