package com.scmd.trello.repositores

import com.scmd.trello.models.User
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : ReactiveMongoRepository<User, String> {
}