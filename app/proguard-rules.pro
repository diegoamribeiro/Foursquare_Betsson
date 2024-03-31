##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-dontwarn sun.misc.**
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { <fields>; }

# Prevent proguard from stripping interface information from TypeAdapter, TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Prevent R8 from leaving Data object members always null
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}

# Retain generic signatures of TypeToken and its subclasses with R8 version 3.0 and higher.
-keep,allowobfuscation,allowshrinking class com.google.gson.reflect.TypeToken
-keep,allowobfuscation,allowshrinking class * extends com.google.gson.reflect.TypeToken

# Mantém todas as classes anotadas com @HiltAndroidApp, bem como suas superclasses.
-keep,allowobfuscation @dagger.hilt.android.HiltAndroidApp class * {
    <init>(...);
}

# Mantém as classes geradas pelo Dagger Hilt, evitando que sejam removidas pelo ProGuard.
-keep class dagger.hilt.android.internal.managers.* { *; }
-keep class * implements androidx.lifecycle.ViewModel {
    <init>(...);
}
#-keep class * extends androidx.hilt.lifecycle.ViewModelInject { *; }
-keep class * extends dagger.hilt.android.AndroidEntryPoint { *; }

# Mantém as classes que usam injeção de dependência e podem ser acessadas via reflexão.
-keepclasseswithmembers class * {
    @javax.inject.Inject <init>(...);
}
-keepclasseswithmembers class * {
    @dagger.hilt.InstallIn <init>(...);
}

# Mantém classes que usam as anotações do Jetpack (por exemplo, para salvar estados da UI com ViewModel)
-keep class * extends androidx.lifecycle.ViewModel {
    <fields>;
    <methods>;
}
-keep class * extends androidx.room.RoomDatabase {
    <fields>;
    <methods>;
}

# Adicione regras específicas para o seu pacote, como a retenção de modelos de dados que podem ser usados pelo Gson e pelo Hilt
-keep class com.dmribeiro87.foursquarebetsson.** { *; }


##---------------End: proguard configuration for Gson  ----------