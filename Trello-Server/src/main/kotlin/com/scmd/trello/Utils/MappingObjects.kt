package com.scmd.trello.Utils

import com.scmd.trello.models.User
import com.scmd.userservice.model.pub.TransferUserDto
import com.scmd.userservice.model.pub.UserDto

fun User.toDto(): UserDto =
    UserDto()
        .id(this.id)
        .name(this.name)
        .email(this.email)
        .password(this.password)


fun User.toTransferUser(): TransferUserDto =
    TransferUserDto()
        .name(this.name)
        .email(this.email)
        .password(this.password)

fun TransferUserDto.toUser(): User =
    User(
        id = generateId(),
        name = this.name,
        email = this.email,
        password = this.password
    )

fun TransferUserDto.toDto(): TransferUserDto =
    TransferUserDto()
        .name(this.name)
        .email(this.email)
        .password(this.password)

