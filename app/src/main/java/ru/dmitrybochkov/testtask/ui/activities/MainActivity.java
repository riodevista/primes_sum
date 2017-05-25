package ru.dmitrybochkov.testtask.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.dmitrybochkov.testtask.R;
import ru.dmitrybochkov.testtask.ui.presenters.MainScreenPresenter;
import ru.dmitrybochkov.testtask.ui.views.MainView;


public class MainActivity extends MvpActivity<MainView, MainScreenPresenter> implements MainView{

    @BindView(R.id.input)
    EditText input;
    @BindView(R.id.output)
    TextView primesList;
    @BindView(R.id.sum)
    TextView primesSum;
    @BindView(R.id.progress)
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        presenter.initView();
    }

    @NonNull
    @Override
    public MainScreenPresenter createPresenter() {
        return new MainScreenPresenter();
    }

    private void init() {
        input.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH
                                || actionId == EditorInfo.IME_ACTION_DONE
                                || event.getAction() == KeyEvent.ACTION_DOWN
                                && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            presenter.generateSieve(input.getText().toString());
                            return true;
                        }
                        return false;
                    }
                });
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setPrimesListText(String text) {
        primesList.setText(text);
    }

    @Override
    public void setPrimesSumText(String text) {
        primesSum.setText(text);
    }

    @Override
    public void showInputError() {
        Toast.makeText(this, "Error while parsing input", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showOutOfMemoryError() {
        Toast.makeText(this, "Out of Memory", Toast.LENGTH_SHORT).show();
    }

}
