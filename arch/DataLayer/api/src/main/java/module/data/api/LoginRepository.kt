package module.data.api

import kotlinx.coroutines.flow.Flow
import module.data.model.User

interface LoginRepository {
    fun login(user: User): Flow<Boolean>
}