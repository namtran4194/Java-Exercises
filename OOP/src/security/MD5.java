package security;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * Message digest: mã hoá một chiều.
 * Mã hoá thông tin thành một đoạn mã không thể phục hồi.
 */
public class MD5 {
	public static void main(String[] args) {
		try {
			MD5 m = new MD5();
			System.out.print("Nhap vao chuoi can ma hoa: ");
			Scanner scn = new Scanner(System.in);
			String input = scn.nextLine();
			String ouput = m.encryptPass(input);
			System.out.println("Chuoi: " + input + " da duoc ma hoa kieu so: " + ouput);
			String ouput2 = m.encryptPass2(input);
			System.out.println("Chuoi: " + input + " da duoc ma hoa kieu @_@ troi dat =)): " + ouput2);
			scn.close();
		} catch (UnsupportedEncodingException ex) {
			System.out.println(ex.toString());
		}
	}

	public String encryptPass(String pass) {
		String passEncrypt;
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException ex) {
			System.out.println(ex.toString());
		}
		md5.update(pass.getBytes());
		BigInteger dis = new BigInteger(1, md5.digest());
		passEncrypt = dis.toString();
		return passEncrypt;
	}

	@SuppressWarnings("deprecation")
	public String encryptPass2(String pass) throws UnsupportedEncodingException {
		String passEncrypt;
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException ex) {
			System.out.println(ex.toString());
		}
		md5.update(pass.getBytes());
		String dis = new String(md5.digest(), 10);
		passEncrypt = dis.toString();
		return passEncrypt;
	}
}
