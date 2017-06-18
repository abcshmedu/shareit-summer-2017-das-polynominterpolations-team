package edu.hm;

import edu.hm.logic.MediaServiceResult;
import edu.hm.logic.TokenTester;

public class Test {
    public static void main(String... args) {
	TokenTester test = new TokenTester();
	String token = "r5grrf3tm59pqfgqf153rnc4om";

	MediaServiceResult mr = test.testToken(token);
	System.out.println(mr.getDetail());
    }
}
