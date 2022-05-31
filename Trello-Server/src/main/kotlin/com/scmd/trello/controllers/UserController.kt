package com.scmd.trello.controllers

import com.scmd.trello.Service.UserService
import com.scmd.trello.utils.toDto
import com.scmd.trello.utils.toTransferUser
import com.scmd.trello.utils.toUser
import com.scmd.userservice.api.pub.UsersApi
import com.scmd.userservice.model.pub.TransferUserDto
import com.scmd.userservice.model.pub.UserDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*
import kotlin.NoSuchElementException


@RestController
@RequestMapping(path = ["/api"])
class UserController(private val userService: UserService): UsersApi {

    override fun getUser(userId: String, exchange: ServerWebExchange): Mono<ResponseEntity<UserDto>> =
        userService.get(userId)
            .map { ResponseEntity.ok(it.toDto())}
            .switchIfEmpty(Mono.error(NoSuchElementException("User with id: '$userId' does not exist.")))

    override fun getUsers(userIds: Optional<Set<String>>, exchange: ServerWebExchange): Mono<ResponseEntity<Flux<UserDto>>> =
        Mono.just(userIds.map { userService.getMultipleUsers(it) }.orElseGet { userService.getAll() })
            .map { flux -> flux.map { it.toDto() } }
            .map { ResponseEntity.ok(it) }

    override fun createUser(transferUserDto: Mono<TransferUserDto>, exchange: ServerWebExchange): Mono<ResponseEntity<TransferUserDto>> =
        transferUserDto.flatMap { userService.save(it.toUser()) }
            .map { ResponseEntity.status(HttpStatus.CREATED).body(it.toTransferUser()) }

    override fun updateUser( userId: String, transferUserDto: Mono<TransferUserDto>, exchange: ServerWebExchange): Mono<ResponseEntity<TransferUserDto>> =
        transferUserDto.flatMap { userService.update(userId, it  ) }
            .map { ResponseEntity.status(HttpStatus.CREATED).body(it.toTransferUser()) }

    override fun deleteUser(userId: String, exchange: ServerWebExchange?): Mono<ResponseEntity<Void>> =
        userService.delete(userId)
            .map { ResponseEntity.noContent().build<Void>() }
            .switchIfEmpty(Mono.error(NoSuchElementException("User with id: '$userId' does not exist.")))

}