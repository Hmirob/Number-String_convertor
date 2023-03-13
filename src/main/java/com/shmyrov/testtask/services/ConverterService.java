package com.shmyrov.testtask.services;

import com.shmyrov.testtask.ConvertInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//
@Service
@Slf4j
public class ConverterService {
    private final static Map<Integer, String> indeclinableOrderNumbersToStringMap = new HashMap<>();

    public ConverterService() {
        indeclinableOrderNumbersToStringMap.put(0, "");
        indeclinableOrderNumbersToStringMap.put(1, "один");
        indeclinableOrderNumbersToStringMap.put(2, "два");
        indeclinableOrderNumbersToStringMap.put(3, "три");
        indeclinableOrderNumbersToStringMap.put(4, "четыре");
        indeclinableOrderNumbersToStringMap.put(5, "пять");
        indeclinableOrderNumbersToStringMap.put(6, "шесть");
        indeclinableOrderNumbersToStringMap.put(7, "семь");
        indeclinableOrderNumbersToStringMap.put(8, "восемь");
        indeclinableOrderNumbersToStringMap.put(9, "девять");
        indeclinableOrderNumbersToStringMap.put(10, "десять");
        indeclinableOrderNumbersToStringMap.put(11, "одиннадцать");
        indeclinableOrderNumbersToStringMap.put(12, "двенадцать");
        indeclinableOrderNumbersToStringMap.put(13, "тринадцать");
        indeclinableOrderNumbersToStringMap.put(14, "четырнадцать");
        indeclinableOrderNumbersToStringMap.put(15, "пятнадцать");
        indeclinableOrderNumbersToStringMap.put(16, "шестнадцать");
        indeclinableOrderNumbersToStringMap.put(17, "семнадцать");
        indeclinableOrderNumbersToStringMap.put(18, "восемнадцать");
        indeclinableOrderNumbersToStringMap.put(19, "девятнадцать");
        indeclinableOrderNumbersToStringMap.put(20, "двадцать");
        indeclinableOrderNumbersToStringMap.put(30, "тридцать");
        indeclinableOrderNumbersToStringMap.put(40, "сорок");
        indeclinableOrderNumbersToStringMap.put(50, "пятьдесят");
        indeclinableOrderNumbersToStringMap.put(60, "шестьдесят");
        indeclinableOrderNumbersToStringMap.put(70, "семьдесят");
        indeclinableOrderNumbersToStringMap.put(80, "восемьдесят");
        indeclinableOrderNumbersToStringMap.put(90, "девяносто");
        indeclinableOrderNumbersToStringMap.put(100, "сто");
        indeclinableOrderNumbersToStringMap.put(200, "двести");
        indeclinableOrderNumbersToStringMap.put(300, "триста");
        indeclinableOrderNumbersToStringMap.put(400, "четыреста");
        indeclinableOrderNumbersToStringMap.put(500, "пятьсот");
        indeclinableOrderNumbersToStringMap.put(600, "шестьсот");
        indeclinableOrderNumbersToStringMap.put(700, "семьсот");
        indeclinableOrderNumbersToStringMap.put(800, "восемьсот");
        indeclinableOrderNumbersToStringMap.put(900, "девятьсот");
    }

    public Integer stringToNumber(String value) {
        return 0;
    }

    public String numberToString(Long value) {
        if (value == 0) {
            return "ноль";
        }
        if (value >= 1_000_000_000) {
            return getMilliardsString(new ConvertInstance(value));
        }
        if (value >= 1_000_000) {
            return getMillionsString(new ConvertInstance(value));
        }
        if (value >= 1000) {
            return getThousandsString(new ConvertInstance(value));
        }
        return getHundredsString(value.intValue());
    }

