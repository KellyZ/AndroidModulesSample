package module.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import module.data.api.LoginRepository
import module.data.model.User
import javax.inject.Inject

class OfflineLoginRepository @Inject constructor(): LoginRepository {
    override fun login(user: User): Flow<Boolean> = flow {
        emit(
            "kelly" == user.name && "123456" == user.password
        )
    }.flowOn(Dispatchers.IO)
}