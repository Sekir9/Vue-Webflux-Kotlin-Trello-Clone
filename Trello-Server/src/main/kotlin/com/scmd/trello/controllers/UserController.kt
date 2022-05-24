package com.scmd.trello.controllers

import com.scmd.trello.Service.UserService
import com.scmd.trello.models.User
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@RestController
@RequestMapping(path = ["/users"])
class UserController(private val carService: UserService) {

    @GetMapping("/")
    fun getUsers(): ResponseEntity<Flux<String>> {
        return ResponseEntity.ok(Flux.just("xd"))
    }

//    @GetMapping("/{id}")
//    fun getUserByID(@PathVariable id: String): ResponseEntity<Mono<User>> {
//        return ResponseEntity.ok(userRepository.findById(id).switchIfEmpty(Mono.error(NoSuchElementException("User with id: '$id' does not exist."))))
//    }
//
//    @PostMapping("/addUser")
//    fun addUser(@RequestBody user: User): ResponseEntity<Mono<User>> {
//
//        return ResponseEntity.ok(userRepository.save(user));
//    }


//
//
//    @PutMapping("/{id}")
//    fun updateUser(@PathVariable id: String, @RequestBody user: User): ResponseEntity<User> {
//        var oldUser = this.userRepository.findById(id).orElse(null);
//        oldUser.name = user.name
//        oldUser.email = user.email
//        oldUser.password = user.password
//        return ResponseEntity.ok(this.userRepository.findById(id).orElse(null));
//    }
//
//    @DeleteMapping("/{id}")
//    fun deleteUser(@PathVariable id: String): ResponseEntity<String> {
//        this.userRepository.deleteById(id);
//        return ResponseEntity.ok(id);
//    }
}