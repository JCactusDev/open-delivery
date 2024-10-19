package com.github.jcactus.clientadmin.model;

public enum OrganizationType {
    LegalPerson("Юридическое лицо"),
    SoleProprietorship("Индивидуальный предприниматель"),
    SelfEmployment("Самозанятый");

    private String view;

    OrganizationType(String view) {
        this.view = view;
    }

    public String getView() {
        return view;
    }
}
