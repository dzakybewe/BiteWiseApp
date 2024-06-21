package com.bewe.bitewiseapp.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")

class UserPreference private constructor(private val dataStore: DataStore<Preferences>) {

    suspend fun saveSession(user: UserModel) {
        dataStore.edit { preferences ->
            preferences[DEVICE_ID_KEY] = user.deviceId
            preferences[TOKEN_KEY] = user.token

        }
    }

    suspend fun setTypeId(typeId: Int) {
        dataStore.edit { preferences ->
            preferences[TYPE_ID] = typeId
        }
    }

    suspend fun setIsLoggedIn(isLoggedIn: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] = isLoggedIn
        }
    }

    fun getSession(): Flow<UserModel> {
        return dataStore.data.map { preferences ->
            UserModel(
                preferences[DEVICE_ID_KEY] ?: "",
                preferences[TOKEN_KEY] ?: "",
            )
        }
    }

    fun getTypeId(): Flow<Int> {
        return dataStore.data.map { preferences ->
            preferences[TYPE_ID] ?: 1
        }
    }

    fun getIsLoggedIn(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[IS_LOGGED_IN] ?: false
        }
    }


    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null

        private val DEVICE_ID_KEY = stringPreferencesKey("deviceId")
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val IS_LOGGED_IN = booleanPreferencesKey("isLoggedIn")
        private val TYPE_ID = intPreferencesKey("typeId")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}