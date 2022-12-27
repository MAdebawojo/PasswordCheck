/*
This class connects with the "HIBP" API
*/
// For establishing connection and reading input
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.lang.*;

public class myHttpURLConnection
{
    private static final String USER_AGENT = "Mozilla/5.0";

/*
    public static void main(String[] args) throws IOException
    {
        sendHttpGETRequest();
    }
*/

    public void sendHttpGETRequest() throws IOException
    {
        PasswordHash obj = new PasswordHash();
        obj.generateHash(); // this generates the hash so that the prefix and suffix can be gotten
        String sha1Prefix = obj.getPasswordHashPrefix();
        String sha1Postfix = obj.getPasswordHashPostfix();

        System.out.println(sha1Postfix.length());
        sha1Prefix = sha1Prefix.toUpperCase();
        sha1Postfix = sha1Postfix.toUpperCase();

        String GET_URL = "https://api.pwnedpasswords.com/range/" + sha1Prefix;

        URL url = new URL(GET_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = conn.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK)
        // success
        {
            BufferedReader in; //declaration
            in = new BufferedReader(new InputStreamReader(conn.getInputStream())); //assignment
            String inputLine;
            ArrayList<String> pwndList = new ArrayList<String>();
            Map<String, String> pwndDict = new HashMap<String, String>();
            while ((inputLine = in.readLine()) != null)
            {
                pwndList.add(inputLine);
            }

            String [] pwndPass = new String[pwndList.size()];

            for(int i = 0; i < pwndList.size(); i++)
            {
                pwndPass[i] = Arrays.toString(pwndList.get(i).split("\r\n"));
            }
            for(String part : pwndPass)
            {
                String []hashData = part.split(":");
                String foundHash = hashData[0].trim();
                String num = hashData[1].trim();
                pwndDict.put(foundHash, num);
            }

            // I concatenated the sha1Postfix with "[" since the passDict key contains the bracket
            if(pwndDict.containsKey("["+sha1Postfix))
            {
                System.out.println("Password not strong");
            }
            else
            {
                System.out.println("Password is strong");
            }
            in.close();
        }
        else
        {
            System.out.println("GET request not worked");
        }

/*
        for (int i = 1; i <= 8; i++) {
        System.out.println(conn.getHeaderFieldKey(i) + " = " +
        conn.getHeaderField(i));
        }
*/

    }
}