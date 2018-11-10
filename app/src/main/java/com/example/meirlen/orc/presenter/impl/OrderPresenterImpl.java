package com.example.meirlen.orc.presenter.impl;

import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.api.NetworkResponse;
import com.example.meirlen.orc.interactor.OrderInteractor;
import com.example.meirlen.orc.interactor.ProductInteractor;
import com.example.meirlen.orc.model.CardResponse;
import com.example.meirlen.orc.model.ProductResponse;
import com.example.meirlen.orc.model.request.Filter;
import com.example.meirlen.orc.presenter.OrderPresenter;
import com.example.meirlen.orc.presenter.ProductPresenter;
import com.example.meirlen.orc.view.FieldView;
import com.example.meirlen.orc.view.OrderView;
import com.example.meirlen.orc.view.ProductView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class OrderPresenterImpl implements OrderPresenter {

    public OrderInteractor interactor;
    public OrderView pView;
    private Disposable getMessagesDisposable;


    public OrderPresenterImpl(OrderInteractor interactor) {
        this.interactor = interactor;
    }


    @Override
    public void sendOrder(String token, String lat, String lng,String address) {
        pView.showLoading();
        getMessagesDisposable = interactor.sendOrder(token, lat, lng,address)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((APIResponse apiResponse) -> {
                    pView.hideLoading();
                    if (apiResponse.getIsSuccess() && apiResponse.getStatus() == NetworkResponse.CODE_OK) {
                        if (this.isViewAttached()) {
                            pView.isSussess(apiResponse.getIsSuccess());
                        }
                    } else {
                        if (this.isViewAttached()) {
                            pView.loadingFailed(String.valueOf(apiResponse.getMessage()));
                        }
                    }
                }, throwable -> {
                    if (isViewAttached()) {
                        pView.hideLoading();
                        pView.loadingFailed(throwable.getMessage());
                    }
                });
    }


    private boolean isViewAttached() {
        return this.pView != null;
    }


    @Override
    public void setView(OrderView view) {
        this.pView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }


}
