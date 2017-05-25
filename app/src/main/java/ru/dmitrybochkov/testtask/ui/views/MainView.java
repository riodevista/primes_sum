package ru.dmitrybochkov.testtask.ui.views;

import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * Created by Dmitry Bochkov on 25.05.2017.
 */

public interface MainView extends MvpView {

    void showProgress();

    void hideProgress();

    void setPrimesListText(String text);

    void setPrimesSumText(String text);

    void showInputError();

    void showOutOfMemoryError();

}
