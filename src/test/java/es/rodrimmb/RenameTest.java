package es.rodrimmb;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RenameTest {

    Rename rename = new Rename();

    @Rule
    public TemporaryFolder folder= new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        for(int i = 0; i<=5;i++){
            folder.newFile("test_"+i+".txt");
        }
    }

    @Test
    public void testgetListOfFiles() throws Exception {
        List<Path> listOfFiles = rename.getListOfFiles(folder.getRoot().getPath());

        assertThat(listOfFiles.size(), is(6));
        assertThat(listOfFiles.get(0).toString(), containsString(folder.getRoot().getPath()+"/test_"));
    }

    @Test
    public void testRenameFiles() throws Exception {
        List<Path> listOfFiles = rename.getListOfFiles(folder.getRoot().getPath());

        List<Path> filesChanged = rename.renameFiles(listOfFiles, "test_", " 7");

        assertThat(filesChanged.size(), is(6));
        assertThat(filesChanged.get(0).toString(), containsString(folder.getRoot().getPath()+"/test_ 7"));
    }

    @Test
    public void testExistFolder() throws Exception {
        File testFolder = folder.newFolder("testFolder");

        assertThat(rename.existFolder(testFolder.getPath()), is(true));
    }

    @Test
    public void testInvalidFolder() throws Exception {
        File testFolder = folder.newFolder("testFolder");
        String path = testFolder.getPath()+"_fail";

        assertThat(rename.existFolder(path), is(false));
    }
}