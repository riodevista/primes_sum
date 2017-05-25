package ru.dmitrybochkov.testtask.utils;

import java.util.BitSet;


/**
 * Created by Dmitry Bochkov on 25.05.2017.
 */


// This class is based on https://ru.wikibooks.org/wiki/%D0%A0%D0%B5%D0%B0%D0%BB%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D0%B8_%D0%B0%D0%BB%D0%B3%D0%BE%D1%80%D0%B8%D1%82%D0%BC%D0%BE%D0%B2/%D0%A0%D0%B5%D1%88%D0%B5%D1%82%D0%BE_%D0%90%D1%82%D0%BA%D0%B8%D0%BD%D0%B0#Java

public abstract class AtkinSieve {

    public static BitSet getPrimesUpTo (int limit) {
        BitSet sieve = new BitSet();
        // Предварительное просеивание
        for (long x2 = 1L, dx2 = 3L; x2 < limit; x2 += dx2, dx2 += 2L)
            for (long y2 = 1L, dy2 = 3L, n; y2 < limit; y2 += dy2, dy2 += 2L) {
                // n = 4x² + y²
                n = (x2 << 2L) + y2;
                if (n <= limit && (n % 12L == 1L || n % 12L == 5L))
                    sieve.flip((int)n);
                // n = 3x² + y²
                n -= x2;
                if (n <= limit && n % 12L == 7L)
                    sieve.flip((int)n);
                // n = 3x² - y² (при x > y)
                if (x2 > y2) {
                    n -= y2 << 1L;
                    if (n <= limit && n % 12L == 11L)
                        sieve.flip((int)n);
                }
            }
        // Все числа, кратные квадратам, помечаются как составные
        int r = 5;
        for (long r2 = r * r, dr2 = (r << 1L) + 1L; r2 < limit; ++r, r2 += dr2, dr2 += 2L)
            if (sieve.get(r))
                for (long mr2 = r2; mr2 < limit; mr2 += r2)
                    sieve.set((int)mr2, false);
        // Числа 2 и 3 — заведомо простые
        if (limit > 2)
            sieve.set(2, true);
        if (limit > 3)
            sieve.set(3, true);

        return sieve;
    }

}
