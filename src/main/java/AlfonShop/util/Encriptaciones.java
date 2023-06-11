package AlfonShop.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Encriptaciones {

    private static final String CLAVE = "13Alfon11$h@p126"; // Clave para encriptar y desencriptar (16bytes)

    /**
     * Encripta el texto utilizando AES.
     *
     * @param texto El texto a encriptar.
     * @return El texto encriptado.
     */
    public static String encriptar(String texto) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            byte[] claveEnBytes = CLAVE.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec claveSecreta = new SecretKeySpec(claveEnBytes, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, claveSecreta);
            byte[] textoEnBytes = texto.getBytes(StandardCharsets.UTF_8);
            byte[] textoEncriptado = cipher.doFinal(textoEnBytes);
            return Base64.getEncoder().encodeToString(textoEncriptado);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Desencripta el texto encriptado utilizando AES.
     *
     * @param textoEncriptado El texto encriptado.
     * @return El texto desencriptado.
     */
    public static String desencriptar(String textoEncriptado) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            byte[] claveEnBytes = CLAVE.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec claveSecreta = new SecretKeySpec(claveEnBytes, "AES");
            cipher.init(Cipher.DECRYPT_MODE, claveSecreta);
            byte[] textoEnBytes = Base64.getDecoder().decode(textoEncriptado);
            byte[] textoDesencriptado = cipher.doFinal(textoEnBytes);
            return new String(textoDesencriptado, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
