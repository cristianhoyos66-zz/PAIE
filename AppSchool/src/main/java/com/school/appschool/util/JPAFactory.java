package com.school.appschool.util;

import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAFactory {
    
    public static final String UP = "SCHOOLAPP_PU";
    public static final EntityManagerFactory FACTORY;
    
    static {
        FACTORY = Persistence.createEntityManagerFactory(UP);
    }

    public static EntityManagerFactory getFACTORY() {
        return FACTORY;
    }
    
}
