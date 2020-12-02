package io.github.cchenxi.w7.db.fx01.core;

import lombok.Getter;

public enum DataSourceType {
    NORMAL("normal"),
    READONLY("readOnly");

    DataSourceType(String value) {
        this.value = value;
    }

    @Getter
    private String value;
}
