import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class fibHeapDataGenerate {

    public static void main(String[] args) {
        generateDataSet(10000, "dataset.txt");
    }

    public static void generateDataSet(int size, String fileName) {
        Random random = new Random();
        try {
            FileWriter writer = new FileWriter(fileName);
            int[] data = new int[size];
            for (int j = 0; j < size; j++) {
                data[j] = random.nextInt(1000);
                if (j != (size - 1)) {
                    writer.write(String.valueOf(data[j]) + ",");
                } else writer.write(String.valueOf(data[j]));
            }
            writer.write("\n");
            writer.close();
            System.out.println("Сгенерированные данные записаны в файл: " + fileName);
        } catch (IOException e) {
            System.err.println("Ошибка при записи данных в файл: " + e.getMessage());
        }
    }
}
