import java.io.*;

public class Main {

    public static void main(String args[]) throws IOException {
        process();
    }

    public static void quickSort(int[] sortArr, int low, int high, Count count) {
        if (sortArr.length == 0 || low >= high) return;
        int middle = low + (high - low) / 2;
        int m = sortArr[middle];
        int i = low, j = high;
        while (i <= j) {
            while (sortArr[i] < m){
                i++;
                count.count += 1;
            }
            while (sortArr[j] > m){
                j--;
                count.count += 1;
            }
            if (i <= j) {
                int swap = sortArr[i];
                sortArr[i] = sortArr[j];
                sortArr[j] = swap;
                count.count += 2;
                i++;
                j--;
            }
        }
        if (low < j) quickSort(sortArr, low, j, count);
        if (high > i) quickSort(sortArr, i, high, count);
    }

    public static void process() throws IOException {
        String filename = "datasets.txt";
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while ((line = reader.readLine()) != null) {
                String[] elements = line.split(",");
                int[] data = new int[elements.length];

                for (int i = 0; i < elements.length; i++) {
                    data[i] = Integer.parseInt(elements[i]);
                }
                Count count = new Count(0);
                long t1 = System.nanoTime();
                quickSort( data, 0, data.length - 1, count);
                long t2 = System.nanoTime();
                FileWriter writer1 = new FileWriter("resultTime.txt", true);
                FileWriter writer2 = new FileWriter("resultIter.txt", true);
                printTime((int) (t2 - t1), data.length, writer1);
                printIterations(count.count, data.length, writer2);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void printTime(int time, int lenght, Writer writer) throws IOException {
        writer.write(lenght + " ");
        writer.write(time + "\n");
        writer.close();
    }
    public static void printIterations(int iter, int lenght, Writer writer) throws IOException {
        writer.write(lenght + " ");
        writer.write(iter + "\n");
        writer.close();
    }



}