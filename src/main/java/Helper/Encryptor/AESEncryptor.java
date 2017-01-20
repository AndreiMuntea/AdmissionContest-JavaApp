package Helper.Encryptor;

import Helper.ConfigLoader.ConfigLoader;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by andrei on 2017-01-20.
 */
public class AESEncryptor implements IEncryption {

    private static final byte[] secretKey = "TheBestSecretKey".getBytes();
    private static final String ALGO = "AES";

    public AESEncryptor() {
    }

    @Override
    public String encrypt(String data) throws Exception {
        SecretKeySpec  key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(data.getBytes());
        return new BASE64Encoder().encode(encVal);
    }

    private SecretKeySpec  generateKey()
    {
        return new SecretKeySpec(secretKey, ALGO);
    }
}
