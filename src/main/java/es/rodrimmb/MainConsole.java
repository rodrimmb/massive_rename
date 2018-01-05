package es.rodrimmb;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class MainConsole {

    private static Rename rename = new Rename();

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        while (option != 2){
            System.out.println(menu());
            option = scanner.nextInt();
            System.out.println("\n");
            if(option == 2){
                System.out.println("Adios");
            }
            if(option == 1){
                renameFiles();
            }
        }
    }

    private static void renameFiles() {
        Scanner scanner = new Scanner(System.in);
        String path;
        do {
            System.out.println("Introduce la ruta a la carpeta:");
            path = scanner.nextLine();
        }while (!rename.existFolder(path));
        System.out.println("\nLa ruta existe y es valida!!!");
        try {
            List<Path> listOfFiles = rename.getListOfFiles(path);
            System.out.println("Los fichero contenidos en "+path+" son:");
            for (Path file : listOfFiles){
                System.out.println(file.getFileName());
            }
            System.out.println("\nIntroduce patron:");
            String pattern = scanner.nextLine();

            System.out.println("Introduce caracteres a incluir despues del patron:");
            String textToAdd = scanner.nextLine();

            List<Path> paths = rename.renameFiles(listOfFiles, pattern, textToAdd);
            System.out.println("\nSe han cambiado el nombre de "+paths.size()+" ficheros");
        } catch (IOException e) {
            System.out.println("Ha habido un fallo al leer los ficheros");
        }
    }

    private static String menu(){
        return  "\n1- Cambiar nombres de fichero en una carpeta\n" +
                "2- Salir\n" +
                "Introduce tu opcion:";
    }
}
