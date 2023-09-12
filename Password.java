package ysquaresecuritysystemproject;


	import javax.crypto.Cipher;
	import javax.crypto.SecretKey;
	import java.security.Key;
	import java.security.SecureRandom;
	import java.util.Base64;
	import javax.crypto.KeyGenerator;
	
public class Password {
private  static Key key;
    
    public Password(String secretKey) throws Exception
    {
        key = generateKey(secretKey);
    }
	    public static String encryptPassword(String plainPassword, String secretKey) throws Exception {
	        
	        Cipher cipher = Cipher.getInstance("AES");
	        cipher.init(Cipher.ENCRYPT_MODE, key);
	        byte[] encryptedBytes = cipher.doFinal(plainPassword.getBytes());
	        return Base64.getEncoder().encodeToString(encryptedBytes);
	    }

	    public static String decryptPassword(String encryptedPassword, String secretKey) throws Exception {
	        
	        Cipher cipher = Cipher.getInstance("AES");
	        cipher.init(Cipher.DECRYPT_MODE, key);
	        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
	        return new String(decryptedBytes);
	    }
	    
	    private static Key generateKey(String secretKey) throws Exception {
	        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
	        keyGenerator.init(128, new SecureRandom(secretKey.getBytes()));
	        SecretKey key = keyGenerator.generateKey();
	        return key;
	    }
	}


