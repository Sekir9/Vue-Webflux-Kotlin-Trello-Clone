package com.scmd.trello.Service

import com.scmd.trello.Utils.generateId
import com.scmd.trello.models.User
import com.scmd.trello.repositores.UserRepository
import com.scmd.userservice.model.pub.TransferUserDto
import com.scmd.userservice.model.pub.UserDto
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class UserService(private val userRepository: UserRepository) {

    fun get(userId: String): Mono<User> = userRepository.findById(userId)

    fun getMultipleUsers(usersIDs: Set<String>): Flux<User> =
        userRepository.findAllById(usersIDs).sort(Comparator.comparing { it.name })

    fun getAll(): Flux<User> = userRepository.findAll().sort(Comparator.comparing { it.name })

    fun save(userData: User): Mono<User> = userRepository.save(createNewUser(userData))

    fun update(userId: String, newUser: TransferUserDto): Mono<User> = userRepository.findById(userId)
        .flatMap { userRepository.save(updateUser(it, newUser)) }

    fun delete(objectId: String): Mono<Void> = userRepository.findById(objectId)
        .flatMap { userRepository.deleteById(objectId) }.switchIfEmpty(Mono.empty())

    private fun createNewUser(userData: User): User =
        User(
            id = generateId(),
            name = userData.name,
            email = userData.email,
            password = userData.password
        )

    private fun updateUser(currentUser: User, newUser: TransferUserDto): User =
        currentUser.copy(
            name = newUser.name,
            email = newUser.email,
            password = newUser.password
        )
}