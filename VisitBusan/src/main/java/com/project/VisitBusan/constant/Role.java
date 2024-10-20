package com.project.VisitBusan.constant;

import lombok.Getter;

@Getter
public enum Role {
    USER("USER"), ADMIN("ADMIN"), ROOT("ROOT"); // 0,1,2

    //roleName 변수 정의 추가
    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
