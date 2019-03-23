package by.training.epam.seredinski.encryption;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {

    private static final Logger logger = Logger.getLogger(Encryption.class);

    public static String encryptPassword (String password) {
        StringBuffer result = new StringBuffer();
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("md5");
            md5.update(password.getBytes());
            byte digest[] = md5.digest();
            for (byte data: digest) {
                if ((0xff & data) < 0x10) {
                    result.append("0" + Integer.toHexString((0xFF & data)));
                }
                else {
                    result.append(Integer.toHexString(0xFF & data));
                }
            }

        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return new String(result);
    }
}
