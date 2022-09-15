package com.Jakko.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum Language {

    ru(1),
    kz(2);

    private int id;

    public static Language getById(int id) {
        for (Language language : values()) {
            if (language.id == (id)) return language;
        }
        return kz;
    }

}
