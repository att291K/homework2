import org.junit.Assert;
import org.junit.Test;
import ru.edu.lecture3.FileAnalyser;
import ru.edu.lecture3.FileAnalyserImpl;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FileAnalyserTests {

    private FileAnalyser fileAnalyser = null;

    private void initialise(){
        String fileName = "fileForAnalyse.txt";
        String pathFile = Objects.requireNonNull(getClass().getResource(fileName)).getFile();

        fileAnalyser = new FileAnalyserImpl(pathFile);
    }

    @Test (expected = Exception.class)
    public void fileAnalyserTest_Constructor(){
        String fileName = "fileForAnalyse_Error.txt";
        String pathFile = Objects.requireNonNull(getClass().getResource(fileName)).getFile();

        System.out.println("Constructor");
        fileAnalyser = new FileAnalyserImpl(pathFile);
    }

    @Test
    public void fileAnalyserTest_getFileName() {
        initialise();
        String testFileName = "fileForAnalyse.txt";

        Assert.assertEquals(fileAnalyser.getFileName(),testFileName);

        Assert.assertNotEquals(fileAnalyser.getFileName(), "rrnjnknfjd.txt");
        System.out.println("getFileName");
    }

    @Test (expected = IllegalAccessException.class)
    public void fileAnalyserTest_getRowsCount() throws IllegalAccessException {
        initialise();

        Assert.assertEquals(fileAnalyser.getRowsCount(), 2);
        System.out.println("getRowsCount");
        throw new IllegalAccessException("test");
    }

    @Test (expected = ArithmeticException.class)
    public void fileAnalyserTest_getLettersCount(){
        initialise();

        Assert.assertEquals(fileAnalyser.getLettersCount(), 16);
        System.out.println("getLettersCount");
        throw new ArithmeticException("test");
    }

    @Test (expected = RuntimeException.class)
    public void fileAnalyserTest_getSymbolsStatistics(){
        initialise();

        Object mapSymbolsStatistics = fileAnalyser.getSymbolsStatistics();
        Map<Character, Integer> testMap = new HashMap<>();
        final char[] alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPRQTUVWXYZ".toCharArray();
        for (char letter : alphabet) {
            int nn = 0;
            switch (letter){
                case 'H':
                    nn =2;
                    break;
                case 'W':
                    nn =1;
                    break;
                case 'd':
                    nn =1;
                    break;
                case 'e':
                    nn =3;
                    break;
                case 'h':
                    nn =1;
                    break;
                case 'l':
                    nn =3;
                    break;
                case 'o':
                    nn =2;
                    break;
                case 'r':
                    nn =1;
                    break;
                case 'y':
                    nn =2;
                    break;

            }
            testMap.put(letter, nn);
        }

        Assert.assertEquals( mapSymbolsStatistics, testMap);

        System.out.println("getSymbolsStatistics");
        throw new RuntimeException();
    }

    @Test (expected = Exception.class)
    public void getTopNPopularSymbols() throws Exception {
        initialise();
        List<Character> NTop = fileAnalyser.getTopNPopularSymbols(5);
        System.out.println("getTopNPopularSymbols");
        if (NTop.size() == 5) throw new Exception("Test");

    }

    @Test (expected = Exception.class)
    public void fileAnalyserTest_saveSummary(){
        initialise();

        if (false){
        String fileName = "Summary.txt";
        File folder = FileSystemView.getFileSystemView().getDefaultDirectory();
        //пишем в домашнюю папку
        fileAnalyser.saveSummary(folder.getPath()+"/"+fileName);
        }
        System.out.println("saveSummary");

        fileAnalyser.saveSummary("");

    }

}