    private String getMilliardsString(ConvertInstance instance) {
        StringBuilder stringBuilder = new StringBuilder(getHundredsString((int) (instance.getMilliards())));
        int twoLastMilliardsDigit = (int) (instance.getMilliards() % 100);

        if (twoLastMilliardsDigit >= 10 && twoLastMilliardsDigit <= 20) {
            return stringBuilder.append(" ").append("миллиардов").append(" ").append(
                    getMillionsString(instance)
            ).toString().trim();
        }

        if (stringBuilder.length() > 0) {
            switch (twoLastMilliardsDigit % 10) {
                case 1 -> stringBuilder.append(" ").append("миллиард");
                case 2, 3, 4 -> stringBuilder.append(" ").append("миллиарда");
                default -> stringBuilder.append(" ").append("миллиардов");
            }
        }

        return stringBuilder.append(" ").append(
                getMillionsString(instance)
        ).toString().trim();
    }

    private String getMillionsString(ConvertInstance instance) {
        StringBuilder stringBuilder = new StringBuilder(getHundredsString(instance.getMillions()));
        int twoLastMillionsDigit = instance.getMillions() % 100;

        if (twoLastMillionsDigit >= 10 && twoLastMillionsDigit <= 20) {
            return stringBuilder.append(" ").append("миллионов").append(" ").append(
                    getThousandsString(instance)
            ).toString().trim();
        }

        if (stringBuilder.length() > 0) {
            switch (twoLastMillionsDigit % 10) {
                case 1 -> stringBuilder.append(" ").append("миллион");
                case 2, 3, 4 -> stringBuilder.append(" ").append("миллиона");
                default -> stringBuilder.append(" ").append("миллионов");
            }
        }

        return stringBuilder.append(" ").append(
                getThousandsString(instance)
        ).toString().trim();
    }

    private String getThousandsString(ConvertInstance instance) {
        StringBuilder stringBuilder = new StringBuilder(getHundredsString(instance.getThousands()));
        int twoLastThousandDigit = instance.getThousands() % 100;

        if (twoLastThousandDigit >= 10 && twoLastThousandDigit <= 20) {
            return stringBuilder.append(" ").append("тысяч").append(" ").append(
                    getHundredsString(instance.getHundreds() + instance.getDozens() + instance.getDigit())
            ).toString().trim();
        }

        if (twoLastThousandDigit % 10 == 1 || twoLastThousandDigit % 10 == 2) {
            String[] arr = stringBuilder.toString().split(" ");
            if (twoLastThousandDigit % 10 == 1) {
                arr[arr.length - 1] = "одна";
            } else if (twoLastThousandDigit % 10 == 2) {
                arr[arr.length - 1] = "две";
            }
            StringBuilder finalStringBuilder = new StringBuilder();
            Arrays.stream(arr).forEach(x -> finalStringBuilder.append(x).append(" "));
            stringBuilder = new StringBuilder(finalStringBuilder.toString().trim());
        }

        if (stringBuilder.length() > 0) {
            switch (twoLastThousandDigit % 10) {
                case 1 -> stringBuilder.append(" ").append("тысяча");
                case 2, 3, 4 -> stringBuilder.append(" ").append("тысячи");
                default -> stringBuilder.append(" ").append("тысяч");
            }
        }
        return stringBuilder.append(" ").append(
                getHundredsString(instance.getHundreds() + instance.getDozens() + instance.getDigit())
        ).toString().trim();
    }

    private String getHundredsString(Integer value) {
        String str = indeclinableOrderNumbersToStringMap.get(value / 100 * 100) +
                " " +
                getDozenString(value % 100);
        return str.trim();
    }

    private String getDozenString(Integer value) {
        if (value < 20) {
            return indeclinableOrderNumbersToStringMap.get(value);
        } else {
            String str = indeclinableOrderNumbersToStringMap.get(value / 10 * 10) + " " + getDigitString(value % 10);
            return str.trim();
        }
    }

    private String getDigitString(Integer value) {
        return indeclinableOrderNumbersToStringMap.get(value);
    }
}
