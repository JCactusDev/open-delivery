package com.github.jcactus.serviceorganization.model;

public enum OrganizationType {
    LegalPerson("Юридическое лицо"),
    SoleProprietorship("Индивидуальный предприниматель"),
    SelfEmployment("Самозанятый");

    private final String view;

    OrganizationType(String view) {
        this.view = view;
    }

    public String getView() {
        return view;
    }
}
