package br.com.api.bank.enums;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum EnumEntity {
    DEPOSIT("deposit"),
    WITHDRAWAL("withdraw"),
    TRANSFER("transfer"),
    CREATED("created");

    private String values;
    private static final Map<String, EnumEntity> ENUM;

    EnumEntity(final String param) {
        values = param;
    }

    public String getValues() {
        return values;
    }

    static {
        Map<String, EnumEntity> map = new ConcurrentHashMap<>();
        for (EnumEntity instance : EnumEntity.values()) {
            map.put(instance.getValues(),instance);
        }
        ENUM = Collections.unmodifiableMap(map);
    }

    public static EnumEntity get (String name) {
        return ENUM.get(name);
    }

    public static boolean isCreated(final String type) {
        return EnumEntity.CREATED.equals(get(type));
    }

    public static boolean isTransfer(final String type) {
        return EnumEntity.TRANSFER.equals(get(type));
    }

    public static boolean isDeposit(final String type) {
        return EnumEntity.DEPOSIT.equals(get(type));
    }

    public static boolean isWithdrawal(final String type) {
        return EnumEntity.WITHDRAWAL.equals(get(type));
    }
}
