import java.io.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        process();
    }
    public static void process() throws IOException {
        String filename = "dataset.txt";
        String line;
        fibHeap fb = new fibHeap();
        int[] data = new int[10000];
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while ((line = reader.readLine()) != null) {
                String[] elements = line.split(",");
                for (int i = 0; i < elements.length; i++) {
                    data[i] = Integer.parseInt(elements[i]);
                }
                processInsert(data, fb);
                processFind(data, fb);
                processDelete(data, fb);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void printTime(int time, Writer writer) throws IOException {
        writer.write(time + " ");
        writer.close();
    }
    public static void printOperations(int oper, Writer writer) throws IOException {
        writer.write(oper + " ");
        writer.close();
    }
    public static void printMiddleTime(double middle, Writer writer) throws IOException {
        writer.write("\n" + middle);
        writer.close();
    }
    public static void printMiddleOper(double middle, Writer writer) throws IOException {
        writer.write("\n" + middle);
        writer.close();
    }
    public static void processInsert(int[] data, fibHeap fb) throws IOException {
        double middleTime = 0;
        double middleOper = 0;
        for (int i = 0; i < 10000; i++) {
            Count count = new Count(0);
            long t1 = System.nanoTime();
            fb.insert(data[i], count);
            long t2 = System.nanoTime();
            FileWriter writer1 = new FileWriter("resultTimeInsert.txt", true);
            FileWriter writer2 = new FileWriter("resultOperInsert.txt", true);
            middleTime += (double) (t2-t1);
            middleOper += count.val;
            printTime((int) (t2 - t1), writer1);
            printOperations(count.val, writer2);
        }
        Writer wmt = new FileWriter("resultTimeInsert.txt", true);
        Writer wmo = new FileWriter("resultOperInsert.txt", true);
        printMiddleTime(middleTime/10000, wmt);
        printMiddleOper(middleOper/10000, wmo);
    }
    public static void processFind(int[] data, fibHeap fb) throws IOException {
        int[] selectedElements = select(data, 100);
        double middleTime = 0;
        double middleOper = 0;
        for (int i = 0; i < selectedElements.length; i++) {
            Count count = new Count(0);
            long t1 = System.nanoTime();
            fb.find(selectedElements[i], count);
            long t2 = System.nanoTime();
            FileWriter writer1 = new FileWriter("resultTimeFind.txt", true);
            FileWriter writer2 = new FileWriter("resultOperFind.txt", true);
            middleTime += (double) (t2-t1);
            middleOper += count.val;
            printTime((int) (t2 - t1), writer1);
            printOperations(count.val, writer2);
        }
        Writer wmt = new FileWriter("resultTimeFind.txt", true);
        Writer wmo = new FileWriter("resultOperFind.txt", true);
        printMiddleTime(middleTime/100, wmt);
        printMiddleOper(middleOper/100, wmo);
    }
    public static void processDelete(int[] data, fibHeap fb) throws IOException {
        int[] selectedElements = select(data, 1000);
        double middleTime = 0;
        double middleOper = 0;
        for (int i = 0; i < selectedElements.length; i++) {
            Count count = new Count(0);
            long t1 = System.nanoTime();
            fb.delete(fb.find(selectedElements[i], count), count);
            long t2 = System.nanoTime();
            FileWriter writer1 = new FileWriter("resultTimeDelete.txt", true);
            FileWriter writer2 = new FileWriter("resultOperDelete.txt", true);
            middleTime += (double) (t2-t1);
            middleOper += count.val;
            printTime((int) (t2 - t1), writer1);
            printOperations(count.val, writer2);
        }
        Writer wmt = new FileWriter("resultTimeDelete.txt", true);
        Writer wmo = new FileWriter("resultOperDelete.txt", true);
        printMiddleTime(middleTime/1000, wmt);
        printMiddleOper(middleOper/1000, wmo);
    }

    public static int[] select(int[] data, int n){
        Random random = new Random();

        // Выбрать 100 случайных индексов
        int[] selectedIndices = new int[n];
        for (int i = 0; i < n; i++) {
            selectedIndices[i] = random.nextInt(10000);
        }

        // Выбрать элементы по выбранным индексам
        int[] selectedElements = new int[n];
        for (int i = 0; i < n; i++) {
            selectedElements[i] = data[selectedIndices[i]];
        }
        return selectedElements;
    }
}
