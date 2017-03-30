package yen.nguyen.instagramapidemo.common;

public interface PresenterFactory<T extends BasePresenter> {
    T create();
}