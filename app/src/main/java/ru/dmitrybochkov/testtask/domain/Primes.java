package ru.dmitrybochkov.testtask.domain;

/**
 * Created by Dmitry Bochkov on 25.05.2017.
 */

public class Primes {

    private String primes;
    private long sum;


    public Primes(String primes, long sum) {
        this.primes = primes;
        this.sum = sum;
    }

    public String getPrimesList() {
        return primes;
    }

    public void setPrimes(String primes) {
        this.primes = primes;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }
}
