package com.example.firebasetest20.util

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.firebase.ui.storage.images.FirebaseImageLoader
import com.google.firebase.storage.StorageReference
import java.io.InputStream

@GlideModule
// @GlideModule은 모든 라이브러리에서 사용이 가능함
// Glide와 Firebase를 다운로드 한 후 이미지 처리하는 부분을 편하게 사용하려고
// 원래는 비트맵으로 변환해서 등록 후 사용하는 방법이었는데, 이를 자동화함
class MyAppGlideModule : AppGlideModule() {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        // Register FirebaseImageLoader to handle StorageReference
        registry.append(
            StorageReference::class.java, InputStream::class.java,
            FirebaseImageLoader.Factory()
        )
    }
}