package com.quyvx.main_server.api.application.services.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.quyvx.main_server.shared.constants.ProjectConstants;
import com.quyvx.main_server.shared.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.converter.RsaKeyConverters;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class TokenService {
    @Value("${jwt.keys.private_key_path}")
    private String jwtPrivateKeyPath;
    @Value("${jwt.keys.public_key_path}")
    private String jwtPublicKeyPath;

    @Value("${jwt.access_token_expiration}")
    private Long accessTokenExpirationTime;
    @Value("${jwt.refresh_token_expiration}")
    private Long refreshTokenExpirationTime;

    private RSAPrivateKey privateKey;
    private RSAPublicKey publicKey;


    public String signAccessToken(Long identityId, List<String> roles) {
        getKeys();
        LocalDateTime expireAt = TimeUtils.now().plusMinutes(accessTokenExpirationTime);

        JWTCreator.Builder builder = JWT.create()
                .withSubject(ProjectConstants.SUBJECTNAME)
                .withIssuer(ProjectConstants.SUBJECTNAME)
                .withClaim("id", identityId)
                .withClaim("roles", roles)
                .withExpiresAt(Date.from(TimeUtils.getInstant(expireAt)));
        return builder.sign(Algorithm.RSA256(publicKey, privateKey));
    }

    public String signRefreshToken() {
        String nowString = String.valueOf(TimeUtils.getMilliSecond(TimeUtils.now()));
        String randomString = DigestUtils.sha256Hex(UUID.randomUUID().toString());
        return nowString+ randomString;
    }

    private void getKeys() {
        try {
            if (privateKey == null) {
                String privateKeyContent = IOUtils.toString(new FileInputStream(jwtPrivateKeyPath), StandardCharsets.UTF_8);
                privateKey = RsaKeyConverters.pkcs8().convert(new ByteArrayInputStream(privateKeyContent.getBytes()));
            }
            if (publicKey == null) {
                String publicKeyContent = IOUtils.toString(new FileInputStream(jwtPublicKeyPath), StandardCharsets.UTF_8);
                publicKey = RsaKeyConverters.x509().convert(new ByteArrayInputStream(publicKeyContent.getBytes()));
            }
        } catch (IOException e) {
            log.error("Error while reading keys", e);
        }
    }

    public LocalDateTime getRefreshTokenExpirationLocalDateTime() {
        return TimeUtils.now().plusSeconds(refreshTokenExpirationTime);
    }
}
