package com.example.meirlen.orc.rest;


import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.model.CartCount;
import com.example.meirlen.orc.model.Category;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface CategoryApi {

    @GET(ApiMethods.CATEGORIES_GET)
    Observable<APIResponse<List<Category>>> getCategories(@Header("Token") String token);


    @GET(ApiMethods.CART_COUNT)
    Observable<APIResponse<CartCount>> getCartCount(@Header("Token") String token);

}
