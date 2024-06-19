package com.swapnil.chatapp.helpers

import com.swapnil.chatapp.data.LocalRepo
import com.swapnil.chatapp.data.UserRepo
import com.swapnil.chatapp.feature.editProfile.EditProfileViewModel
import com.swapnil.chatapp.feature.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        UserRepo()
    }
    single {
        LocalRepo(DataStoreUtil.create(get()))
    }
}

val viewModelModule = module {
    viewModel {
        EditProfileViewModel(get(), get())
    }
    viewModel {
        SplashViewModel(get())
    }
}
