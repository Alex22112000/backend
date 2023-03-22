package utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
	MessageDigest md5;
	
	public Encryption(){
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// Блок перехвата
			e.printStackTrace();
		}
	}
	
	
	public String getCryptPassword(String pass){
		byte[]hash = md5.digest(pass.getBytes());
		
		StringBuilder builder = new StringBuilder();
		for(byte b: hash){
			builder.append(String.format("%02X", b));
		}
		
		
		return builder.toString();
	}
	
	
	public boolean checkPassword(String outPass, String inPass){
		byte[]hash = md5.digest(outPass.getBytes());
		
		System.out.println("11111111111111111111111111111111111111111111111111111");
		StringBuilder builder = new StringBuilder();
		for(byte b: hash){
			builder.append(String.format("%02X", b));
		}
		
		if(builder.toString() == inPass) {
			return true;
		}
		
		return false;
	}
	
}
