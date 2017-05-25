package ru.dmitrybochkov.testtask.ui.presenters;


import android.os.AsyncTask;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import ru.dmitrybochkov.testtask.domain.Primes;
import ru.dmitrybochkov.testtask.tasks.GeneratePrimesTask;
import ru.dmitrybochkov.testtask.ui.views.MainView;

/**
 * Created by Dmitry Bochkov on 01.11.2016.
 */

public class MainScreenPresenter extends MvpBasePresenter<MainView> {

    private String primesList = "";
    private String primesSum = "";

    private GeneratePrimesTask generatePrimesTask;

    private void cancelGenerateTaskIfRunning(){
        if (generatePrimesTask != null){
            generatePrimesTask.cancel(true);
        }
    }

    private boolean isGenerateTaskRunning() {
        return generatePrimesTask != null && generatePrimesTask.getStatus() == AsyncTask.Status.RUNNING;
    }



    public void generateSieve(String text){
        try {
            final int limit = Integer.parseInt(text);
            cancelGenerateTaskIfRunning();
            generatePrimesTask = new GeneratePrimesTask(limit, new GeneratePrimesTask.GeneratePrimesListener() {

                @Override
                public void onPrimesGenerated(Primes primes) {
                    if (isViewAttached())
                        getView().hideProgress();
                    showOutput(primes.getPrimesList(), primes.getSum());
                }

                @Override
                public void onOutOfMemoryError() {
                    if (isViewAttached()) {
                        getView().hideProgress();
                        getView().showOutOfMemoryError();
                    }
                }

            });
            if (isViewAttached()) {
                getView().showProgress();
                showOutput("", 0);
            }
            generatePrimesTask.execute();
        } catch (NumberFormatException e) {
            if (isViewAttached())
                getView().showInputError();
        }
    }

    private void showOutput(String primesList, long sum) {
        this.primesList = primesList;
        this.primesSum = String.valueOf(sum);

        if (isViewAttached()) {
            getView().setPrimesListText(primesList);
            getView().setPrimesSumText(primesSum);
        }
    }

    public void initView() {
        if (isViewAttached()) {
            getView().setPrimesListText(primesList);
            getView().setPrimesSumText(primesSum);
            if (isGenerateTaskRunning())
                getView().showProgress();
        }

    }
    @Override
    public void attachView(MainView view) {
        super.attachView(view);

    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }

}
