//package PasswordGenerator;

//for the hash
import java.math.BigInteger;
import java.security.MessageDigest;

public class PasswordHash 
{
	// instance variable sha1 stores the sha1 of the generated password
	private String sha1 = "";

	public String generateHash() 
	{
		PasswordGenerator obj = new PasswordGenerator(); // created an object of class PasswordGenerator
		String password = obj.generateRandomPassword(30);

		try 
		{
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.reset();
			digest.update(password.getBytes("utf8"));
			sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return sha1;

		// System.out.print( "The SHA-1 of \""+ password + "\" is: ");
		// System.out.println(sha1);

		// this.sha1Prefix = sha1.substring(0,5);
		// this.sha1Postfix = sha1.substring(5, sha1.length());
	}
	public String getPasswordHashPrefix() {
		// PasswordHash obj = new PasswordHash();
		return sha1.substring(0, 5);
//		return generatePasswordHashPrefix();
	}

	public String getPasswordHashPostfix() {
		// PasswordHash obj = new PasswordHash();
		return sha1.substring(5, sha1.length());

//		return generatePasswordHashPostfix();
	}
//	public String generatePasswordHashPrefix() {
//		// System.out.println(sha1.substring(0,5));
//		return sha1.substring(0, 5);
//	}
//
//	public String generatePasswordHashPostfix() {
//		// System.out.println(sha1.substring(5,sha1.length()));
//		return sha1.substring(5, sha1.length());
//	}
	


	
}