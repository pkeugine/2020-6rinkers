package com.cocktailpick.back.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cocktailpick.back.cocktail.domain.Cocktail;
import com.cocktailpick.back.cocktail.domain.CocktailRepository;
import com.cocktailpick.back.user.domain.User;
import com.cocktailpick.back.user.domain.UserRepository;
import com.cocktailpick.back.users.favorite.domain.Favorite;
import com.cocktailpick.back.users.favorite.domain.FavoriteRepository;
import com.cocktailpick.back.users.favorite.dto.FavoriteRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Service
public class UserService {
	private final UserRepository userRepository;
	private final FavoriteRepository favoriteRepository;
	private final CocktailRepository cocktailRepository;

	@Transactional
	public Favorite addFavorite(User user, FavoriteRequest favoriteRequest) {
		Favorite favorite = new Favorite();
		Optional<Cocktail> cocktail = cocktailRepository.findById(favoriteRequest.getCocktailId());
		favorite.setCocktail(cocktail.orElseThrow(IllegalArgumentException::new));
		favorite.setUser(user);
		return favoriteRepository.save(favorite);
	}

	public void deleteFavorite(User user, Long cocktailId) {
		user.getFavorites().deleteFavorite(cocktailId);
	}
}
