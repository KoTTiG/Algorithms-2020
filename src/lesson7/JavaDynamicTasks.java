package lesson7;

import kotlin.NotImplementedError;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     * <p>
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
     * При сравнении подстрок, регистр символов *имеет* значение.
     */
    /*
        трудоемкость: T = O(n*m)
        ресурсоемкость: R = O(n*m)
        n - длина стоки first
        m - длина стоки second
     */
    public static String longestCommonSubSequence(String first, String second) {
        if (second.length() > first.length()) return longestCommonSubSequence(second, first);
        if (second.length() == 1) {
            if (first.contains(second)) return second;
            else return "";
        }

        int[][] count = new int[first.length() + 1][second.length() + 1];

        for (int i = 0; i < first.length(); i++) {
            for (int j = 0; j < second.length(); j++) {
                if (first.charAt(i) == second.charAt(j)) {
                    count[i + 1][j + 1] = count[i][j] + 1;
                } else {
                    count[i + 1][j + 1] = Math.max(count[i + 1][j], count[i][j + 1]);
                }
            }
        }

        StringBuilder subSeq = new StringBuilder();
        int i = first.length();
        int j = second.length();
        while (i > 0 && j > 0) {
            if (first.charAt(i - 1) == second.charAt(j - 1)) {
                subSeq.insert(0, first.charAt(i - 1));
                i -= 1;
                j -= 1;
            } else if (i > 0 && j > 0 && count[i - 1][j] > count[i][j - 1]) {
                i -= 1;
            } else {
                j -= 1;
            }
        }
        return subSeq.toString();


    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Сложная
     * <p>
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     */
    /*
        трудоемкость: T = O(n^2)
        ресурсоемкость: R = O(n)
     */
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        if (list.size() <= 1) return list;
        List<Integer> subSeqLengths = new ArrayList<>();
        List<Integer> parents = new ArrayList<>();

        for (int i = 0; i < list.size(); ++i) {
            subSeqLengths.add(i, -1);
            parents.add(i, -1);
            for (int j = 0; j < i; ++j)
                if (list.get(j) < list.get(i))
                    if (subSeqLengths.get(j) + 1 > subSeqLengths.get(i)) {
                        subSeqLengths.set(i, subSeqLengths.get(j) + 1);
                        parents.set(i, j);
                    }
        }
        int longestIndex = subSeqLengths.indexOf(Collections.max(subSeqLengths));
        List<Integer> subSeq = new ArrayList<>();
        while (longestIndex != -1) {
            subSeq.add(0, list.get(longestIndex));
            longestIndex = parents.get(longestIndex);
        }
        return subSeq;
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Средняя
     * <p>
     * В файле с именем inputName задано прямоугольное поле:
     * <p>
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     * <p>
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     * <p>
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    public static int shortestPathOnField(String inputName) {
        throw new NotImplementedError();
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
