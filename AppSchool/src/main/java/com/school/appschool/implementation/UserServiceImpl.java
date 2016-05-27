package com.school.appschool.implementation;

import com.school.appschool.contract.UserServices;
import com.school.appschool.entities.UsersTbl;
import com.school.appschool.jpacontroller.UsersTblJpaController;
import com.school.appschool.util.JPAFactory;

public class UserServiceImpl implements UserServices {

    @Override
    public UsersTbl getUsuario(String email) {
        UsersTblJpaController jpaController = new UsersTblJpaController(JPAFactory.getFACTORY());
        return jpaController.findUsersTbl(email);
    }

    @Override
    public UsersTbl getUsuario(String email, String pass) {
        UsersTblJpaController jpaController = new UsersTblJpaController(JPAFactory.getFACTORY());
        return jpaController.findUsersByEmailAndPassTbl(email, pass);
    }
}
