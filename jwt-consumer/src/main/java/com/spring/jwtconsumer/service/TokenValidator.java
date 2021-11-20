package com.spring.jwtconsumer.service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEDecrypter;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.util.X509CertUtils;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.text.ParseException;
import java.util.Date;
import java.util.Enumeration;

@Slf4j
@Service
public class TokenValidator {

    @Value("${token.consumer.pkcs12}")
    private String tokenConsumerPublicKey;

    @Value("${token.issuer.x509}")
    private String tokenIssuerPublicCertificate;

    private String user = "unknown";
    private String account = "unknown";
    private String token = "unknown";


    public boolean isValid(HttpServletRequest request) {
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String key = headers.nextElement();

            if (key.trim().equalsIgnoreCase("Authorization")) {
                String authorzationHeader = request.getHeader(key);

                if (!authorzationHeader.isBlank()) {
                    String[] tokenData = authorzationHeader.split(" ");
                    if (tokenData.length == 2 && tokenData[0].trim().equalsIgnoreCase("Bearer")) {
                        token = tokenData[1];
                        log.info("Received token: {}", token);
                        break;
                    }
                }
            }
        }

        try {
            JWT jsonWebToken = JWTParser.parse(token);
            if (jsonWebToken instanceof EncryptedJWT) {
                EncryptedJWT jsonWebEncryption = (EncryptedJWT) jsonWebToken;
                RSAKey tokenConsumerJsonWebKey = getJsonWebKey(tokenConsumerPublicKey);
                JWEDecrypter jweDecrypter = new RSADecrypter(tokenConsumerJsonWebKey);
                jsonWebEncryption.decrypt(jweDecrypter);

                SignedJWT jsonWebSignature = jsonWebEncryption.getPayload().toSignedJWT();

                RSAKey tokenIssuerJsonWebKey = getTokenIssuerPublicKey(tokenIssuerPublicCertificate);
                RSASSAVerifier signVerifier = new RSASSAVerifier(tokenIssuerJsonWebKey);

                if (jsonWebSignature.verify(signVerifier)) {
                    JWTClaimsSet claims = jsonWebSignature.getJWTClaimsSet();
                    Date expiryTime = claims.getExpirationTime();
                    log.info("Expiry time : {} ", expiryTime.toString());
                    if(expiryTime.after(new Date())) {
                        user = claims.getStringClaim("user");
                        account = claims.getStringClaim("account");
                        log.info("Token validated for user : {} and account : {}", user, account);
                        return true;
                    }
                }
            }
        } catch (ParseException | JOSEException e) {
            e.printStackTrace();
        }
        return false;
    }

    private RSAKey getTokenIssuerPublicKey(String tokenIssuerPublicCertificate) {
        RSAKey publicKey = null;
        try {
            String fileContent = new String(Files.readAllBytes(Paths.get(tokenIssuerPublicCertificate)));
            X509Certificate certificate = X509CertUtils.parse(fileContent);
            publicKey = RSAKey.parse(certificate);
            log.info("Public key was fetched from the certificate");
        } catch (JOSEException | IOException e) {
            log.error(e.toString());
        }
        return publicKey;
    }

    private RSAKey getJsonWebKey(String tokenConsumerPublicKey) {
        RSAKey jwk = null;
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            FileInputStream pkcs = new FileInputStream(tokenConsumerPublicKey);
            keyStore.load(pkcs, "".toCharArray());
            Enumeration aliases = keyStore.aliases();
            while(aliases.hasMoreElements()){
                String alias = (String)aliases.nextElement();
                RSAPrivateKey privateKey = (RSAPrivateKey) keyStore.getKey(alias,"".toCharArray());
                X509Certificate certificate = (X509Certificate) keyStore.getCertificate(alias);
                RSAKey publicKey = RSAKey.parse(certificate);
                jwk = new RSAKey.Builder(publicKey).privateKey(privateKey).build();
                break;
            }
        } catch (IOException | KeyStoreException | JOSEException | NoSuchAlgorithmException | UnrecoverableKeyException | CertificateException ex) {
            log.error(ex.toString());
        }
        return jwk;
    }

    public String getUser() {
        return user;
    }

    public String getAccount() {
        return account;
    }
}
