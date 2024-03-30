import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class QuickSortDataGenerator {

    public static void main(String[] args) {
        generateDataSets(75, 100, 10000, "datasets.txt");
    }

    public static void generateDataSets(int numSets, int minSize, int maxSize, String fileName) {
        Random random = new Random();

        try {
            FileWriter writer = new FileWriter(fileName);
            for (int i = 0; i < numSets; i++) {
                int size = random.nextInt(maxSize - minSize + 1) + minSize;
                int[] data = new int[size];

                for (int j = 0; j < size; j++) {
                    data[j] = random.nextInt(1000);
                    if (j != (size - 1)){
                        writer.write(String.valueOf(data[j]) + ",");
                    }else writer.write(String.valueOf(data[j]) );
                }
                if (i != (numSets - 1)){
                    writer.write("\n");
                }

            }

            writer.close();
            System.out.println("Сгенерированные данные записаны в файл: " + fileName);
        } catch (IOException e) {
            System.err.println("Ошибка при записи данных в файл: " + e.getMessage());
        }
    }
}
