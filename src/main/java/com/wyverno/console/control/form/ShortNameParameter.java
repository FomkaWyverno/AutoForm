package com.wyverno.console.control.form;

public enum ShortNameParameter {
    _int("int","число"),
    _string("String", "строка"),
    _typeBan("typeBan", "тип бана 0/1"),
    _days("days", "дни"),
    _reason("reason","причина");

    private final String nameParameter;
    private final String translateName;

    ShortNameParameter(String nameParameter, String translateName) {
        this.nameParameter = nameParameter;
        this.translateName = translateName;
    }

    public String getNameParameter() {
        return nameParameter;
    }

    public String getTranslateName() {
        return translateName;
    }

    public static String getTranslateNameTypeParameter(String stringParameter) {
        ShortNameParameter[] values = ShortNameParameter.values();

        for (ShortNameParameter value : values) {
            if (value.getNameParameter().equals(stringParameter)) return value.getTranslateName();
        }
        return stringParameter;
    }
}
