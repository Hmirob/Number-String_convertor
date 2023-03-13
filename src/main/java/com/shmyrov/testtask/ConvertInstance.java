package com.shmyrov.testtask;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConvertInstance {
    private long value;
    private long milliards;
    private int millions, thousands, hundreds, dozens, digit;


    public ConvertInstance(long value) {
        this.value = value;

        milliards = value / 1_000_000_000;
        millions = (int) (value % 1_000_000_000 / 1_000_000);
        thousands = (int) (value % 1_000_000 / 1000);
        hundreds = (int) (value % 1000 / 100 * 100);
        dozens = (int) (value % 100 / 10 * 10);
        digit = (int) (value % 10);
    }
}
