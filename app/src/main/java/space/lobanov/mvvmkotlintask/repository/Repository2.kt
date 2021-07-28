package space.lobanov.mvvmkotlintask.repository

import space.lobanov.mvvmkotlintask.local_db.AppResult
import space.lobanov.mvvmkotlintask.model.Hits

interface Repository2 {
    suspend fun getAllImages() :AppResult<List<Hits>>
}