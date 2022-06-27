package lk.ijse.hibernate.bo;

import lk.ijse.hibernate.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){

    }
    public static BOFactory getBOFactory(){
        if (boFactory==null){
            return new BOFactory();
        }
        return boFactory;
    }

    public enum BOTypes {
       LOG,PENDINGKEYMONEY,REGISTRATION,ROOM,STUDENT,USER
    }


    public SuperBO getBO(BOFactory.BOTypes types) {
        switch (types) {
            case LOG:
                return new LogBOImpl();
            case PENDINGKEYMONEY:
                return new PendingKeyMoneyBOImpl();
            case REGISTRATION:
                return new RegistrationBOImpl();
            case ROOM:
                return new RoomBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }
}
