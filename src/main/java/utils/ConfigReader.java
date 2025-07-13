package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * @author Suma Kondapaneni
 * @created 12 Jul 2025
 */
public class ConfigReader {
	private static Properties prop;
	private static final String ENCRYPTION_PASSWORD = System.getenv("JASYPT_SECRET"); // 🛡️ Securely loaded

    private static final StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

	static {
		try {
			FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
			prop = new Properties();
			prop.load(fis);
			 encryptor.setPassword(ENCRYPTION_PASSWORD); // 🔐 Set master password
	            encryptor.setAlgorithm("PBEWithMD5AndDES");

		} catch (IOException e) {
			throw new RuntimeException("Failed to load config.properties: " + e.getMessage());
		}
	}

	public static String get(String key) {
		String value = prop.getProperty(key);
		 if (value != null && value.startsWith("ENC(") && value.endsWith(")")) {
	            return encryptor.decrypt(value.substring(4, value.length() - 1));
	        }
	        return value;
	}
}