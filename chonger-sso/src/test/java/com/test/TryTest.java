package com.test;

import org.junit.Test;

import com.chonger.sso.utils.SendValidateCode;

public class TryTest {
	public static void main(String[] args) {
		try {
			SendValidateCode.send("147258", "18702506365");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
