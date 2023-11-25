package com.quyvx.main_server.shared.libs.infrastructure.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.quyvx.main_server.shared.libs.application.dto.UserDetail;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.converter.RsaKeyConverters;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.interfaces.RSAPublicKey;

@Slf4j
@Service
public class SharedTokenService {
    @Value("${jwt.keys.public_key_path}")
    private String jwtPublicKeyPath;

    private RSAPublicKey publicKey;

    public UserDetail verify(String token) {
        getKeys();
        Algorithm algorithm = Algorithm.RSA256(publicKey, null);
        DecodedJWT  decodedJWT= JWT.require(algorithm)
                .build().verify(token);
        return new UserDetail(decodedJWT);
    }

    private void getKeys() {
        try {
            if (publicKey == null) {
                String publicKeyContent = IOUtils.toString(new FileInputStream(jwtPublicKeyPath), StandardCharsets.UTF_8);
                publicKey = RsaKeyConverters.x509().convert(new ByteArrayInputStream(publicKeyContent.getBytes()));
            }
        } catch (IOException e) {
            log.error("Error while reading keys", e);
        }
    }
}
