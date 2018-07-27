package com.example.meirlen.orc.di.modules;

import com.example.meirlen.orc.di.scopes.BasketScope;
import com.example.meirlen.orc.di.scopes.CategoryScope;
import com.example.meirlen.orc.interactor.BasketInteractor;
import com.example.meirlen.orc.interactor.CategoryInteractor;
import com.example.meirlen.orc.interactor.impl.BasketInteractorImpl;
import com.example.meirlen.orc.interactor.impl.CategoryInteractorImpl;
import com.example.meirlen.orc.model.basket.Basket;
import com.example.meirlen.orc.presenter.BasketPresenter;
import com.example.meirlen.orc.presenter.CategoryPresenter;
import com.example.meirlen.orc.presenter.impl.BasketPresenterImpl;
import com.example.meirlen.orc.presenter.impl.CategoryPresenterImpl;
import com.example.meirlen.orc.rest.BasketApi;
import com.example.meirlen.orc.rest.CategoryApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


@Module
public class BasketModule {

    @BasketScope
    @Provides
    BasketInteractor provideInteractor() {
        return new BasketInteractorImpl();
    }

    @BasketScope
    @Provides
    BasketPresenter providePresenter(BasketInteractor interactor) {
        return new BasketPresenterImpl(interactor);
    }

    @BasketScope
    @Provides
    BasketApi provideApiService(Retrofit retrofit) {
        return retrofit.create(BasketApi.class);
    }
}
