package ru.vorobyev.task_1.command;


import org.apache.log4j.Logger;
import ru.vorobyev.task_1.admin.Admin;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Command {

    public static final Logger LOG = Logger.getLogger(Command.class.getName());

    private Admin admin = new Admin();

    private static Scanner scanner = new Scanner(System.in);

    public void create() {
        String directory = connect();
        System.out.println("Укажите имя файла: ");
        String fileName =scanner.nextLine() + ".txt";
        LOG.info("The user is trying to create a file woth name: " + fileName);
        File file = new File(directory + fileName);
        try {
            if (file.createNewFile()){
                LOG.info("File" + fileName + " was created successfully");
                System.out.println("Создан файл: " + fileName);
            }else{
                System.out.println("Файл не создан:");
                if (file.exists()){
                    System.out.println("Файл с таким именем уже существует!");
                    LOG.info("Invalid file name");
                }
            }
        } catch (IOException e) {
            LOG.error("Ошибка IOException при создании файла", e);
            System.out.println("Ошибка IOException при создании файла");
        }

    }

    public void delete(){
        String directory = connect();
        System.out.println("Укажите имя файла для удаления: ");
        String fileName =scanner.nextLine() + ".txt";
        LOG.info("User tries to delete the file: "+ fileName);
        if (admin.checkAdmin()==true){
            File file = new File(directory + fileName);
            file.delete();
            System.out.println("Удален файл: " + fileName);
        }else{
            System.out.println("Отказ в удалении. Неверный пароль.");
        }
    }

    public void rename(){
        String directory = connect();
        System.out.println("Укажите файл который требуется переименовать: ");
        String fileName =scanner.nextLine() + ".txt";
        LOG.info("Set file " + fileName+" to rename");
        File file = new File(directory + fileName);
        System.out.println("Укажите новое имя файла: ");
        String fileNameNew = scanner.nextLine() + ".txt";
        LOG.info("Given a new name " + fileNameNew);
        if (file.renameTo(new File(directory + fileNameNew))){
            System.out.println("Файл " + fileName + " успешно переименован в " + fileNameNew);
            LOG.info("SUCCESS");
        }else{
            System.out.println("Отказано в переименовании по неизвестным причинам.");
            LOG.info("DENIED");
        }
    }

    public void copy(){
        String directory = connect();
        System.out.println("Что скопировать?");
        String fileName =scanner.nextLine();
        LOG.info("Set file" + fileName + " to copy");
        Path file = Paths.get(directory + fileName + ".txt");
        int i = 1;
        Path file1 = Paths.get(directory + fileName + "_copy_" + i +".txt");
        while (Files.exists(file1)){
            i++;
            file1 = Paths.get(directory + fileName + "_copy_" + i +".txt");
        }
        try {
            Files.copy(file, file1);
            System.out.println("Файл скопирован: " + fileName + "_copy_" + i +".txt");
            LOG.info("SUCCESS, add file " + fileName + "_copy_" + i +".txt" );
            //if ()
        } catch (IOException e) {
            LOG.error("Ошибка IOException при копировании файла", e);
            System.out.println("Ошибка IOException при копировании файла");
        }
    }

    public void showContent(){
        File file = new File(connect());//SHOWDIRECTORY C:\  C:\Users  C:\Users\Гость
        //File file = new File("C:\\Users\\Анатолий\\IdeaProjects\\lesson_15");
        LOG.info("Requesting folder contents");
        if (file.isDirectory()){
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                System.out.println(files[i]);
            }
        }else{
            LOG.info("Incorrect address folder input");
            System.out.println("Вы указали не директорию");
        }
    }

    public String connect(){
        System.out.println("Укажите путь директории");
        String direcotry = scanner.nextLine()+"\\";
        LOG.info("USER SELECT A FOLDER: " + direcotry);
        return direcotry;
    }
}
