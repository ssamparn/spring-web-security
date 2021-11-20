package com.spring.jwtissuer.service;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.util.X509CertUtils;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.spring.jwtissuer.model.RequestData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;

@Slf4j
@Service
public class TokenService {

    @Value("${token.issuer.url}")
    private String tokenIssuerUrl;

//    @Value("${token.issuer.pkcs12}")
    private String tokenIssuerPublicKey = "/Users/sashankasamantray/my_projects/backend/spring-security/spring-security-projects/web-security/jwt-issuer/src/main/resources/keystore/jwt-issuer-pkcs.p12";

//    @Value("${token.consumer.x509}")
    private String clientPublicCertificate = "/Users/sashankasamantray/my_projects/backend/spring-security/spring-security-projects/web-security/jwt-issuer/src/main/resources/keystore/jwt-consumer-x509.txt";
;

    public String getJsonWebToken(RequestData request) {
        String jwtToken = "unknown";
        try {
            log.info("User : {}, Account : {}, Subject : {}", request.getUser() , request.getAccount() , request.getSubject());
            RSAKey serverJwk = getJsonWebKey(tokenIssuerPublicKey);
            JWSHeader jwtHeader = new JWSHeader.Builder(JWSAlgorithm.RS256).jwk(serverJwk).build();

            Calendar calendarNow = Calendar.getInstance();
            Date tokenIssueTime = calendarNow.getTime();
            calendarNow.add(Calendar.HOUR, 2400);
            Date tokenExpiryTime = calendarNow.getTime();
            String tokenId = String.valueOf(tokenIssueTime.getTime());

            JWTClaimsSet jwtClaims = new JWTClaimsSet.Builder()
                    .issuer(tokenIssuerUrl)
                    .subject(request.getSubject())
                    .issueTime(tokenIssueTime)
                    .expirationTime(tokenExpiryTime)
                    .claim("user", request.getUser())
                    .claim("account", request.getAccount())
                    .jwtID(tokenId)
                    .build();
            log.info("JWT claims : {} ", jwtClaims.toString());

            SignedJWT jsonWebSignature = new SignedJWT(jwtHeader, jwtClaims);
            RSASSASigner signer = new RSASSASigner(serverJwk);
            jsonWebSignature.sign(signer);

            JWEHeader jsonWebHeader = new JWEHeader
                    .Builder(JWEAlgorithm.RSA_OAEP_256, EncryptionMethod.A256GCM)
                    .contentType("JWT")
                    .build();

            JWEObject jsonWebObject = new JWEObject(jsonWebHeader, new Payload(jsonWebSignature));

            // Encrypt with the Jwt Consumer's public key
            RSAKey clientPublicKey = getPublicKey(clientPublicCertificate);

            jsonWebObject.encrypt(new RSAEncrypter(clientPublicKey));

            jwtToken = jsonWebObject.serialize();
            log.info("Token : {}", jwtToken);
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return jwtToken;
    }

    private RSAKey getPublicKey(String clientPublicCertificate) {
        RSAKey clientPublicKey = null;
        try {
            String fileContent = new String(Files.readAllBytes(Paths.get(clientPublicCertificate)));
            X509Certificate x509Certificate = X509CertUtils.parse(fileContent);
            clientPublicKey = RSAKey.parse(x509Certificate);
            log.info("Public key was fetched from the certificate");
        } catch (IOException | JOSEException e) {
            log.error(e.toString());
        }
        return clientPublicKey;
    }

    private RSAKey getJsonWebKey(String tokenIssuerPublicKey) {
        RSAKey jsonWebKey = null;
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            FileInputStream tokenIssuerPublicKeyFile = new FileInputStream(tokenIssuerPublicKey);
            keyStore.load(tokenIssuerPublicKeyFile, "password".toCharArray());
            Enumeration aliases = keyStore.aliases();
            while (aliases.hasMoreElements()) {
                String alias = (String) aliases.nextElement();
                RSAPrivateKey privateKey = (RSAPrivateKey) keyStore.getKey(alias, "password".toCharArray());
                X509Certificate publicCertificate = (X509Certificate) keyStore.getCertificate(alias);
                RSAKey publicKey = RSAKey.parse(publicCertificate);
                jsonWebKey = new RSAKey.Builder(publicKey).privateKey(privateKey).build();
                break;
            }
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException | UnrecoverableKeyException | JOSEException e) {
            log.error(e.toString());
        }
        return jsonWebKey;
    }
}
