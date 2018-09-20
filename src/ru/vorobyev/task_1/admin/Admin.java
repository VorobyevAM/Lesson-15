package ru.vorobyev.task_1.admin;



import org.apache.log4j.Logger;

import java.util.Scanner;

public class Admin {
    private static final Logger LOG = Logger.getLogger(Admin.class.getName());

    private String password = "Pass_123";

    public boolean checkAdmin(){
        boolean checkAdmin;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Удаление подтверждается паролем. Просьба указать пароль: ");
        String userTry = scanner.nextLine();
        if (userTry.equals(password)){
            checkAdmin = true;
            LOG.info("SUCCESS");
        }else{
            checkAdmin = false;
            LOG.info("DENIED");
        }
        return checkAdmin;
    }

}
