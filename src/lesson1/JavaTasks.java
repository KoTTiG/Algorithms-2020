package lesson1;

import kotlin.NotImplementedError;

import java.io.*;
import java.util.*;
import java.util.Map.*;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     * <p>
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
     * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
     * <p>
     * Пример:
     * <p>
     * 01:15:19 PM
     * 07:26:57 AM
     * 10:00:03 AM
     * 07:56:14 PM
     * 01:15:19 PM
     * 12:40:31 AM
     * <p>
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
     * <p>
     * 12:40:31 AM
     * 07:26:57 AM
     * 10:00:03 AM
     * 01:15:19 PM
     * 01:15:19 PM
     * 07:56:14 PM
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    /*
        трудоемкость: T = O(n*log(n))
        ресурсоемкость: R = O(n)
     */
    static public void sortTimes(String inputName, String outputName) throws IOException {

        FileReader fileReader = new FileReader(inputName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        bufferedReader.close();
        int[] intTime = strListToIntArr(lines);
        Sorts.mergeSort(intTime);
        lines = intArrToStrList(intTime);

        try (PrintWriter writer = new PrintWriter(outputName, "UTF-8")) {

            for (String str : lines) {
                writer.println(str);

            }
        } catch (IOException ex) {
            throw new IOException();
        }
    }

    static private int[] strListToIntArr(List<String> strTime) {
        int[] intTime = new int[strTime.size()];
        for (int i = 0; i < strTime.size(); i++) {
            intTime[i] = strToSeconds(strTime.get(i));
        }
        return intTime;
    }

    static private int strToSeconds(String time) {
        try {
            List<String> timeArr = Arrays.asList(time.split(":"));
            timeArr.set(2, timeArr.get(2).substring(0, 2));
            int sec = (parseInt(timeArr.get(0)) % 12) * 3600 + parseInt(timeArr.get(1)) * 60 + parseInt(timeArr.get(2));
            if (time.charAt(9) == 'P') sec += 43200;
            return sec;
        } catch (Throwable t) {
            throw new IllegalArgumentException();
        }
    }

    static private List<String> intArrToStrList(int[] intTime) {
        List<String> strTime = new ArrayList<>();
        for (int j : intTime) {
            strTime.add(secondsToStr(j));
        }
        return strTime;
    }

    static private String secondsToStr(int time) {
        int pm = time / 43200;
        int sec = time % 43200;

        int hours = sec / 3600;
        if (hours == 0) hours = 12;
        String hourStr = hours <= 9 ? "0" + hours : String.valueOf(hours);
        sec %= 3600;
        int min = sec / 60;
        String minStr = min <= 9 ? "0" + min : String.valueOf(min);
        sec %= 60;
        String secStr = sec <= 9 ? "0" + sec : String.valueOf(sec);
        return hourStr + ":" + minStr + ":" + secStr + (pm == 1 ? " PM" : " AM");
    }

    /**
     * Сортировка адресов
     * <p>
     * Средняя
     * <p>
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     * <p>
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     * <p>
     * Людей в городе может быть до миллиона.
     * <p>
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     * <p>
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка температур
     * <p>
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     * <p>
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     * <p>
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     * <p>
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */
    /*
        трудоемкость: T = O(n+k)
        ресурсоемкость: R = O(n+k)
        k - диапазон значений
        При n >> k приводится к 0(n)
     */
    static public void sortTemperatures(String inputName, String outputName) throws IOException {
        FileReader fileReader = new FileReader(inputName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        bufferedReader.close();

        double[] doubleTempers = new double[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            doubleTempers[i] = parseDouble(lines.get(i));
        }

        int[] temperCounts = new int[7731];
        for (double temper : doubleTempers) {
            temperCounts[(int) (Math.round((temper + 273.0) * 10.0 * 10.0) / 10.0)]++;
        }

        double[] sortedTemper = new double[doubleTempers.length];
        int currentSortedIndex = 0;
        for (int n = 0; n < temperCounts.length; n++) {
            int count = temperCounts[n];
            for (int k = 0; k < count; k++) {
                sortedTemper[currentSortedIndex] = Math.round((n / 10.0 - 273.0) * 10) / 10.0;//
                currentSortedIndex++;
            }
        }

        try (PrintWriter writer = new PrintWriter(outputName, "UTF-8")) {
            for (double temper : sortedTemper) {
                writer.println(temper);

            }
        } catch (IOException ex) {
            throw new IOException();
        }


    }

    /**
     * Сортировка последовательности
     * <p>
     * Средняя
     * (Задача взята с сайта acmp.ru)
     * <p>
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     * <p>
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     * <p>
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     * <p>
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     */
    static public void sortSequence(String inputName, String outputName) throws IOException {
        throw new NotImplementedError();
    }

    /**
     * Соединить два отсортированных массива в один
     * <p>
     * Простая
     * <p>
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     * <p>
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     * <p>
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }
}
