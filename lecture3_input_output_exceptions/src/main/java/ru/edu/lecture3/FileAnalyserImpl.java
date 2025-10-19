package ru.edu.lecture3;

import java.io.*;
import java.util.*;

public class FileAnalyserImpl implements FileAnalyser{

    private final File file;
    private String value;
    private Map<Character,Integer> statistic;
    private final char[] alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPRQTUVWXYZ".toCharArray();

    public FileAnalyserImpl(String path) {
        file = new File(path);
        if (!file.exists()){
            throw new RuntimeException();
        }
    }

    @Override
    public String getFileName(){
        return file.getName();
    }

    @Override
    public int getRowsCount() {

        getAllSymbolsFromFile();

        return getLetterCount('\n');
    }

    @Override
    public int getLettersCount() {

        getAllSymbolsFromFile();
        getStatistic();

        int size = 0;
        for(Map.Entry<Character, Integer> item : statistic.entrySet()){
            if(item.getValue() != null){
                size  += item.getValue();
            }
        }

        return size;
    }

    public void getStatistic (){
        if (statistic == null) {
            statistic = new HashMap<>();
            for (char letter : alphabet) {
                statistic.put(letter, getLetterCount(letter));
            }
        }
    }

    public Integer getLetterCount(char letter){
        return (int) value.chars().filter(ch -> ch == letter).count();
    }

    @Override
    public Map<Character, Integer> getSymbolsStatistics() {
        getAllSymbolsFromFile();
        getStatistic();
        return statistic;
    }

    @Override
    public List<Character> getTopNPopularSymbols(int n) {
        getAllSymbolsFromFile();
        getStatistic();

        Map<Character,Integer> topNPopularSymbols = new HashMap<>();
        Map<Character,Integer> curStatistic = new HashMap<>(statistic);
        for(int i=0;i<n;i++){
            Optional<Map.Entry<Character, Integer>> maxEntry = curStatistic.entrySet().stream().max(Map.Entry.comparingByValue()) ;
            if (maxEntry.isPresent()){
                Character key = maxEntry.get().getKey();
                topNPopularSymbols.put(key, maxEntry.get().getValue());
                curStatistic.remove(maxEntry.get().getKey());
            }
        }
        return new ArrayList<>(topNPopularSymbols.keySet());
    }

    @Override
    public void saveSummary(String filePath) {
        getAllSymbolsFromFile();
        getStatistic();

        StringBuilder text = new StringBuilder("Summary\n");
        for(Map.Entry<Character, Integer> item : statistic.entrySet()){
            text.append("'").append(item.getKey()).append("' ").append(item.getValue()).append(",");
        }

        try(FileWriter writer = new FileWriter(filePath, false))
        {
            writer.write(text.substring(0, text.length() - 1));

            writer.flush();
        }
        catch(IOException ex){
            throw new RuntimeException(ex);
        }
    }

    private void getAllSymbolsFromFile() {
        if (value == null){
            StringBuilder result = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;

                while((line = br.readLine()) != null) {
                    result.append(line);
                    result.append('\n');
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            value = result.toString();
        }
    }
}
