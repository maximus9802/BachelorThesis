package com.quyvx.main_server.infrastructure.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "authentication_history")
public class AuthenticationHistoryEntity extends BaseEntity{
    private Long authenLoginId;
    private Long authenLogoutId;
    private Long statusParkingId; // one to one entity status parking
    private Long duration;
    private String licensePlateCode;
}
