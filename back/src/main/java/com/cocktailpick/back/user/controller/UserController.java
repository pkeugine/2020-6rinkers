package com.cocktailpick.back.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cocktailpick.back.common.exceptions.ResourceNotFoundException;
import com.cocktailpick.back.security.CurrentUser;
import com.cocktailpick.back.security.UserPrincipal;
import com.cocktailpick.back.user.domain.User;
import com.cocktailpick.back.user.domain.UserRepository;
import com.cocktailpick.back.user.service.UserService;
import com.cocktailpick.back.users.favorite.dto.FavoriteRequest;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@GetMapping("/user/me")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<User> getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
		return ResponseEntity.ok(userRepository.findById(userPrincipal.getId())
			.orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId())));
	}

	@PostMapping("/user/me/favorites")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Void> addFavorite(@CurrentUser UserPrincipal userPrincipal, @Valid FavoriteRequest favoriteRequest) {
		User user = userRepository.findById(userPrincipal.getId())
			.orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));

		userService.addFavorite(user, favoriteRequest);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/user/me/favorites/{cocktailId}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Void> addFavorite(@CurrentUser UserPrincipal userPrincipal, @PathVariable Long cocktailId) {
		User user = userRepository.findById(userPrincipal.getId())
			.orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));

		userService.deleteFavorite(user, cocktailId);
		return ResponseEntity.noContent().build();
	}
}
