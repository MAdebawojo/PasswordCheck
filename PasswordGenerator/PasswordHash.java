//package PasswordGenerator;

//for the hash
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Scanner;

public class PasswordHash 
{
	// instance variable sha1 stores the sha1 of the generated password
	private String sha1 = "";

	public String generateHash() 
	{
		String password = null;
		System.out.println("Enter 1 to Enter a Password or 2 to Generate a Random Password: ");
		Scanner sc = new Scanner(System.in);
		int option = sc.nextInt();
		switch(option)
		{
			case 1:
				System.out.println("Enter Password: ");
				sc.nextLine();
				password = sc.nextLine();
				System.out.println("My password is: " + password);
				break;
			case 2:
				System.out.println("Enter the password length: ");
				int len = sc.nextInt();
				PasswordGenerator obj = new PasswordGenerator(); // created an object of class PasswordGenerator
				password = obj.generateRandomPassword(len);
		}
		

		try 
		{
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.reset();
			digest.update(password.getBytes("utf8"));
			sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
			System.out.println(sha1);
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
