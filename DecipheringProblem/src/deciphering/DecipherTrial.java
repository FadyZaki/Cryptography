package deciphering;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class DecipherTrial {

	public static void main(String[] args) {

		BufferedReader br = null;
		HashMap<Integer, Integer> digraphCount = new HashMap<Integer, Integer>();

		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader("16cipher.txt"));

			int maxDigraphCount = 0;
			int mostOccuringDigraph = 0;
			while ((sCurrentLine = br.readLine()) != null) {
				String[] words = sCurrentLine.split(":");
				if (words.length > 1) {
					for (int i = 0; i < words.length; i++) {
						int currentDigraph = Integer.valueOf(words[i]);
						if(digraphCount.containsKey(currentDigraph)) {
							int currentCount = digraphCount.get(currentDigraph).intValue();
							currentCount++;
							if(maxDigraphCount < currentCount){
								mostOccuringDigraph = currentDigraph;
								maxDigraphCount = currentCount;
							}
							digraphCount.put(currentDigraph, currentCount);
						}
						else {
							digraphCount.put(currentDigraph, 1);
						}
					}
				}

			}

			System.out.println("size : " + digraphCount.size());
			for (Entry<Integer, Integer> entry : digraphCount.entrySet()) {
			    System.out.println(entry.getKey() + " " + entry.getValue());
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

}
