package com.test.importconfig.service;

public class AllNewService {
    private final CommonService commonService;
    private final AnotherService anotherService;
    private final OneMoreService oneMoreService;

    public AllNewService(CommonService commonService, AnotherService anotherService, OneMoreService oneMoreService) {
        this.commonService = commonService;
        this.anotherService = anotherService;
        this.oneMoreService = oneMoreService;
    }
}
