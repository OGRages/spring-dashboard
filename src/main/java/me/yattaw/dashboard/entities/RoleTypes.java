package me.yattaw.dashboard.entities;

public enum RoleTypes {

    USER("ROLE_USER"),
    SUPPORT("ROLE_SUPPORT"),
    ADMIN("ROLE_ADMIN"),
    DEVELOPER("ROLE_DEVELOPER");

    private String authority;

    RoleTypes(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }

    public static final String[] userAuthorities() {
        return new String[]{
                USER.getAuthority(),
                SUPPORT.getAuthority(),
                ADMIN.getAuthority(),
                DEVELOPER.getAuthority()
        };
    }

    public static final String[] adminAuthorities() {
        return new String[]{
                ADMIN.getAuthority(),
                DEVELOPER.getAuthority()
        };
    }

}
