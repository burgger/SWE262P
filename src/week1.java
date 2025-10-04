import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class week1 {

    static void main(String[] args) throws IOException {
        String filePath = "src/pride-and-prejudice.txt";
        BufferedReader bf = new BufferedReader(new FileReader(filePath));
        Map<String, Integer> termFreq = new HashMap<>();
        String line;

        while ((line = bf.readLine()) != null) {
            for (int i = 0; i < line.length(); i++) {
                if (Character.isLetter(line.charAt(i))) {
                    int k = i;
                    while (k < line.length() && Character.isLetter(line.charAt(k))) {
                        k++;
                    }
                    String word = line.substring(i, k).toLowerCase();
                    if (word.length() > 1) {
                        termFreq.put(word, termFreq.getOrDefault(word, 0) + 1);
                    }
                    i = k - 1;
                }
            }
        }
        bf.close();

        removeStopWords(termFreq);

        // Print sorted result
        List<Map.Entry<String, Integer>> list = new ArrayList<>(termFreq.entrySet());
        list.sort((a, b) -> b.getValue() - a.getValue());
        for (int i = 0; i < 25; i++) {
            System.out.println(list.get(i));
        }
    }

    static void removeStopWords(Map<String, Integer> termFreq) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("src/stop_words.txt"));
        String[] stopWords = bf.readLine().split(",");
        bf.close();

        for (String stopWord : stopWords) {
            termFreq.remove(stopWord.toLowerCase());
        }
    }
}

