package com.cr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MyGrantedAuthority implements GrantedAuthority {
    String authority;
    @Override
    public String getAuthority() {
        return authority;
    }
}
