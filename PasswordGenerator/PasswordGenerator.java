// package PasswordGenerator;

import java.util.Random;
import java.util.Scanner;
public class PasswordGenerator 
{
    public String generateRandomPassword(int len)
    {
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String num = "0123456789";
        String specialCharacters = "!@#$%^&*()[]{}<>/|~,.:;=+_-?";
        String combination; // Universal set from which the password is chosen

        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want an alphanumeric password?");
        String response = sc.nextLine().toLowerCase();
        if(response.equals("yes"))
        {
            combination = lower + upper + num + specialCharacters;
        }
        else
        {
            combination = lower + upper;
        }

        StringBuilder password = new StringBuilder(); 
        /* I'm using a string builder class to minimize memory use. 
          Using a string class instead would mean that for every time the for-loop below runs, and concatenates 
          various characters to form the final password memory is being allocated for every iteration as String 
          is immutable while the StringBuilder is mutable.
        
        i.e A String can be used when immutability is required, or concatenation operation is not required. 
        A StringBuilder can be used when a mutable string is needed without the performance overhead of 
        constructing lots of strings along the way.*/
        
        Random rand = new Random();
        
        //The charAt() method returns the character at the specified index in a string.
        /* The rand object is used to generate a random number between 0 and the length of combination
        which serves as an argument that charAt method receives to return the character at that index.*/

        for(int i = 0; i < len; i++)
        {
            password.append(combination.charAt(rand.nextInt(combination.length()))) ;
        }

        System.out.println("Password is " + password);
        return password.toString(); // .toString is a method that converts a StringBuilder type to a string
    }

    
}