package com.Jakko.command;

import com.Jakko.command.impl.*;
import com.Jakko.exceptions.NotRealizedMethodException;

public class CommandFactory {

    public static Command getCommand(long id) {
        Command result = getCommandWithoutReflection((int) id);
        if (result == null) throw new NotRealizedMethodException("Not realized for type: " + id);
        return result;
    }

    private static Command getCommandWithoutReflection(int id) {
        switch (id) {
            case 1:
                return new id001_ShowInfo();
            case 2:
                return new id002_Registration();
            case 3:
                return new id003_FileOrPhoto();
            case 4:
                return new id004_Injection();
            case 5:
                return new id005_Extruder();
            case 6:
                return new id006_EditAdmin();
            case 7:
                return new id007_Mailing();
            case 8:
                return new id008_ShowAdminInfo();
            case 9:
                return new id009_EditingMenuButton();
            case 10:
                return new id010_EditingMenuMessage();
            case 11:
                return new id011_RequestForRepair();
            case 12:
                return new id0012_EditMenuPerson();
            case 13:
                return new id013_ShowOperatorInfo();
            case 14:
                return new id014_Operator();
            case 15:
                return new id015_RequestForPurchase();
            case 16:
                return new id016_TempRegistration();
            case 17:
                return new id017_ShowZakupInfo();
            case 18:
                return new id018_Zakup();
            case 19:
                return new id0019_EditZakupshik();

        }
        return null;
    }
}
