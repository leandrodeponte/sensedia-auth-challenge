package br.com.sensedia.StepsAuthenticator.core;

import br.com.sensedia.StepsAuthenticator.model.AuthenticationKey;
import br.com.sensedia.StepsAuthenticator.utils.DateUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class TwoFactorAuthEngine {

    private final int SECRET_ENCODED_SIZE = 16;
    private final long TIME_TO_EXPIRE_IN_SECONDS = 10;
    private final String ALGORITMH_TO_GENERATE_THE_AUTH_KEY = "HmacSHA1";

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    public String generateRandomSecretKey(){

        StringBuilder sb = new StringBuilder(SECRET_ENCODED_SIZE);
        Random random = new SecureRandom();
        for (int i = 0; i < SECRET_ENCODED_SIZE; i++) {
            int val = random.nextInt(32);
            if (val < 26) {
                sb.append((char) ('A' + val));
            } else {
                sb.append((char) ('2' + (val - 26)));
            }
        }
        return sb.toString();
    }


    /***
     * The validate method generates the last TIME_TO_EXPIRE_IN_SECONDS keys
     * and compare with the Provided Key
     * @param data Data used to generate the Authenticaion Key
     * @param secret Secret Key provided
     * @param providedSecretAuthenticationKey Authentication Key provided to be validated
     * @return
     * @throws NoSuchAlgorithmException
     * @throws SignatureException
     * @throws InvalidKeyException
     */
    public boolean validateAuthenticationKey(String data,
                                             String secret,
                                             String providedSecretAuthenticationKey)
            throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {

        if(providedSecretAuthenticationKey == null ){
            return false;
        }

        LocalDateTime dateToValidate = DateUtils.getCurrentTimeNoMiliseconds();

        for(int i = 0; i < TIME_TO_EXPIRE_IN_SECONDS; i++){

            AuthenticationKey authenticationKey
                    = this.generateAuthenticationKey(data, secret, dateToValidate);

            if(providedSecretAuthenticationKey
                    .equalsIgnoreCase(authenticationKey.getProvidedAuthenticationKey())){
                return true;
            }
            dateToValidate = dateToValidate.minusSeconds(1);
        }

        return false;
    }

    /***
     * Generates the Authentication Key using the secret and the data
     * @param data
     * @param secret
     * @return
     * @throws NoSuchAlgorithmException
     * @throws SignatureException
     * @throws InvalidKeyException
     */
    public AuthenticationKey generateAuthenticationKey(String data, String secret )
            throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {

        LocalDateTime currentDate = DateUtils.getCurrentTimeNoMiliseconds();
        return generateAuthenticationKey( data,  secret, currentDate);
    }

    /***
     * Generates the Authentication Key using the secret, the data
     * and the time passed on the parameters
     * @param data
     * @param secret
     * @param currentDate
     * @return
     * @throws NoSuchAlgorithmException
     * @throws SignatureException
     * @throws InvalidKeyException
     */
    public AuthenticationKey generateAuthenticationKey(String data, String secret, LocalDateTime currentDate)
            throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {

        String formattedDate = formatter.format(currentDate);
        String authenticationKey = this.calculateRFC2104HMAC(data + formattedDate, secret);

        AuthenticationKey generatedAuthenticationKey
                = AuthenticationKey.build(authenticationKey, calculateTimeToExpireKey(currentDate));

        return generatedAuthenticationKey;
    }

    /***
     * Method based on the RFC4226
     * Section 5.4 Example of HOTP Computation for Digit = 6
     * @param data
     * @param key
     * @return
     * @throws SignatureException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    private String calculateRFC2104HMAC(String data, String key)
            throws SignatureException, NoSuchAlgorithmException, InvalidKeyException
    {
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), ALGORITMH_TO_GENERATE_THE_AUTH_KEY);
        Mac mac = Mac.getInstance(ALGORITMH_TO_GENERATE_THE_AUTH_KEY);
        mac.init(signingKey);

        byte[] result = mac.doFinal(data.getBytes());

        int offset = result[result.length - 1] & 0xF;
        int bin_code = (result[offset]  & 0x7f) << 24
                | (result[offset+1] & 0xff) << 16
                | (result[offset+2] & 0xff) <<  8
                | (result[offset+3] & 0xff) ;

        bin_code %= 1000000;

        return String.valueOf(bin_code);
    }

    private LocalDateTime calculateTimeToExpireKey(LocalDateTime currentTime){
        return currentTime.plusSeconds(TIME_TO_EXPIRE_IN_SECONDS);
    }

    public long getTIME_TO_EXPIRE_IN_SECONDS() {
        return TIME_TO_EXPIRE_IN_SECONDS;
    }
}
