package com.github.jcactus.serviceorganization.model;

public enum OrganizationType {
    LegalPerson("Legal Person"),
    SoleProprietorship("Sole Proprietorship"),
    SelfEmployment("Self Employment");

    private String view;

    OrganizationType(String view) {
        this.view = view;
    }

    public String getView() {
        return view;
    }
}
