package com.websystique.nikita.model;

/**
 * Created by nikita on 30.08.16.
 */
public enum RoleType {

    ADMIN("ADMIN"),
    USER("USER");

    private String roleType;

    RoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    @Override
    public String toString() {
        return this.roleType;
    }
}
