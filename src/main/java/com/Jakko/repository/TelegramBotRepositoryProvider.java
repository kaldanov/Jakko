package com.Jakko.repository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TelegramBotRepositoryProvider {

    @Getter
    @Setter
    private static PropertiesRepo propertiesRepo;
    @Getter
    @Setter
    private static UsersRepo usersRepo;
    @Getter
    @Setter
    private static ButtonRepo buttonRepo;
    @Getter
    @Setter
    private static MessageRepo messageRepo;
    @Getter
    @Setter
    private static KeyboardRepo keyboardRepo;
    @Getter
    @Setter
    private static AdminRepo adminRepo;
    @Getter
    @Setter
    private static ReportRepo reportRepo;
    @Getter
    @Setter
    private static RepairRepo repairRepo;
    @Getter
    @Setter
    private static MenuRepo menuRepo;
    @Getter
    @Setter
    private static RejectRepo rejectRepo;
    @Getter
    @Setter
    private static RejectReasonRepo rejectReasonRepo;
    @Getter
    @Setter
    private static TempUsersRepo tempUsersRepo;
    @Getter
    @Setter
    private static MasterRepo masterRepo;
    @Getter
    @Setter
    private static ZakupshikRepo zakupshikRepo;
    @Getter
    @Setter
    private static RequestZakupRepo requestZakupRepo;


    //---------------------------------------------------------------
    @Autowired
    public TelegramBotRepositoryProvider(
            PropertiesRepo propertiesRepo,
            UsersRepo usersRepo, ButtonRepo buttonRepo, MessageRepo messageRepo,
            KeyboardRepo keyboardRepo, AdminRepo adminRepo, ReportRepo reportRepo,
            RepairRepo repairRepo, MenuRepo menuRepo, RejectRepo rejectRepo, RejectReasonRepo rejectReasonRepo,
            TempUsersRepo tempUsersRepo, MasterRepo masterRepo, RequestZakupRepo requestZakupRepo,ZakupshikRepo zakupshikRepo) {
        setPropertiesRepo(propertiesRepo);
        setUsersRepo(usersRepo);
        setButtonRepo(buttonRepo);
        setMessageRepo(messageRepo);
        setKeyboardRepo(keyboardRepo);
        setAdminRepo(adminRepo);
        setReportRepo(reportRepo);
        setRepairRepo(repairRepo);
        setMenuRepo(menuRepo);
        setReportRepo(reportRepo);
        setRejectRepo(rejectRepo);
        setRejectReasonRepo(rejectReasonRepo);
        setTempUsersRepo(tempUsersRepo);
        setMasterRepo(masterRepo);
        setZakupshikRepo(zakupshikRepo);
        setRequestZakupRepo(requestZakupRepo);
    }


}
