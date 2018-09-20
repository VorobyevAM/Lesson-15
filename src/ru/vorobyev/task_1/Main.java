package ru.vorobyev.task_1;



import org.apache.log4j.Logger;
import ru.vorobyev.task_1.command.Command;

import java.util.Scanner;



public class Main {

    public static final Logger LOG = Logger.getLogger(Main.class.getName());

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        LOG.info("START PROGRAM");

        Command commandComand = new Command();

        //C:\Users\Анатолий\Desktop

        printInfo();

        while (scanner.hasNext()){
            String command = scanner.next();
            LOG.info("SELECT COMMAND: " + command);
            switch (command){

                case "CONTENT": commandComand.showContent();
                break;

                case "CREATE": commandComand.create();
                    break;

                case "RENAME": commandComand.rename();
                    break;

                case "COPY": commandComand.copy();
                    break;

                case "DELETE": commandComand.delete();
                    break;

                case "EXIT":
                    LOG.info("FINISH PROGRAM");
                    return;

                case "HELP":
                    printInfo();

                default:
                    System.out.println("UNKNOWN COMMAND");
                    printInfo();
            }
            scanner.nextLine();
        }
    }

    private static void printInfo(){
        //Команды для управления
        System.out.println("CONTENT\t- содержание директории;");
        System.out.println("CREATE\t- создание нового файла;");
        System.out.println("RENAME\t- переименовывание файла;");
        System.out.println("COPY\t- копирование файла;");
        System.out.println("DELETE\t- удаление файла (требуется админский пароль);");
        System.out.println("EXIT\t- выход;");
        System.out.println("HELP\t- вызов команд.");

    }



}
