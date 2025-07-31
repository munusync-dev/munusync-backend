package com.munusync.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private int companyId;
    private int addressId;
    private String name;
    private String phone;
    private String email;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    private TimeZone timeZone;
    private Date createdAt;
    private Date updatedAt;

    public void setTimezone(TimeZone timezone) {
        this.timeZone = timezone;
    }
}
