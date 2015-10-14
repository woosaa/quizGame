package application.model.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;

class MD5
{
    /**
     * Get MD5 String
     * @return MD5 hexString
     */
    public String make() {
        try {
            Random rand = new Random();
            int random = rand.nextInt( 100 );
            Date date = new Date();
            String newHash = (date.toString() + random);
            
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(newHash.getBytes());
     
            byte byteData[] = md.digest();
     
            StringBuilder hexString;
            hexString = new StringBuilder();
            for (byte aByteData : byteData) {
                String hex = Integer.toHexString(0xff & aByteData);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException ex) {
            return "";
        }
    } 
}
