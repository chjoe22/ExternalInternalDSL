package org.xtext.dsl.validation;

import java.util.Objects;

public class PokemonType {
    // 1. Define the nominal "Domain" types
    public static final String DOMAIN_STAT = "Stat";
    public static final String DOMAIN_MONEY = "Money";
    public static final String DOMAIN_LEVEL = "Level";
    public static final String DOMAIN_VOID = "Void";

    // 2. Pre-defined instances for easy comparison
    public static final PokemonType STAT_INT = new PokemonType(DOMAIN_STAT, false);
    public static final PokemonType STAT_FLOAT = new PokemonType(DOMAIN_STAT, true);
    public static final PokemonType MONEY_INT = new PokemonType(DOMAIN_MONEY, false);
    public static final PokemonType LEVEL_INT = new PokemonType(DOMAIN_LEVEL, false);
    public static final PokemonType VOID = new PokemonType(DOMAIN_VOID, false);

    private final String domain;
    private final boolean isFloat;

    public PokemonType(String domain, boolean isFloat) {
        this.domain = domain;
        this.isFloat = isFloat;
    }

    public boolean isFloat() { return isFloat; }
    public String getDomain() { return domain; }

    // Crucial for the Type Checker: comparing if two types are exactly the same
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokemonType that = (PokemonType) o;
        return isFloat == that.isFloat && Objects.equals(domain, that.domain);
    }

    @Override
    public String toString() {
        return domain + (isFloat ? " (Decimal)" : " (Integer)");
    }
}