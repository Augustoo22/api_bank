package com.api.bank.entity;

import com.api.bank.entity.enums.EnumUserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankUserModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "USER_AGE")
    private int userAge;

    @Column(name = "USER_EMAIL")
    private String userEmail;

    @Column(name = "USER_PASSWORD")
    private String userPassword;

    @Column(name = "USER_BALANCE")
    private BigDecimal userBalance;

    @ManyToOne
    @JoinColumn(name="user_type_id")
    private EnumUserType bankUserType;

    public BankUserModel(String username,
                         int userAge,
                         String userEmail,
                         String userPassword,
                         BigDecimal userBalance,
                         EnumUserType bankUserType) {
        this.username = username;
        this.userAge = userAge;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userBalance = userBalance;
        this.bankUserType = bankUserType;
    }

    public boolean isBalancerEqualOrGreatherThan(BigDecimal value) {
        return this.userBalance.doubleValue() >= value.doubleValue();
    }

    public void debit(BigDecimal value) {
        this.userBalance = this.userBalance.subtract(value);
    }

    public void credit(BigDecimal value) {
        this.userBalance = this.userBalance.add(value);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       if(this.bankUserType == EnumUserType.ADMIN){ return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));}
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}