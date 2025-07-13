package utils;

/**
 * @author Suma Kondapaneni
 * @created 13 Jul 2025
 */



import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class CryptoUtil {

    private static final String ALGORITHM = "PBEWithMD5AndDES";

    public static String encrypt(String plainText, String secretKey) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(secretKey);
        encryptor.setAlgorithm(ALGORITHM);
        return encryptor.encrypt(plainText);
    }

    public static String decrypt(String encryptedText, String secretKey) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(secretKey);
        encryptor.setAlgorithm(ALGORITHM);
        return encryptor.decrypt(encryptedText);
    }

    public static void main(String[] args) {
        if (args.length < 2 || (!args[0].equalsIgnoreCase("encrypt") && !args[0].equalsIgnoreCase("decrypt"))) {
            System.out.println("Usage: java CryptoUtil encrypt|decrypt <value>");
            return;
        }

        String operation = args[0];
        String value = args[1];
        String secretKey = System.getenv("JASYPT_SECRET");

        if (secretKey == null || secretKey.isEmpty()) {
            System.out.println("Error: JASYPT_SECRET environment variable is not set.");
            return;
        }

        if (operation.equalsIgnoreCase("encrypt")) {
            String encrypted = encrypt(value, secretKey);
            System.out.println("Encrypted: ENC(" + encrypted + ")");
        } else {
            try {
                String decrypted = decrypt(value, secretKey);
                System.out.println("Decrypted: " + decrypted);
            } catch (Exception e) {
                System.out.println("Invalid encrypted value or wrong secret key.");
            }
        }
    }
}

