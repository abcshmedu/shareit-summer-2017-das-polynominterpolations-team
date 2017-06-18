package edu.hm.logic;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/** Diese Klasse wird benutzt, um ein Authentifizierungs-Token zu überprüfen.
 * 
 * @author Sebastian Becker
 * @author Peter Straßer */
public class TokenTester {
    private final String verificationURL;

    public TokenTester() {
	this("http://localhost:8083/shareit/account/verify");
    }

    public TokenTester(final String verificationURL) {
	this.verificationURL = verificationURL;
    }

    /** Diese Methode testet ein Token auf seine gültigkeit.
     * 
     * @param token
     *            Das zu testende Token */
    public MediaServiceResult testToken(final String token) {
	return connectToAuthServer(token);
    }

    private MediaServiceResult connectToAuthServer(final String token) {
	MediaServiceResult result = MediaServiceResult.FAIL;

	try {
	    URL url = new URL(verificationURL);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("POST");
	    conn.setRequestProperty("Accept", "application/json");

	    conn.setRequestProperty("Token", token);

	    System.out.println("TokenTester.connectToAuthServer: token = " + token);

	    conn.getInputStream();

	    switch (conn.getResponseCode()) {
	    case 200:
		result = MediaServiceResult.TOKEN_VALID;
		result.setDetail(conn.getHeaderField("JWT"));
		break;
	    }
	    conn.disconnect();
	}
	catch (MalformedURLException e) {
	    e.printStackTrace();
	}
	catch (IOException e) {
	    System.err.println("No connection to auth-server.");
	}

	return result;
    }
}