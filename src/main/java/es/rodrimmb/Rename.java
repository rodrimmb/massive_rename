package es.rodrimmb;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Rename {

    public List<Path> getListOfFiles(String directoryPath) throws IOException {
        List<Path> files = new ArrayList<>();
        DirectoryStream<Path> paths = Files.newDirectoryStream(Paths.get(directoryPath));
        for(Path filePath : paths){
            if(Files.isRegularFile(filePath)){
                files.add(filePath);
            }
        }
        return files;
    }


    public List<Path> renameFiles(List<Path> oldFiles, String pattern, String addString) throws IOException {
        List<Path> files = new ArrayList<>();
        for(Path path : oldFiles){
            if(Files.isRegularFile(path) && hasValidPattern(pattern, path.getFileName().toString())){
                Path parent = path.getParent();
                String newName = givenNewName(pattern, addString, path.getFileName().toString());
                Path moved = Files.move(path, parent.resolve(newName));
                files.add(moved);
            }
        }
        return files;
    }

    private String givenNewName(String pattern, String addString, String oldFileName) {
        return oldFileName.replaceFirst(pattern, pattern+addString);
    }

    private boolean hasValidPattern(String pattern, String fileName) {
        if(fileName.startsWith(pattern)){
            return true;
        }
        return false;
    }
}
