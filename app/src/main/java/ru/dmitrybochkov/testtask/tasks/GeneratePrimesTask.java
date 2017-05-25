package ru.dmitrybochkov.testtask.tasks;

import android.os.AsyncTask;

import java.util.BitSet;

import ru.dmitrybochkov.testtask.domain.Primes;
import ru.dmitrybochkov.testtask.utils.AtkinSieve;

/**
 * Created by Dmitry Bochkov on 25.05.2017.
 */

public class GeneratePrimesTask extends AsyncTask<Void, Void, Primes> {

    public interface GeneratePrimesListener {

        void onPrimesGenerated(Primes primes);

        void onOutOfMemoryError();

    }

    private int limit;
    private GeneratePrimesListener listener;

    public GeneratePrimesTask(int limit, GeneratePrimesListener generatePrimesListener){
        this.limit = limit;
        this.listener = generatePrimesListener;
    }

    @Override
    protected Primes doInBackground(Void... params) {
        try {
            BitSet primes = AtkinSieve.getPrimesUpTo(limit);
            long sum = 0;
            StringBuilder primesList = new StringBuilder();
            for (int i = 1; i <= limit; ++i)
                if (primes.get(i)) {
                    sum += i;
                    primesList.append(String.valueOf(i)).append(" ");
                }
                return new Primes(primesList.toString(), sum);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    protected void onPostExecute(Primes primes){
        if (primes == null)
            listener.onOutOfMemoryError();
        else
            listener.onPrimesGenerated(primes);
    }
}
