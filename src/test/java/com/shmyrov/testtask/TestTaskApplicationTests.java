package com.shmyrov.testtask;

import com.shmyrov.testtask.services.ConverterService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class TestTaskApplicationTests {

    private final static Map<Long, String> oneWordIntToStringMap = new HashMap<>();
    private final static Map<Long, String> multiWordIntToStringMap = new HashMap<>();
    private final static Map<Long, String> thousendsToStringMap = new HashMap<>();
    private final static Map<Long, String> millionsToStringMap = new HashMap<>();
    private final static Map<Long, String> milliardsToStringMap = new HashMap<>();

    @Autowired
    ConverterService converterService;

    @BeforeAll
    static void beforeAll() {
        oneWordIntToStringMap.put(0L, "ноль");
        oneWordIntToStringMap.put(1L, "один");
        oneWordIntToStringMap.put(2L, "два");
        oneWordIntToStringMap.put(3L, "три");
        oneWordIntToStringMap.put(4L, "четыре");
        oneWordIntToStringMap.put(5L, "пять");
        oneWordIntToStringMap.put(6L, "шесть");
        oneWordIntToStringMap.put(7L, "семь");
        oneWordIntToStringMap.put(8L, "восемь");
        oneWordIntToStringMap.put(9L, "девять");
        oneWordIntToStringMap.put(10L, "десять");
        oneWordIntToStringMap.put(11L, "одиннадцать");
        oneWordIntToStringMap.put(12L, "двенадцать");
        oneWordIntToStringMap.put(13L, "тринадцать");
        oneWordIntToStringMap.put(14L, "четырнадцать");
        oneWordIntToStringMap.put(15L, "пятнадцать");
        oneWordIntToStringMap.put(16L, "шестнадцать");
        oneWordIntToStringMap.put(17L, "семнадцать");
        oneWordIntToStringMap.put(18L, "восемнадцать");
        oneWordIntToStringMap.put(19L, "девятнадцать");
        oneWordIntToStringMap.put(20L, "двадцать");
        oneWordIntToStringMap.put(30L, "тридцать");
        oneWordIntToStringMap.put(40L, "сорок");
        oneWordIntToStringMap.put(50L, "пятьдесят");
        oneWordIntToStringMap.put(60L, "шестьдесят");
        oneWordIntToStringMap.put(70L, "семьдесят");
        oneWordIntToStringMap.put(80L, "восемьдесят");
        oneWordIntToStringMap.put(90L, "девяносто");
        oneWordIntToStringMap.put(100L, "сто");

        multiWordIntToStringMap.put(109L, "сто девять");
        multiWordIntToStringMap.put(208L, "двести восемь");
        multiWordIntToStringMap.put(307L, "триста семь");
        multiWordIntToStringMap.put(406L, "четыреста шесть");
        multiWordIntToStringMap.put(505L, "пятьсот пять");
        multiWordIntToStringMap.put(604L, "шестьсот четыре");
        multiWordIntToStringMap.put(703L, "семьсот три");
        multiWordIntToStringMap.put(802L, "восемьсот два");
        multiWordIntToStringMap.put(901L, "девятьсот один");

        multiWordIntToStringMap.put(159L, "сто пятьдесят девять");
        multiWordIntToStringMap.put(248L, "двести сорок восемь");
        multiWordIntToStringMap.put(337L, "триста тридцать семь");
        multiWordIntToStringMap.put(426L, "четыреста двадцать шесть");
        multiWordIntToStringMap.put(515L, "пятьсот пятнадцать");
        multiWordIntToStringMap.put(694L, "шестьсот девяносто четыре");
        multiWordIntToStringMap.put(783L, "семьсот восемьдесят три");
        multiWordIntToStringMap.put(872L, "восемьсот семьдесят два");
        multiWordIntToStringMap.put(961L, "девятьсот шестьдесят один");

        thousendsToStringMap.put(1000L, "одна тысяча");
        thousendsToStringMap.put(2000L, "две тысячи");
        thousendsToStringMap.put(3000L, "три тысячи");
        thousendsToStringMap.put(4000L, "четыре тысячи");
        thousendsToStringMap.put(5000L, "пять тысяч");
        thousendsToStringMap.put(6000L, "шесть тысяч");
        thousendsToStringMap.put(7000L, "семь тысяч");
        thousendsToStringMap.put(8000L, "восемь тысяч");
        thousendsToStringMap.put(9000L, "девять тысяч");
        thousendsToStringMap.put(16000L, "шестнадцать тысяч");
        thousendsToStringMap.put(20000L, "двадцать тысяч");
        thousendsToStringMap.put(21000L, "двадцать одна тысяча");
        thousendsToStringMap.put(22000L, "двадцать две тысячи");
        thousendsToStringMap.put(25000L, "двадцать пять тысяч");
        thousendsToStringMap.put(147000L, "сто сорок семь тысяч");
        thousendsToStringMap.put(569000L, "пятьсот шестьдесят девять тысяч");
        thousendsToStringMap.put(666666L, "шестьсот шестьдесят шесть тысяч шестьсот шестьдесят шесть");
        thousendsToStringMap.put(616616L, "шестьсот шестнадцать тысяч шестьсот шестнадцать");

        millionsToStringMap.put(1_000_000L, "один миллион");
        millionsToStringMap.put(2_000_000L, "два миллиона");
        millionsToStringMap.put(5_000_000L, "пять миллионов");
        millionsToStringMap.put(10_000_000L, "десять миллионов");
        millionsToStringMap.put(14_000_000L, "четырнадцать миллионов");
        millionsToStringMap.put(20_000_000L, "двадцать миллионов");
        millionsToStringMap.put(22_123_302L, "двадцать два миллиона сто двадцать три тысячи триста два");

        milliardsToStringMap.put(1_000_000_000L, "один миллиард");
        milliardsToStringMap.put(2_000_000_000L, "два миллиарда");
        milliardsToStringMap.put(3_000_000_000L, "три миллиарда");
        milliardsToStringMap.put(5_000_000_000L, "пять миллиардов");
        milliardsToStringMap.put(10_000_000_000L, "десять миллиардов");
        milliardsToStringMap.put(13_000_000_000L, "тринадцать миллиардов");
        milliardsToStringMap.put(21_000_000_000L, "двадцать один миллиард");
        milliardsToStringMap.put(390_000_000_000L, "триста девяносто миллиардов");
        milliardsToStringMap.put(999_999_999_999L, "девятьсот девяносто девять миллиардов девятьсот девяносто девять миллионов девятьсот девяносто девять тысяч девятьсот девяносто девять");
        milliardsToStringMap.put(111_111_111_111L, "сто одиннадцать миллиардов сто одиннадцать миллионов сто одиннадцать тысяч сто одиннадцать");
        milliardsToStringMap.put(202_202_202_202L, "двести два миллиарда двести два миллиона двести две тысячи двести два");
        milliardsToStringMap.put(303_000_303_303L, "триста три миллиарда триста три тысячи триста три");
        milliardsToStringMap.put(404_404_000_404L, "четыреста четыре миллиарда четыреста четыре миллиона четыреста четыре");
        milliardsToStringMap.put(505_505_505_000L, "пятьсот пять миллиардов пятьсот пять миллионов пятьсот пять тысяч");
    }

    @TestFactory
    Collection<DynamicTest> oneWordIntToString() {
        return getDynamicTestsFromMap(oneWordIntToStringMap);
    }

    @TestFactory
    Collection<DynamicTest> multiWordIntToString() {
        return getDynamicTestsFromMap(multiWordIntToStringMap);
    }

    @TestFactory
    Collection<DynamicTest> thousandsToString() {
        return getDynamicTestsFromMap(thousendsToStringMap);
    }

    @TestFactory
    Collection<DynamicTest> millionsToString() {
        return getDynamicTestsFromMap(millionsToStringMap);
    }

    @TestFactory
    Collection<DynamicTest> milliardsToString() {
        return getDynamicTestsFromMap(milliardsToStringMap);
    }

    Collection<DynamicTest> getDynamicTestsFromMap(Map<Long, String> map) {
        return map.entrySet().stream().map((entry) -> DynamicTest.dynamicTest(
                entry.getKey().toString(),
                () -> assertEquals(entry.getValue(), converterService.numberToString(entry.getKey()))
        )).toList();
    }

    //    @TestFactory
    Collection<DynamicTest> stringToNumber() {
        return multiWordIntToStringMap.entrySet().stream().map((entry) -> DynamicTest.dynamicTest(
                entry.getKey().toString(),
                () -> assertEquals(entry.getKey(), converterService.stringToNumber(entry.getValue()))
        )).toList();
    }
}
