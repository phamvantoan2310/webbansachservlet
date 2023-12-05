package until;

import java.security.MessageDigest;

import org.apache.tomcat.util.codec.binary.Base64;




public class maHoaMatKhau {
	
	public static String maHoa(String matKhau) {
		String salt = "phamvantoan";
		String result = "";
		
		matKhau += salt;
		
		try {
			byte[] mahoamatkhau = matKhau.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			result = Base64.encodeBase64String(md.digest(mahoamatkhau));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
