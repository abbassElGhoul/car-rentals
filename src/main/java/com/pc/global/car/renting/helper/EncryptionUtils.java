package com.pc.global.car.renting.helper;

import com.pc.global.car.renting.customeResponse.Response;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Base64;

@Log4j2
@Component
public class EncryptionUtils
{
    @Value("${encryption.secret.key}")
    private String SECRET_KEY;


    private final String AES_ALGORITHM = "AES";
    private final String AES_CIPHER = "AES/ECB/PKCS5Padding"; // ECB mode for simplicity

    public Response encrypt(String input)
    {
        try
        {
            Key key = new SecretKeySpec(SECRET_KEY.getBytes(), AES_ALGORITHM);
            Cipher cipher = Cipher.getInstance(AES_CIPHER);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes = cipher.doFinal(input.getBytes());
            return new Response(Base64.getEncoder().encodeToString(encryptedBytes));
        }
        catch (Exception e)
        {
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, "error while encrypting key");
        }
    }

    public Response decrypt(String encryptedString)
    {
        try
        {
            Key key = new SecretKeySpec(SECRET_KEY.getBytes(), AES_ALGORITHM);
            Cipher cipher = Cipher.getInstance(AES_CIPHER);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedString);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new Response(new String(decryptedBytes));
        }
        catch (Exception e)
        {
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, "error while decrypting key");
        }
    }


}
