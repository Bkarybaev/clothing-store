package java16.clothingstore.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Category implements GrantedAuthority {
    LAPTOP,
    PHONE,
    HEADPHONE,
    SMART_WATCH
    ;

    @Override
    public String getAuthority() {
        return name();
    }
}
